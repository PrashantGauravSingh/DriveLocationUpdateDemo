package com.codecamp.prashant.driveudemo.Presenter;

import com.codecamp.prashant.driveudemo.Model.Datamodel;

import retrofit2.Response;

public interface MapActivityPresenter {

    interface presenter{

        void requestDataServer();

    }

    interface MainView {

        void setLatLong(Datamodel response);

    }
    /**
     * Intractors are classes built for fetching data from your database, web services, or any other data source.
     **/
    interface GetNoticeIntractor {

        interface OnFinishedListener {
            void onFinished(Datamodel response);
            void onFailure(Throwable t);
        }

        void getLatLongData(OnFinishedListener onFinishedListener);
    }
}
