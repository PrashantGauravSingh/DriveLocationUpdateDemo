package com.codecamp.prashant.driveudemo.Presenter;

public class MapActivityPresenterImp implements MapActivityPresenter.MainView,MapActivityPresenter.GetNoticeIntractor.OnFinishedListener {

    private MapActivityPresenter.MainView mainView;
    private MapActivityPresenter.GetNoticeIntractor getNoticeIntractor;

    public MapActivityPresenterImp(MapActivityPresenter.MainView mainView, MapActivityPresenter.GetNoticeIntractor getNoticeIntractor) {
        this.mainView = mainView;
        this.getNoticeIntractor = getNoticeIntractor;
    }

    @Override
    public void onFinished(String response) {

    }

    @Override
    public void onFailure(Throwable t) {

    }

    @Override
    public void setLatLong(String response) {

    }
}
