package net.gshp.app1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.facebook.stetho.common.ArrayListAccumulator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leo on 20/06/18.
 */

public class AnswerSku extends SQLiteOpenHelper {

    String tabla1="create table c_sku(id_sku INTEGER PRIMARY KEY AUTO INCREMENT, value TEXT)";
    String table2="create table answer_sku(idans INTEGER PRIMARY KEY AUTO INCREMENT, answer INTEGER, idSku INTEGER," +
            "FOREIGN KEY(idSku) REFERENCES C_sku(id_sku)  ON DELETE CASCADE";

    public AnswerSku(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        creaTables(db);


    }
    public void creaTables(SQLiteDatabase db){
        db.execSQL(tabla1);
        db.execSQL(table2);
        llenaDB(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table IF exists c_sku");
        db.execSQL("Drop table IF exists answer_sku");
        creaTables(db);
        llenaDB(db);

    }
    public void llenaDB(SQLiteDatabase db){
        ContentValues contentValues = new ContentValues();
        for (int i=1; i<=10;i++){
            contentValues.put("value","SKU"+i);
            db.insert("c_sku",null,contentValues);
        }
        contentValues = new ContentValues();
        for (int i=0; i<10;i++){
            contentValues.put("answer",0);
            contentValues.put("idSku",i);
            db.insert("answer_sku",null,contentValues);
        }

    }
    public List<SKU> getAll(SQLiteDatabase db){
        String query="Select * from answer_sku ";
        List<SKU> sk= new ArrayList<>();
        db=getReadableDatabase();
        SKU sku;
        Cursor cursor= db.rawQuery(query,null);
        while(cursor.moveToNext()){
            sku= new SKU(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2));
            sk.add(sku);
        }
        return  sk;
    }
    public void update(SKU sk){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("answer",sk.getIdSku());
        db.update("answer_sku",contentValues,"idans="+sk.getIdSku(),null);

    }
}
