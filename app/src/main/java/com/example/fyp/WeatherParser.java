package com.example.fyp;

import android.os.AsyncTask;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;



public class WeatherParser extends AsyncTask {

    URL url;

    ArrayList<Weather> weatherArrayList = new ArrayList<>();

    String time;
    String degrees;
    String weatherType;

    Weather weather = new Weather();


    @Override
    protected Object doInBackground(Object[] objects) {

        try {
            url = new URL("http://metwdb-openaccess.ichec.ie/metno-wdb2ts/locationforecast?lat=54.7210798611;long=-8.7237392806");

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(false);
            XmlPullParser xpp = factory.newPullParser();

            xpp.setInput(url.openConnection().getInputStream(), "UTF_8");


            boolean insideItem = false;

            int eventType = xpp.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {

                if (eventType == XmlPullParser.START_TAG) {

                    if (xpp.getName().equalsIgnoreCase("time")) {

                        insideItem = true;
                        time = xpp.getAttributeValue(1);

                        Log.i("Tag","Time: " + time);

                    }

                    else if (xpp.getName().equalsIgnoreCase("location")) {

                        insideItem = true;

                        if(xpp.getName().equalsIgnoreCase("temperature") && insideItem){

                            degrees = xpp.getAttributeValue(2);

                        }
                    }

                    else if (xpp.getName().equalsIgnoreCase("symbol")) {

                        if(insideItem){

                            weatherType = xpp.getAttributeValue(0);

                        }
                    }



                    weatherArrayList.add(new Weather(weatherType, time, degrees));
                }
            }



        }catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return weatherArrayList;
    }

    public InputStream getInputStream(URL url) {
        try {
            return url.openConnection().getInputStream();
        } catch (IOException e) {
            return null;
        }
    }

    public ArrayList<Weather> weathers()
    {
        return weatherArrayList;
    }
}
