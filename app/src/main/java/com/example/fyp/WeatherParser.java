package com.example.fyp;

import android.os.AsyncTask;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


public class WeatherParser extends AsyncTask{



    MaintenancePlanner mp;

    private String weatherT, degrees, time;



    ArrayList<Weather> weatherArrayList = new ArrayList<>();

    public Object doInBackground(Object[] objects){

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();

            String url = "http://metwdb-openaccess.ichec.ie/metno-wdb2ts/locationforecast?lat=" + mp.latitude + ";long=" + mp.longitude;

            Document doc = builder.parse(url);

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

                            weatherArrayList.add(new Weather(weatherT, time, degrees));

                        }


                    }


                }
            }


        }catch (ParserConfigurationException | SAXException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return weatherArrayList;
    }








    public ArrayList<Weather> getWeatherArrayList() {
        return weatherArrayList;
    }



}
