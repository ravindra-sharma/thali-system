package com.example.cod_plyr.thalisystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Cod_PlYr on 1/8/2017.
 */
public class ListViewAdapter extends BaseAdapter {
    //daclare variable
    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String,String>> data;
    ImageLoader imageLoader;
    HashMap<String,String> resultp=new HashMap<String, String>();

    public ListViewAdapter(Context context, ArrayList<HashMap<String, String>> arrayList) {
    this.context=context;
        data=arrayList;
        imageLoader=new ImageLoader(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView rank;
        TextView country;
        TextView population;
        ImageView flag;

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = LayoutInflater.from(context).inflate(R.layout.listview_item,parent,false);
        // Get the position
        resultp = data.get(position);

        // Locate the TextViews in listview_item.xml
        rank = (TextView) itemView.findViewById(R.id.rank);
        country = (TextView) itemView.findViewById(R.id.country);
        population = (TextView) itemView.findViewById(R.id.population);

        // Locate the ImageView in listview_item.xml
        flag = (ImageView) itemView.findViewById(R.id.flag);

        // Capture position and set results to the TextViews
        rank.setText(resultp.get(MainActivity.RANK));
        country.setText(resultp.get(MainActivity.COUNTRY));
        population.setText(resultp.get(MainActivity.POPULATION));
        // Capture position and set results to the ImageView
        // Passes flag images URL into ImageLoader.class
        imageLoader.DisplayImage(resultp.get(MainActivity.FLAG), flag);
        return null;
    }
}
