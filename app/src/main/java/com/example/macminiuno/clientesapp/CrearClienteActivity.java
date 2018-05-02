package com.example.macminiuno.clientesapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.macminiuno.clientesapp.Models.Cliente;
import com.example.macminiuno.clientesapp.Models.CrearCliente;
import com.example.macminiuno.clientesapp.api_service.ClienteApiService;
import com.example.macminiuno.clientesapp.retrofit.Utils.ClienteAPIUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CrearClienteActivity extends AppCompatActivity {
    private static final String TAG = "Cliente_guardado";
    private Button Btn_guardar_cliente;
    private ClienteApiService clienteApiService;
    SharedPreferences shared_preferences;

    private TextInputEditText Txt_input_n_identificacion;
    private TextInputEditText Txt_input_nombre;
    private TextInputEditText Txt_input_apellido;
    private TextInputEditText Txt_input_alias;
    private TextInputEditText Txt_input_direccion_local;
    private TextInputEditText Txt_input_celular;
    private TextInputEditText Txt_input_telefono;
    private TextInputEditText Txt_input_ciudad;


    String n_identificacion,nombre,apellido,alias,direccion_local,celular,telefono,ciudad;
    String ruta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cliente);

        clienteApiService = ClienteAPIUtils.getClienteApiService();


        Txt_input_n_identificacion = findViewById(R.id.input_n_identificacion);
        Txt_input_nombre = findViewById(R.id.input_nombre);
        Txt_input_apellido = findViewById(R.id.input_apellido);
        Txt_input_alias = findViewById(R.id.input_apodo);
        Txt_input_direccion_local = findViewById(R.id.input_direccion_local);
        Txt_input_celular = findViewById(R.id.input_celular);
        Txt_input_telefono = findViewById(R.id.input_telefono);
        Txt_input_ciudad = findViewById(R.id.input_ciudad);

        Txt_input_n_identificacion.setText("1088338011");
        Txt_input_nombre.setText("Pepe");
        Txt_input_apellido.setText("Ponsio");
        Txt_input_alias.setText("PP");
        Txt_input_direccion_local.setText("Cr 45 N#65-2 ");
        Txt_input_celular.setText("30021457");
        Txt_input_telefono.setText("325875");
        Txt_input_ciudad.setText("Pereira");


        Btn_guardar_cliente = (Button) findViewById(R.id.btn_guardar_cliente);
        Btn_guardar_cliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                n_identificacion=Txt_input_n_identificacion.getText().toString();
                nombre = Txt_input_nombre.getText().toString();
                apellido = Txt_input_apellido.getText().toString();
                alias = Txt_input_alias.getText().toString();
                direccion_local = Txt_input_direccion_local.getText().toString();
                celular = Txt_input_celular.getText().toString();
                telefono = Txt_input_telefono.getText().toString();
                ciudad = Txt_input_ciudad.getText().toString();

                GuardarCliente();
            }
        });

    }

    private void GuardarCliente() {
        shared_preferences = getSharedPreferences("shared_preferences",
                MODE_PRIVATE);
        ruta = shared_preferences.getString("ruta", "Default");
        clienteApiService.saveCliente(
                n_identificacion,
                nombre,
                apellido,
                alias,
                direccion_local,
                celular,
                telefono,
                ciudad,
                true,
                Integer.parseInt(ruta)
        ).enqueue(new Callback<CrearCliente>() {
            @Override
            public void onResponse(Call<CrearCliente> call, Response<CrearCliente> response) {
                if (response.isSuccessful()){
                    Toast toast = Toast.makeText(getApplicationContext(),"Guardado con exito !",Toast.LENGTH_LONG);
                    toast.show();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                } else {
                    Log.e(TAG, "onResponse " + response.errorBody());
                }
            }
            @Override
            public void onFailure(Call<CrearCliente> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
                Toast toast = Toast.makeText(getApplicationContext(),"Error en el servidor !",Toast.LENGTH_LONG);
                toast.show();
                //startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }
}
