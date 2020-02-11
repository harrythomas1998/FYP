package com.example.fyp;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;

import android.os.Bundle;

import android.util.Log;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.fyp.Adapters.WeatherAdapter;
import com.example.fyp.Objects.Weather;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.OnSuccessListener;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;



import static android.Manifest.permission.ACCESS_FINE_LOCATION;


public class MaintenancePlanner extends AppCompatActivity implements ConnectionCallbacks, OnConnectionFailedListener, WeatherAdapter.OnItemClickListener {

    public static final String WEATHER = "weather";
    public static final String TIME = "time";
    public static final String DATE = "date";
    public static final String TEMP = "temp";

    public static final int RequestPermissionCode = 1;

    private GoogleApiClient googleApiClient;
    private FusedLocationProviderClient fusedLocationProviderClient;

    Double latitude, longitude;

    SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat fromUser = new SimpleDateFormat("yyyy-MM-dd");





    private RecyclerView recyclerView;
    private WeatherAdapter weatherAdapter;
    private RequestQueue requestQueue;
    private ArrayList<Weather> weatherData;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance_planner);


        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();


        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        weatherData = new ArrayList<>();




        recyclerView = findViewById(R.id.weather_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        requestQueue = Volley.newRequestQueue(this);
        parseJSON();

    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {

        Toast.makeText(MaintenancePlanner.this, "Im here", Toast.LENGTH_LONG).show();


        if(ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){

            requestPermission();
        }
        else {
            fusedLocationProviderClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location != null) {

                                latitude =  location.getLatitude();
                                System.out.print("");
                                longitude = location.getLongitude();

                                Toast.makeText(MaintenancePlanner.this, "Longitude: " + longitude, Toast.LENGTH_LONG).show();
                            }
                        }
                    });

        }
    }


    private void parseJSON() {

        double latt = 53.338519;
        double longg = -6.266483;

        String url = "https://api.openweathermap.org/data/2.5/forecast?lat=" + latitude + "&lon=" + longitude + "&APPID=43ef55f9e03de2e95cc48537a99240ec";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        System.out.println("Got weather");
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

                                weatherData.add(new Weather(weatherType, time, temp, reformattedDate));

                            }

                            weatherAdapter = new WeatherAdapter(MaintenancePlanner.this, weatherData);
                            recyclerView.setAdapter(weatherAdapter);
                            weatherAdapter.setOnItemClickListener(MaintenancePlanner.this);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (ParseException e) {
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



    @Override
    public void onItemClick(int position) {

        Intent i = new Intent(this, JobActivity.class);
        Weather clickedWeatherItem = weatherData.get(position);

        i.putExtra(WEATHER, clickedWeatherItem.getWeatherType());
        i.putExtra(TIME, clickedWeatherItem.getTime());
        i.putExtra(DATE, clickedWeatherItem.getDate());
        i.putExtra(TEMP, clickedWeatherItem.getTemperature());

        startActivity(i);


    }
}
