package com.example.manng.csc_25_07_navigationdrawerlayout2.adapter;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by DINH TDT on 11/08/2017.
 */

public class PreferenceManager {
    private static final String PREF_NAME = "welcome" ;
    private static final int PRIVATE_MODE = 0;
    private static final String IS_FIRST_TIME_LAUCH = "IsFirstTimeLauch";
    Context context;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;

    public PreferenceManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME,PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public void setFirstTimeLauch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUCH,isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLauch() {
        return sharedPreferences.getBoolean(IS_FIRST_TIME_LAUCH,true);
    }
}
