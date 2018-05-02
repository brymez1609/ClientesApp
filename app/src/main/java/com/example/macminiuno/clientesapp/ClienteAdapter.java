package com.example.macminiuno.clientesapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.macminiuno.clientesapp.Models.Cliente;
import com.example.macminiuno.clientesapp.api_service.ClienteApiService;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by macminiuno on 5/03/18.
 */

public class ClienteAdapter extends RecyclerView.Adapter<ClienteAdapter.ViewHolder> {

    private ArrayList<Cliente> dataset;
    private Context context;
    private ClienteApiService clienteApiService;

    public ClienteAdapter(Context context) {
        this.context = context;
        dataset = new ArrayList<>();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cliente, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Cliente p = dataset.get(position);
        holder.Txt_nombre_cliente.setText(p.getNombre());
        holder.Txt_alias.setText(p.getAlias());
        holder.Txt_ciudad.setText(p.getCiudad());
        holder.Txt_ruta.setText(p.getIdRuta().getNombre());
        holder.Txt_n_identificacion.setText(p.getDocumento());
        //Toast.makeText(context, holder.nombreTextView.getText(), Toast.LENGTH_SHORT).show();
        //Bundle bundle = holder.nombreTextView.getInputExtras(true).getBundle(String.valueOf(p.getIdestanque()));
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void adicionarListaClientes (ArrayList<Cliente> listaCliente) {
        dataset.addAll(listaCliente);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //private ImageView fotoImageView;
        private TextView Txt_nombre_cliente;
        private TextView Txt_alias;
        private TextView Txt_ciudad;
        private TextView Txt_ruta;
        private TextView Txt_n_identificacion;


        public ViewHolder(final View itemView) {
            super(itemView);
            CardView cardView = (CardView) itemView.findViewById(R.id.cv_cliente);
            Txt_nombre_cliente = (TextView) itemView.findViewById(R.id.txt_nombre_cliente);
            Txt_alias = (TextView) itemView.findViewById(R.id.txt_alias);
            Txt_ciudad = (TextView) itemView.findViewById(R.id.txt_ciudad);
            Txt_ruta = (TextView) itemView.findViewById(R.id.txt_ruta);
            Txt_n_identificacion = (TextView) itemView.findViewById(R.id.txt_n_identifiacion);
        }
    }
}
