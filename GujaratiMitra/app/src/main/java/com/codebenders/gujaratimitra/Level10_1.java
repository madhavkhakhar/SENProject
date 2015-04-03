package com.codebenders.gujaratimitra;

import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Level10_1 extends ActionBarActivity {
    protected static int count=0;
    protected static int correctans=2;
    public static int score=0;
    public MediaPlayer mp;
    public TextView score_text;
    public int disable=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level10_1);

        final ImageView[]image = new ImageView[5];
        mp=new MediaPlayer();

        final List<Integer> rand_array=new ArrayList<Integer>(50);
        for(int i=0;i<50;i++){
            rand_array.add(i+1);
        }
        Collections.shuffle(rand_array);

        final int resource_s = getResources().getIdentifier("s"+String.valueOf(rand_array.get(count)), "drawable", getPackageName());
        final int resource_d = getResources().getIdentifier("d"+String.valueOf(rand_array.get(count)), "drawable", getPackageName());

        image[0] = (ImageView) findViewById(R.id.imageView);
        image[1] = (ImageView) findViewById(R.id.imageView2);
        image[2] = (ImageView) findViewById(R.id.imageView3);
        image[3] = (ImageView) findViewById(R.id.imageView4);
        image[4]=  (ImageView) findViewById(R.id.imageView5);

        final ImageView speaker=(ImageView) findViewById(R.id.imageView6);

    }




}
