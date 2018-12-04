package com.codecamp.prashant.driveudemo.Presenter;

import android.util.Log;

import com.codecamp.prashant.driveudemo.Model.Datamodel;
import com.codecamp.prashant.driveudemo.Services.ApiCall;
import com.codecamp.prashant.driveudemo.Services.Client;
import retrofit2.Call;
import retrofit2.Response;

public class callServerData implements MapActivityPresenter.GetNoticeIntractor {

    @Override
    public void getLatLongData(final OnFinishedListener onFinishedListener) {

        try{

            Client client=new Client();
            ApiCall service=client.getClient().create(ApiCall.class);


            Call<Datamodel> call=service.getKnownLatLong();
            call.enqueue(new retrofit2.Callback<Datamodel>() {
                @Override
                public void onResponse(Call<Datamodel> call, Response<Datamodel> response) {

                    onFinishedListener.onFinished(response.body());
                }

                @Override
                public void onFailure(Call<Datamodel> call, Throwable t) {

                    onFinishedListener.onFailure(t);
                    Log.e("Error"," "+t.getMessage());
                }


            });

        }catch (Exception e){
            e.getLocalizedMessage();
        }
    }

}
