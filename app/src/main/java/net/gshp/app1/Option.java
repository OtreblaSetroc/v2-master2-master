package net.gshp.app1;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Option extends AppCompatActivity {
    private SQLiteDatabase db;
    private AnswerSku answerSku;
    private List<SKU> answers;
    private RecyclerView recyclerView;
    private  RecyclerViewAdapter myAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base);
        answerSku = new AnswerSku(this,"SKUDB",null,1);
        answers= new ArrayList<>();
        recyclerView = findViewById(R.id.ReciclerV);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        consultarLista();
        myAdapter = new RecyclerViewAdapter(answers);
        recyclerView.setAdapter(myAdapter);




    }
    private void consultarLista() {
        SQLiteDatabase db = answerSku.getReadableDatabase();

        SKU sku = null;
        answers = new ArrayList<>();
        //select * from usuarios
        Cursor cursor = db.rawQuery("SELECT s.id_sku,s.value,an.answer FROM    c_sku s  left join   answer_sku an on s.id_sku=an.idSku ", null);

        while (cursor.moveToNext()) {
            sku = new SKU();

            sku.setValor(cursor.getString(cursor.getColumnIndex("value")));
            sku.setIdSku(cursor.getInt(cursor.getColumnIndex("id_sku")));
            int ans=cursor.getInt(cursor.getColumnIndex("answer"));
            Toast.makeText(this,ans+"",Toast.LENGTH_SHORT).show();
            sku.setAnswer(ans);
            answers.add(sku);
        }

        cursor.close();




    }
    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tool,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.Save:
               // db= answerSku.getWritableDatabase();
                Toast.makeText(Option.this,"Save1", Toast.LENGTH_LONG).show();
                insertar();

                break;
        }


        return super.onOptionsItemSelected(item);
    }
    public  void insertar(){
        SQLiteDatabase db = answerSku.getReadableDatabase();
            Toast.makeText(this,"Ya",Toast.LENGTH_SHORT).show();
            db.delete("answer_sku",null,null);
            for (SKU s :answers ) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("answer",s.getAnswer());
                contentValues.put("idSku",s.getIdSku());
               db.insert("answer_sku",null,contentValues);
            }


       /* SQLiteDatabase db = answerSku.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
       /* for (SKU sk:answers ) {
            contentValues.put("answer",an);
        }


            contentValues.put("value","SKU"+i);
            db.insert("c_sku",null,contentValues);*/



    }
    public int updateData(SKU sku){
        SQLiteDatabase db = answerSku.getReadableDatabase();
        ContentValues valoresActualizar = new ContentValues();
        valoresActualizar.put("answer",sku.getValor());

        int a= db.update("answer_sku", valoresActualizar, "idans" + "=" + sku.getIdSku(), null);
        Toast.makeText(this,a+" id "+sku.getIdSku(),Toast.LENGTH_SHORT).show();
      return a;



    }

}
