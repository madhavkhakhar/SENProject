package com.codebenders.gujaratimitra;

import android.app.Application;
import android.content.SharedPreferences;
import android.media.MediaPlayer;

import com.codebenders.gujaratimitra.database.AppDB;

import java.util.Map;
import java.util.Set;

/**
 * Created by saurabh on 8/4/15.
 */
public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Util.mediaPlayer = new MediaPlayer();
        Util.appDB = new AppDB(getApplicationContext());
        Util.prefs = new AppPreferences(getApplicationContext());
    }
}

