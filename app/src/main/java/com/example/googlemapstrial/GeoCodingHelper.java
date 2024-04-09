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
                countryName = "no country just water";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return countryName;
    }
}