package main.velocity;

import main.velocity.util.NameConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class processes {
    
    
Table CurrentTable;
   

    public void addRelationsObjectsAndGenerateColumnsAnnotations(Table table ,ArrayList<Table> tables, boolean hideManyToOneColumn) {
        this.CurrentTable=table;
        try {

            this.CurrentTable.setHideManyToOneColumn(hideManyToOneColumn);

            this.generateEmbeddedID();

            Map<String, List<Column>> ForeignKeyTables = this.CurrentTable.getColumnsList().stream().filter(column -> column.isForeignKey()).collect(Collectors.groupingBy(Column::getReferenceTableName));


            if (this.CurrentTable.getColumnsList().size() == this.CurrentTable.getColumnsList().stream().filter(col -> col.isPrimaryKey()).count() && ForeignKeyTables.size() == 2) //
            {

                this.addManyToManyToRelation(tables);
                return;
            }

            for (String key : ForeignKeyTables.keySet()) {

                List<Column> joinColumns = ForeignKeyTables.get(key);

                Column column = ForeignKeyTables.get(key).get(0);
                Table refTable = tables.stream().filter(itemTable -> itemTable.getSqlName().equals(column.getReferenceTableName())).findFirst().get();


                if (column.isPrimaryKey() || column.isUnique())//  is curent column is uniq or primary then it's oneToOne
                {
                    int primaryKeyCount = this.CurrentTable.getColumnsList().stream().filter(item -> item.isPrimaryKey()).collect(Collectors.toList()).size();
                    int uniqueCount = this.CurrentTable.getColumnsList().stream().filter(item -> item.isUnique()).collect(Collectors.toList()).size();

                    if (primaryKeyCount <= 1 && uniqueCount <= 1) {
                        addOneToOne(refTable);
                    } else {
                        addOneToManyManyToOne(refTable, joinColumns);
                    }
                } else {
                    addOneToManyManyToOne(refTable, joinColumns);
                }


                //  embeddableClasses.containsKey()

            }
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }


    }

    List<Column>  getPrimaryKeysColumns()
    {
        return this.CurrentTable.getColumnsList().stream().filter(item -> item.isPrimaryKey()).collect(Collectors.toList());
    }

    public void generateEmbeddedID() {

        List<Column> primaryKeysColumns = getPrimaryKeysColumns();
//check extra column
        if (primaryKeysColumns.size() < this.CurrentTable.getColumnsList().size() && primaryKeysColumns.size() > 1) {
            EmbeddableClass embeddableClass = new EmbeddableClass();
            embeddableClass.setJavaName("Embeddable");
            for (Column column : primaryKeysColumns) {
                column.setEmbededInEmbeddableClass(true);
                column.getConditionalAnnotationsList().add("@Column(name = \"" + column.getColumnSQlName() + "\")");
                if (column.getJavaSQLDataTypeMap().isCheckLenght()) {
                    embeddableClass.getImports().add("javax.validation.constraints.Size");
                    column.getConditionalAnnotationsList().add("@Size(max = " + column.getMaxlength() + ")");
                }
                if (column.getJavaSQLDataTypeMap().getTypeNeedImportThisLine() != null) {
                    embeddableClass.getImports().add(column.getJavaSQLDataTypeMap().getTypeNeedImportThisLine());
                }
                embeddableClass.setJavaName(embeddableClass.getJavaName() + column.getColumnJavaName());
            }
            embeddableClass.setJavaNameFirstCharSmall(NameConverter.toLOWER_CAMEL(embeddableClass.getJavaName()));
            embeddableClass.setColumnsList(primaryKeysColumns);
            this.CurrentTable.setEmbeddedId(embeddableClass);
            this.CurrentTable.getImports().add("com.fasterxml.jackson.annotation.JsonProperty");
        }
    }

    private void addManyToManyToRelation(ArrayList<Table> tables) {
        this.CurrentTable.setManyToManyTable(true);

        Map<String, List<Column>> ForeignKeyTables = this.CurrentTable.getColumnsList().stream().filter(column -> column.isForeignKey()).collect(Collectors.groupingBy(Column::getReferenceTableName));
        Object[] keys = ForeignKeyTables.keySet().toArray();

        Table tableOwn = tables.stream().filter(itemTable -> itemTable.getSqlName().equals(keys[0])).findFirst().get();
        List<Column> JoinForeignColumns = ForeignKeyTables.get(keys[0]);

        Table tableNonOwn = tables.stream().filter(itemTable -> itemTable.getSqlName().equals(keys[1])).findFirst().get();
        List<Column> inverseJoinColumns = ForeignKeyTables.get(keys[1]);

        ObjectManyToManyOwning objectManyToManyOwning = new ObjectManyToManyOwning(this.CurrentTable, JoinForeignColumns, inverseJoinColumns, tableNonOwn);
        tableOwn.getObjectManyToManyOwningList().add(objectManyToManyOwning);
        tableOwn.getImports().add("java.util.HashSet");
        tableOwn.getImports().add("java.util.Set");

        ObjectManyToManyNonOwning objectManyToManyNonOwning = new ObjectManyToManyNonOwning(tableOwn);

        tableNonOwn.getObjectManyToManyNonOwningList().add(objectManyToManyNonOwning);
        tableNonOwn.getImports().add("java.util.HashSet");
        tableNonOwn.getImports().add("java.util.Set");
    }


    private void addOneToManyManyToOne(Table refTable, List<Column> joinColumns) {

        final boolean[] isJoinColumnsEmbededInEmbeddableClass = new boolean[1];
        isJoinColumnsEmbededInEmbeddableClass[0]=false;

        joinColumns.stream().forEach(col->{
            col.getConditionalAnnotationsList().add("@Column(name = \""+col.getColumnSQlName()+"\")");
            col.getConditionalAnnotationsList().add("@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)");
            refTable.getImports().add("com.fasterxml.jackson.annotation.JsonProperty");
            if(this.CurrentTable.isHideManyToOneColumn())
            {
                col.setHideCosItsManyToOneColumn(this.CurrentTable.isHideManyToOneColumn());
            }
            if(col.isEmbededInEmbeddableClass() )
            {
                isJoinColumnsEmbededInEmbeddableClass[0] =true;
            }
        });


        this.CurrentTable.getObjectManyToOneList().add(new ObjectManyToOne(joinColumns.get(0).isNullable(), joinColumns, refTable.getJavaNameFirstCharSmall(), refTable.getJavaName(),isJoinColumnsEmbededInEmbeddableClass[0]));
        this.CurrentTable.getImports().add("com.fasterxml.jackson.annotation.JsonInclude");
        refTable.getObjectOneToManyList().add(new ObjectOneToMany(refTable.getJavaNameFirstCharSmall(), this.CurrentTable.getJavaNameFirstCharSmall(), this.CurrentTable.getJavaName(), refTable.getJavaName()));
        refTable.getImports().add("com.fasterxml.jackson.annotation.JsonIgnore");
        refTable.getImports().add("java.util.Set");






    }

    private void addOneToOne(Table refTable) {
        this.CurrentTable.getObjectOneToOneDetailsList().add(new ObjectOneToOneDetails(refTable.getJavaNameFirstCharSmall(), refTable.getJavaName()));
        refTable.getObjectOneToOneParentList().add(new ObjectOneToOneParent(this.CurrentTable.getJavaNameFirstCharSmall(), this.CurrentTable.getJavaName()));
    }

    private void generateColumnAnnotationAndImports(Column column) {

        //check is not part of ebeddedd column
        List<Column> primaryKeysColumns = this.CurrentTable.getColumnsList().stream().filter(item -> item.isPrimaryKey()).collect(Collectors.toList());
        if (column.isPrimaryKey() && primaryKeysColumns.size() < this.CurrentTable.getColumnsList().size() && primaryKeysColumns.size() > 1) {
            return;
        }
        try {
            if (column.getJavaSQLDataTypeMap().getTypeNeedImportThisLine() != null) {
                this.CurrentTable.getImports().add(column.getJavaSQLDataTypeMap().getTypeNeedImportThisLine());
            }
            if (column.isPrimaryKey()) {
                column.getConditionalAnnotationsList().add("@Id");
            }
            if (column.isAutoIncrement()) {
                column.getConditionalAnnotationsList().add("@GeneratedValue(strategy = GenerationType.IDENTITY)");
            }
            if (column.getJavaSQLDataTypeMap().isCheckLenght()) {
                this.CurrentTable.getImports().add("javax.validation.constraints.Size");
                column.getConditionalAnnotationsList().add("@Size(max = " + column.getMaxlength() + ")");
            }
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }

    }

    public void generateColumnsAnnotationAndImports() {
        this.CurrentTable.getColumnsList().stream().forEach(column1 -> generateColumnAnnotationAndImports(column1));
    }

}
