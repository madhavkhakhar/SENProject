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
import android.widget.ImageView;
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
    protected static int correctans=2;
    public static int score=0;
    public MediaPlayer mp;
    public TextView score_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level3_1);
        final ImageView []image = new ImageView[4];
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

        final ImageView speaker=(ImageView) findViewById(R.id.imageView6);
        score_text=(TextView) findViewById(R.id.score);
        Random r=new Random();
        int random1=r.nextInt(4);
        correctans=random1;
        for(int i=0;i<4;i++){
            if(i==correctans){
                image[i].setImageResource(resource_d);
            }
            else{
                image[i].setImageResource(resource_s);
            }
        }

//set the listener
        image[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                nextQues(image,0);
            }
        });

        image[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                nextQues(image, 1);
            }
        });

        image[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                nextQues(image, 2);
            }
        });

        image[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                nextQues(image, 3);

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
        new Thread(){
            public void run(){
                try{
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            Vibrator v = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
                            if(image_no==correctans) {
                                image[image_no].setColorFilter(Color.argb(255, 0, 255, 0));
                                score++;
                                final Toast toast = Toast.makeText(getApplicationContext(), "CORRECT", Toast.LENGTH_SHORT);
                                toast.show();

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        toast.cancel();
                                    }
                                }, 500);
                                score_text.setText(String.valueOf(score) + "/10");
                            }
                            else{
                                image[image_no].setColorFilter(Color.argb(255, 255, 0, 0));
                                image[correctans].setColorFilter(Color.argb(255, 0, 255, 0));
                                final Toast toast = Toast.makeText(getApplicationContext(), "WRONG", Toast.LENGTH_SHORT);
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
                            int resource_s = getResources().getIdentifier("s"+String.valueOf(count), "drawable", getPackageName());
                            int resource_d = getResources().getIdentifier("d"+String.valueOf(count), "drawable", getPackageName());

                            Random r=new Random();
                            int random=r.nextInt(4);
                            correctans=random;
                            for(int i=0;i<4;i++){
                                if(i==correctans){
                                    image[i].setImageResource(resource_d);
                                }
                                else{
                                    image[i].setImageResource(resource_s);
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
