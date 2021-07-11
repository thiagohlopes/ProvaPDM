package com.example.provathiagolopes.Singleton;

import android.content.Context;
import android.util.Log;

import com.example.provathiagolopes.SQLite.Finance;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FileManager {
    String nome = "arquivo"+".csv";
    private final String FILENAME = nome;
    private File file;

    FileManager(Context context) {
        this.file = new File(context.getFilesDir(), FILENAME);
    }

    private void write(String stream) throws IOException {
        BufferedWriter bfwriter = new BufferedWriter(new FileWriter(this.file, true));
        bfwriter.write(stream);
        bfwriter.close();
    }

    private ArrayList<Finance> read() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(this.file));
        ArrayList<Finance> finances = new ArrayList<>();
        String line;
        while((line = reader.readLine()) != null) {
            finances.add(this.csvRowToFinance(line));
        }
        reader.close();
        return finances;
    }

    private Finance csvRowToFinance(String row) {
        String[] transStr = row.split(";");
        String nome = transStr[0];
        Double price = Double.parseDouble(transStr[1]);
        return new Finance(price, nome);
    }

    private String financeToCsvRow(Finance finance) {
        return finance.getName()+";"+finance.getPrice().toString()+";"+ finance.getType().toString()+"\n";
    }

    protected void save(Finance finance) throws IOException {
        String row = this.financeToCsvRow(finance);
        this.write(row);
    }

    protected ArrayList<Finance> load() throws IOException {
        return this.read();
    }
}
