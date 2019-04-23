package com.example.cod_plyr.thalisystem;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    //declare the variables
    JSONObject jsonObject;
    JSONArray jsonArray;
    ListView listView;
    ListViewAdapter adapter;
    ProgressDialog mProgressDialog;
    ArrayList<HashMap<String,String>> arrayList;
    static String RANK="rank";
    static String COUNTRY="country";
    static String POPULATION="population";
    static String FLAG="flag";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new DownloadJSON().execute();
    }

    private class DownloadJSON extends AsyncTask<Void,Void,Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //create a progress dialoge
            mProgressDialog=new ProgressDialog(MainActivity.this);
            //set title to progress dialogue
            mProgressDialog.setTitle("Android json parsing tutorial");
            //set message to progress dialigue
            mProgressDialog.setMessage("Loading....");
            mProgressDialog.setIndeterminate(false);
            //show progress dialogue
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            //create an arraylist
            arrayList=new ArrayList<HashMap<String, String>>();
            //retrive JSON object from the given url address
            jsonObject=JSONfunctions.getJSONfromurl("http://www.androidbegin.com/tutorial/jsonparsetutorial.txt");
            try
            {
                //locate the array name in json
                jsonArray=jsonObject.getJSONArray("worldpopulation");
                for(int i=0;i<jsonArray.length();i++)
                {
                    HashMap<String,String> map=new HashMap<String, String>();
                    jsonObject=jsonArray.getJSONObject(i);
                    //retrive json object
                    map.put("rank",jsonObject.getString("rank"));

                    map.put("country",jsonObject.getString("country"));
                    map.put("population",jsonObject.getString("population"));
                    map.put("flag",jsonObject.getString("flag"));
                    arrayList.add(map);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            //locate the list view in main activity
            listView=(ListView)findViewById(R.id.listview);
            adapter =new ListViewAdapter(MainActivity.this,arrayList);
            listView.setAdapter(adapter);
            mProgressDialog.dismiss();
        }
    }
}
