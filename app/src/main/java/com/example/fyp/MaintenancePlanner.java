package com.example.fyp;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.OnSuccessListener;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;


public class MaintenancePlanner extends AppCompatActivity implements ConnectionCallbacks, OnConnectionFailedListener  {




    public static final int RequestPermissionCode = 1;
    TextView latitude, longitude;
    private GoogleApiClient googleApiClient;
    FusedLocationProviderClient fusedLocationProviderClient;






    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance_planner);


        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        latitude = findViewById(R.id.maintainenceTextView1);
        longitude = findViewById(R.id.maintainenceTextView2);

      

    }

    @Override
    protected void onStart() {
        super.onStart();
        googleApiClient.connect();
    }

    @Override
    protected void onStop() {

        super.onStop();
        if(googleApiClient.isConnected()){

            googleApiClient.disconnect();

        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        if(ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            requestPermission();
        }
        else {
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {

                    if (location != null) {
                        latitude.setText(String.valueOf(location.getLatitude()));
                        longitude.setText(String.valueOf(location.getLongitude()));
                    }

                }
            });
        }

    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(MaintenancePlanner.this, new
                String[]{ACCESS_FINE_LOCATION}, RequestPermissionCode);


    }

    @Override
    public void onConnectionSuspended(int i) {

        Log.e("MaintenancePlanner", "Connection suspended");

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

        Log.e("MaintenancePlanner", "Connection failed " + connectionResult.getErrorCode());

    }



    



}
