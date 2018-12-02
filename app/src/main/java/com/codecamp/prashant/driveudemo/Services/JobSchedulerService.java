package com.codecamp.prashant.driveudemo.Services;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;

import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class JobSchedulerService extends JobService {

    private static final String TAG="JOB_SCHEDULER_SERVICE";
    private boolean jobCanceled=false;
    String body;
    JSONObject jsonObject;
    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        backgroundTask(jobParameters);
        return true;
    }

    public void backgroundTask(JobParameters jobParameters){

        new Thread(new Runnable() {
            @Override
            public void run() {
                getUpdatedLatLong();
            }
        }).start();
    }
    public void getUpdatedLatLong() {

        try{

            Client client=new Client();
            ApiCall service=client.getClient().create(ApiCall.class);


            Call<ResponseBody> call=service.getKnownLatLong();
            call.enqueue(new retrofit2.Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        body = response.body().string();
                        Log.e("Latest Data from Api "," "+body);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        jsonObject = new JSONObject(body);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                    Log.e("Error"," "+t.getMessage());
                }


            });

        }catch (Exception e){
            e.getLocalizedMessage();
        }
    }
    @Override
    public boolean onStopJob(JobParameters jobParameters) {

        Log.e(TAG,"Job scheduled STOPPED ");
        jobCanceled=true;
        return false;
    }
}
