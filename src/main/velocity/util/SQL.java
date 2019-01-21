package main.velocity.util;

import java.util.HashMap;

public class SQL {



        public static HashMap<String, JavaSQLDataTypeMap> getJavaMSSQLDataTypeMapList()
    {

//
        HashMap<String, JavaSQLDataTypeMap> javaMSSQLDataTypeMap= new HashMap<String, JavaSQLDataTypeMap>();
        javaMSSQLDataTypeMap.put("int",new JavaSQLDataTypeMap("int","Long",null,false));
        javaMSSQLDataTypeMap.put("nchar",new JavaSQLDataTypeMap("nchar","String",null,true));
        javaMSSQLDataTypeMap.put("bigint",new JavaSQLDataTypeMap("bigint","Long",null,false));
        javaMSSQLDataTypeMap.put("bit",new JavaSQLDataTypeMap("bit","Boolean",null,false));
        javaMSSQLDataTypeMap.put("char",new JavaSQLDataTypeMap("char","String",null,true));
        javaMSSQLDataTypeMap.put("date",new JavaSQLDataTypeMap("date","Date","java.sql.Date",false));
        javaMSSQLDataTypeMap.put("datetime",new JavaSQLDataTypeMap("datetime","Date","java.sql.Date",false));
        javaMSSQLDataTypeMap.put("decimal",new JavaSQLDataTypeMap("decimal","BigDecimal","java.math.BigDecimal",false));
        javaMSSQLDataTypeMap.put("image",new JavaSQLDataTypeMap("image","byte[]",null,false));
        javaMSSQLDataTypeMap.put("text",new JavaSQLDataTypeMap("text","String",null,false));
        javaMSSQLDataTypeMap.put("numeric",new JavaSQLDataTypeMap("numeric","BigDecimal","java.math.BigDecimal",false));
        javaMSSQLDataTypeMap.put("nvarchar",new JavaSQLDataTypeMap("nvarchar","String",null,true));
        javaMSSQLDataTypeMap.put("real",new JavaSQLDataTypeMap("real","Float",null,false));
        javaMSSQLDataTypeMap.put("smalldatetime",new JavaSQLDataTypeMap("smalldatetime","Date","java.sql.Date",false));
        javaMSSQLDataTypeMap.put("smallint",new JavaSQLDataTypeMap("smallint","Short",null,false));
        javaMSSQLDataTypeMap.put("tinyint",new JavaSQLDataTypeMap("tinyint","Byte",null,false));
        javaMSSQLDataTypeMap.put("varbinary",new JavaSQLDataTypeMap("varbinary","byte[]",null,false));
        javaMSSQLDataTypeMap.put("varchar",new JavaSQLDataTypeMap("varchar","String",null,true));
        javaMSSQLDataTypeMap.put("float",new JavaSQLDataTypeMap("float","BigDecimal","java.math.BigDecimal",false));
        javaMSSQLDataTypeMap.put("uniqueidentifier",new JavaSQLDataTypeMap("uniqueidentifier","String",null,false));
        javaMSSQLDataTypeMap.put("time",new JavaSQLDataTypeMap("time","Time","java.sql.Time",false));
        javaMSSQLDataTypeMap.put("timestamp",new JavaSQLDataTypeMap("timestamp","Timestamp","java.sql.Timestamp",false));
        javaMSSQLDataTypeMap.put("smallmoney",new JavaSQLDataTypeMap("smallmoney","BigDecimal","java.math.BigDecimal",false));
        javaMSSQLDataTypeMap.put("money",new JavaSQLDataTypeMap("money","BigDecimal","java.math.BigDecimal",false));
        javaMSSQLDataTypeMap.put("geography",new JavaSQLDataTypeMap("geography","byte[]",null,false));
        javaMSSQLDataTypeMap.put("binary",new JavaSQLDataTypeMap("binary","byte[]",null,false));
        javaMSSQLDataTypeMap.put("tinytext",new JavaSQLDataTypeMap("tinytext","String",null,true));
        javaMSSQLDataTypeMap.put("mediumtext",new JavaSQLDataTypeMap("mediumtext","String",null,true));
        javaMSSQLDataTypeMap.put("longtext",new JavaSQLDataTypeMap("longtext","String",null,false));
        javaMSSQLDataTypeMap.put("mediumint",new JavaSQLDataTypeMap("mediumint","Integer",null,false));
        javaMSSQLDataTypeMap.put("longblob",new JavaSQLDataTypeMap("longblob","Blob","java.sql.Blob",false));
        javaMSSQLDataTypeMap.put("mediumblob",new JavaSQLDataTypeMap("mediumblob","Blob","java.sql.Blob",false));
        javaMSSQLDataTypeMap.put("tinyblob",new JavaSQLDataTypeMap("tinyblob","Blob","java.sql.Blob",false));
        javaMSSQLDataTypeMap.put("blob",new JavaSQLDataTypeMap("blob","Blob","java.sql.Blob",false));


        //was Object
        javaMSSQLDataTypeMap.put("json",new JavaSQLDataTypeMap("json","Blob","java.sql.Blob",false));
        javaMSSQLDataTypeMap.put("year",new JavaSQLDataTypeMap("year","Blob","java.sql.Blob" ,false));
        javaMSSQLDataTypeMap.put("geometrycollection",new JavaSQLDataTypeMap("geometrycollection","Blob",null,false));
        javaMSSQLDataTypeMap.put("geometry",new JavaSQLDataTypeMap("geometry","Blob","java.sql.Blob",false));
        javaMSSQLDataTypeMap.put("point",new JavaSQLDataTypeMap("point","Blob","java.sql.Blob",false));
        javaMSSQLDataTypeMap.put("linestring",new JavaSQLDataTypeMap("linestring","Blob","java.sql.Blob",false));
        javaMSSQLDataTypeMap.put("polygon",new JavaSQLDataTypeMap("polygon","Blob","java.sql.Blob",false));
        javaMSSQLDataTypeMap.put("multilinestring",new JavaSQLDataTypeMap("multilinestring","Blob","java.sql.Blob",false));
        javaMSSQLDataTypeMap.put("hierarchyid",new JavaSQLDataTypeMap("hierarchyid","Blob","java.sql.Blob",false));
        javaMSSQLDataTypeMap.put("datetimeoffset",new JavaSQLDataTypeMap("datetimeoffset","Blob","java.sql.Blob",false));
        javaMSSQLDataTypeMap.put("hierarchyid",new JavaSQLDataTypeMap("hierarchyid","Object","java.sql.Blob",false));
        javaMSSQLDataTypeMap.put("sqlVariant",new JavaSQLDataTypeMap("sqlVariant","Blob","java.sql.Blob",false));


        return javaMSSQLDataTypeMap;
    }


