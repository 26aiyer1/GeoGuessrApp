package com.example.googlemapstrial;

import android.location.Geocoder;
import android.content.Context;
import android.location.Address;
import java.util.List;
import java.io.IOException;

public class GeoCodingHelper {

    public static String getCountryFromLocation(Context context, double latitude, double longitude) {
        Geocoder geocoder = new Geocoder(context);
        String countryName = null;
        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (addresses != null && addresses.size() > 0) {
                countryName = addresses.get(0).getCountryName();
            }
            else{
                countryName = "no country just ocean";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return countryName;
    }

    /*public static double calculateDistanceToSanFrancisco(double latitude, double longitude) {
        final double SAN_FRANCISCO_LATITUDE = 37.7749;
        final double SAN_FRANCISCO_LONGITUDE = -122.4194;

        double earthRadius = 6371;
        double dLat = Math.toRadians(SAN_FRANCISCO_LATITUDE - latitude);
        double dLon = Math.toRadians(SAN_FRANCISCO_LONGITUDE - longitude);

        double a = Math.pow(Math.sin(dLat / 2), 2) + Math.cos(Math.toRadians(latitude)) * Math.cos(Math.toRadians(SAN_FRANCISCO_LATITUDE)) * Math.pow(Math.sin(dLon / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return earthRadius * c;
    }*/
}