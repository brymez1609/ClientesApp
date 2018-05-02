package com.example.macminiuno.clientesapp.sqlite;

import android.provider.BaseColumns;

/**
 * Created by root on 5/1/18.
 *
 * Esquema del modelo Clientes
 */

public class ClienteSchema {

    public static abstract class ClientesEntry implements BaseColumns {
        public static final String TABLE_NAME ="Clientes";

        public static final String ID = "idCliente";
        public static final String DOCUMENTO = "documento";
        public static final String NOMBRE = "nombre";
        public static final String APELLIDO = "apellido";
        public static final String ALIAS = "alias";
        public static final String DIRECCION = "dirección";
        public static final String CELULAR = "celular";
        public static final String TELEFONO = "telefono";
        public static final String CIUDAD = "ciudad";
        public static final String ID_RUTA = "idRuta";

    }
}
