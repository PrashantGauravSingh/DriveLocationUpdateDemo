package com.codecamp.prashant.driveudemo.Presenter;

import android.util.Log;

import com.codecamp.prashant.driveudemo.Services.ApiCall;
import com.codecamp.prashant.driveudemo.Services.Client;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class callServerData implements MapActivityPresenter.GetNoticeIntractor {

    @Override
    public void getLatLongData(final OnFinishedListener onFinishedListener) {

        try{

            Client client=new Client();
            ApiCall service=client.getClient().create(ApiCall.class);


            Call<ResponseBody> call=service.getKnownLatLong();
            call.enqueue(new retrofit2.Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                    String data=response.headers().toString();

                    onFinishedListener.onFinished(data);
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                    onFinishedListener.onFailure(t);
                    Log.e("Error"," "+t.getMessage());
                }


            });

        }catch (Exception e){
            e.getLocalizedMessage();
        }
    }

}
