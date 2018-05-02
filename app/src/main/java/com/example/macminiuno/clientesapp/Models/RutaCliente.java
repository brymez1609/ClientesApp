package com.example.macminiuno.clientesapp.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RutaCliente {

    @SerializedName("imei")
    @Expose
    private String imei;
    @SerializedName("clave")
    @Expose
    private String clave;
    @SerializedName("error")
    @Expose
    private String error;
    @SerializedName("ruta")
    @Expose
    private String ruta;

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

}