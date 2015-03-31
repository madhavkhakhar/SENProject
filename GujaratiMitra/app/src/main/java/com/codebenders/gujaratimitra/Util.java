package com.codebenders.gujaratimitra;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.widget.ImageView;

import com.codebenders.gujaratimitra.database.AppDB;

import java.io.IOException;

/**
 * Created by saurabh on 26/3/15.
 */
public class Util {

    public static MediaPlayer mediaPlayer;
    public static AppDB appDB;

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

    public static void setImageFromPath(ImageView imView, String path) {
        Bitmap b = BitmapFactory.decodeFile(path);
        imView.setImageBitmap(b);
    }
}
