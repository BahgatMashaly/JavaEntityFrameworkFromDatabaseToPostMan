package main.velocity;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class EmbeddableClass {
    private  String javaName;
    private  String javaNameFirstCharSmall;
    private List<Column> columnsList = new ArrayList<Column>();
    private LinkedHashSet<String> imports = new LinkedHashSet<String>();



    public String getJavaName() {
        return javaName;
    }

    public void setJavaName(String javaName) {
        this.javaName = javaName;
    }

    public String getJavaNameFirstCharSmall() {
        return javaNameFirstCharSmall;
    }

    public void setJavaNameFirstCharSmall(String javaNameFirstCharSmall) {
        this.javaNameFirstCharSmall = javaNameFirstCharSmall;
    }

    public List<Column> getColumnsList() {
        return columnsList;
    }

    public void setColumnsList(List<Column> columnsList) {
        this.columnsList = columnsList;
    }

    public LinkedHashSet<String> getImports() {
        return imports;
    }

    public void setImports(LinkedHashSet<String> imports) {
        this.imports = imports;
    }



//    {
//        "centerCourse": {
//        "center": {
//            "id": 0,
//                    "name": "string"
//        },
//        "centerId": 0,
//                "course": {
//            "id": 0,
//                    "name": "string",
//                    "price": 0
//        },
//        "courseId": 0,
//                "id": 0
//    },
//        "date": "string",
//            "embeddableStudentIdCenterCourseId": {
//        "centerCourseId": 0,
//                "studentId": 0
//    },
//        "student": {
//        "address": "string",
//                "email": "string",
//                "id": 0,
//                "name": "string",
//                "studentDetails": {
//            "address": "string",
//                    "studentIdF": 0
//        }
//    }
//    }

}
