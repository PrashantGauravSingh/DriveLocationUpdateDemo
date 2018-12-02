package com.codecamp.prashant.driveudemo.Model;

import com.google.gson.annotations.SerializedName;

public class Datamodel {

    @SerializedName("status")
    private String status;
    @SerializedName("latitude")
    private String latitude;
    @SerializedName("longitude")
    private String longitude;

    public Datamodel(String status, String latitude, String longitude) {
        this.status = status;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getStatus() {
        return status;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }
}
