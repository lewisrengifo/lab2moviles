package com.example.laboratorio2;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laboratorio2.entidades.Empleado;

public class ListaEmpleadosAdapter extends RecyclerView.Adapter<ListaEmpleadosAdapter.EmpleadoViewHolder> {

    Empleado[] listaEmpleados;
    Context contexto;

    public ListaEmpleadosAdapter(Empleado[] lista, Context c){
        this.listaEmpleados = lista;
        this.contexto = c;

    }

    public static class EmpleadoViewHolder extends  RecyclerView.ViewHolder{
        TextView textView;

        public EmpleadoViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView3);
        }
    }

    @NonNull
     @Override
   public EmpleadoViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
    View item = LayoutInflater.from(contexto).inflate(R.layout.item_rv,parent, false);
    EmpleadoViewHolder empleadoViewHolder = new EmpleadoViewHolder(item);
    return empleadoViewHolder;
    }

    @Override
    public void onBindViewHolder(EmpleadoViewHolder holder, int position) {

        Empleado e = listaEmpleados[position];

        if (e.getCreatedBy().equals("grupo_4")) {

            String texto = e.getEmployeeId()+" " +e.getFirstName() + " " + e.getLastName() + " "
                    + e.getEmail() + " " + e.getPhoneNumber() + " " + String.valueOf(e.getSalary()) + " "
                    + String.valueOf(e.getCommissionPct()) ;
            holder.textView.setText(texto);
        } else {
            String texto = " ";
            holder.textView.setText(texto);
        }
    }

    @Override
    public int getItemCount() {
        return listaEmpleados.length;
    }
}
