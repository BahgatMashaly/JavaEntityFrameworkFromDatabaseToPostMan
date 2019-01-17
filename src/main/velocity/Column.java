package main.velocity;



import main.velocity.util.JavaSQLDataTypeMap;

import java.util.LinkedHashSet;


public class Column {


    private String columnSQlName;

    private String columnJavaName;
    private String columnJavaNameFirstCharSmall;

    private boolean isNullable;
    private String tableName;

    private String comment;

    //@Id
    private boolean isPrimaryKey;

    //@Id
    private boolean isUnique;

    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private boolean isAutoIncrement;

//    @Size(max = 50)
    private int maxlength;


//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    @javax.persistence.Column(name = "center_id")
    private boolean isForeignKey;




    private boolean isEmbededInEmbeddableClass =false;




    private boolean hideCosItsManyToOneColumn =false;


   // @JsonInclude(JsonInclude.Include.NON_NULL)
    //@ManyToOne(optional = false isNullable==true ) /  //default is it will be EAGER and EAGER  doesn't   mean that it will be return to Get service , but will execute join select without ny reason
    //@JoinColumn(name = "center_id",updatable = false,insertable = false)//column name in database in current table, need if colum name is not flow the name convention
    private String referenceTableName;
    private String referenceColumnName;



    private LinkedHashSet<String> conditionalAnnotationsList = new LinkedHashSet<String>();


    private JavaSQLDataTypeMap javaSQLDataTypeMap;



    public String getColumnSQlName() {
        return columnSQlName;
    }

    public void setColumnSQlName(String columnSQlName) {
        this.columnSQlName = columnSQlName;
    }

    public String getColumnJavaNameFirstCharSmall() {
        return columnJavaNameFirstCharSmall;
    }

    public void setColumnJavaNameFirstCharSmall(String columnJavaNameFirstCharSmall) {
        this.columnJavaNameFirstCharSmall = columnJavaNameFirstCharSmall;
    }

    public boolean isNullable() {
        return isNullable;
    }

    public void setNullable(boolean nullable) {
        isNullable = nullable;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isPrimaryKey() {
        return isPrimaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        isPrimaryKey = primaryKey;
    }

    public boolean isUnique() {
        return isUnique;
    }

    public void setUnique(boolean unique) {
        isUnique = unique;
    }

    public boolean isAutoIncrement() {
        return isAutoIncrement;
    }

    public void setAutoIncrement(boolean autoIncrement) {
        isAutoIncrement = autoIncrement;
    }

    public int getMaxlength() {
        return maxlength;
    }

    public void setMaxlength(int maxlength) {
        this.maxlength = maxlength;
    }

    public boolean isForeignKey() {
        return isForeignKey;
    }

    public void setForeignKey(boolean foreignKey) {
        isForeignKey = foreignKey;
    }

    public String getReferenceTableName() {
        return referenceTableName;
    }

    public void setReferenceTableName(String referenceTableName) {
        this.referenceTableName = referenceTableName;
    }

    public String getReferenceColumnName() {
        return referenceColumnName;
    }

    public void setReferenceColumnName(String referenceColumnName) {
        this.referenceColumnName = referenceColumnName;
    }



    public JavaSQLDataTypeMap getJavaSQLDataTypeMap() {
        return javaSQLDataTypeMap;
    }

    public void setJavaSQLDataTypeMap(JavaSQLDataTypeMap javaSQLDataTypeMap) {
        this.javaSQLDataTypeMap = javaSQLDataTypeMap;
    }


    public LinkedHashSet<String> getConditionalAnnotationsList() {
        return conditionalAnnotationsList;
    }

    public void setConditionalAnnotationsList(LinkedHashSet<String> conditionalAnnotationsList) {
        this.conditionalAnnotationsList = conditionalAnnotationsList;
    }


    public String getColumnJavaName() {
        return columnJavaName;
    }

    public void setColumnJavaName(String columnJavaName) {
        this.columnJavaName = columnJavaName;
    }

    public boolean isEmbededInEmbeddableClass() {
        return isEmbededInEmbeddableClass;
    }

    public void setEmbededInEmbeddableClass(boolean embededInEmbeddableClass) {
        isEmbededInEmbeddableClass = embededInEmbeddableClass;
    }

    public boolean isHideCosItsManyToOneColumn() {
        return hideCosItsManyToOneColumn;
    }

    public void setHideCosItsManyToOneColumn(boolean hideCosItsManyToOneColumn) {
        this.hideCosItsManyToOneColumn = hideCosItsManyToOneColumn;
    }
}
