package com.morales.labo7t;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.morales.labo7t.Datos.estudiante;

import java.util.ArrayList;

/**
 * Created by Karla on 25/05/2018.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {

    private ArrayList<estudiante> estudiantes;
    private ArrayList<estudiante> estudianteArrayList;






    public static class RecyclerViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        Context context;
        public TextView textViewName;
        public TextView textViewCarnet;
        public TextView notas;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardview);
            context = itemView.getContext();
            textViewName = itemView.findViewById(R.id.nombreCardView);
            textViewCarnet = itemView.findViewById(R.id.carnetCardView);
            notas = itemView.findViewById(R.id.notaCardView);
        }
    }

    public RecyclerAdapter(ArrayList<estudiante> estudiantes){
        this.estudiantes = estudiantes;
        //this.mContext = mContext;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_notas, parent, false);

        return new RecyclerViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, final int position) {

        holder.textViewName.setText(estudiantes.get(position).getNombre());
        holder.textViewCarnet.setText(estudiantes.get(position).getCarnet());
        holder.notas.setText(estudiantes.get(position).getNota());
       // holder.textViewAddress.setText(estudiantes.get(position).getAddress());
    }

    @Override
    public int getItemCount() {
        return estudiantes.size();

    }
}
