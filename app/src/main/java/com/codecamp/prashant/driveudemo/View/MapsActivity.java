package com.codecamp.prashant.driveudemo.View;

import android.Manifest;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.codecamp.prashant.driveudemo.Model.Datamodel;
import com.codecamp.prashant.driveudemo.Presenter.MapActivityPresenter;
import com.codecamp.prashant.driveudemo.Presenter.MapActivityPresenterImp;
import com.codecamp.prashant.driveudemo.Presenter.callServerData;
import com.codecamp.prashant.driveudemo.R;
import com.codecamp.prashant.driveudemo.Services.JobSchedulerService;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.JSONObject;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback,MapActivityPresenter.MainView {

    private GoogleMap mMap;
    private static final int MY_REQUEST_CODE=100;
    private static final int JOB_ID=123;
    private static final int UPDATE_DURATION=15000;
    private ActivityPreference prefManager;
    JSONObject newObject;
    public static MapActivityPresenter.presenter presenter;
    Double Latitude;
    Double Longitude;

    @BindView(R.id.fab)
    FloatingActionButton FAB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefManager = new ActivityPreference(this);
        setContentView(R.layout.activity_maps);
        ButterKnife.bind(this);
        presenter = new MapActivityPresenterImp(this, new callServerData());


        /**
         *Obtain the SupportMapFragment and get notified when the map is ready to be used.
          */

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        if (prefManager.isPlayClicked())
            FAB.setImageResource(R.drawable.stop_button);
        else
            FAB.setImageResource(R.drawable.play_button);
    }

    @OnClick(R.id.fab)
    public void StartApiCall(View view){

        if(!prefManager.isPlayClicked()) {
           prefManager.PlayClicked(true);

           FAB.setImageResource(R.drawable.stop_button);
           ComponentName component = new ComponentName(this, JobSchedulerService.class);
           JobInfo jobInfo = new JobInfo.Builder(JOB_ID, component)
                   .setRequiresCharging(true)
                   .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                   .setPersisted(true)
                   .setPeriodic(UPDATE_DURATION)
     //              .setMinimumLatency(5000)
                   .build();
           JobScheduler jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
           int resultCode = jobScheduler.schedule(jobInfo);
           if (resultCode == JobScheduler.RESULT_SUCCESS) {
               Log.e("", "Job Scheduled");
           } else {
               Log.e("", "Job Scheduled failed.");
           }

       }else{
           FAB.setImageResource(R.drawable.play_button);
           JobScheduler jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
           jobScheduler.cancel(JOB_ID);

            prefManager.PlayClicked(false);
       }

    }



    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        /**
          *Here we are checking Location permission.
         */
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},MY_REQUEST_CODE);
            }
            return;
        }else{
            mMap.setMyLocationEnabled(true);
        }

        /** Add a marker in Kormangala and move the camera
         *
         */
        LatLng india = new LatLng(12.9279, 77.6271);
        mMap.addMarker(new MarkerOptions().position(india).title("Kormangala"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(india,10.2f));
    }

    /**
     *  Updating / Adding Marker on Map Whenever New latLong Comes.
     */


    @Override
    public void setLatLong(Datamodel response) {

            Latitude= Double.valueOf(response.getLatitude());
            Longitude= Double.valueOf(response.getLongitude());

         //   Log.e("Location Update","Lat :: " +Latitude+" Long :: "+Longitude);

        LatLng india = new LatLng(Double.valueOf(Latitude), Double.valueOf(Longitude));
        mMap.addMarker(new MarkerOptions().position(india).title("Updated LatLong"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(india,10.2f));
    }
}
