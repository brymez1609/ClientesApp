package com.example.macminiuno.clientesapp.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.macminiuno.clientesapp.Models.Cliente;
import com.example.macminiuno.clientesapp.Models.CrearCliente;

import static com.example.macminiuno.clientesapp.sqlite.ClienteSchema.ClientesEntry;
/**
 * Created by root on 5/1/18.
 */

public class ClientesDBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 6;
    public static final String db_name = "next_pay.db";

    public ClientesDBHelper(Context context) {
        super(context, db_name,null,DATABASE_VERSION);
    }

    public ClientesDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + ClientesEntry.TABLE_NAME + " ("
                + ClientesEntry.API_ID_CLIENTE + " TEXT,"
                + ClientesEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + ClientesEntry.DOCUMENTO + " TEXT NOT NULL UNIQUE,"
                + ClientesEntry.NOMBRE + " TEXT ,"
                + ClientesEntry.APELLIDO + " TEXT ,"
                + ClientesEntry.ALIAS + " TEXT ,"
                + ClientesEntry.DIRECCION + " TEXT ,"
                + ClientesEntry.CELULAR + " TEXT,"
                + ClientesEntry.TELEFONO + " TEXT,"
                + ClientesEntry.CIUDAD + " TEXT,"
                + ClientesEntry.ID_RUTA + " INTEGER NOT NULL,"
                + "UNIQUE (" + ClientesEntry._ID + "))");

        sqLiteDatabase.execSQL("CREATE TABLE " + ErroresSchema.ErroresEntry.TABLE_NAME + " ("

                + ErroresSchema.ErroresEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + ErroresSchema.ErroresEntry.CODIGO_ERROR + " TEXT ,"
                + ErroresSchema.ErroresEntry.TABLA + " TEXT ,"
                + ErroresSchema.ErroresEntry.ID_REGISTRO + " TEXT ,"
                + ErroresSchema.ErroresEntry.MENSAJE + " TEXT ,"
                + "UNIQUE (" + ClientesEntry._ID + "))");
     }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    private void datos_fict(SQLiteDatabase sqLiteDatabase){
        mockCliente(sqLiteDatabase,
                new CrearCliente("1088338011","Bryan","Gomez","BG","Cr 25 N72","3173225632","326 5886","Pereira",true,1)
                );

    }

    public long mockCliente(SQLiteDatabase db, CrearCliente cliente) {
        return db.insert(
                ClientesEntry.TABLE_NAME,
                null,
                cliente.toContentValues());
    }

    //Guardar Cliente
    public String[] saveCliente(Cliente cliente) {
        String[] exito = new String[1];
        exito[0] = "Exito";
        try {
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            sqLiteDatabase.insertOrThrow(ClientesEntry.TABLE_NAME,
                    null,
                    cliente.toContentValues());
            return exito;
        } catch (SQLiteConstraintException e) {
            //Log.d(TAG, "failure to insert word,", e);
            String[] partes = e.getMessage().split("Clientes.");
            return partes[1].split("code");
        }

    }
    //Consultar Clientes
    public Cursor getAllCliente() {
        return getReadableDatabase()
                .query(
                        ClientesEntry.TABLE_NAME,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);
    }
}
