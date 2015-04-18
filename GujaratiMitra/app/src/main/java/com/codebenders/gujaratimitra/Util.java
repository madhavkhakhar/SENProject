package com.codebenders.gujaratimitra;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.codebenders.gujaratimitra.database.AppDB;

import java.io.IOException;

/**
 * Created by saurabh on 26/3/15.
 */
public class Util {

    public static MediaPlayer mediaPlayer;
    public static AppDB appDB;
    public static AppPreferences prefs;


    public static void playMediaFromPath(String path) {
        mediaPlayer.reset();
        try {
            mediaPlayer.setDataSource(path);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*public static void setNextLevel(final Context c) {
        LinearLayout l = new LinearLayout(c);
        ((Activity) c).setContentView(l);
        ImageView iv = new ImageView(c);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        iv.setLayoutParams(lp);
        iv.setImageResource(R.drawable.nextlevel);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) c).finish();
            }
        });
        l.addView(iv);
    }*/

    public static void setNextLevel(final Context c, int score, int sublevelNo, int levelNo, boolean isLast) {
        System.out.println("util "+levelNo);
        if (appDB.getSubLevelScore(prefs.getStudentId(), sublevelNo) <= score)
            appDB.addSubLevelScore(levelNo, sublevelNo, score, prefs.getStudentId());
        if (appDB.getLastLevelUnlocked(prefs.getStudentId()) >= levelNo && isLast)
            appDB.setCurrentLevel(prefs.getStudentId(), levelNo+1);
        LinearLayout l = new LinearLayout(c);
        ((Activity) c).setContentView(l);
        ImageView iv = new ImageView(c);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        iv.setLayoutParams(lp);
        iv.setImageResource(R.drawable.nextlevel);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) c).finish();
            }
        });
        l.addView(iv);
    }

    public static void setImageFromPath(ImageView imView, String path) {
        Bitmap b = BitmapFactory.decodeFile(path);
        imView.setImageBitmap(b);
    }
}
