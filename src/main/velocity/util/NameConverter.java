package main.velocity.util;

import com.google.common.base.CaseFormat;

public class NameConverter {


    public static String  toLOWER_CAMEL(String tableName) {
        return  CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL,
                CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, tableName));
    }

    public static String toUPPER_CAMEL(String tableName) {
        return  CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL,
                CaseFormat.UPPER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, tableName));
    }



//
//    public static String UPPER_UNDERSCOREtoUPPER_CAMEL(String tableName ) {
//
//        return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName);
//    }
//
//
//
//    public static String UPPER_CAMELtoLOWER_UNDERSCORE(String tableName) {
//        return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, tableName);
//    }


}
