package com.example.macminiuno.clientesapp.retrofit;

/**
 * Created by macminiuno on 5/03/18.
 */
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitCliente {
    private static Retrofit retrofit = null;

    public static Retrofit getCliente(String baseUrl){
        if (retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
