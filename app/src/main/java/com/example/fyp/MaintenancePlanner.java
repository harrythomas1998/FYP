package com.example.fyp;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;



import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.OnSuccessListener;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;


public class MaintenancePlanner extends AppCompatActivity implements ConnectionCallbacks, OnConnectionFailedListener  {




    public static final int RequestPermissionCode = 1;

    Weather weather = new Weather();

    String time;
    String weathr;
    String degrees;
    double latitude, longitude;

    ArrayList<Weather> weathers = new ArrayList<Weather>();


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


    MaintenancePlanner var;

    public void DomParser(){

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();

            String url = "http://metwdb-openaccess.ichec.ie/metno-wdb2ts/locationforecast?lat="+var.latitude+";long="+var.longitude;

            Document doc = builder.parse(url);

            NodeList weatherList = doc.getElementsByTagName("weatherdata");

            for(int i = 0; i < weatherList.getLength(); i++) {

                Node w = weatherList.item(i);

                if(w.getNodeType() == Node.ELEMENT_NODE) {

                    Element data = (Element) w;


                    NodeList forecastList = data.getElementsByTagName("product");

                    for(int j= 0; j < forecastList.getLength(); j++) {

                        Node n = forecastList.item(j);

                        if(n.getNodeType() == Node.ELEMENT_NODE) {

                            Element f = (Element) n;
                            //=====================================================

                            NodeList timeList = f.getElementsByTagName("time");

                            for(int l = 0; l < timeList.getLength(); l++) {

                                Node a = timeList.item(l);

                                if(a.getNodeType() == Node.ELEMENT_NODE) {

                                    Element e = (Element) a;

                                    time = e.getAttribute("from");

                                    //===========================================================


                                    NodeList locationList = e.getElementsByTagName("location");

                                    for(int v = 0; v < locationList.getLength(); v++) {

                                        Node s = locationList.item(v);

                                        if(s.getNodeType() == Node.ELEMENT_NODE) {

                                            Element loc = (Element) s;
                                            //===========================================================

                                            NodeList tempList = loc.getElementsByTagName("temperature");

                                            for(int y = 0; y < tempList.getLength(); y++){

                                                Node temp = tempList.item(y);

                                                if(temp.getNodeType() == Node.ELEMENT_NODE){

                                                    //Temperature
                                                    Element tempNode = (Element) temp;

                                                    degrees = tempNode.getAttribute("value");

                                                }
                                            }

                                            NodeList symbolList = loc.getElementsByTagName("symbol");

                                            for(int r = 0; r < symbolList.getLength(); r++) {

                                                Node sym =  symbolList.item(r);

                                                if(sym.getNodeType() == Node.ELEMENT_NODE) {

                                                    Element weatherType = (Element) sym;

                                                    weathr = weatherType.getAttribute("id");
                                                }

                                            }




                                        }
                                    }





                                }

                            }

                            weathers.add(new Weather(weathr, time, degrees));

                        }


                    }






                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }



    }



    



}
