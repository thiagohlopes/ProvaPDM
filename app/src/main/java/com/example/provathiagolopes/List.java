package com.example.provathiagolopes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.provathiagolopes.RecyclerView.FinanceAdapter;
import com.example.provathiagolopes.SQLite.DAOFinance;
import com.example.provathiagolopes.SQLite.Finance;

import java.util.ArrayList;

public class List extends Fragment {

    private RecyclerView rv_finance;
    private FinanceAdapter financeAdapter;
    private ArrayList<Finance> finances;

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
        finances= DAOFinance.getAllPizzas(getContext());

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        this.rv_finance.setLayoutManager(layoutManager);
        this.rv_finance.setHasFixedSize(true);
        this.financeAdapter = new FinanceAdapter(finances, getActivity());
        this.rv_finance.setAdapter(this.financeAdapter);
    }
}