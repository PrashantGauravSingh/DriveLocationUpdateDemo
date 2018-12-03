package com.codecamp.prashant.driveudemo.View;

import android.content.Context;
import android.content.SharedPreferences;

public class ActivityPreference {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Context context;

    int PRIVATE_MODE=0;
    private static final String PREF_NAME="mapActivityScreen";
    private static final String FIRST_TIME_LAUNCH="is_play_clicked";

    public ActivityPreference(Context context) {
        this.context = context;
        preferences=context.getSharedPreferences(PREF_NAME,PRIVATE_MODE);
        editor=preferences.edit();
    }

    public void PlayClicked(boolean firstTimeLaunch){
        editor.putBoolean(FIRST_TIME_LAUNCH,firstTimeLaunch);
        editor.commit();
    }
    public boolean isPlayClicked(){
        return preferences.getBoolean(FIRST_TIME_LAUNCH,false);
    }
}
