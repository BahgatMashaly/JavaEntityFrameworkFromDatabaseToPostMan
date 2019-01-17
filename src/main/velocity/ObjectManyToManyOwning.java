package main.velocity;

import java.util.List;

public class ObjectManyToManyOwning {



    Table directJoinTableWhichIsManyToManyTable;


    List<Column> joinColumns;
    List<Column> inverseJoinColumns;
    Table indDirectDirectJoinTable;


    public ObjectManyToManyOwning(
            Table directJoinTableWhichIsManyToManyTable,
            List<Column> joinColumns,
            List<Column> inverseJoinColumns,
            Table indDirectDirectJoinTable )
    {
        this.directJoinTableWhichIsManyToManyTable=directJoinTableWhichIsManyToManyTable;
        this.joinColumns=joinColumns;
        this.inverseJoinColumns=inverseJoinColumns;
        this.indDirectDirectJoinTable=indDirectDirectJoinTable;

    }



    public List<Column> getJoinColumns() {
        return joinColumns;
    }

    public void setJoinColumns(List<Column> joinColumns) {
        this.joinColumns = joinColumns;
    }



    public List<Column> getInverseJoinColumns() {
        return inverseJoinColumns;
    }

    public void setInverseJoinColumns(List<Column> inverseJoinColumns) {
        this.inverseJoinColumns = inverseJoinColumns;
    }

    public Table getIndDirectDirectJoinTable() {
        return indDirectDirectJoinTable;
    }

    public void setIndDirectDirectJoinTable(Table indDirectDirectJoinTable) {
        this.indDirectDirectJoinTable = indDirectDirectJoinTable;
    }


    public Table getDirectJoinTableWhichIsManyToManyTable() {
        return directJoinTableWhichIsManyToManyTable;
    }

    public void setDirectJoinTableWhichIsManyToManyTable(Table directJoinTableWhichIsManyToManyTable) {
        this.directJoinTableWhichIsManyToManyTable = directJoinTableWhichIsManyToManyTable;
    }




//class post
//    @ManyToMany(cascade = {
//            CascadeType.PERSIST,
//            CascadeType.MERGE
//    })
//    @JoinTable(name = "post_tag",
//            joinColumns = @JoinColumn(name = "post_id"),
//            inverseJoinColumns = @JoinColumn(name = "tag_id")
//    )
//    private Set<Tag> tags = new HashSet<>();

//    public void addTag(Tag tag) {
//        tags.add(tag);
//        tag.getPosts().add(this);
//    }
//
//    public void removeTag(Tag tag) {
//        tags.remove(tag);
//        tag.getPosts().remove(this);
//    }

}
