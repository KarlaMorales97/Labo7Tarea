package com.morales.labo7t.Entidades;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.morales.labo7t.Datos.estudiante;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Karla on 21/05/2018.
 */

public class DBHelper extends SQLiteOpenHelper {

    public  static final String DB_NAME="bd_usuarios";
    public  static final String TABLA_USUARIO="Estudiante";
    public  static final String CAMPO_ID="carnet";
    public  static final String CAMPO_NOMBRE="nombre";
    public  static final String CAMPO_NOTA="nota";
    public static final String  PROMEDIO = "SELECT AVG("+CAMPO_NOTA +") FROM "+TABLA_USUARIO;
    public  static final String CREAR_TABLA_USUARIO="CREATE TABLE "+ TABLA_USUARIO
            +"("+ CAMPO_ID +" TEXT,"+ CAMPO_NOMBRE + " TEXT," + CAMPO_NOTA + " TEXT)";


    public estudiante estudiantes;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAR_TABLA_USUARIO);
    }




    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CAMPO_NOMBRE);
        onCreate(db);
    }





    public static DBHelper myDB = null;
    private Context context;
    SQLiteDatabase db;

    public static DBHelper getInstance(Context ctx){
        if(myDB == null){
            myDB = new DBHelper(ctx.getApplicationContext());
        }
        return myDB;

    }
    public DBHelper(Context context){
        super(context,DB_NAME,null,1);
        this.context=context;
        db = this.getWritableDatabase();
    }


    public boolean add(estudiante P){
        ContentValues values = new ContentValues();
        values.put(CAMPO_ID, P.getCarnet());
        values.put(CAMPO_NOMBRE, P.getNombre());
        values.put(CAMPO_NOTA, P.getNota());
        db.insert(TABLA_USUARIO,null, values);
        Toast.makeText(context,P.getNombre()+"  Insertado con exito", Toast.LENGTH_SHORT).show();
        return true;
    }


    public float promedio() {

        float Avg;
        Cursor prom = db.rawQuery(PROMEDIO, null);
        prom.moveToFirst();


        Avg =(prom.getFloat(0));

        prom.close();


        return Avg;
    }

    public estudiante findUser(String carnet){
        estudiante p;
        String[] parametros = {carnet};
        String[] campos = {CAMPO_NOMBRE};
        String[] camposn = {CAMPO_NOTA};

        try {
            Cursor cursor = db.query(TABLA_USUARIO, campos, CAMPO_ID + "=?", parametros, null, null, null);
            cursor.moveToFirst();

            Cursor cursorn = db.query(TABLA_USUARIO, camposn, CAMPO_ID + "=?", parametros, null, null, null);
            cursorn.moveToFirst();

            p = new estudiante(carnet, cursor.getString(0), cursorn.getString(0));
            cursor.close();
            cursorn.close();

        } catch (Exception e) {
            p = null;
        }
        return p;
    }

    public boolean editUser(estudiante p){
        String[] parametros = {p.getCarnet()};
        String[] campos = {CAMPO_NOMBRE};
        String[] notas = {CAMPO_NOTA};
        ContentValues values = new ContentValues();
        ContentValues valuesNota = new ContentValues();
        values.put(CAMPO_NOMBRE, p.getNombre());
        valuesNota.put(CAMPO_NOTA, p.getNota());
        db.update(TABLA_USUARIO, values, CAMPO_ID + "=?", parametros);
        db.update(TABLA_USUARIO, valuesNota, CAMPO_ID + "=?", parametros);
        Toast.makeText(context, "Usuario Actualizado con exito", Toast.LENGTH_SHORT).show();
        return true;
    }

    public boolean deleteUser(String carnet){
        String[] parametros = {carnet};
        db.delete(TABLA_USUARIO, CAMPO_ID + "=?", parametros);
        Toast.makeText(context, "Usuario Eliminado con exito", Toast.LENGTH_SHORT).show();
        return true;
    }



    public ArrayList<estudiante> getEstudiantesDBH(){

        ArrayList<estudiante> list = new ArrayList<estudiante>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLA_USUARIO;


        Cursor info = db.rawQuery(selectQuery, null);
        try {

            // looping through all rows and adding to list
            if (info.moveToFirst()) {
                do {
                    estudiante obj = new estudiante();
                    //only one column
                    obj.setCarnet(info.getString(0));
                    obj.setNombre(info.getString(1));

                    list.add(obj);
                } while (info.moveToNext());
            }

        } finally {
            try {
                info.close();
            } catch (Exception ignore) {
            }
        }



        return list;
    }

}

