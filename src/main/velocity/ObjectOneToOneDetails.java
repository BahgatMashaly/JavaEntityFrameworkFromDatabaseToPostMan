package main.velocity;

public class ObjectOneToOneDetails {
    private String refreanceTableJavaNameFirstCharSmall;
    private String refreanceTableJavaName;

    public ObjectOneToOneDetails(String refreanceTableJavaNameFirstCharSmall, String refreanceTableJavaName)
    {
        this.refreanceTableJavaNameFirstCharSmall=refreanceTableJavaNameFirstCharSmall;
        this.refreanceTableJavaName=refreanceTableJavaName;
    }


    //    @OneToOne(fetch = FetchType.LAZY)
//    @MapsId
//    private Post post;




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


}