    //////////////////

public static String MySQLSelectStatment="SELECT c.table_name as tableName,\n" +
        "       c.ORDINAL_POSITION as columnOrder, \n" +
         "       c.COLUMN_NAME as columnName,\n" +
         "\n" +
         "       IF(EXISTS(select *\n" +
         "                 FROM information_schema.KEY_COLUMN_USAGE k\n" +
         "                             JOIN information_schema.TABLE_CONSTRAINTS tc\n" +
         "                          ON (k.TABLE_SCHEMA=tc.TABLE_SCHEMA\n" +
         "                                     AND k.TABLE_NAME=tc.TABLE_NAME\n" +
         "                                     AND k.CONSTRAINT_NAME=tc.CONSTRAINT_NAME)\n" +
         "                 WHERE k.TABLE_SCHEMA=c.TABLE_SCHEMA\n" +
         "                   AND k.TABLE_NAME=c.TABLE_NAME\n" +
         "                   AND tc.CONSTRAINT_TYPE='PRIMARY KEY'\n" +
         "                   AND c.COLUMN_NAME=k.COLUMN_NAME),1,0) AS isPrimaryKey,\n" +
         "\n" +
         "\n" +
         "        case when c.EXTRA ='auto_increment' then 1 else 0 end as isAutoIncrement,\n" +
         "       case when c.IS_NULLABLE ='YES' then 1 else 0 end as isNullable,\n" +
         "\n" +
         "       c.DATA_TYPE as columnSqlDataType,\n" +
         "\n" +
         "       c.CHARACTER_MAXIMUM_LENGTH as Maxlength,\n" +
         "       c.COLUMN_COMMENT as comment,\n" +
         "\n" +
         "       IF(EXISTS(select *\n" +
         "                 FROM information_schema.KEY_COLUMN_USAGE k\n" +
         "                        JOIN information_schema.TABLE_CONSTRAINTS tc\n" +
         "                          ON (k.TABLE_SCHEMA=tc.TABLE_SCHEMA\n" +
         "                                AND k.TABLE_NAME=tc.TABLE_NAME\n" +
         "                                AND k.CONSTRAINT_NAME=tc.CONSTRAINT_NAME)\n" +
         "                 WHERE k.TABLE_SCHEMA=c.TABLE_SCHEMA\n" +
         "                   AND k.TABLE_NAME=c.TABLE_NAME\n" +
         "                   AND tc.CONSTRAINT_TYPE='UNIQUE'\n" +
         "                   AND c.COLUMN_NAME=k.COLUMN_NAME),1,0) AS isUnique ,\n" +
         "\n" +
         "       IF(EXISTS(select *\n" +
         "                 FROM information_schema.KEY_COLUMN_USAGE k\n" +
         "                        JOIN information_schema.TABLE_CONSTRAINTS tc\n" +
         "                          ON (k.TABLE_SCHEMA=tc.TABLE_SCHEMA\n" +
         "                                AND k.TABLE_NAME=tc.TABLE_NAME\n" +
         "                                AND k.CONSTRAINT_NAME=tc.CONSTRAINT_NAME)\n" +
         "                 WHERE k.TABLE_SCHEMA=c.TABLE_SCHEMA\n" +
         "                   AND k.TABLE_NAME=c.TABLE_NAME\n" +
         "                   AND tc.CONSTRAINT_TYPE='FOREIGN KEY'\n" +
         "                   AND c.COLUMN_NAME=k.COLUMN_NAME),1,0) AS isForeignKey,\n" +
         "\n" +
         "       k.REFERENCED_TABLE_NAME as referenceTableName,\n" +
         "       k.REFERENCED_COLUMN_NAME as referenceColumnName\n" +
         "FROM information_schema.COLUMNS c\n" +
         "            LEFT JOIN information_schema.KEY_COLUMN_USAGE k\n" +
         "         ON (k.TABLE_SCHEMA=c.TABLE_SCHEMA\n" +
         "                    AND k.TABLE_NAME=c.TABLE_NAME\n" +
         "                    AND k.COLUMN_NAME=c.COLUMN_NAME\n" +
         "                    AND k.POSITION_IN_UNIQUE_CONSTRAINT IS NOT NULL)\n" +
         "WHERE c.TABLE_SCHEMA='mydb'\n" +
         "order by  tableName , columnOrder ";








public static String   MSSQLSelectStatment =" with fcc as\n" +
         "(\n" +
         "\n" +
         "SELECT  \n" +
         " t.name as tableName, \n" +
        "c.column_id AS columnOrder ,\n" +
         "c.name AS columnName,\n" +
         "   ic.is_primary_key as  isPrimaryKey,\n" +
         "   is_identity as isAutoIncrement,\n" +
         "    c.is_nullable as  isNullable,\n" +
         "\ttyp.name as columnSqlDataType,\n" +
         "\tcase when typ.name='nvarchar' or   typ.name='nchar' then c.max_length/2 else c.max_length end as maxlength,\n" +
         "     c.precision,\n" +
         "   c.scale,\n" +
         "   ic.object_id,\n" +
         "   \n" +
         "   ic.column_id,\n" +
         "   ic.index_id,\n" +
         "\n" +
         "\n" +
         "case when f.name is null then 0 else 1 end as isForeignKey,\n" +
         "     \n" +
         "    OBJECT_NAME (f.referenced_object_id) AS referenceTableName,\n" +
         "   COL_NAME(fc.referenced_object_id, fc.referenced_column_id) AS referenceColumnName\n" +
         "FROM \n" +
         "  sys.tables       as t       \n" +
         "inner join    sys.columns AS c on t.object_id = c.object_id  \n" +
         "INNER JOIN sys.types AS typ ON c.user_type_id=typ.user_type_id\n" +
         "left outer   JOIN (select x.object_id,column_id,x.is_primary_key,x.index_id  from  sys.indexes x inner JOIN sys.index_columns icx on icx.object_id = x.object_id AND x.index_id = icx.index_id   where is_primary_key = 1 )  ic ON ic.object_id = c.object_id AND c.column_id = ic.column_id\n" +
         "    \n" +
         "LEFT OUTER JOIN sys.foreign_key_columns AS fc ON fc.parent_object_id = c.object_id AND COL_NAME(fc.parent_object_id, fc.parent_column_id) = c.name\n" +
         "LEFT OUTER JOIN sys.foreign_keys AS f\n" +
         " ON f.parent_object_id = c.object_id AND fc.constraint_object_id = f.object_id)\n" +
         " select fcc.tableName,fcc.columnName,fcc.isPrimaryKey,fcc.isAutoIncrement,fcc.isNullable,fcc.columnSqlDataType,fcc.maxlength,fcc.precision,fcc.scale,fcc.isForeignKey,fcc.referenceTableName,fcc.referenceColumnName ,th_indexes.is_unique as isUnique from fcc \n" +
         "\n" +
         " left outer  join ( select x.object_id, x.index_id, column_id,x.is_primary_key ,x.is_unique ,x.is_unique_constraint  from  sys.indexes x inner JOIN sys.index_columns icx on icx.object_id = x.object_id AND x.index_id = icx.index_id )\n" +
         " as th_indexes on fcc.column_id =th_indexes.column_id and fcc.object_id =th_indexes.object_id and fcc.index_id=th_indexes.index_id\n" +
         "  order by tableName, columnOrder  ";








}



