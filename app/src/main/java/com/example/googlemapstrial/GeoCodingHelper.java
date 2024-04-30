package com.example.googlemapstrial;

import android.location.Geocoder;
import android.content.Context;
import android.location.Address;
import java.util.List;
import java.io.IOException;

/**
 * GeoCodingHelper class provides methods for reverse geocoding to obtain country information from location coordinates.
 * Source: https://developer.android.com/reference/android/location/Geocoder
 */
public class GeoCodingHelper {

    /**
     * Retrieves the country name from the given latitude and longitude coordinates using reverse geocoding.
     *
     * @param context   The application context.
     * @param latitude  The latitude coordinate.
     * @param longitude The longitude coordinate.
     * @return The name of the country corresponding to the location coordinates.
     */
    public static String getCountryFromLocation(Context context, double latitude, double longitude) {
        Geocoder geocoder = new Geocoder(context);
        String countryName = null;
        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (addresses != null && addresses.size() > 0) {
                countryName = addresses.get(0).getCountryName();
            } else {
                countryName = "No country information available";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return countryName;
    }
}
