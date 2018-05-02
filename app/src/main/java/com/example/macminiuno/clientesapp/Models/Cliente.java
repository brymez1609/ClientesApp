package com.example.macminiuno.clientesapp.Models;

import android.content.ContentValues;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.example.macminiuno.clientesapp.sqlite.ClienteSchema.ClientesEntry;

public class Cliente {

    @SerializedName("idCliente")
    @Expose
    private Integer idCliente;
    @SerializedName("idRuta")
    @Expose
    private IdRuta idRuta;
    @SerializedName("documento")
    @Expose
    private String documento;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("apellido")
    @Expose
    private String apellido;
    @SerializedName("alias")
    @Expose
    private String alias;
    @SerializedName("direccion")
    @Expose
    private String direccion;
    @SerializedName("celular")
    @Expose
    private String celular;
    @SerializedName("telefono")
    @Expose
    private String telefono;
    @SerializedName("ciudad")
    @Expose
    private String ciudad;
    @SerializedName("estado")
    @Expose
    private Boolean estado;

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public IdRuta getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(IdRuta idRuta) {
        this.idRuta = idRuta;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }



    @Override
    public String toString() {
        return "Cliente{" +
                "idCliente=" + idCliente +
                ", idRuta=" + idRuta +
                ", documento='" + documento + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", alias='" + alias + '\'' +
                ", direccion='" + direccion + '\'' +
                ", celular='" + celular + '\'' +
                ", telefono='" + telefono + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", estado=" + estado +
                '}';
    }
}