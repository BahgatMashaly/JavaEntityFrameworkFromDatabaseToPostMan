package main.velocity;

public class ObjectManyToManyNonOwning {

    Table ownTable;

    public ObjectManyToManyNonOwning(Table ownTable)
    {
          this.ownTable =ownTable;
    }


    public Table getOwnTable() {
        return ownTable;
    }

    public void setOwnTable(Table ownTable) {
        this.ownTable = ownTable;
    }


//    @ManyToMany(mappedBy = "tags")
//    private Set<Post> posts = new HashSet<>();
}
