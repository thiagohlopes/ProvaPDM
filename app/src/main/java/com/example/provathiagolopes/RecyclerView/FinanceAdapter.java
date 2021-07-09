package com.example.provathiagolopes.RecyclerView;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.provathiagolopes.R;
import com.example.provathiagolopes.SQLite.Finance;

import java.util.ArrayList;

public class FinanceAdapter extends RecyclerView.Adapter<FinanceViewHolder>{

    private ArrayList<Finance> finances;
    private Activity activity;

    public FinanceAdapter(ArrayList<Finance> Finances, Activity activity) {
        this.finances = Finances;
        this.activity = activity;
    }

    @NonNull
    @Override
    public FinanceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.activity_view_holder_finance, parent, false);
        FinanceViewHolder viewHolder = new FinanceViewHolder(itemView, activity);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FinanceViewHolder holder, int position) {
        holder.bind(this.finances.get(position));
    }

    @Override
    public int getItemCount() {
        if(this.finances!=null)
            return this.finances.size();
        else return 0;
    }
}
