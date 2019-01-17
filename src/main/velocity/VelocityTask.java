package main.velocity;



//import org.apache.commons.io.output.FileWriterWithEncoding;
import org.apache.commons.io.output.FileWriterWithEncoding;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.log.NullLogChute;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
 import main.velocity.util.NameConverter;
import main.velocity.util.SQL;


import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class VelocityTask {

    Connection con;
    Statement stmt;

    PreparedStatement preStatement,updatePreStmt;
    VelocityEngine ve;

//"{\n\"name\": \"String\",\n\"email\": \"String\",\n\"address\": \"String\"\n}"

    String applicationName="AppName";
    String  protocol ="http";
    String    host ="localhost";
    String port="8080";
    String controllerPrefixWithoutSlashs="api";
    String outputPath="/src/main/java/net/tedata/MVSC";
    String parentPackageName="net.tedata.MVSC";
    boolean protectRest=false;

    String modelFolderPackageName="model";
    String repositoryFolderPackageName="repository";
    String serviceFolderPackageName="service";
    String controllerFolderPackageName="controller";
    String baseRepositoryName="ExtendedQueryDslJpaRepository";
      boolean    hideManyToOneColumn=false;
    String databaseType;
    String connectionString;

    public void startVelocity(String applicationName,
                              String protocol,
                              String host,
                              String port,
                              String controllerPrefixWithoutSlashs,
                              String parentPackageName,
                             boolean protectRest,
                              String databaseType,
                              String connectionString,
                              boolean hideManyToOneColumn
                              ) throws SQLException, ClassNotFoundException {

        this.outputPath=this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        this.applicationName=applicationName;
        this.protocol=protocol;
        this.host=host;
        this.port=port;
        this.controllerPrefixWithoutSlashs=controllerPrefixWithoutSlashs;
        this.parentPackageName=parentPackageName;
        this.protectRest=protectRest;
        this.databaseType=databaseType;
        this.connectionString=connectionString;
        this.hideManyToOneColumn=hideManyToOneColumn;


        ve = new VelocityEngine();


        if(outputPath.endsWith("/"))
        {
            outputPath = outputPath.substring(0, outputPath.length() - 1);
        }
        this.outputPath=outputPath;
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class",ClasspathResourceLoader.class.getName());
        ve.setProperty("runtime.log.logsystem.class", NullLogChute.class.getName());

        ve.init();

     HashMap<String, Table> tables=   this.getAllTables();

        for (Map.Entry<String, Table> item : tables.entrySet()) {
            Table table = item.getValue();

            //check if  many to many table
          if(table.isManyToManyTable()==false )
          {
              if(table.getEmbeddedId()!=null)
              {
                  this.createEmmbadedClass(table.getEmbeddedId());
              }

              this.createEntity(table);

              this.createRepository(table);

              this.createService(table);
              this.createController(table);

          }


         }

        this.createPostManFile(new ArrayList<Table>(this.getAllTables().values()) );



    }










public void createPostManFile(List<Table> tables)
{
    VelocityContext context = new VelocityContext();
PostMan postMan =new PostMan();
postMan.createPostManFile(tables);


    context.put("applicationName",   applicationName);
    context.put("protocol",   "http");
    context.put("host",   "localhost");
    context.put("port",   port);
    context.put("tables",   tables);

    if(this.controllerPrefixWithoutSlashs.equals(""))
    {
        context.put("controllerPrefixWithSlashs","");
    }
    else
    {
        context.put("controllerPrefixWithSlashs","/"+this.controllerPrefixWithoutSlashs);
    }
    Template template = ve.getTemplate("velocity/postMan.json.vm", "utf-8");


    File directory = new File(this.outputPath+"/"+this.modelFolderPackageName+"/");
    if (! directory.exists()){
        directory.mkdir();
    }

    File outputFile = new File(this.outputPath+"/"+this.modelFolderPackageName+"/postMan.json");
    if(outputFile.exists())
    {
        System.out.print("\n" + "be consider that the file "+outputFile.getName() + "  already exist");
        return;
    }
    FileWriterWithEncoding writer = null;
    try {
        writer = new FileWriterWithEncoding(outputFile, "utf-8");
        template.merge(context, writer);
        writer.close();

    } catch (IOException e) {
        e.printStackTrace();
    }
    tables.stream().forEach(table->{



    });
}

    public void   createRepository(Table table)
    {
        this.copyRepositoriesBaseClasses();
        VelocityContext context = new VelocityContext();
        LinkedHashSet<String> repositoryImports = new LinkedHashSet<String>();

        context.put("package",   this.parentPackageName+"."+this.repositoryFolderPackageName);
        repositoryImports.add(this.parentPackageName+"."+this.modelFolderPackageName+"."+ table.getJavaName());
        repositoryImports.add(this.parentPackageName+"."+this.repositoryFolderPackageName+".baseRepository."+this.baseRepositoryName);

        context.put("table",table);




        if( table.getEmbeddedId()!=null)
        {
            context.put("PK_Type",table.getEmbeddedId().getJavaName());
            repositoryImports.add(this.parentPackageName+"."+ this.modelFolderPackageName+"."+table.getEmbeddedId().getJavaName());
        }
        else
        {
            Column col=  table.getColumnsList().stream().filter(column -> column.isPrimaryKey()==true).findFirst().orElse(null);
            if(col!=null)
            {
                context.put("PK_Type",col.getJavaSQLDataTypeMap().getJavaType());
                if(col.getJavaSQLDataTypeMap().getTypeNeedImportThisLine()!=null)
                {
                    repositoryImports.add(col.getJavaSQLDataTypeMap().getTypeNeedImportThisLine());
                }
            }

        }

        context.put("repositoryImports",repositoryImports);

        Template t = ve.getTemplate("velocity/EntityRepository.java.e.vm", "utf-8");
        File directory = new File(this.outputPath+"/"+this.repositoryFolderPackageName+"/");
        if (! directory.exists()){
            directory.mkdir();
        }

        File outputFile = new File(this.outputPath+"/"+this.repositoryFolderPackageName+"/"+table.getJavaName()+"Repository.java");
        if(outputFile.exists())
        {
            System.out.print("\n" + "be consider that the file "+outputFile.getName() + "  already exist");
            return;
        }
        FileWriterWithEncoding writer = null;
        try {
            writer = new FileWriterWithEncoding(outputFile, "utf-8");
            t.merge(context, writer);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    void copyRepositoriesBaseClasses()
{
    String[] files= new String[]{"EnableExtendedRepositories",
            "ExtendedQueryDslJpaRepository",
            "SimpleExtendedQueryDslJpaRepository",
            "StoredProcedureQueryBuilder"
    };
    File directory = new File(this.outputPath+"/"+this.repositoryFolderPackageName+"/baseRepository/");
    if (! directory.exists()){
        directory.mkdir();
    }
    VelocityContext context = new VelocityContext();
    context.put("package",   this.parentPackageName+"."+this.repositoryFolderPackageName+".baseRepository");

    Arrays.stream(files).forEach(file->{
        Template template = ve.getTemplate("velocity/baseRepository/"+file+".java.e.vm", "utf-8");


        //net.tedata.MVSC.repository
        File outputFile = new File(this.outputPath+"/"+this.repositoryFolderPackageName+"/baseRepository/"+file +".java");
        if(outputFile.exists())
        {
            System.out.print("\n" + "be consider that the file "+outputFile.getName() + "  already exist");
            return;
        }
        FileWriterWithEncoding writer = null;
        try {
            writer = new FileWriterWithEncoding(outputFile, "utf-8");
            template.merge(context, writer);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    });


}


    void copyServiceBase()
    {
        String[] files= new String[]{"BaseService" };
        File directory = new File(this.outputPath+"/"+this.serviceFolderPackageName+"/baseService/");
        if (! directory.exists()){
            directory.mkdir();
        }
        VelocityContext context = new VelocityContext();
        LinkedHashSet<String> baseServiceImports = new LinkedHashSet<String>();

        context.put("package",   this.parentPackageName+"."+this.serviceFolderPackageName+".baseService");
        baseServiceImports.add(this.parentPackageName+"."+this.repositoryFolderPackageName+".baseRepository."+this.baseRepositoryName);
        baseServiceImports.add(this.parentPackageName+"."+this.repositoryFolderPackageName+".baseRepository.StoredProcedureQueryBuilder");
        context.put("baseServiceImports", baseServiceImports);



        Arrays.stream(files).forEach(file->{
            Template template = ve.getTemplate("velocity/baseService/"+file+".java.e.vm", "utf-8");


            //net.tedata.MVSC.repository
            File outputFile = new File(this.outputPath+"/"+this.serviceFolderPackageName+"/baseService/"+file +".java");
            if(outputFile.exists())
            {
                System.out.print("\n" + "be consider that the file "+outputFile.getName() + "  already exist");
                return;
            }
            FileWriterWithEncoding writer = null;
            try {
                writer = new FileWriterWithEncoding(outputFile, "utf-8");
                template.merge(context, writer);
                writer.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        });


    }

    void createEmmbadedClass(EmbeddableClass embeddableClass)
    {
        VelocityContext context = new VelocityContext();

        context.put("package",   this.parentPackageName+"."+this.modelFolderPackageName+"");

        context.put("embeddableClass",embeddableClass);


        Template template = ve.getTemplate("velocity/Embeddable.java.e.vm", "utf-8");


        File directory = new File(this.outputPath+"/"+this.modelFolderPackageName+"/");
        if (! directory.exists()){
            directory.mkdir();
        }

        File outputFile = new File(this.outputPath+"/"+this.modelFolderPackageName+"/"+embeddableClass.getJavaName() +".java");
        if(outputFile.exists())
        {
            System.out.print("\n" + "be consider that the file "+outputFile.getName() + "  already exist");
            return;
        }
        FileWriterWithEncoding writer = null;
        try {
            writer = new FileWriterWithEncoding(outputFile, "utf-8");
            template.merge(context, writer);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void   createEntity(Table table)
    {


        VelocityContext context = new VelocityContext();


        context.put("package",   this.parentPackageName+"."+this.modelFolderPackageName);

        context.put("table",table);


        Template template = ve.getTemplate("velocity/Entity.java.e.vm", "utf-8");


        File directory = new File(this.outputPath+"/"+this.modelFolderPackageName+"/");
        if (! directory.exists()){
            directory.mkdir();
        }

        File outputFile = new File(this.outputPath+"/"+this.modelFolderPackageName+"/"+table.getJavaName() +".java");
        if(outputFile.exists())
        {
            System.out.print("\n" + "be consider that the file "+outputFile.getName() + "  already exist");
            return;
        }
        FileWriterWithEncoding writer = null;
        try {
            writer = new FileWriterWithEncoding(outputFile, "utf-8");
            template.merge(context, writer);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public void   createService(Table table)
    {

        VelocityContext context = new VelocityContext();
        LinkedHashSet<String> serviceImports = new LinkedHashSet<String>();

this.copyServiceBase();

        context.put("table",table);
                context.put("package",   this.parentPackageName+"."+this.serviceFolderPackageName);


        serviceImports.add(this.parentPackageName+"."+this.serviceFolderPackageName+".baseService.BaseService");
        serviceImports.add(this.parentPackageName+"."+this.modelFolderPackageName+"."+table.getJavaName());
        serviceImports.add(this.parentPackageName+"."+this.repositoryFolderPackageName+"."+table.getJavaName()+"Repository");

       if( table.getEmbeddedId()!=null)
       {
           context.put("PK_Type",table.getEmbeddedId().getJavaName());
           serviceImports.add(this.parentPackageName+"."+ this.modelFolderPackageName+"."+table.getEmbeddedId().getJavaName());
       }
       else
       {
         Column col=  table.getColumnsList().stream().filter(column -> column.isPrimaryKey()==true).findFirst().orElse(null);
         if(col!=null)
         {
             context.put("PK_Type",col.getJavaSQLDataTypeMap().getJavaType());
             if(col.getJavaSQLDataTypeMap().getTypeNeedImportThisLine()!=null)
             {
                 serviceImports.add(col.getJavaSQLDataTypeMap().getTypeNeedImportThisLine());
             }
         }

       }

        context.put("serviceImports",serviceImports);

        Template t = ve.getTemplate("velocity/EntityService.java.e.vm", "utf-8");
        File directory = new File(this.outputPath+"/"+this.serviceFolderPackageName+"/");
        if (! directory.exists()){
            directory.mkdir();
        }

        File outputFile = new File(this.outputPath+"/"+this.serviceFolderPackageName+"/"+table.getJavaName()+"Service.java");
        if(outputFile.exists())
        {
            System.out.print("\n" + "be consider that the file "+outputFile.getName() + "  already exist");
            return;
        }
        FileWriterWithEncoding writer = null;
        try {
            writer = new FileWriterWithEncoding(outputFile, "utf-8");
            t.merge(context, writer);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void   createController(Table table)
    {

        VelocityContext context = new VelocityContext();
        LinkedHashSet<String> controllerImports = new LinkedHashSet<String>();



        context.put("table",table);
        context.put("package",   this.parentPackageName+"."+this.controllerFolderPackageName);


         controllerImports.add(this.parentPackageName+"."+this.modelFolderPackageName+"."+table.getJavaName());
        controllerImports.add(this.parentPackageName+"."+this.serviceFolderPackageName+"."+table.getJavaName()+"Service");

        if( table.getEmbeddedId()!=null)
        {
            context.put("PK_Type",table.getEmbeddedId().getJavaName());
            controllerImports.add(this.parentPackageName+"."+ this.modelFolderPackageName+"."+table.getEmbeddedId().getJavaName());
        }
        else
        {
            Column col=  table.getColumnsList().stream().filter(column -> column.isPrimaryKey()==true).findFirst().orElse(null);
            if(col!=null)
            {
                context.put("PK_Type",col.getJavaSQLDataTypeMap().getJavaType());
                if(col.getJavaSQLDataTypeMap().getTypeNeedImportThisLine()!=null)
                {
                    controllerImports.add(col.getJavaSQLDataTypeMap().getTypeNeedImportThisLine());
                }
            }

        }

        if(this.controllerPrefixWithoutSlashs.equals(""))
        {
            context.put("controllerPrefixWithSlashs","");
        }
        else
        {
            context.put("controllerPrefixWithSlashs","/"+this.controllerPrefixWithoutSlashs+"/");
        }

        if(this.protectRest==false)
        {
            context.put("preAuthorize","");

        }
        else

        {
            context.put("preAuthorize","@PreAuthorize(\"Bahgat\")");

        }

        context.put("controllerImports",controllerImports);

        Template t = ve.getTemplate("velocity/EntityController.java.e.vm", "utf-8");
        File directory = new File(this.outputPath+"/"+this.controllerFolderPackageName+"/");
        if (! directory.exists()){
            directory.mkdir();
        }

        File outputFile = new File(this.outputPath+"/"+this.controllerFolderPackageName+"/"+table.getJavaName()+"Controller.java");
        if(outputFile.exists())
        {
            System.out.print("\n" + "be consider that the file "+outputFile.getName() + "  already exist");
            return;
        }
        FileWriterWithEncoding writer = null;
        try {
            writer = new FileWriterWithEncoding(outputFile, "utf-8");
            t.merge(context, writer);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();

        }
    }










    public HashMap<String, Table> getAllTables() throws SQLException, ClassNotFoundException {
        ResultSet rs;
        try
        {
            HashMap<String,Table> tables= new HashMap<>();



            rs= this.connect().createStatement().executeQuery(SQL.mySQLselectStatment);

            while (rs.next())
            {
                if(tables.containsKey(rs.getString("tableName"))==false)
                {
                    Table  newTable=new Table();
                    //  select fcc.tableName,fcc.columnName,fcc.isPrimaryKey,fcc.isAutoIncrement,fcc.isNullable,fcc.columnSqlDataType,fcc.max_length,fcc.precision,fcc.scale,fcc.isForeignKey,fcc.referenceTableName,fcc.referenceColumnName ,th_indexes.is_unique as isUnique from fcc

                    newTable.setSqlName(rs.getString("tableName"));
                    newTable.setJavaName(NameConverter.toUPPER_CAMEL(rs.getString("tableName")));
                    newTable.setJavaNameFirstCharSmall(NameConverter.toLOWER_CAMEL(rs.getString("tableName")));

                    tables.put(rs.getString("tableName"),newTable);

                }

                Column  newColumn=new Column();

                newColumn.setJavaSQLDataTypeMap(SQL.getJavaMSSQLDataTypeMapList().get(rs.getString("columnSqlDataType")));
                newColumn.setColumnSQlName(rs.getString("columnName"));
                newColumn.setColumnJavaName(NameConverter.toUPPER_CAMEL(rs.getString("columnName")));
                 newColumn.setColumnJavaNameFirstCharSmall(NameConverter.toLOWER_CAMEL(rs.getString("columnName")));
                newColumn.setTableName(rs.getString("tableName"));
                newColumn.setNullable(rs.getBoolean("isNullable"));
                newColumn.setAutoIncrement(rs.getBoolean("isAutoIncrement"));
                if(rs.getBigDecimal("maxlength")!=null)
                {
                    newColumn.setMaxlength(Integer.valueOf( rs.getBigDecimal("maxlength").intValue()));

                }
                newColumn.setComment(rs.getString("comment"));
                newColumn.setUnique(rs.getBoolean("isUnique"));
                newColumn.setPrimaryKey(rs.getBoolean("isPrimaryKey"));
                newColumn.setForeignKey(rs.getBoolean("isForeignKey"));
                newColumn.setReferenceTableName(rs.getString("referenceTableName"));
                newColumn.setReferenceColumnName(rs.getString("referenceColumnName"));

                tables.get(rs.getString("tableName")).getColumnsList().add(newColumn);


            }

            processes processes=new processes();


            for (Map.Entry<String, Table> item : tables.entrySet()) {
                try {
                    String key = item.getKey();
                    Table table = item.getValue();
                    if (table.getColumnsList().size() == table.getColumnsList().stream().filter(column -> column.isPrimaryKey()).count()) {
                        table.setManyToManyTable(true);
                    }
                    processes.addRelationsObjectsAndGenerateColumnsAnnotations(table,new ArrayList<Table>(tables.values()),this.hideManyToOneColumn);
                    processes.generateColumnsAnnotationAndImports();

                }
                catch (Exception ex)
                {
                    System.out.print(ex.getMessage());
                }
            }

            return tables;

        }
        catch (Exception ex)
        {
            System.out.print(ex.getMessage());
            System.out.print(ex);
            throw ex;
        }

    }




//    public DataSource dataSource(){
//        DriverManagerDataSource ds = new DriverManagerDataSource();
//        ds.setDriverClassName("com.mysql.jdbc.Driver");
//        ds.setUrl("jdbc:mysql://localhost:3306/myDB");
//        ds.setUsername("sa");
//        ds.setPassword("123");
//        return ds;
//    }



    public Connection connect() throws SQLException, ClassNotFoundException {

        try{

            if(this.databaseType.equals("MYSQL"))
            {
                Class.forName("com.mysql.jdbc.Driver");
                //con = DriverManager.getConnection(   "jdbc:mysql://localhost/myDB?user=sa&password=123");
            }
            else
            {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            }
            con = DriverManager.getConnection(this.connectionString);
            return con;

        }catch(Exception e){

            System.out.print(e.getMessage());
             throw e;
        }

    }



//    public void   createRepository_old(Class modelClass,Field field)
//    {
//
//
//        VelocityContext context = new VelocityContext();
//
//        List<String> tmp=new ArrayList<String>(Arrays.asList(modelClass.getPackage().getName().split("\\.")));
//        tmp.remove(tmp.size()-1);
//        String parent_package_name=tmp.toString().replace(", ", ".").replaceAll("[\\[\\]]", "");
//
//
//        context.put("package", parent_package_name+".repository");
//
//        context.put("import_ExtendedQueryDslJpaRepository",this.extendedQueryDslJpaRepositoryPackage);
//        context.put("import_model",modelClass.getName());
//        context.put("model_name",modelClass.getSimpleName());
//        context.put("test","abcxxxxx");
//
//        context.put("import_PK_Type",field.getType().getName()  );
//
//
//
//        context.put("repository_name",modelClass.getSimpleName()+"Repository");
//        context.put("PK_Type",field.getType().getSimpleName());
//
//
//
//
//
//
//
//
//        Template t = ve.getTemplate("JavaEntityFramework.velocity/EntityRepository.java.e.vm", "utf-8");
//        File directory = new File(this.outputPath+"/repository/");
//        if (! directory.exists()){
//            directory.mkdir();
//        }
//
//        File outputFile = new File(this.outputPath+"/repository/"+modelClass.getSimpleName()+"Repository.java");
//        if(outputFile.exists())
//        {
//            System.out.print("\n" + "be consider that the file "+outputFile.getName() + "  already exist");
//            return;
//        }
//        FileWriterWithEncoding writer = null;
//        try {
//            writer = new FileWriterWithEncoding(outputFile, "utf-8");
//            t.merge(context, writer);
//            writer.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


}
