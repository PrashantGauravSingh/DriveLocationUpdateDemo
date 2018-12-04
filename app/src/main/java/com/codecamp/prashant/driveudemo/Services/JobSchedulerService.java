package com.codecamp.prashant.driveudemo.Services;

import android.app.job.JobParameters;
import android.app.job.JobService;

import com.codecamp.prashant.driveudemo.View.MapsActivity;

public class JobSchedulerService extends JobService{

    private static final String TAG="JOB_SCHEDULER_SERVICE";
    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        backgroundTask(jobParameters);
        return true;
    }

    public void backgroundTask(JobParameters jobParameters){

          MapsActivity.presenter.requestDataServer();


    }
    @Override
    public boolean onStopJob(JobParameters jobParameters) {

       // Log.e(TAG,"Job scheduled STOPPED ");
        return false;
    }

}
