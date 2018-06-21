package net.gshp.app1;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class GeosDB extends SQLiteOpenHelper {
    private Double l1;
    private  Double l2;
    private  Double time;


    String sql="Create table geos(idGeos INTEGER PRIMARY KEY  AUTOINCREMENT NOT NULL, latitud Double, longitud Double,time INTEGER)";

    public GeosDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(sql);


    }
   /* public  void insertaDatos(Double l1, Double l2,Double time, SQLiteDatabase db){
        String sql= "INSERT INTO geos values("+l1+","+l2+","+time+")";
        db.execSQL(sql);


    }*/

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("Drop table IF exists geos");
        sqLiteDatabase.execSQL(sql);

    }
}
