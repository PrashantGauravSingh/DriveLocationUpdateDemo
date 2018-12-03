package com.codecamp.prashant.driveudemo.Presenter;

import android.util.Log;

import org.json.JSONObject;

public class MapActivityPresenterImp implements MapActivityPresenter.presenter,MapActivityPresenter.MainView,MapActivityPresenter.GetNoticeIntractor.OnFinishedListener {

    private MapActivityPresenter.MainView mainView;
    private MapActivityPresenter.GetNoticeIntractor getNoticeIntractor;

    public MapActivityPresenterImp(MapActivityPresenter.MainView mainView, MapActivityPresenter.GetNoticeIntractor getNoticeIntractor) {
        this.mainView = mainView;
        this.getNoticeIntractor = getNoticeIntractor;
    }

    @Override
    public void onFinished(String response) {

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
    public void setLatLong(String response) {

        Log.e("Map","Data new "+response);
    }
}
