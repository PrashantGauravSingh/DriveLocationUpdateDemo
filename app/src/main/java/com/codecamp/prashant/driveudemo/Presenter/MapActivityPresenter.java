package com.codecamp.prashant.driveudemo.Presenter;

import org.json.JSONObject;

import java.util.List;

public interface MapActivityPresenter {

    interface presenter{

        void requestDataServer();

    }

    interface MainView {

        void setLatLong(String response);

    }
    /**
     * Intractors are classes built for fetching data from your database, web services, or any other data source.
     **/
    interface GetNoticeIntractor {

        interface OnFinishedListener {
            void onFinished(String response);
            void onFailure(Throwable t);
        }

        void getLatLongData(OnFinishedListener onFinishedListener);
    }
}
