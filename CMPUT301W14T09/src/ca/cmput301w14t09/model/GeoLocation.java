package ca.cmput301w14t09.model;

import java.io.Serializable;

public class GeoLocation implements Serializable {

    private static final long serialVersionUID = 60L;
    private double latitude;
    private double longitude;
    private String name;

    public GeoLocation() {
        latitude = 0.0;
        longitude = 0.0;
        name = "";
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }


    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public double calculateProximity(GeoLocation targetLocation){
        return 0;
    }

}
