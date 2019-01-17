package main.velocity.util;

public class  JavaSQLDataTypeMap {
    String SQLType;
    String JavaType;
    String TypeNeedImportThisLine;
    boolean checkLenght;

    public JavaSQLDataTypeMap(String SQLType, String JavaType, String TypeNeedImportThisLine, boolean checkLenght) {
        this.JavaType = JavaType;
        this.SQLType = SQLType;
        this.TypeNeedImportThisLine = TypeNeedImportThisLine;
        this.checkLenght = checkLenght;

    }







    public String getJavaType() {
        return JavaType;
    }

    public void setJavaType(String javaType) {
        JavaType = javaType;
    }

    public boolean isCheckLenght() {
        return checkLenght;
    }

    public void setCheckLenght(boolean checkLenght) {
        this.checkLenght = checkLenght;
    }



    public String getSQLType() {
        return SQLType;
    }

    public void setSQLType(String SQLType) {
        this.SQLType = SQLType;
    }


 

    public String getTypeNeedImportThisLine() {
        return TypeNeedImportThisLine;
    }

    public void setTypeNeedImportThisLine(String typeNeedImportThisLine) {
        TypeNeedImportThisLine = typeNeedImportThisLine;
    }


}
