package com.example.fyp;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.fyp.Adapters.ViewPagerAdapter;
import com.example.fyp.Adapters.WeatherAdapter;
import com.example.fyp.Objects.Weather;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;


public class MaintenancePlanner extends AppCompatActivity implements ArrayInterface, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    public static final String WEATHER = "weather";
    public static final String TIME = "time";
    public static final String DATE = "date";
    public static final String TEMP = "temp";

    private String latitude, longitude;

    public static final int RequestPermissionCode = 1;

    private GoogleApiClient googleApiClient;
    private FusedLocationProviderClient fusedLocationProviderClient;


    SimpleDateFormat myFormat = new SimpleDateFormat("EEE, dd/MM/yyyy");
    SimpleDateFormat fromUser = new SimpleDateFormat("yyyy-MM-dd");

    private RequestQueue requestQueue;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance_planner);

            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.statusBar));


        mondayData.clear();
        tuesdayData.clear();
        wednesdayData.clear();
        thursdayData.clear();
        fridayData.clear();
        saturdayData.clear();
        sundayData.clear();


        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();


        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);


        requestQueue = Volley.newRequestQueue(this);

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.viewPager);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.AddFragment(new FragmentMonday(), "Monday");
        adapter.AddFragment(new FragmentTuesday(), "Tuesday");
        adapter.AddFragment(new FragmentWednesday(), "Wednesday");
        adapter.AddFragment(new FragmentThursday(), "Thursday");
        adapter.AddFragment(new FragmentFriday(), "Friday");
        adapter.AddFragment(new FragmentSaturday(), "Saturday");
        adapter.AddFragment(new FragmentSunday(), "Sunday");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


    }



    private void parseJSON(String la, String lo) {


        String url = "https://api.openweathermap.org/data/2.5/forecast?lat=" + la + "&lon=" + lo + "&APPID=43ef55f9e03de2e95cc48537a99240ec";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            JSONArray jsonArray = response.getJSONArray("list");

                            for(int i = 0; i < jsonArray.length(); i++){


                                JSONObject list = jsonArray.getJSONObject(i);

                                String dateTime = list.getString("dt_txt");
                                String[] parts = dateTime.split(" ");
                                String date = parts[0];
                                String time = parts[1];

                                String reformattedDate = myFormat.format(fromUser.parse(date));


                                JSONObject main = list.getJSONObject("main");
                                int temp = main.getInt("temp");

                                JSONArray weather = list.getJSONArray("weather");


                                JSONObject b = weather.getJSONObject(0);

                                String weatherType = b.getString("description");


                                if(reformattedDate.contains("Mon")){

                                    mondayData.add(new Weather(weatherType, time, temp, reformattedDate));
                                }
                                if(reformattedDate.contains("Tue")){

                                    tuesdayData.add(new Weather(weatherType, time, temp, reformattedDate));
                                }
                                if(reformattedDate.contains("Wed")){

                                    wednesdayData.add(new Weather(weatherType, time, temp, reformattedDate));
                                }
                                if(reformattedDate.contains("Thu")){

                                    thursdayData.add(new Weather(weatherType, time, temp, reformattedDate));
                                }
                                if(reformattedDate.contains("Fri")){

                                    fridayData.add(new Weather(weatherType, time, temp, reformattedDate));
                                }
                                if(reformattedDate.contains("Sat")){

                                    saturdayData.add(new Weather(weatherType, time, temp, reformattedDate));
                                }
                                if(reformattedDate.contains("Sun")){

                                    sundayData.add(new Weather(weatherType, time, temp, reformattedDate));

                                }

                                else{

                                    Toast.makeText(MaintenancePlanner.this, "No data to display", Toast.LENGTH_SHORT).show();
                                }

                            }

                        } catch (JSONException | ParseException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

            }
        });

        requestQueue.add(request);


    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        if(ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){

            requestPermission();
        }
        else {

            fusedLocationProviderClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {

                        @Override
                        public void onSuccess(Location location) {
                            if (location != null) {

                                latitude =  String.valueOf(location.getLatitude());
                                longitude = String.valueOf(location.getLongitude());

                                parseJSON(latitude, longitude);

                            }
                            else{
                                Toast.makeText(MaintenancePlanner.this, "Couldn't get your location", Toast.LENGTH_LONG).show();
                            }
                        }
                    });

        }
    }

    @Override
    public void onConnectionSuspended(int i) {

        Log.e("Message", "Connection Suspended");

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

        Log.e("Error", "Connection Failed: " + connectionResult.getErrorCode());

    }

    private void requestPermission(){

        ActivityCompat.requestPermissions(MaintenancePlanner.this, new
                String[]{ACCESS_FINE_LOCATION}, RequestPermissionCode);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(googleApiClient != null) {

            googleApiClient.connect();

        }
    }

    @Override
    protected void onStop(){
        super.onStop();

        if(googleApiClient.isConnected()){
            googleApiClient.disconnect();
        }
    }

}
