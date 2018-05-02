package com.example.macminiuno.clientesapp.api_service;

import com.example.macminiuno.clientesapp.Models.Cliente;
import com.example.macminiuno.clientesapp.Models.RutaCliente;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by macminiuno on 7/03/18.
 */

public interface RutaApiService {
    @POST("api_cliente/verificar_ruta/")
    @FormUrlEncoded
    Call<RutaCliente> VerificarClientes(
          @Field("imei") String imei,
          @Field("clave") String clave
    );

}
