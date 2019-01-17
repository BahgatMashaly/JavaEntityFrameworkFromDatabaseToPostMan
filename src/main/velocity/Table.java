package main.velocity;


import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class Table {

    private String SqlName;
    private String javaName;
    private String javaNameFirstCharSmall;

//
    private boolean isManyToManyTable = false;

    private List<ObjectOneToMany> objectOneToManyList = new ArrayList<ObjectOneToMany>();
    private List<ObjectOneToOneParent> objectOneToOneParentList = new ArrayList<ObjectOneToOneParent>();
    private List<ObjectOneToOneDetails> objectOneToOneDetailsList = new ArrayList<ObjectOneToOneDetails>();
    private List<ObjectManyToOne> objectManyToOneList = new ArrayList<ObjectManyToOne>();


    private List<ObjectManyToManyOwning> objectManyToManyOwningList = new ArrayList<ObjectManyToManyOwning>();


    private List<ObjectManyToManyNonOwning> objectManyToManyNonOwningList = new ArrayList<ObjectManyToManyNonOwning>();


    private List<Column> columnsList = new ArrayList<Column>();


    private EmbeddableClass embeddedId;

    private LinkedHashSet<String> imports = new LinkedHashSet<String>();

    private String  postManPatchUpdateBody;
    private String  postManUpdateBody;
    private String postManCreateBody;


    private boolean    hideManyToOneColumn=false;





    public LinkedHashSet<String> getImports() {
        return imports;
    }

    public void setImports(LinkedHashSet<String> imports) {
        this.imports = imports;
    }

    public String getSqlName() {
        return SqlName;
    }

    public void setSqlName(String sqlName) {
        SqlName = sqlName;
    }

    public String getJavaName() {
        return javaName;
    }

    public void setJavaName(String javaName) {
        this.javaName = javaName;
    }

    public String getJavaNameFirstCharSmall() {
        return javaNameFirstCharSmall;
    }

    public void setJavaNameFirstCharSmall(String javaNameFirstCharSmall) {
        this.javaNameFirstCharSmall = javaNameFirstCharSmall;
    }

    public List<ObjectOneToMany> getObjectOneToManyList() {
        return objectOneToManyList;
    }

    public void setObjectOneToManyList(List<ObjectOneToMany> objectOneToManyList) {
        this.objectOneToManyList = objectOneToManyList;
    }

    public List<ObjectOneToOneParent> getObjectOneToOneParentList() {
        return objectOneToOneParentList;
    }

    public void setObjectOneToOneParentList(List<ObjectOneToOneParent> objectOneToOneParentList) {
        this.objectOneToOneParentList = objectOneToOneParentList;
    }

    public List<ObjectOneToOneDetails> getObjectOneToOneDetailsList() {
        return objectOneToOneDetailsList;
    }

    public void setObjectOneToOneDetailsList(List<ObjectOneToOneDetails> objectOneToOneDetailsList) {
        this.objectOneToOneDetailsList = objectOneToOneDetailsList;
    }

    public List<ObjectManyToOne> getObjectManyToOneList() {
        return objectManyToOneList;
    }

    public void setObjectManyToOneList(List<ObjectManyToOne> objectManyToOneList) {
        this.objectManyToOneList = objectManyToOneList;
    }

    public List<Column> getColumnsList() {
        return columnsList;
    }

    public void setColumnsList(List<Column> columnsList) {
        this.columnsList = columnsList;
    }


    public boolean isManyToManyTable() {
        return isManyToManyTable;
    }

    public void setManyToManyTable(boolean manyToManyTable) {
        isManyToManyTable = manyToManyTable;
    }

    public List<ObjectManyToManyOwning> getObjectManyToManyOwningList() {
        return objectManyToManyOwningList;
    }

    public void setObjectManyToManyOwningList(List<ObjectManyToManyOwning> objectManyToManyOwningList) {
        this.objectManyToManyOwningList = objectManyToManyOwningList;
    }

    public List<ObjectManyToManyNonOwning> getObjectManyToManyNonOwningList() {
        return objectManyToManyNonOwningList;
    }

    public void setObjectManyToManyNonOwningList(List<ObjectManyToManyNonOwning> objectManyToManyNonOwningList) {
        this.objectManyToManyNonOwningList = objectManyToManyNonOwningList;
    }

    public EmbeddableClass getEmbeddedId() {
        return embeddedId;
    }

    public void setEmbeddedId(EmbeddableClass embeddedId) {
        this.embeddedId = embeddedId;
    }


    public String getPostManPatchUpdateBody() {
        return postManPatchUpdateBody;
    }

    public void setPostManPatchUpdateBody(String postManPatchUpdateBody) {
        this.postManPatchUpdateBody = postManPatchUpdateBody;
    }

    public String getPostManUpdateBody() {
        return postManUpdateBody;
    }

    public void setPostManUpdateBody(String postManUpdateBody) {
        this.postManUpdateBody = postManUpdateBody;
    }

    public String getPostManCreateBody() {
        return postManCreateBody;
    }

    public void setPostManCreateBody(String postManCreateBody) {
        this.postManCreateBody = postManCreateBody;
    }



    public boolean isHideManyToOneColumn() {
        return hideManyToOneColumn;
    }

    public void setHideManyToOneColumn(boolean hideManyToOneColumn) {
        this.hideManyToOneColumn = hideManyToOneColumn;
    }

}


//    List<String> result = lines.stream()
//            .filter(line -> !"pramod".equals(line))
//            .collect(Collectors.toList());