package com.learning.vinicius.helloworld;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Vinicius on 28/01/15.
 */
public class CustomItem extends FrameLayout {

    public int myGeneratedFrameLayoutId;

    public CustomItem(Context context,Location location) {
        super(context);
        myGeneratedFrameLayoutId = 10101010 + location.id; // choose any way you want to generate your view id

        LayoutInflater inflater = ((Activity) context).getLayoutInflater();

        FrameLayout view = (FrameLayout) inflater.inflate(R.layout.my_custom_item,null);
        FrameLayout frame = new FrameLayout(context);
        frame.setId(myGeneratedFrameLayoutId);

        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 150, getResources().getDisplayMetrics());
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT,height);
        frame.setLayoutParams(layoutParams);

        view.addView(frame);

        GoogleMapOptions options = new GoogleMapOptions();
        options.liteMode(true);
        MapFragment mapFrag = MapFragment.newInstance(options);

        mapFrag.getMapAsync(new MyMapCallback(location.lat,location.lng));

        FragmentManager fm = ((Activity) context).getFragmentManager();
        fm.beginTransaction().add(frame.getId(),mapFrag).commit();

        addView(view);
    }

    public class MyMapCallback implements OnMapReadyCallback {

        Double lat;
        Double lng;

        MyMapCallback(Double latitude,Double longitude) {
            this.lat = latitude;
            this.lng = longitude;
        }


        @Override
        public void onMapReady(GoogleMap googleMap) {
            LatLng latLng = new LatLng(lat,lng);
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLng(latLng);
            googleMap.moveCamera(cameraUpdate);
            googleMap.animateCamera(CameraUpdateFactory.zoomTo(5));
        }
    }
}
