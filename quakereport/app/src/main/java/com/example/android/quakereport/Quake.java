package com.example.android.quakereport;

public class Quake {
    private String location;
    private double magnitude;
    private long quake_date;
    /** Website URL of the earthquake */
    private String mUrl;

    public Quake(String loc,double mag,long q_date,String url){
        location = loc;
        magnitude = mag;
        quake_date = q_date;
        mUrl = url;
    }

    public String getLocation(){
        return location;
    }

    public double getMagnitude(){
        return magnitude;
    }

    public long getQuake_date(){
        return quake_date;
    }

    public String getUrl(){
        return mUrl;
    }
}
