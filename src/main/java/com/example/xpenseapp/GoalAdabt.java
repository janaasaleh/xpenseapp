package com.example.xpenseapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GoalAdabt extends RecyclerView.Adapter<GoalAdabt.goalViewHolder> {
    private List<String> L_Text;
    private List<String> amount;
    private List<String> money;
    private List<String> mon;
    private List<String> target;
    int counter=0;

    public GoalAdabt(List<String> Ltext , List<String> amount, List<String> money, List<String> mon,  List<String> target )
    {
        this.amount= amount;
        this.money= money;
        this.mon= mon;
        this.target= target;
        this.L_Text = Ltext;
    }

    @Override
    public goalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.goalview, parent, false);
        return new goalViewHolder(v).linkadapter2(this);

    }

    @Override
    public void onBindViewHolder(goalViewHolder holder, int position) {
        holder.TextView3.setText(L_Text.get(position));
        holder.amountsaved.setText(amount.get(position));
        holder.money1.setText(money.get(position));
        holder.total.setText(target.get(position));
        holder.money2.setText(mon.get(position));
    }

    @Override
    public int getItemCount() {
        return L_Text.size();
    }

    public class goalViewHolder extends RecyclerView.ViewHolder {
        TextView TextView3;
        TextView amountsaved;
        TextView money1;
        TextView total;
        TextView money2;
        Button save;
        private GoalAdabt adapter1;

        public goalViewHolder(View itemView) {
            super(itemView);
            TextView3 = itemView.findViewById(R.id.textView3);
            amountsaved =itemView.findViewById(R.id.textView5);
            money1 =itemView.findViewById(R.id.textView6);
            total =itemView.findViewById(R.id.textView11);
            money2 =itemView.findViewById(R.id.textView8);
            save=itemView.findViewById(R.id.S);
        }

        public goalViewHolder linkadapter2(GoalAdabt adapterx){
            this.adapter1=adapterx;
            return this;

        }
    }

}