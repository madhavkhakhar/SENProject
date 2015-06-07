package com.codebenders.gujaratimitra;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.provider.Settings;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
    private static int mScore;

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

    public static void setNextLevel(final Context c, final int score, final int sublevelNo, final int levelNo, final boolean isLast, boolean isPager) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        if(isPager){
            mScore=-1;
            builder.setIcon(R.drawable.alert);
            builder.setTitle("Score");
            final EditText input = new EditText(c);
            input.setInputType(InputType.TYPE_CLASS_NUMBER);
            builder.setCancelable(false);
            builder.setMessage("Please enter the score");
            builder.setView(input);
            builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,10,0);
                    mScore = Integer.parseInt(input.getText().toString());
                    System.out.println("score  " + mScore);
                    if (appDB.getSubLevelScore(prefs.getStudentId(), sublevelNo, levelNo) <= mScore)
                        appDB.addSubLevelScore(levelNo, sublevelNo, mScore, prefs.getStudentId());
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    if (appDB.getSubLevelScore(prefs.getStudentId(), sublevelNo, levelNo) <= score)
                        appDB.addSubLevelScore(levelNo, sublevelNo, score, prefs.getStudentId());
                }
            });
            builder.show();
        }
        else {
            System.out.println("score  " + score);
            System.out.println("sublevelscore " + appDB.getSubLevelScore(prefs.getStudentId(), sublevelNo, levelNo));
            System.out.println("lastlevel " + appDB.getLastLevelUnlocked(prefs.getStudentId()));
            if (appDB.getSubLevelScore(prefs.getStudentId(), sublevelNo, levelNo) <= score)
                appDB.addSubLevelScore(levelNo, sublevelNo, score, prefs.getStudentId());
        }
        if (appDB.getLastLevelUnlocked(prefs.getStudentId()) >= levelNo && isLast)
            appDB.setCurrentLevel(prefs.getStudentId(), levelNo + 1);
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
