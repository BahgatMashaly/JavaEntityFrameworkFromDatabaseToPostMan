package main.velocity;

import java.util.List;

public class ObjectManyToOne {
    private boolean optional;


    private List<Column> joinColumns;//referencedColumnNameNameInParent

    private String refreanceTableJavaNameFirstCharSmall;
    private String refreanceTableJavaName;




    private boolean isJoinColumnsEmbededInEmbeddableClass;

    //    @JsonInclude(JsonInclude.Include.NON_NULL)
//    @ManyToOne(optional = false   ) //default it will be EAGER
//    @JoinColumn(name = "course_id" ,updatable = false,insertable = false)////column name in database in current table
//    private Course course;

    public ObjectManyToOne(boolean  optional,List<Column> joinColumns,String refreanceTableJavaNameFirstCharSmall,String refreanceTableJavaName,boolean isJoinColumnsEmbededInEmbeddableClassx)
    {
        this.optional=optional;
        this.joinColumns=joinColumns;
        this.refreanceTableJavaNameFirstCharSmall=refreanceTableJavaNameFirstCharSmall;
        this.refreanceTableJavaName=refreanceTableJavaName;
        this.isJoinColumnsEmbededInEmbeddableClass=isJoinColumnsEmbededInEmbeddableClassx;
    }


    public boolean isOptional() {
        return optional;
    }

    public void setOptional(boolean optional) {
        this.optional = optional;
    }


    public String getRefreanceTableJavaNameFirstCharSmall() {
        return refreanceTableJavaNameFirstCharSmall;
    }

    public void setRefreanceTableJavaNameFirstCharSmall(String refreanceTableJavaNameFirstCharSmall) {
        this.refreanceTableJavaNameFirstCharSmall = refreanceTableJavaNameFirstCharSmall;
    }

    public String getRefreanceTableJavaName() {
        return refreanceTableJavaName;
    }

    public void setRefreanceTableJavaName(String refreanceTableJavaName) {
        this.refreanceTableJavaName = refreanceTableJavaName;
    }

    public List<Column> getJoinColumns() {
        return joinColumns;
    }

    public void setJoinColumns(List<Column> joinColumns) {
        this.joinColumns = joinColumns;
    }

    public boolean getIsJoinColumnsEmbededInEmbeddableClass() {
        return isJoinColumnsEmbededInEmbeddableClass;
    }

    public void setIsJoinColumnsEmbededInEmbeddableClass(boolean joinColumnsEmbededInEmbeddableClass) {
        isJoinColumnsEmbededInEmbeddableClass = joinColumnsEmbededInEmbeddableClass;
    }

//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    @Column(name = "center_id")
//    private Long centerId;
//

//    @JsonInclude(JsonInclude.Include.NON_NULL)
//// @ManyToOne(optional = false  )   //default is it will be EAGER and EAGER  doesn't   mean that it will be return to Get service , but will execute join select without ny reason
////    @JoinColumn(name = "center_id",updatable = false,insertable = false)//column name in database in current table, need if colum name is not flow the name convention
//    @JoinColumns({
//            @JoinColumn(name="DEPT_NAME", referencedColumnName="Name",updatable = false,insertable = false), // name=column name in database in current table ,    referencedColumnName == sql name of the parent column
//            @JoinColumn(name="DEPT_ID", referencedColumnName="ID",updatable = false,insertable = false)
//    })
//    private Center center;


}
