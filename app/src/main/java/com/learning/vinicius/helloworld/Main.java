package com.learning.vinicius.helloworld;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class Main extends Activity {

    ListView myListOfMaps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myListOfMaps = (ListView) findViewById(R.id.mapList);
        myListOfMaps.setAdapter( new MapListAdapter(this) );

    }

    public class MapListAdapter extends BaseAdapter {

        ArrayList<Location> myLocations;
        Context mContext;

        MapListAdapter(Context context) {
            mContext = context;

            myLocations = new ArrayList<Location>();
            myLocations.add(new Location(0,"Brazil",-14.235004,-51.925280));
            myLocations.add(new Location(1,"Africa",8.783195,34.508523));
            myLocations.add(new Location(2,"GERMANY",51.165691,10.451526));
        }

        @Override
        public int getCount() {
            return myLocations.size();
        }

        @Override
        public Object getItem(int position) {
            return myLocations.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if ( convertView == null )
                convertView = new CustomItem(mContext,myLocations.get(position));

            return convertView;
        }
    }
}
