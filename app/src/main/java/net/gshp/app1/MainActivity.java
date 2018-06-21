package net.gshp.app1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         bn = findViewById(R.id.bottom_navigation);
         final Intent op = new Intent(this,Option.class);
         bn.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
             @Override
             public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                 switch(item.getItemId()){
                     case R.id.action_start:
                         startActivity(op);
                         return true;
                     case R.id.action_start2:

                        return  true;
                 }

                 return  true;
             }
         });
    }
}
