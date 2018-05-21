package com.example.macminiuno.clientesapp.sqlite;

import android.provider.BaseColumns;

public class ErroresSchema {

    public static abstract class ErroresEntry implements BaseColumns {
        public static final String TABLE_NAME ="Errores";

        // public static final String ID = "_id";
        public static final String CODIGO_ERROR = "codigo_error";
        public static final String TABLA = "tabla";
        public static final String ID_REGISTRO = "id_registro";
        public static final String MENSAJE = "mensaje";


    }
}
