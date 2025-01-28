package com.example.xpenseapp;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class homepageadapt extends RecyclerView.Adapter<homepageadapt.homepageadaptViewHolder> {
    private final RecyclerviewInterface recyclerviewInterface;
    private List<Integer> L_Image;
    private List<String> L_Text;


    //constructor
    public homepageadapt(List<String> Ltext , List<Integer> Limage,RecyclerviewInterface recyclerviewInterface)
    {
        this.L_Image= Limage;
        this.L_Text = Ltext;
        this.recyclerviewInterface = recyclerviewInterface;
    }

    @Override
    public homepageadaptViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new homepageadaptViewHolder(v,recyclerviewInterface).linkadapter(this);
    }

    @Override
    public void onBindViewHolder(homepageadaptViewHolder holder, int position) {
        holder.mTextView2.setText(L_Text.get(position));
        holder.tImageview.setImageResource(L_Image.get(position));


    }

    @Override
    public int getItemCount() {
        return L_Text.size();
    }

    public class homepageadaptViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView2;
        ImageView tImageview;
        private homepageadapt adapter;

        public homepageadaptViewHolder(View itemView,RecyclerviewInterface recyclerviewInterface) {
            super(itemView);
            mTextView2 = itemView.findViewById(R.id.textView7);
            tImageview =itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(recyclerviewInterface!=null){
                        int pos = getAdapterPosition();
                        if(pos!=RecyclerView.NO_POSITION){
                            recyclerviewInterface.onItemClickListener(pos);
                        }
                    }
                }
            });
        }

        public homepageadaptViewHolder linkadapter(homepageadapt adapter){
            this.adapter=adapter;
            return this;

        }


    }


}

