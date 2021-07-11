package com.example.provathiagolopes.SQLite;

import android.provider.BaseColumns;

public class DBSchema {

    public static final class Finance implements BaseColumns {
        public static final String TABLENAME = "finance";
        public static final String PRICE = "f_price";
        public static final String NAME = "f_name";
        public static final String TYPE = "f_type";

        public static final String getCreationQuery() {
            return "CREATE TABLE "+TABLENAME+" ("+
                    _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    PRICE + " DOUBLE, "+
                    NAME + " STRING, "+
                    TYPE + " BOOLEAN" +
                    ")";
        }
    }
}
