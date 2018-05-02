package com.example.macminiuno.clientesapp.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.macminiuno.clientesapp.Models.Cliente;
import com.example.macminiuno.clientesapp.Models.CrearCliente;

import static com.example.macminiuno.clientesapp.sqlite.ClienteSchema.ClientesEntry;
/**
 * Created by root on 5/1/18.
 */

public class ClientesDBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
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
                + ClientesEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + ClientesEntry.ID + " TEXT NOT NULL,"
                + ClientesEntry.DOCUMENTO + " TEXT NOT NULL,"
                + ClientesEntry.NOMBRE + " TEXT NOT NULL,"
                + ClientesEntry.APELLIDO + " TEXT NOT NULL,"
                + ClientesEntry.ALIAS + " TEXT NOT NULL,"
                + ClientesEntry.DIRECCION + " TEXT NOT NULL,"
                + ClientesEntry.CELULAR + " TEXT,"
                + ClientesEntry.TELEFONO + " TEXT,"
                + ClientesEntry.CIUDAD + " TEXT,"
                + ClientesEntry.ID_RUTA + " INTEGER NOT NULL,"
                + "UNIQUE (" + ClientesEntry.ID + "))");


        datos_fict(sqLiteDatabase);
    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    private void datos_fict(SQLiteDatabase sqLiteDatabase){
        mockCliente(sqLiteDatabase,
                new CrearCliente(1,"1088338011","Bryan","Gomez","BG","Cr 25 N72","3173225632","326 5886","Pereira",true,1)
                );

    }

    public long mockCliente(SQLiteDatabase db, CrearCliente cliente) {
        return db.insert(
                ClientesEntry.TABLE_NAME,
                null,
                cliente.toContentValues());
    }

    //Guardar Cliente
    public long saveCliente(CrearCliente cliente) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        return sqLiteDatabase.insert(
                ClientesEntry.TABLE_NAME,
                null,
                cliente.toContentValues());

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
