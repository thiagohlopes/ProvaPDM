package com.example.provathiagolopes.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.provathiagolopes.List;

import java.util.ArrayList;

public class DAOFinance {

    public static final void execQueryFinance(Context context, String sql) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL(sql);
        db.close();
    }

    public static final void insertFinance(Context context, Finance finance) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DBSchema.Finance.PRICE, finance.getPrice());
        cv.put(DBSchema.Finance.NAME, finance.getName());
        cv.put(DBSchema.Finance.TYPE, finance.getType());
        db.insert(DBSchema.Finance.TABLENAME, null, cv);
        db.close();
    }

    public static final ArrayList<Finance> getAllPizzas(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<Finance> pizzas = new ArrayList<>();

        // SELECT * FROM pizza;

        Cursor cursor = db.query(
                DBSchema.Finance.TABLENAME,
                null,
                null,
                null,
                null,
                null,
                DBSchema.Finance.NAME
        );

        while(cursor.moveToNext()) {
            long id = cursor.getLong(cursor.getColumnIndex(DBSchema.Finance._ID));
            Double price = cursor.getDouble(cursor.getColumnIndex(DBSchema.Finance.PRICE));
            String type = cursor.getString(cursor.getColumnIndex(DBSchema.Finance.TYPE));
            String name = cursor.getString(cursor.getColumnIndex(DBSchema.Finance.NAME));
            Finance finance = new Finance( id,  price,  name,  type);
            pizzas.add(finance);
//            Log.e(finance.toString(),"<<<<<<<<<<<<<<<<<<<<");
        }

        cursor.close();
        db.close();

        return pizzas;
    }
}
