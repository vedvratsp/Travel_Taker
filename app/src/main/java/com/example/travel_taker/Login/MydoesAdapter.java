package com.example.travel_taker.Login;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travel_taker.R;

import java.util.ArrayList;

public class MydoesAdapter extends RecyclerView.Adapter<MydoesAdapter.MyViewHolder> {
    Context context;
    ArrayList<Mydoes> myDoes;

    public MydoesAdapter(Context c, ArrayList<Mydoes> p){
        context = c;
        myDoes = p;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_todos,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.titledoes.setText(myDoes.get(position).getTitledoes());
        holder.descdoes.setText(myDoes.get(position).getDescdoes());
        holder.datedoes.setText(myDoes.get(position).getDatedoes());

        final String getTitleDoes = myDoes.get(position).getTitledoes();
        final String getDescDoes = myDoes.get(position).getDescdoes();
        final String getDateDoes = myDoes.get(position).getDatedoes();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, MydoesUpdate.class);
                i.putExtra("titledoes", getTitleDoes);
                i.putExtra("descdoes", getDescDoes);
                i.putExtra("datedoes", getDateDoes);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return myDoes.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView titledoes,descdoes,datedoes;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titledoes = itemView.findViewById(R.id.todoTitle);
            descdoes = itemView.findViewById(R.id.todoSubtitle);
            datedoes = itemView.findViewById(R.id.todoTime);

        }
    }

}