//MSSQL
//        SELECT
//    t.name as tableName,
//        c.name AS columnName,
//        ic.is_primary_key as  isPrimaryKey,
//        is_identity as isAutoIncrement,
//        c.is_nullable as  isNullable,
//        typ.name as columnSqlDataType,
//        case when typ.name='nvarchar' or   typ.name='nchar' then c.max_length/2 else c.max_length end as maxlength,
//        c.precision,
//        c.scale,
//
//        case when f.name is null then 0 else 1 end as isForeignKey,
//
//        OBJECT_NAME (f.referenced_object_id) AS referenceTableName,
//        COL_NAME(fc.referenced_object_id, fc.referenced_column_id) AS referenceColumnName
//        FROM
//        sys.tables       as t
//        inner join    sys.columnsList AS c on t.object_id = c.object_id
//        INNER JOIN sys.types AS typ ON c.user_type_id=typ.user_type_id
//        left outer   JOIN (select x.object_id,column_id,x.is_primary_key  from  sys.indexes x inner JOIN sys.index_columns icx on icx.object_id = x.object_id AND x.index_id = icx.index_id   where is_primary_key = 1 )  ic ON ic.object_id = c.object_id AND c.column_id = ic.column_id
//        LEFT OUTER JOIN sys.foreign_key_columns AS fc ON fc.parent_object_id = c.object_id AND COL_NAME(fc.parent_object_id, fc.parent_column_id) = c.name
//        LEFT OUTER JOIN sys.foreign_keys AS f
//        ON f.parent_object_id = c.object_id AND fc.constraint_object_id = f.object_id
//        ORDER BY  t.name;
