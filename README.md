# JavaEntityFrameworkFromDatabaseToPostMan
Auto generate  model, repository, service, controller and postman file 

- all database relations has been implemented with best practises.  Many thanks  @Vlad Mihalcea for his articles about the best practises of mapping Database relationship in Hibernate   https://vladmihalcea.com/
- Spring-Boot Repository.
- Lombok.
- Generate postman file.
- Integrated with MYSQL and MSSQL (MSSQL not tested yet)
- Integrated with QueryDsl and Marge between spring-data-jpa and QueryDsl.(don't forget to run maven compiler to generate Querydsl files from models). thanks @lpandzic  https://github.com/infobip/infobip-spring-data-jpa-querydsl




USING:
- set "resources" directory as resource root
- The option "Insert update ManyToOne by Object" : it's about how it will manage the ManyToOne relation in the model file that will effect how you will insert or update the object.


example for manage the ManyToOne by object

{
id:xx
name:xx
ManyToOneTable:{
    id:xx,
    name:xx
  }
}

example for manage the ManyToOne by id

{
id:xx
name:xx
ManyToOneTableID:xx
}


- currently the genrated postman file mange ManyToOne by id only


       // jdbc:sqlserver://localhost;databaseName=PRIVACY;user=sa;password=123



