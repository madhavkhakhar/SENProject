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
        Util.mPref = new SharedPreferences() {
            @Override
            public Map<String, ?> getAll() {
                return null;
            }

            @Override
            public String getString(String key, String defValue) {
                return null;
            }

            @Override
            public Set<String> getStringSet(String key, Set<String> defValues) {
                return null;
            }

            @Override
            public int getInt(String key, int defValue) {
                return 0;
            }

            @Override
            public long getLong(String key, long defValue) {
                return 0;
            }

            @Override
            public float getFloat(String key, float defValue) {
                return 0;
            }

            @Override
            public boolean getBoolean(String key, boolean defValue) {
                return false;
            }

            @Override
            public boolean contains(String key) {
                return false;
            }

            @Override
            public Editor edit() {
                return null;
            }

            @Override
            public void registerOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {

            }

            @Override
            public void unregisterOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {

            }
        };
    }
}

