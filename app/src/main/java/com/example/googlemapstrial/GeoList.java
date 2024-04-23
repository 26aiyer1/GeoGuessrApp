package com.example.googlemapstrial;

public class GeoList {
    String country;
    String city;

    public GeoList(String country, String city) {
        this.country = country;
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "GeoList{" +
                "country='" + country + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
