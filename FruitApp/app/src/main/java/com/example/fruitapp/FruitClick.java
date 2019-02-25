package com.example.fruitapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class FruitClick extends RecyclerView.Adapter<FruitClick.Holder> {

    private Context mContext;
    private List<Fruits> fruits;

    public FruitClick(List<Fruits> fruits,Context mContext){
        this.fruits=fruits;
        this.mContext=mContext;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_items,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Holder hold, int position) {
        final Fruits fposition=fruits.get(position);
        hold.textView.setText(fposition.getType());
        hold.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(hold.itemView.getContext(),FruitSecond.class);
                intent.putExtra("type", fposition.getType());
                intent.putExtra("price", fposition.getPrice());
                intent.putExtra("weight", fposition.getWeight());
                hold.itemView.getContext().startActivity(intent);
            }

        });

    }

    @Override
    public int getItemCount() {
        return fruits.size();}
        class Holder extends RecyclerView.ViewHolder{
        private CardView cardView;
        private TextView textView;

        Holder (View itemView){
            super(itemView);

            cardView=itemView.findViewById(R.id.card);
            textView=itemView.findViewById(R.id.type);
        }
    }
}
