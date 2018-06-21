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
    private List<CSKU> csku;
    private RecyclerView recyclerView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base);
        answerSku = new AnswerSku(this,"SKUDB",null,1);
        answers= new ArrayList<>();
        recyclerView = findViewById(R.id.ReciclerV);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        consultaCatalogo();
        consultarLista();
        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(csku,answers);
        recyclerView.setAdapter(myAdapter);




    }
    private void consultarLista() {
        SQLiteDatabase db = answerSku.getReadableDatabase();

        SKU sku = null;
        answers = new ArrayList<>();
        //select * from usuarios
        Cursor cursor = db.rawQuery("SELECT * FROM    c_sku  left join   answer_sku where c_sku.id_sku=answer_sku.idSku ", null);

        while (cursor.moveToNext()) {
            sku = new SKU();
            sku.setValor(cursor.getInt(cursor.getColumnIndex("answer")));
            sku.setIdforeign(cursor.getInt(cursor.getColumnIndex("idSku")));
            answers.add(sku);
        }
        if (!answers.isEmpty()){
            Toast.makeText(this,answers.size()+" "+answers.get(0).getValor(),Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"nel",Toast.LENGTH_SHORT).show();
        }
        cursor.close();




    }
    public  void consultaCatalogo(){
        SQLiteDatabase db = answerSku.getReadableDatabase();
        CSKU sku = null;
        csku = new ArrayList<>();
        //select * from usuarios
        Cursor cursor = db.rawQuery("SELECT * FROM  c_sku  ", null);

        while (cursor.moveToNext()) {
            sku = new CSKU();
            sku.setValor(cursor.getString(cursor.getColumnIndex("value")));

            csku.add(sku);
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
                for (SKU s:answers) {
                   int a= updateData(s);
                }

                break;
        }


        return super.onOptionsItemSelected(item);
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
