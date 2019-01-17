package main.velocity;

public class ObjectOneToMany {

    private String mappedByTheProbertyItsMyNameStartWithSmall;
    private String refreanceTableJavaNameFirstCharSmall;
    private String refreanceTableJavaName;

    private String thisJavaNameForSetItSelfInMany;
    ObjectOneToMany(String mappedByTheProbertyItsMyNameStartWithSmall,String refreanceTableJavaNameFirstCharSmall,String refreanceTableJavaName,String thisJavaNameForSetItSelfInMany)
    {
        this.mappedByTheProbertyItsMyNameStartWithSmall =mappedByTheProbertyItsMyNameStartWithSmall;
        this.refreanceTableJavaNameFirstCharSmall=refreanceTableJavaNameFirstCharSmall;
        this.refreanceTableJavaName=refreanceTableJavaName;
        this.thisJavaNameForSetItSelfInMany=thisJavaNameForSetItSelfInMany;
    }

    //    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//    @JsonIgnore//don't forget this not for all cases you will change it in case like person address's
//    @OneToMany(mappedBy = "center" ) //property name in CenterCourse Entity // it will be LAZY , EAGER  doesn't   mean that it will be return to Get service , but will execute join select without ny reason
//    private Set<CenterCourse> centerCourses;
//
//    //بدلا من
//    // this.getCenterCourses().Add(mycource)// it mean it will get all courses to add one course
//    public void addCenterCourse(CenterCourse centerCourse) {
//        this.centerCourses.add(centerCourse);
//        centerCourse.setCenter(this);//need to know why this line
//
//    }
//
//    public void removeCenterCourse(CenterCourse centerCourse) {
//        this.centerCourses.remove(centerCourse);
//        centerCourse.setCenter(null);//need to know why this line
//    }






    public String getMappedByTheProbertyItsMyNameStartWithSmall() {
        return mappedByTheProbertyItsMyNameStartWithSmall;
    }

    public void setMappedByTheProbertyItsMyNameStartWithSmall(String mappedByTheProbertyItsMyNameStartWithSmall) {
        this.mappedByTheProbertyItsMyNameStartWithSmall = mappedByTheProbertyItsMyNameStartWithSmall;
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

    public String getThisJavaNameForSetItSelfInMany() {
        return thisJavaNameForSetItSelfInMany;
    }

    public void setThisJavaNameForSetItSelfInMany(String thisJavaNameForSetItSelfInMany) {
        this.thisJavaNameForSetItSelfInMany = thisJavaNameForSetItSelfInMany;
    }


}
