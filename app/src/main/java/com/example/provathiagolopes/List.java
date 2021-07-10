package com.example.provathiagolopes;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.provathiagolopes.RecyclerView.FinanceAdapter;
import com.example.provathiagolopes.SQLite.DAOFinance;
import com.example.provathiagolopes.SQLite.DBHelper;
import com.example.provathiagolopes.SQLite.Finance;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class List extends Fragment {

    private static final int MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE = 1;
    private RecyclerView rv_finance;
    private FinanceAdapter financeAdapter;
    private ArrayList<Finance> finances;
    private double total = 0.0;
    private TextView tv_total;
    private FloatingActionButton fbtn_relatory;

    public static List newInstance() {
        return new List();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.rv_finance = view.findViewById(R.id.rv_finance);
        this.tv_total = view.findViewById(R.id.tv_total);
        this.fbtn_relatory = view.findViewById(R.id.fbtn_relatory);
        finances= DAOFinance.getAllPizzas(getContext());
        this.total = 0;
        for(int i=0;i<finances.size(); i++){
            total += finances.get(i).getPrice();
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        this.rv_finance.setLayoutManager(layoutManager);
        this.rv_finance.setHasFixedSize(true);
        this.financeAdapter = new FinanceAdapter(finances, getActivity());
        this.rv_finance.setAdapter(this.financeAdapter);
        this.tv_total.setText(Double.toString(total));
        this.fbtn_relatory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(v.getContext());
                builder1.setMessage("Gostaria de gerar um relatorio financeiro?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "SIM",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                exportDB();
                                String msg = "Relatorio foi salvo com Sucesso";
                                Toast.makeText(view.getContext(), msg, Toast.LENGTH_LONG).show();
                                dialog.cancel();
                            }
                        });

                builder1.setNegativeButton(
                        "NÃO",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });
    }

    private void exportDB() {
        DBHelper dbhelper = new DBHelper(getContext());
        File exportDir = new File(Environment.getExternalStorageDirectory(), "");
        if (!exportDir.exists())
        {
            exportDir.mkdirs();
        }

        File file = new File(exportDir, "csvname.csv");
        try
        {
            file.createNewFile();
            CSVWriter csvWrite = new CSVWriter(new FileWriter(file));
            SQLiteDatabase db = dbhelper.getReadableDatabase();
            Cursor curCSV = db.rawQuery("SELECT * FROM finance",null);
            csvWrite.writeNext(curCSV.getColumnNames());
            while(curCSV.moveToNext())
            {
                //Which column you want to exprort
                String arrStr[] ={curCSV.getString(0),curCSV.getString(1), curCSV.getString(2)};
                csvWrite.writeNext(arrStr);
            }
            csvWrite.close();
            curCSV.close();
        }
        catch(Exception sqlEx)
        {
            Log.e("Export", sqlEx.getMessage(), sqlEx);
            getPermission();
        }
    }

    private void getPermission(){
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
        }
    }
}