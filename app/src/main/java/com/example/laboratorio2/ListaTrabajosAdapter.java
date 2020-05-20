package com.example.laboratorio2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laboratorio2.entidades.Trabajo;

public class ListaTrabajosAdapter extends RecyclerView.Adapter<ListaTrabajosAdapter.TrabajoViewHolder> {

    Trabajo[] listaTrabajos;
    Context context;
    public ListaTrabajosAdapter(Trabajo[] lista, Context c){
        this.listaTrabajos= lista;
        this.context = c;
    }

    public static class TrabajoViewHolder extends RecyclerView.ViewHolder{
        TextView textView;

        public TrabajoViewHolder( View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.itemtrabajos);
        }
    }
    @NonNull
    @Override
    public TrabajoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(context).inflate(R.layout.item_rvtra, parent, false);
            TrabajoViewHolder trabajoViewHolder = new TrabajoViewHolder(view);
        return trabajoViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TrabajoViewHolder holder, int position) {
        Trabajo t = listaTrabajos[position];
        String texto = t.getJobTitle() + " " + t.getMaxSalary() ;
        holder.textView.setText(texto);
    }

    @Override
    public int getItemCount() {
        return listaTrabajos.length;
    }
}
