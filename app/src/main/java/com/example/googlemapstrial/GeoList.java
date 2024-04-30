package com.example.googlemapstrial;

/**
 * GeoList class represents a geographical location consisting of a country and a city.
 */
public class GeoList {
    String country;
    String city;

    /**
     * Constructs a new GeoList object with the specified country and city.
     *
     * @param country The country of the geographical location.
     * @param city    The city of the geographical location.
     */
    public GeoList(String country, String city) {
        this.country = country;
        this.city = city;
    }

    /**
     * Gets the country of the geographical location.
     *
     * @return The country.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Gets the city of the geographical location.
     *
     * @return The city.
     */
    public String getCity() {
        return city;
    }

    /**
     * Returns a string representation of the GeoList object.
     *
     * @return A string representation containing the country and city.
     */
    @Override
    public String toString() {
        return "GeoList{" +
                "country='" + country + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
