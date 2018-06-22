package net.gshp.app1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Coneccion extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 5;
    public static final String DATABASE_NAME = "sku.db";

    //final String usr="CREATE TABLE Sku (id INTEGER PRIMARY KEY AUTOINCREMENT, value INTEGER)";
    final String asw="CREATE TABLE Answer_SKU (id INTEGER PRIMARY KEY AUTOINCREMENT, value)";
    final String pos="CREATE TABLE gps (id INTEGER PRIMARY KEY AUTOINCREMENT, lon DOUBLE, lat DOUBLE, time VARCHAR(50))";
    //final String sq="CREATE TABLE Cont (id INTEGER, id_aws INTEGER, FOREIGN KEY (id) REFERENCES Sku(id), FOREIGN KEY (id_aws) REFERENCES Answer_SKU(id), PRIMARY KEY (id,id_aws))"
    public Coneccion(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL(usr);
        db.execSQL(asw);
        db.execSQL(pos);
        //db.execSQL(sq);
        ContentValues cont = new ContentValues();

        cont.put("value",0);

        for (int i=0;i<20;i++) {
            cont.put("id",i);
            db.insert("Answer_SKU",null,cont);
            System.out.println(cont);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Answer_SKU");
        db.execSQL("DROP TABLE IF EXISTS gps");
        //db.execSQL("DROP TABLE IF EXISTS Sku");
        onCreate(db);
    }


    public void InsertPosi(double lat, double lon, String time){
        SQLiteDatabase db = this.getReadableDatabase();
        String tabla = "gps";
        ContentValues con = new ContentValues();
        con.put("lon",lon);
        con.put("lat",lat);
        con.put("time",time);
        db.insert(tabla,null,con);
    }
    public List<AsnwerSku> obtenerSKU(){

        SQLiteDatabase db = this.getReadableDatabase();
        String table = "Answer_SKU";
        String[]col = new String[] {"id","value"};
        List<AsnwerSku> a = new ArrayList<>();

        Cursor cursor =db.query(table,col,null,null,null,null,null,null);

        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                //a.add(new AsnwerSku(3,1));
                a.add(new AsnwerSku(Integer.parseInt(cursor.getString(0)),Integer.parseInt(cursor.getString(1))));
            } while(cursor.moveToNext());
        }

        return a;

    }




    public int maxSKu(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c= db.rawQuery("SELECT MAX(id) from Answer_SKU",null);
        if (c.moveToFirst()) {
            return Integer.parseInt(c.getString(0));
        }
        else{
            return 0;
        }

    }

    public void UPDATESKU(int val,int id){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues c = new ContentValues();
        c.put("value",val);
        db.update("Answer_SKU",c,"id="+id,null);

    }


}