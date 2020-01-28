package com.example.fyp;

import android.os.AsyncTask;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


public class WeatherParser extends AsyncTask<String, Void, String>{



    MaintenancePlanner mp;

    private String weatherT, degrees, time;



    ArrayList<Weather> weatherArrayList = new ArrayList<>();

    public String doInBackground(String[] params){

        String result = "";

        String urlString = "http://metwdb-openaccess.ichec.ie/metno-wdb2ts/locationforecast?lat=" + mp.latitude + ";long=" + mp.longitude;

        try{

            URL url = new URL(urlString);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");

            if(httpURLConnection.getResponseCode() == 200){
                BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while((line = reader.readLine()) != null){
                    stringBuilder.append(line);
                    System.out.println("LINE APPENDED :"+line);
                }
                result = stringBuilder.toString();

                httpURLConnection.disconnect();
            }
            else{
                System.out.println("RESULT SET TO BLANK, HTTP CODE not 200");
                result = "";
            }
        }catch (IOException e){

            e.printStackTrace();
        }
        System.out.println("RESULT FINALLY RETURNED :"+result);
        return result;

    }



    protected void onPostExecute(String result){

        super.onPostExecute(result);

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document doc = builder.parse(result);

            NodeList weatherList = doc.getElementsByTagName("weatherdata");

            for (int i = 0; i < weatherList.getLength(); i++) {

                Node w = weatherList.item(i);

                if (w.getNodeType() == Node.ELEMENT_NODE) {

                    Element data = (Element) w;


                    NodeList forecastList = data.getElementsByTagName("product");

                    for (int j = 0; j < forecastList.getLength(); j++) {

                        Node n = forecastList.item(j);

                        if (n.getNodeType() == Node.ELEMENT_NODE) {

                            Element f = (Element) n;
                            //=====================================================

                            NodeList timeList = f.getElementsByTagName("time");

                            for (int l = 0; l < timeList.getLength(); l++) {

                                Node a = timeList.item(l);

                                if (a.getNodeType() == Node.ELEMENT_NODE) {

                                    Element e = (Element) a;

                                    time = e.getAttribute("from");

                                    //===========================================================


                                    NodeList locationList = e.getElementsByTagName("location");

                                    for (int v = 0; v < locationList.getLength(); v++) {

                                        Node s = locationList.item(v);

                                        if (s.getNodeType() == Node.ELEMENT_NODE) {

                                            Element loc = (Element) s;
                                            //===========================================================

                                            NodeList tempList = loc.getElementsByTagName("temperature");

                                            for (int y = 0; y < tempList.getLength(); y++) {

                                                Node temp = tempList.item(y);

                                                if (temp.getNodeType() == Node.ELEMENT_NODE) {

                                                    //Temperature
                                                    Element tempNode = (Element) temp;

                                                    degrees = tempNode.getAttribute("value");

                                                }
                                            }

                                            NodeList symbolList = loc.getElementsByTagName("symbol");

                                            for (int r = 0; r < symbolList.getLength(); r++) {

                                                Node sym = symbolList.item(r);

                                                if (sym.getNodeType() == Node.ELEMENT_NODE) {

                                                    Element weatherType = (Element) sym;

                                                    weatherT = weatherType.getAttribute("id");
                                                }

                                            }


                                        }
                                    }


                                }

                            }

                            Weather weather = new Weather(weatherT, time, degrees);
                            weatherArrayList.add(weather);
                            System.out.println("WEATHER DATA ADDED TO LIST: "+weatherT);
                            System.out.println("WEATHER LIST SIZE: "+weatherArrayList.size());

                        }


                    }


                }
            }


        }catch (ParserConfigurationException | SAXException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }



    }





    public ArrayList<Weather> getWeatherArrayList() {

        weatherArrayList.add(new Weather("Cloudy", "28/01/2020", "9"));

        return weatherArrayList;
    }



}
