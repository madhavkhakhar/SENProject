package com.codebenders.gujaratimitra;

import android.app.Application;
import android.media.MediaPlayer;

import com.codebenders.gujaratimitra.database.AppDB;

/**
 * Created by saurabh on 8/4/15.
 */
public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Util.mediaPlayer = new MediaPlayer();
        Util.appDB = new AppDB(getApplicationContext());

    }
}

