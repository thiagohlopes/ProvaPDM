package com.example.provathiagolopes.RecyclerView;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.provathiagolopes.R;
import com.example.provathiagolopes.SQLite.Finance;

public class FinanceViewHolder extends RecyclerView.ViewHolder {

    private TextView tv_name;
    private TextView tv_price;
    private Finance finance;
    private Activity activity;
    private ImageView iv_color;

    public FinanceViewHolder(View itemView, final Activity activity){
        super(itemView);
        this.tv_name = itemView.findViewById(R.id.tv_name);
        this.tv_price = itemView.findViewById(R.id.tv_price);
        this.iv_color = itemView.findViewById(R.id.iv_color);
        this.activity = activity;
    }

    public void bind(final Finance finance) {
        this.finance = finance;
        this.tv_name.setText(this.finance.getName());
        this.tv_price.setText(Double.toString(this.finance.getPrice()));
        if (finance.getType().toString() == "Crédito"){
            iv_color.setBackgroundColor(activity.getResources().getColor(android.R.color.holo_green_light));
        }
    }
}
