package com.example.fyp;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;


import android.content.RestrictionEntry;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;


import com.example.fyp.Fragments.EventsFragment;
import com.example.fyp.Fragments.WeatherFragment;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.tabs.TabLayout;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;


public class MaintenancePlanner extends AppCompatActivity implements ConnectionCallbacks, OnConnectionFailedListener {




    public static final int RequestPermissionCode = 1;

    Weather weather = new Weather();

    String time;
    String weathr;
    String degrees;
    double latitude, longitude;


    public ArrayList<Weather> weathers = new ArrayList<Weather>();


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



        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager viewPager = findViewById(R.id.view_pager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        viewPagerAdapter.addFragment(new EventsFragment(), "My Jobs");
        viewPagerAdapter.addFragment(new WeatherFragment(), "Times");


        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);




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
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();
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







    class ViewPagerAdapter extends FragmentPagerAdapter {

        private ArrayList<Fragment> fragments;
        private ArrayList<String> titles;

        ViewPagerAdapter(FragmentManager fm){

            super(fm);
            this.fragments = new ArrayList<>();
            this.titles = new ArrayList<>();
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        public void addFragment(Fragment fragment, String title){

            fragments.add(fragment);
            titles.add(title);
        }


        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }


    private class GetDataTask extends AsyncTask<String, Void, String>{


        @Override
        protected String doInBackground(String[] params) {

            String result = "";
            String urlString = params[0];

            try{

                URL url = new URL("http://metwdb-openaccess.ichec.ie/metno-wdb2ts/locationforecast?lat="+latitude+";long="+longitude);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");


                if(httpURLConnection.getResponseCode() == 200){

                    BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;

                    while((line = reader.readLine()) != null){

                        stringBuilder.append(line);
                    }

                    result = stringBuilder.toString();

                    httpURLConnection.disconnect();
                }

                else{

                    result = "";
                }
            }catch (IOException e){

                e.printStackTrace();
            }

            return result;
        }



    }
    



}
