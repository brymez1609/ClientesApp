package com.example.macminiuno.clientesapp.retrofit.Utils;

import com.example.macminiuno.clientesapp.api_service.ClienteApiService;
import com.example.macminiuno.clientesapp.retrofit.RetrofitCliente;

/**
 * Created by macminiuno on 5/03/18.
 */

public class ClienteAPIUtils {
    public ClienteAPIUtils() {
    }
    //public static final String BASE_URL = "http://192.168.0.22:8000/";
    //public static final String BASE_URL = "https://sip-adsi.herokuapp.com/";
    //public static final String BASE_URL = "http://192.168.1.86:8001/";
    public static final String BASE_URL = "https://nx-next-pay.herokuapp.com/";
    //public static final String BASE_URL = "http://10.4.20.103:8000/";

    public static ClienteApiService getClienteApiService(){
        return RetrofitCliente.getCliente(BASE_URL).create(ClienteApiService.class);
    }
}
