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



   ArrayList<Weather> w = new ArrayList<>();




    @Override
    protected Object doInBackground(Object[] objects) {

        try {
            url = new URL("http://metwdb-openaccess.ichec.ie/metno-wdb2ts/locationforecast?lat=54.7210798611;long=-8.7237392806");

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(false);
            XmlPullParser parser = factory.newPullParser();

            parser.setInput(url.openConnection().getInputStream(), "UTF_8");

            ArrayList<Weather> weatherArrayList = parseXML(parser);


            for(Weather weather: weatherArrayList ){

                w.add(weather);


            }


        }catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return w;

    }

    private ArrayList<Weather> parseXML(XmlPullParser parser) throws XmlPullParserException, IOException{

        ArrayList<Weather> weathers = null;

        int eventType = parser.getEventType();
        Weather weather = null;

        while(eventType != XmlPullParser.END_DOCUMENT){
            String name;
            switch(eventType){
                case XmlPullParser.START_DOCUMENT:
                    weathers = new ArrayList<>();
                    break;
                case XmlPullParser.START_TAG:
                    name = parser.getName();
                    if(name.equals("time")){
                        weather = new Weather();
                        weather.time = parser.getAttributeValue(null, "from");
                        Log.i("Weather", parser.getAttributeValue(null, "from"));
                    }
                    else if(weather != null){
                        if(name.equals("temperature")){
                            weather.temperature = parser.getAttributeValue(null, "value");
                        }
                        else if(name.equals("symbol")){
                            weather.weatherType = parser.getAttributeValue(null, "id");
                            Log.i("Weather", parser.getAttributeValue(null, "id"));
                        }
                    }


            }

        }

        return weathers;
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
        return w;
    }
}
