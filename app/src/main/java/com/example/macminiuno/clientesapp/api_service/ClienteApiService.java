package com.example.macminiuno.clientesapp.api_service;

import com.example.macminiuno.clientesapp.Models.Cliente;
import com.example.macminiuno.clientesapp.Models.CrearCliente;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


/**
 * Created by root on 10/30/17.
 */

public interface ClienteApiService {
    //@GET("api_cliente/listar/")
    //Call<List<Cliente>> getListClientes();

    @GET("api_cliente/listar_por_ruta/{pk}")
    Call<List<Cliente>> getListClientes(@Path("pk") String pk);


    @POST("api_cliente/crear/")
    @FormUrlEncoded
    Call<CrearCliente> saveCliente(//@Field("idCliente") int id,
                                   @Field("documento") String documento,
                                   @Field("nombre") String nombre,
                                   @Field("apellido") String apellido,
                                   @Field("alias") String alias,
                                   @Field("direccion") String direccion,
                                   @Field("celular") String celular,
                                   @Field("telefono") String telefono,
                                   @Field("ciudad") String ciudad,
                                   @Field("estado") Boolean estado,
                                   @Field("idRuta") int idRuta
    );
}
