package net.gshp.app1;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.facebook.stetho.Stetho;

import java.util.Timer;
import java.util.TimerTask;

public class Main2Splash extends AppCompatActivity {
    private Timer timer;
    private  TimerTask timerTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2_splash);
        init();




    }

    public void init(){
        Stetho.initializeWithDefaults(this);
        final Intent intent = new Intent(this, MapsActivity.class);
        timer = new Timer();
        timerTask = new TimerTask(){
            @Override
            public void run()
            {
                startActivity(intent);
                finish();
            }
        };
        timer.schedule(timerTask,3000);

    }

    @Override
    public void onBackPressed(){
        Toast.makeText(Main2Splash.this,"Saliendo",Toast.LENGTH_SHORT);
        super.onBackPressed();
        timer.cancel();

    }
}
