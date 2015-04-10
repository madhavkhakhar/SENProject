package com.codebenders.gujaratimitra;

import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;



public class Level3_1 extends ActionBarActivity {
    protected static int count=0;
    private int NUM_QUE=50;
    protected static int correctans=2;
    private static int TOTAL_SCORE=10;
    public static int SCORE=0;
    public MediaPlayer mp;
    public TextView score_text;
    public int disable=0;
    List<Integer> rand_array=new ArrayList<Integer>(50);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level3_1);
        final ImageView []image = new ImageView[4];
        mp=new MediaPlayer();

        for(int i=0;i<50;i++){
            rand_array.add(i+1);
        }
        Collections.shuffle(rand_array);
        //final int resource_s = getResources().getIdentifier("s"+String.valueOf(rand_array.get(count)), "drawable", getPackageName());
        //final int resource_d = getResources().getIdentifier("d"+String.valueOf(rand_array.get(count)), "drawable", getPackageName());

        image[0] = (ImageView) findViewById(R.id.imageView);
        image[1] = (ImageView) findViewById(R.id.imageView2);
        image[2] = (ImageView) findViewById(R.id.imageView3);
        image[3] = (ImageView) findViewById(R.id.imageView4);

        final ImageView speaker=(ImageView) findViewById(R.id.imageView6);
        ImageView question = (ImageView)findViewById(R.id.imageView5);
        question.setImageResource(R.drawable.level3title);
        score_text=(TextView) findViewById(R.id.score);
        Random r=new Random();
        int random1=r.nextInt(4);
        correctans=random1;
        for(int i=0;i<4;i++){
            if(i==correctans){
                Util.setImageFromPath(image[i], Environment.getExternalStorageDirectory() + "/GujaratiMitra/l3/1/d" + Integer.toString(rand_array.get(count)) + ".png");
            }
            else{
                Util.setImageFromPath(image[i], Environment.getExternalStorageDirectory() + "/GujaratiMitra/l3/1/s" + Integer.toString(rand_array.get(count)) + ".png");

            }
        }

//set the listener
        if(count==NUM_QUE-1){
            LinearLayout l = new LinearLayout(getApplicationContext());
            setContentView(l);
            ImageView iv = new ImageView(getApplicationContext());
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            iv.setLayoutParams(lp);
            iv.setImageResource(R.drawable.nextlevel);
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            l.addView(iv);
        }
        image[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(disable==0) {
                    count++;
                    nextQues(image, 0);
                }
            }
        });

        image[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(disable==0) {
                    count++;
                    nextQues(image, 1);
                }
            }
        });

        image[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(disable==0) {
                    count++;
                    nextQues(image, 2);
                }
            }
        });

        image[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(disable==0) {
                    count++;
                    nextQues(image, 3);
                }

            }
        });

        speaker.setOnClickListener(new View.OnClickListener(){
           public void onClick(View v){
               try{
                   mp.setDataSource(Environment.getExternalStorageDirectory()+"/sample.mp3");//Write your location here
                   mp.prepare();
                   mp.start();

               }catch(Exception e){e.printStackTrace();}
           }
        });

    }

    public void nextQues(final ImageView []image, final int image_no){
        final ImageView green_tick=new ImageView(this);
        final ImageView red_cross=new ImageView(this);
        final Toast toast = new Toast(this);

        green_tick.setImageResource(R.drawable.greentick);
        red_cross.setImageResource(R.drawable.redcross);

        new Thread(){
            public void run(){
                try{
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            disable=1;
                            Vibrator v = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
                            if(image_no==correctans) {

                                image[image_no].setColorFilter(Color.argb(255, 0, 255, 0));
                                SCORE++;

                                toast.setView(green_tick);
                                toast.show();

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        toast.cancel();
                                    }
                                }, 500);
                                score_text.setText("SCORE "+String.valueOf(SCORE) + "/" + String.valueOf(TOTAL_SCORE));
                            }
                        else{

                                image[image_no].setColorFilter(Color.argb(255, 255, 0, 0));
                                image[correctans].setColorFilter(Color.argb(255, 0, 255, 0));
                                toast.setView(red_cross);
                                toast.show();

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        toast.cancel();
                                    }
                                }, 500);
                                v.vibrate(500);

                            }
                        }
                    });
                    Thread.sleep(1000);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            disable=0;
                            if(count==10){
                                count=0;
                                SCORE=0;
                                Util.setNextLevel(Level3_1.this);
                            }

                            Random r=new Random();
                            int random=r.nextInt(4);
                            correctans=random;
                            for(int i=0;i<4;i++){
                                if(i==correctans){
                                   // image[i].setImageResource(resource_d);
                                    Util.setImageFromPath(image[i], Environment.getExternalStorageDirectory() + "/GujaratiMitra/l3/1/d" + String.valueOf(rand_array.get(count)) + ".png");
                                }
                                else{
                                    Util.setImageFromPath(image[i], Environment.getExternalStorageDirectory() + "/GujaratiMitra/l3/1/s" + String.valueOf(rand_array.get(count)) + ".png");
                                }
                            }

                            image[0].setColorFilter(Color.argb(255, 0, 0, 0));
                            image[1].setColorFilter(Color.argb(255, 0, 0, 0));
                            image[2].setColorFilter(Color.argb(255, 0, 0, 0));
                            image[3].setColorFilter(Color.argb(255, 0, 0, 0));

                        }
                    });
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }.start();
    }

}
