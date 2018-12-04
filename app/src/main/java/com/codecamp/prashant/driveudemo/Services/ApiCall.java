package com.codecamp.prashant.driveudemo.Services;

import com.codecamp.prashant.driveudemo.Model.Datamodel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiCall {

    @GET("/explore")
    Call<Datamodel> getKnownLatLong();
}
