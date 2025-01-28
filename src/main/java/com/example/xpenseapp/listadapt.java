package com.example.xpenseapp;




import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class listadapt extends ArrayAdapter<String> {
    private final Context context;
    private final ArrayList<String> expenses;
    private final ArrayList<String> amount;



    public listadapt(@NonNull Context context, ArrayList<String> values, ArrayList<String>amount) {
        super(context, R.layout.expense_item, values);
        this.context = context;
        this.expenses = values;
        this.amount = amount;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.expense_item, parent, false);
        TextView txt1 = (TextView) rowView.findViewById(R.id.categitem);
        TextView txt2 = (TextView) rowView.findViewById(R.id.expamount);

        txt1.setText(expenses.get(position));
        txt2.setText(amount.get(position));

        // Change icon based on name
        String s = expenses.get(position);
        String f= amount.get(position);

        System.out.println(s);

        return rowView;
    }
}
