package com.example.macminiuno.clientesapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.macminiuno.clientesapp.Models.RutaCliente;
import com.example.macminiuno.clientesapp.api_service.RutaApiService;
import com.example.macminiuno.clientesapp.retrofit.Utils.RutaClienteAPIUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "ClienteRuta";
    private TextView txt_imei;
    private Button btn_login;
    private TextInputEditText ed_clave;
    private RutaApiService rutaApiService;
    private String mClave = " ";
    private Toast toast;
    private String myIMEI = " ";
    SharedPreferences shared_preferences;
    SharedPreferences.Editor shared_preferences_editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txt_imei = findViewById(R.id.txt_imei);
        int permissionCheck = ContextCompat.checkSelfPermission(
                this, Manifest.permission.READ_PHONE_STATE );
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            Log.i("Mensaje", "No se tiene permiso.");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE }, 225);
            txt_imei.setText("Permiso denegado");
        } else {
            Log.i("Mensaje", "Se tiene permiso!");
            myIMEI = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
            txt_imei.setText(myIMEI);

        }
        shared_preferences = getSharedPreferences("shared_preferences",
                MODE_PRIVATE);
        Integer login =  shared_preferences.getInt("login", 0);
        if(login == 1){
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        }
        rutaApiService = RutaClienteAPIUtils.getRutaClienteApiService();
        btn_login = findViewById(R.id.btn_login);
        ed_clave = findViewById(R.id.txt_clave);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ed_clave.getText().toString().isEmpty()) {
                    toast = Toast.makeText(getApplicationContext(), "Ingrese una clave", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    mClave = ed_clave.getText().toString();
                    login();
                }
            }
        });
    }




    private void login() {
        rutaApiService.VerificarClientes(""+txt_imei.getText().toString(),""+mClave).enqueue(new Callback<RutaCliente>() {
            @Override
            public void onResponse(Call<RutaCliente> call, Response<RutaCliente> response) {
                if (response.isSuccessful()){

                    if(response.body().getRuta() != null){
                        //Log.i(TAG,response.body().getError().toString());
                        toast = Toast.makeText(getApplicationContext(),response.body().getRuta(),Toast.LENGTH_SHORT);
                        toast.show();

                        shared_preferences = getSharedPreferences("shared_preferences",
                                MODE_PRIVATE);

                        shared_preferences_editor = shared_preferences.edit();
                        shared_preferences_editor.putString("ruta", response.body().getRuta());
                        shared_preferences_editor.putString("cod_dispostivo", myIMEI);
                        shared_preferences_editor.putString("clave", mClave);
                        shared_preferences_editor.putInt("login", 1);
                        shared_preferences_editor.apply();

                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                    }else{
                        //Log.i(TAG,response.body().getRuta().toString());
                        toast = Toast.makeText(getApplicationContext(),response.body().getError(),Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    Log.i(TAG," "+response.body());
                }else {
                    Log.i(TAG," "+response.body());
                }
            }

            @Override
            public void onFailure(Call<RutaCliente> call, Throwable t) {
                Log.i(TAG," "+t.getMessage());
            }
        });
    }


}
