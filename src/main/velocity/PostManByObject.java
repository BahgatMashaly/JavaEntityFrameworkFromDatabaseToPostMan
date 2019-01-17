package main.velocity;

import java.util.Arrays;
import java.util.List;

public class PostManByObject {

  private   String[] numbersTypes = { "Integer","Long", "BigDecimal","Float","Short"};

    List<Table> tables;

    public List<Table> createPostManFile(List<Table> tables)
    {

        this.tables=tables;
        tables.forEach(table->{

            createTableBody(table);
        });
        return tables;
    }


  Table  createTableBody(Table table)
    {
        table.setPostManCreateBody("\"{ " + this.createPostManCreateBody(table) + "\\n }\"");
        table.setPostManUpdateBody(this.createPostManUpdateBody(table));
        table.setPostManPatchUpdateBody(this.createPostManUpdateBody(table));
        return table;
    }


   private    String createPostManCreateBody(Table table)
    {
        final String[] postManCreateBody = new String[1];
        postManCreateBody[0] =   " ";

        if(table.getEmbeddedId()!=null)
        {                 //  { \n \"userName":\"String" ,
            postManCreateBody[0] = postManCreateBody[0]+getEmbeddedIdBody(table);
        }
        table.getColumnsList().forEach(column->{
            if(column.isEmbededInEmbeddableClass() ==false && column.isPrimaryKey() ==false) {
                postManCreateBody[0] = postManCreateBody[0] + " \\n \\\""+  column.getColumnJavaNameFirstCharSmall() ;
                //
                if(Arrays.asList(numbersTypes).contains(column.getJavaSQLDataTypeMap().getJavaType()))
                {
                    postManCreateBody[0] = postManCreateBody[0]   +"\\\": 0 ,";

                }
                else
                {
                    postManCreateBody[0] = postManCreateBody[0]   +"\\\":\\\""+ column.getJavaSQLDataTypeMap().getJavaType()+"\\\" ,";
                }            }
        });

         if(table.isHideManyToOneColumn() )
        {
            table.getObjectManyToOneList().forEach(item->{
                postManCreateBody[0]= postManCreateBody[0]+ " \\n \\\""+  item.getRefreanceTableJavaNameFirstCharSmall() +  "\\\":\\n {" ;
              Table refTable=this.tables.stream().filter(t->t.getJavaName().equals(item.getRefreanceTableJavaName())).findFirst().get();
                postManCreateBody[0]= postManCreateBody[0]+ createPostManCreateBody(refTable);
                postManCreateBody[0]= postManCreateBody[0]+ "\\n },";

            });
        }

//remove last ,
        if ( postManCreateBody[0].charAt(postManCreateBody[0].length() - 1) == ',') {
            postManCreateBody[0] = postManCreateBody[0].substring(0, postManCreateBody[0].length() - 1);
        }
//        postManCreateBody[0] = postManCreateBody[0] + "\\n }\"";
        return  postManCreateBody[0];
    }






    private   String createPostManUpdateBody(Table table)
    {
        final String[] PostManPatchUpdateBody = new String[1];


        PostManPatchUpdateBody[0] =   "\"{";
        if(table.getEmbeddedId()!=null)
        {                 //  { \n \"userName":\"String" ,
            PostManPatchUpdateBody[0] = PostManPatchUpdateBody[0]+getEmbeddedIdBody(table);
        }



        table.getColumnsList().forEach(column->{
            if(column.isEmbededInEmbeddableClass() ==false ) {
                PostManPatchUpdateBody[0] = PostManPatchUpdateBody[0] + " \\n \\\""+  column.getColumnJavaNameFirstCharSmall() ;
                //
                if(Arrays.asList(numbersTypes).contains(column.getJavaSQLDataTypeMap().getJavaType()))
                {
                    PostManPatchUpdateBody[0] = PostManPatchUpdateBody[0]   +"\\\": 0 ,";

                }
                else
                {
                    PostManPatchUpdateBody[0] = PostManPatchUpdateBody[0]   +"\\\":\\\""+ column.getJavaSQLDataTypeMap().getJavaType()+"\\\" ,";
                }              }
        });
//remove last ,
        if ( PostManPatchUpdateBody[0].charAt(PostManPatchUpdateBody[0].length() - 1) == ',') {
            PostManPatchUpdateBody[0] = PostManPatchUpdateBody[0].substring(0, PostManPatchUpdateBody[0].length() - 1);
        }
        PostManPatchUpdateBody[0] = PostManPatchUpdateBody[0] + "\\n }\"";
        return  PostManPatchUpdateBody[0];
    }








    private String getEmbeddedIdBody(Table table)
    {
        final String[] embeddedIdBody = new String[1];
        embeddedIdBody[0]="";

        if(table.isHideManyToOneColumn())
        {
            embeddedIdBody[0]=   " \\n \\\""+  table.getEmbeddedId().getJavaNameFirstCharSmall() +  "\\\":\\n {" ;

        }
            table.getEmbeddedId().getColumnsList().forEach(column->{
                embeddedIdBody[0] = embeddedIdBody[0] + " \\n \\\""+  column.getColumnJavaNameFirstCharSmall() ;
                if(Arrays.asList(numbersTypes).contains(column.getJavaSQLDataTypeMap().getJavaType()))
                {
                    embeddedIdBody[0] = embeddedIdBody[0]   +"\\\": 0 ,";
                }
                else
                {
                    embeddedIdBody[0] = embeddedIdBody[0]   +"\\\":\\\""+ column.getJavaSQLDataTypeMap().getJavaType()+"\\\" ,";
                }
            });


        if(table.isHideManyToOneColumn())
        {
            if ( embeddedIdBody[0].charAt(embeddedIdBody[0].length() - 1) == ',') {
                embeddedIdBody[0] = embeddedIdBody[0].substring(0, embeddedIdBody[0].length() - 1);
            }
            embeddedIdBody[0]=   embeddedIdBody[0] + "\\n },";

        }

        return embeddedIdBody[0];
    }

}
//"{\n\"name\": \"String\",\n\"email\": \"String\",\n\"address\": \"String\"\n}"