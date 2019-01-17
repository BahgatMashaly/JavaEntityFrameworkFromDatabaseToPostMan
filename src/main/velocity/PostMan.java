package main.velocity;

import java.util.Arrays;
import java.util.List;

public class PostMan {

    String[] numbersTypes = { "Integer","Long", "BigDecimal","Float","Short"};
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
        table.setPostManCreateBody( this.createPostManCreateBody(table)  );
        table.setPostManUpdateBody(this.createPostManUpdateBody(table));
        table.setPostManPatchUpdateBody(this.createPostManUpdateBody(table));
        return table;
    }

    public   String createPostManCreateBody(Table table)
    {
        final String[] postManCreateBody = new String[1];

        //create postManCreateBody
        postManCreateBody[0] =   "\"{";
        if(table.getEmbeddedId()!=null)
        {                 //  { \n \"userName":\"String" ,
            postManCreateBody[0]=postManCreateBody[0]+  getEmbeddedIdBody(table);
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
//remove last ,
        if ( postManCreateBody[0].charAt(postManCreateBody[0].length() - 1) == ',') {
            postManCreateBody[0] = postManCreateBody[0].substring(0, postManCreateBody[0].length() - 1);
        }
        postManCreateBody[0] = postManCreateBody[0] + "\\n }\"";
        return  postManCreateBody[0];
    }


    public   String createPostManUpdateBody(Table table)
    {
        final String[] PostManPatchUpdateBody = new String[1];


        PostManPatchUpdateBody[0] =   "\"{";
        if(table.getEmbeddedId()!=null)
        {
            PostManPatchUpdateBody[0]=PostManPatchUpdateBody[0]+  getEmbeddedIdBody(table);

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



String getEmbeddedIdBody(Table table)
{
    final String[] embeddedIdString = new String[1];

    table.getEmbeddedId().getColumnsList().forEach(column->{
        embeddedIdString[0] = embeddedIdString[0] + " \\n \\\""+  column.getColumnJavaNameFirstCharSmall() ;
        //
        if(Arrays.asList(numbersTypes).contains(column.getJavaSQLDataTypeMap().getJavaType()))
        {
            embeddedIdString[0] = embeddedIdString[0]   +"\\\": 0 ,";

        }
        else
        {
            embeddedIdString[0] = embeddedIdString[0]   +"\\\":\\\""+ column.getJavaSQLDataTypeMap().getJavaType()+"\\\" ,";
        }              });

    return embeddedIdString[0];
}


}
//"{\n\"name\": \"String\",\n\"email\": \"String\",\n\"address\": \"String\"\n}"