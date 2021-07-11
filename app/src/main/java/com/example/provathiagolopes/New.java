package com.example.provathiagolopes;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.provathiagolopes.RecyclerView.FinanceAdapter;
import com.example.provathiagolopes.SQLite.DAOFinance;
import com.example.provathiagolopes.SQLite.Finance;
import com.example.provathiagolopes.Singleton.DAOSingleton;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class New extends Fragment implements AdapterView.OnItemClickListener{
//    implements AdapterView.OnItemClickListener
    Button btn_save;
    TextView etxt_name;
    TextView etxt_price;
    private Activity activity;
    NumberFormat formatter = new DecimalFormat("#0.00");

    public static New newInstance() {
        return new New();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.activity = getActivity();
        Spinner spinner = view.findViewById(R.id.sp_type);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
//        spinner.setOnItemClickListener(this);

        btn_save = view.findViewById(R.id.btn_save);
        etxt_name = view.findViewById(R.id.etxt_name);
        etxt_price = view.findViewById(R.id.etxt_price);
        this.activity = getActivity();
        this.btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String priceStr = etxt_price.getText().toString();
                String choice = spinner.getSelectedItem().toString();
                String nome = etxt_name.getText().toString();
                if(priceStr.isEmpty() || nome.isEmpty()){
                    String msg = "Tente novamente, encontramos campos vazio!";
                    Toast.makeText(view.getContext(), msg, Toast.LENGTH_LONG).show();
                    return;
                }else {
                    Double price = Double.parseDouble(priceStr);
                    if(choice.equals("Débito")){
                        price = price * -1;
                    }
                    Finance finance = new Finance(price, nome,choice);
                    DAOFinance.insertFinance(activity, finance);
                    String msg = "Cadastrado com Sucesso";
                    Toast.makeText(view.getContext(), msg, Toast.LENGTH_LONG).show();
                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.container, new List());
                    ft.commit();
                }
//                activity.finish();
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
//        String choice = parent.getItemAtPosition(pos).toString();
//        Toast.makeText(getApplicationContext(), choice, Toast.LENGTH_LONG).show();
    }
}
