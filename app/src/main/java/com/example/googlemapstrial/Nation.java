package com.example.googlemapstrial;

public class Nation {
    private String country;
    private String city;

    //constructor to initialize a Movie object with the provided values
    public Nation(String country, String city){
        this.country = country;
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }
}
