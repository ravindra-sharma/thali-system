package com.example.cod_plyr.thalisystem;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Cod_PlYr on 1/8/2017.
 */
public class JSONfunctions {
    public static JSONObject getJSONfromurl(String url)  {
        InputStream is=null;
        String result="";
        JSONObject jArray=null;
        URL url1= null;
        try {
            url1 = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            HttpURLConnection httpURLConnection= (HttpURLConnection) url1.openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();
            is=httpURLConnection.getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(is,"iso-8859-1"));
            StringBuilder sb=new StringBuilder();
            String line=null;
            while ((line=bufferedReader.readLine())!=null)
            {
                sb.append(line+"\n");
            }
            is.close();
            result=sb.toString();
            jArray=new JSONObject(result);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  jArray;
    }

}
