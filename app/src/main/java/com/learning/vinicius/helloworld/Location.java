package com.learning.vinicius.helloworld;

/**
 * Created by Vinicius on 27/01/15.
 */
public class Location {
    public int id;
    public String address;
    public Double lng;
    public Double lat;

    public Location(int id,String address, Double lng, Double lat) {
        this.address = address;
        this.lng = lng;
        this.lat = lat;
        this.id = id;
    }
}
