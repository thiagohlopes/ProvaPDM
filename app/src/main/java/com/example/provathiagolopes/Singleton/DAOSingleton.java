package com.example.provathiagolopes.Singleton;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.provathiagolopes.SQLite.Finance;

import java.io.IOException;
import java.util.ArrayList;

public class DAOSingleton {
    private static DAOSingleton INSTANCE;
    private ArrayList<Finance> finances;
    private FileManager fileManager;

    private DAOSingleton(){
        this.finances= new ArrayList<>();
        this.fileManager=null;
    }

    public static DAOSingleton getINSTANCE() {
        if(INSTANCE == null)
            INSTANCE = new DAOSingleton();
        return INSTANCE;
    }

    private FileManager initializeFileManager(Context context) throws IOException {
        if (this.fileManager == null) {
            this.fileManager = new FileManager(context);
            this.finances = this.fileManager.load();
        }
        return this.fileManager;
    }

    public ArrayList<Finance> getFinances(Context context) {
        try {
            this.initializeFileManager(context);
        } catch (IOException e) {
            Toast.makeText(context, "FileErr: "+e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        return this.finances;
    }

    public void addFinance(Context context, Finance finance) {
        Log.e("total string>>>>", finance.toString());
        this.finances.add(finance);
        try {
            this.initializeFileManager(context).save(finance);
        } catch (IOException e) {
            Toast.makeText(context, "FileErr: "+e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
