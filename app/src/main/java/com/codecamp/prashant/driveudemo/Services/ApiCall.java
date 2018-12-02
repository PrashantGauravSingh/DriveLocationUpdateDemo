package com.codecamp.prashant.driveudemo.Services;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiCall {

    @GET("explore")
    Call<ResponseBody> getKnownLatLong();
}
