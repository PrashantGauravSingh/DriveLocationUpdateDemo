package com.codecamp.prashant.driveudemo.Presenter;

import android.util.Log;

import com.codecamp.prashant.driveudemo.Model.Datamodel;

import retrofit2.Response;


public class MapActivityPresenterImp implements MapActivityPresenter.presenter,MapActivityPresenter.MainView,MapActivityPresenter.GetNoticeIntractor.OnFinishedListener {

    private MapActivityPresenter.MainView mainView;
    private MapActivityPresenter.GetNoticeIntractor getNoticeIntractor;

    public MapActivityPresenterImp(MapActivityPresenter.MainView mainView, MapActivityPresenter.GetNoticeIntractor getNoticeIntractor) {
        this.mainView = mainView;
        this.getNoticeIntractor = getNoticeIntractor;
    }

    @Override
    public void onFinished(Datamodel response) {

        if(mainView!=null){
            mainView.setLatLong(response);
        }
    }


    @Override
    public void onFailure(Throwable t) {

    }


    @Override
    public void requestDataServer() {
        getNoticeIntractor.getLatLongData(this);
    }

    @Override
    public void setLatLong(Datamodel response) {

        Log.e("Map","Data new "+response);
    }
}
