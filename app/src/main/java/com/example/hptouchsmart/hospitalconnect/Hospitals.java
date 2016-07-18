package com.example.hptouchsmart.hospitalconnect;

/**
 * Created by hp TouchSmart on 7/3/2016.
 */
public class Hospitals {

    String hospitalName;
    String hospitalAddress;
    String facilities;
    String phone;
    String lat;
    String lng;
    String website;


    public Hospitals(String hospitalName, String hospitalAddress, String facilities, String phone, String lat, String lng, String website) {
        this.hospitalName = hospitalName;
        this.hospitalAddress = hospitalAddress;
        this.facilities = facilities;
        this.phone = phone;
        this.lat = lat;
        this.lng = lng;
        this.website = website;
    }



}
