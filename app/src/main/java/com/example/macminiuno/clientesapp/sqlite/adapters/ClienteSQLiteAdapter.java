package com.example.macminiuno.clientesapp.sqlite.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.macminiuno.clientesapp.R;
import com.example.macminiuno.clientesapp.sqlite.ClienteSchema;

/**
 * Created by root on 5/1/18.
 */

public class ClienteSQLiteAdapter extends CursorAdapter {


    public ClienteSQLiteAdapter(Context context, Cursor c, int flags) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        return layoutInflater.inflate(R.layout.item_cliente, viewGroup,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView nombre_txt = (TextView)view.findViewById(R.id.txt_nombre_cliente);
        TextView alias_txt = (TextView)view.findViewById(R.id.txt_alias);
        TextView ciudad_txt = (TextView)view.findViewById(R.id.txt_ciudad);
        TextView ruta_txt = (TextView)view.findViewById(R.id.txt_ruta);
        TextView numero_identificacion_txt = (TextView)view.findViewById(R.id.txt_n_identifiacion);

        String nombre_db = cursor.getString(cursor.getColumnIndex(ClienteSchema.ClientesEntry.NOMBRE));
        String alias_db = cursor.getString(cursor.getColumnIndex(ClienteSchema.ClientesEntry.ALIAS));
        String ciudad_db = cursor.getString(cursor.getColumnIndex(ClienteSchema.ClientesEntry.CIUDAD));
        String ruta_db = cursor.getString(cursor.getColumnIndex(ClienteSchema.ClientesEntry.ID_RUTA));
        String numero_identificacion_db = cursor.getString(cursor.getColumnIndex(ClienteSchema.ClientesEntry.DOCUMENTO));

        nombre_txt.setText(nombre_db);
        alias_txt.setText(alias_db);
        ciudad_txt.setText(ciudad_db);
        ruta_txt.setText(ruta_db);
        numero_identificacion_txt.setText(numero_identificacion_db);
    }

}
