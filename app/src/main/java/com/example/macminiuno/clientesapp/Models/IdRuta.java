package com.example.macminiuno.clientesapp.Models;

/**
 * Created by macminiuno on 5/03/18.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IdRuta {

    @SerializedName("idRuta")
    @Expose
    private Integer idRuta;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("fecha")
    @Expose
    private String fecha;
    @SerializedName("estado")
    @Expose
    private Boolean estado;
    @SerializedName("imei")
    @Expose
    private String imei;
    @SerializedName("clave")
    @Expose
    private String clave;
    @SerializedName("permisoCreacion")
    @Expose
    private Boolean permisoCreacion;
    @SerializedName("visita")
    @Expose
    private Boolean visita;
    @SerializedName("aperturaMulti")
    @Expose
    private Boolean aperturaMulti;

    public Integer getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(Integer idRuta) {
        this.idRuta = idRuta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

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

    public Boolean getPermisoCreacion() {
        return permisoCreacion;
    }

    public void setPermisoCreacion(Boolean permisoCreacion) {
        this.permisoCreacion = permisoCreacion;
    }

    public Boolean getVisita() {
        return visita;
    }

    public void setVisita(Boolean visita) {
        this.visita = visita;
    }

    public Boolean getAperturaMulti() {
        return aperturaMulti;
    }

    public void setAperturaMulti(Boolean aperturaMulti) {
        this.aperturaMulti = aperturaMulti;
    }

    @Override
    public String toString() {
        return "IdRuta{" +
                "idRuta=" + idRuta +
                ", nombre='" + nombre + '\'' +
                ", fecha='" + fecha + '\'' +
                ", estado=" + estado +
                ", imei='" + imei + '\'' +
                ", clave='" + clave + '\'' +
                ", permisoCreacion=" + permisoCreacion +
                ", visita=" + visita +
                ", aperturaMulti=" + aperturaMulti +
                '}';
    }
}