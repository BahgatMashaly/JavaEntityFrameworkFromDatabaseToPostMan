package main.velocity;

public class ObjectOneToOneParent {



    private String refreanceTableJavaNameFirstCharSmall;
    private String refreanceTableJavaName;






    public ObjectOneToOneParent(String refreanceTableJavaNameFirstCharSmall, String refreanceTableJavaName )
    {
        this.refreanceTableJavaNameFirstCharSmall=refreanceTableJavaNameFirstCharSmall;
        this.refreanceTableJavaName=refreanceTableJavaName;

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




//    @Id
//    @GeneratedValue
//    private Long id;
//
//    private String title;
//
//    @OneToOne(mappedBy = "post", cascade = CascadeType.ALL,
//            fetch = FetchType.LAZY, optional = false)
//    private PostDetails details;
//
//    //Getters and setters omitted for brevity
//
//    public void setDetails(PostDetails details) {
//        if (details == null) {
//            if (this.details != null) {
//                this.details.setPost(null);
//            }
//        }
//        else {
//            details.setPost(this);
//        }
//        this.details = details;
//    }
}
