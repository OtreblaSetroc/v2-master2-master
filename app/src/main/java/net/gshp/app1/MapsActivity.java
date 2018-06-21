package net.gshp.app1;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback ,LocationListener{

    private GoogleMap mMap;
    private BottomNavigationView bn;
    private  Location current;
    private  LocationManager locationManager;
    private GeosDB geosDB;
    private SQLiteDatabase db;
    private int a = 3000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        this.checkISGPSEnabled();
        bn = findViewById(R.id.bottom_navigation);
        final Intent op = new Intent(this, Option.class);
        bn.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_start:
                        startActivity(op);
                        return true;
                    case R.id.action_start2:

                        return true;
                }
                return true;
            }
        });
        if (savedInstanceState==null){
            geosDB = new GeosDB(this,"GeosDB",null,1);
            this.db=geosDB.getWritableDatabase();
        }else {
            geosDB = new GeosDB(this,"GeosDB",null,1);
            this.db=geosDB.getWritableDatabase();

        }




    }


    public long inserta (Double l1, Double l2, int time){

        ContentValues values = new ContentValues();
        values.put("latitud",l1);
        values.put("longitud",l2);
        values.put("time",time);

        return db.insert("geos",null,values);


    }

    @Override
    protected void onResume() {
        super.onResume();
        this.checkISGPSEnabled();
        if (db==null){
            geosDB = new GeosDB(this,"GeosDB",null,1);
            this.db=geosDB.getWritableDatabase();
        }


    }

    @Override
    protected void onDestroy() {
       // db.close();
        super.onDestroy();
    }

    private void checkISGPSEnabled() {
        try {
            int gpsSignal = Settings.Secure.getInt(getContentResolver(), Settings.Secure.LOCATION_MODE);
            if (gpsSignal == 0) {
                // Gps no está activido
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMinZoomPreference(10);
        mMap.setMaxZoomPreference(20);
        locationManager=(LocationManager)getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 3000, 0,this );
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 0,this );

    }

    @Override
    public void onLocationChanged(Location location) {

        // Add a marker in Polanco and move the camera
        Double l1=location.getLatitude();
        Double l2=location.getLongitude();
        LatLng place = new LatLng(l1,l2);
        if (l1!=null && l2!=null){

            mMap.addMarker(new MarkerOptions().position(place).title("Marker in here"));
            long i=inserta(l1,l2,a);
            if (i>0){
                Toast.makeText(MapsActivity.this,"Se inserto correctamente",Toast.LENGTH_LONG).show();
            }
            a+=a;
            CameraPosition camera = new CameraPosition.Builder()
                    .target(place)
                    .zoom(15) // 1 to 21
                    .bearing(90) // 0 to 360
                    .tilt(30)  // 0 to 90
                    .build();

            // mMap.moveCamera(CameraUpdateFactory.newLatLng(polanco));
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(camera));
        }



    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
