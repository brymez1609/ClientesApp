package com.example.macminiuno.clientesapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.macminiuno.clientesapp.Models.Cliente;
import com.example.macminiuno.clientesapp.Models.CrearCliente;
import com.example.macminiuno.clientesapp.Models.IdRuta;
import com.example.macminiuno.clientesapp.api_service.ClienteApiService;
import com.example.macminiuno.clientesapp.retrofit.Utils.ClienteAPIUtils;
import com.example.macminiuno.clientesapp.sqlite.ClientesDBHelper;
import com.example.macminiuno.clientesapp.sqlite.adapters.ClienteSQLiteAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Cliente" ;
    private ClienteApiService clienteApiService;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ClienteAdapter clienteAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Button Btn_crear_cliente;
    private Toast toast;
    private ListView mListClientes;
    private ClienteSQLiteAdapter mClienteSQLiteAdapter;
    private ClientesDBHelper mClientesDBHelper;
    private List<Cliente> clientes_API = new ArrayList<Cliente>();
    SharedPreferences shared_preferences;
    String ruta = "-1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        clienteApiService = ClienteAPIUtils.getClienteApiService();
        Btn_crear_cliente = findViewById(R.id.btn_crear_cliente);
        mListClientes = findViewById(R.id.lv_clientes);
        mClienteSQLiteAdapter = new ClienteSQLiteAdapter(getApplicationContext(),null,0);
        Btn_crear_cliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CrearClienteActivity.class));
            }
        });


        shared_preferences = getSharedPreferences("shared_preferences",
                MODE_PRIVATE);
        ruta = shared_preferences.getString("ruta", "Default");

        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipeLayout);
        //obtenerDatos();
        //obtenerClientesDB();
        obtenerClientesAPI();
        swipeRefreshLayout.setColorSchemeResources(R.color.cardview_shadow_end_color, R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                //obtenerDatos();
                obtenerClientesAPI();
                sincronizar();

            }
        });

        Log.e("netHabilitada", Boolean.toString(isNetDisponible()));
        Log.e("accInternet",   Boolean.toString(isOnlineNet()));

       // toast =  Toast.makeText(getApplicationContext(),Boolean.toString(isNetDisponible()),Toast.LENGTH_LONG);
       // toast.show();

       // toast = Toast.makeText(getApplicationContext(),Boolean.toString(isOnlineNet()),Toast.LENGTH_LONG);
       // toast.show();

    }

    private void sincronizar() {
        mClientesDBHelper = new ClientesDBHelper(getApplicationContext());
        List<Cliente> clientes_DB = db_to_list_clientes();
        Boolean existe = false;
        for(int i=0;i < clientes_API.size();i++){
           for(int j=0;j<clientes_DB.size();j++){
               if(clientes_API.get(i).getDocumento().equals(clientes_DB.get(j).getDocumento())){

               } else {
                   mClientesDBHelper.saveCliente(clientes_API.get(i));
               }
           }
            if(clientes_DB.size()==0)
                mClientesDBHelper.saveCliente(clientes_API.get(i));
        }

        for(int i=0;i < clientes_DB.size();i++){
            for(int j=0;j<clientes_API.size();j++){
                if(clientes_DB.get(i).getDocumento().equals(clientes_API.get(j).getDocumento())){

                } else {
                    guardarDatosAPI(clientes_DB.get(i));
                }
                if(clientes_API.size()==0)
                    guardarDatosAPI(clientes_DB.get(i));
            }
        }
        mListClientes.setAdapter(mClienteSQLiteAdapter);
        cargar_clientes();
    }

    private boolean isNetDisponible() {

        ConnectivityManager connectivityManager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo actNetInfo = connectivityManager.getActiveNetworkInfo();

        return (actNetInfo != null && actNetInfo.isConnected());
    }

    public Boolean isOnlineNet() {

        try {
            Process p = java.lang.Runtime.getRuntime().exec("ping -c 1 www.google.es");

            int val           = p.waitFor();
            boolean reachable = (val == 0);
            return reachable;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }


    private void cargar_clientes() {
        new ClientesLoadTask().execute();
    }

    private class ClientesLoadTask extends AsyncTask<Void, Void, Cursor> {

        @Override
        protected Cursor doInBackground(Void... voids) {
            return mClientesDBHelper.getAllCliente();
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            if (cursor != null && cursor.getCount() > 0) {
                mClienteSQLiteAdapter.swapCursor(cursor);
            } else {
                // Mostrar empty state
            }

        }
    }

    private void obtenerClientesAPI(){
        clienteApiService.getListClientes(ruta).enqueue(new Callback<List<Cliente>>() {
            @Override
            public void onResponse(Call<List<Cliente>> call, Response<List<Cliente>> response) {
                if (response.isSuccessful()){
                    clientes_API = response.body();
                    /*
                    for (int i = 0;i<clientes.size();i++){
                        Cliente cliente = clientes.get(i);
                        try {

                            //mClientesDBHelper = new ClientesDBHelper(getApplicationContext());
                            //mClientesDBHelper.saveCliente(cliente);
                        } catch (Exception e){
                            Log.e(TAG, "Error " + e.getMessage());
                        }
                        Log.i(TAG, "Cliente " + cliente.getIdCliente());
                    }
                   // guardarDatosAPI();
                   */

                } else {
                    Log.e(TAG, "onResponse " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<Cliente>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });

    }



    private void guardarDatosAPI(Cliente cliente) {
        clienteApiService.saveCliente(cliente.getDocumento(),cliente.getNombre(),cliente.getApellido(),cliente.getAlias(),cliente.getDireccion(),cliente.getCelular(),cliente.getTelefono(),cliente.getCiudad(),true,cliente.getIdRuta().getIdRuta())
            .enqueue(new Callback<CrearCliente>() {
                @Override
                public void onResponse(Call<CrearCliente> call, Response<CrearCliente> response) {
                    if (response.isSuccessful()){
                        Log.i(TAG, "onResponse " + response.body());
                    } else {
                        Log.e(TAG, "onResponse " + response.errorBody());
                    }
                }
                @Override
                public void onFailure(Call<CrearCliente> call, Throwable t) {
                    Log.e(TAG, "onFailure: " + t.getMessage());

                }
            });

    }
    /*
    private void guardarDatosAPI() {
        mClientesDBHelper = new ClientesDBHelper(getApplicationContext());
        Integer cont = 0;
        Cursor cursor = mClientesDBHelper.getReadableDatabase().query("Clientes", null, null, null, null, null, null);
        if (cursor.moveToFirst()){
            do {
                cont = cont+1;
                clienteApiService.saveCliente(cursor.getString(2), cursor.getString(3),
                        cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7),
                        cursor.getString(8), cursor.getString(9), true, cursor.getInt(10))
                        .enqueue(new Callback<CrearCliente>() {
                            @Override
                            public void onResponse(Call<CrearCliente> call, Response<CrearCliente> response) {
                                if (response.isSuccessful()){
                                    Log.i(TAG, "onResponse " + response.body());
                                } else {
                                    Log.e(TAG, "onResponse " + response.errorBody());
                                }
                            }
                            @Override
                            public void onFailure(Call<CrearCliente> call, Throwable t) {
                                Log.e(TAG, "onFailure: " + t.getMessage());

                            }
                        });
            } while(cursor.moveToNext());
        }
        cursor.close();
    }
    */
    private void obtenerDatos() {

        clienteApiService.getListClientes(ruta).enqueue(new Callback<List<Cliente>>() {
            @Override
            public void onResponse(Call<List<Cliente>> call, Response<List<Cliente>> response) {
                if (response.isSuccessful()){
                    clienteAdapter = new ClienteAdapter(getApplicationContext());
                    recyclerView.setAdapter(clienteAdapter);
                    recyclerView.setHasFixedSize(true);
                    layoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(layoutManager);
                    List<Cliente> listar_clientes = response.body();
                    clienteAdapter.adicionarListaClientes((ArrayList<Cliente>) listar_clientes);
                    for (int i = 0;i<listar_clientes.size();i++){
                        Cliente cliente = listar_clientes.get(i);
                        Log.i(TAG, "Cliente " + cliente.getIdCliente());
                    }
                }else {
                    Log.e(TAG, "onResponse " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<Cliente>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    private List<Cliente> db_to_list_clientes() {
        Cursor cursor = mClientesDBHelper.getReadableDatabase().query("Clientes", null, null, null, null, null, null);
        ArrayList<Cliente> resultList = new ArrayList<Cliente>();
        while (cursor.moveToNext())
        {
            try
            {
                IdRuta idRuta = new IdRuta(Integer.parseInt(ruta),"","",false,"","",false,false,false);
                Cliente cliente = new Cliente(idRuta, cursor.getString(2),
                        cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6),
                        cursor.getString(7), cursor.getString(8), cursor.getString(9),true);
                resultList.add(cliente);
            }
            catch (Exception e) {
                Log.e(TAG, "Error " + e.toString());
            }
        }
        cursor.close();
        return resultList;
    }
}
