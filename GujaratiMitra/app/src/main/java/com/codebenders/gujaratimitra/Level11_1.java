package com.codebenders.gujaratimitra;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class Level11_1 extends ActionBarActivity {
    protected static int count = 0;
    protected static int correctans = 2;
    public static int score = 0;
    public TextView score_text;
    int NUM_QUES=13;
    public int disable = 0;
    List<Integer> rand_array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level11_1);
        final ImageView[] image = new ImageView[4];

        rand_array = new ArrayList<Integer>(13);
        for (int i = 0; i < 13; i++) {
            rand_array.add(i + 1);
        }
        Collections.shuffle(rand_array);

        image[0] = (ImageView) findViewById(R.id.imageView);
        image[1] = (ImageView) findViewById(R.id.imageView2);
        image[2] = (ImageView) findViewById(R.id.imageView3);
        image[3] = (ImageView) findViewById(R.id.imageView4);

        final ImageView speaker = (ImageView) findViewById(R.id.imageView6);
        ImageView question = (ImageView) findViewById(R.id.imageView5);
        Util.setImageFromPath(question, Environment.getExternalStorageDirectory() + "/GujaratiMitra/All Questions/que_11_1.png");

        score_text = (TextView) findViewById(R.id.score);
        Random r = new Random();
        int random1 = r.nextInt(4);
        correctans = random1;
        for (int i = 0; i < 4; i++) {
            Util.setImageFromPath(image[i], Environment.getExternalStorageDirectory() + "/GujaratiMitra/l11/1/" + Integer.toString(rand_array.get(i)) + ".png");
        }

        image[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (disable == 0) {
                    count++;
                    nextQues(image, 0);
                }
            }
        });

        image[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (disable == 0) {
                    count++;
                    nextQues(image, 1);
                }
            }
        });

        image[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (disable == 0) {
                    count++;
                    nextQues(image, 2);
                }
            }
        });

        image[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (disable == 0) {
                    count++;
                    nextQues(image, 3);
                }

            }
        });

        speaker.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    Util.mediaPlayer.setDataSource(Environment.getExternalStorageDirectory() + "/sample.mp3");//Write your location here
                    Util.mediaPlayer.prepare();
                    Util.mediaPlayer.start();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void nextQues(final ImageView[] image, final int image_no) {
        final ImageView green_tick = new ImageView(this);
        final ImageView red_cross = new ImageView(this);
        final Toast toast = new Toast(this);

        green_tick.setImageResource(R.drawable.greentick);
        red_cross.setImageResource(R.drawable.redcross);

        new Thread() {
            public void run() {
                try {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            disable = 1;
                            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            if (image_no == correctans) {

                                image[image_no].setColorFilter(Color.argb(255, 0, 255, 0));
                                score++;

                                toast.setView(green_tick);
                                toast.show();

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        toast.cancel();
                                    }
                                }, 500);
                                score_text.setText("SCORE " + String.valueOf(score) + "/10");
                            } else {

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
                            disable = 0;
                            if(count>=NUM_QUES){
                                Util.setNextLevel(Level11_1.this,score,1,11,false);
                            }
                            Random r = new Random();
                            int random = r.nextInt(4);
                            correctans = random;
                            Collections.shuffle(rand_array);

                            for (int i = 0; i < 4; i++) {
                                Util.setImageFromPath(image[i], Environment.getExternalStorageDirectory() + "/GujaratiMitra/l11/1/img_" + Integer.toString(rand_array.get(i)) + ".png");
                                image[i].setColorFilter(Color.argb(255, 0, 0, 0));
                            }
                            image[0].setColorFilter(Color.argb(255, 0, 0, 0));
                            image[1].setColorFilter(Color.argb(255, 0, 0, 0));
                            image[2].setColorFilter(Color.argb(255, 0, 0, 0));
                            image[3].setColorFilter(Color.argb(255, 0, 0, 0));

                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

}
