package main.velocity.util;

import java.io.Serializable;

public class ClassNameRule implements Serializable {

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    private String tableName;
    private String className;
}