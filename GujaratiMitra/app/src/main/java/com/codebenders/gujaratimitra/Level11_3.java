package com.codebenders.gujaratimitra;

import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.Handler;
import android.os.SystemClock;
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
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class Level11_3 extends ActionBarActivity {
    ImageView q;
    ImageView[] a;
    Vibrator vib;
    private int qIndex=1,correct=0;
    TextView score;
    private  int mscore=0;
    private int NUM_PAGES=22;
    private boolean sleeping;
    private List<Integer> indexes;
    private  ImageView speaker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level11_3);
        a=new ImageView[4];
        sleeping = false;
        vib = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

        int[] aId={R.id.imgViewa1, R.id.imgViewa2, R.id.imgViewa3, R.id.imgViewa4};
        for(int i=0;i<4;i++){
            a[i]=(ImageView)findViewById(aId[i]);
        }
        q=(ImageView)findViewById(R.id.imgViewq1);

        score = (TextView)findViewById(R.id.score);
        speaker = (ImageView) findViewById(R.id.speaker);
        speaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.playMediaFromPath(Environment.getExternalStorageDirectory()+"/GujaratiMitra/l11/3/aud_que_11_3.wav");
            }
        });

        Util.setImageFromPath((ImageView)findViewById(R.id.q_image), Environment.getExternalStorageDirectory() + "/GujaratiMitra/l11/3/que_11_3.png");
        ImageView speaker = (ImageView) findViewById(R.id.speaker);
        speaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //play question audio file
            }
        });

        Util.setImageFromPath(q, Environment.getExternalStorageDirectory() + "/GujaratiMitra/l11/2/img" + Integer.toString(qIndex) + "_1.png");

        indexes = new ArrayList<Integer>();
        indexes.add(0, 0);
        indexes.add(1, 1);
        indexes.add(2, 2);
        indexes.add(3, 3);
        Collections.shuffle(indexes);
        int random1=indexes.get(0);
        correct=random1;

        for(int j=0;j<4;j++){
            if(j==correct){
                Util.setImageFromPath(a[j], Environment.getExternalStorageDirectory() + "/GujaratiMitra/l11/2/img"+ String.valueOf(qIndex) + "_2"+".png");
            }
            else{
                int ind = (qIndex+indexes.get(j)+1)%22;
                if (ind == qIndex)
                    ind = (qIndex+1)%22;
                Util.setImageFromPath(a[j], Environment.getExternalStorageDirectory() + "/GujaratiMitra/l11/2/img" + String.valueOf(ind) + "_2.png");
            }
            a[j].setBackgroundResource(R.drawable.image_border_black);
        }

        a[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!sleeping) {
                    qIndex++;
                    nextQues(0);
                }
            }
        });

        a[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!sleeping) {
                    qIndex++;
                    nextQues(1);
                }
            }
        });

        a[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!sleeping) {
                    qIndex++;
                    nextQues(2);
                }
            }
        });

        a[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!sleeping) {
                    qIndex++;
                    nextQues(3);
                }
            }
        });

    }

    public void nextQues(final int ansClicked) {
        sleeping = true;
        final ImageView green_tick = new ImageView(this);
        final ImageView red_cross = new ImageView(this);
        final Toast toast = new Toast(this);

        green_tick.setImageResource(R.drawable.greentick);
        red_cross.setImageResource(R.drawable.redcross);

        new Thread() {
            public void run() {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        if (correct == ansClicked) {
                            mscore++;
                            score.setText("SCORE " + String.valueOf(mscore) + "/10");
                            toast.setView(green_tick);
                            toast.show();
                            if (a[correct] != null) {
                                a[correct].setBackgroundResource(R.drawable.image_border_green);
                            }

                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    toast.cancel();
                                }
                            }, 500);
                        } else {
                            toast.setView(red_cross);
                            toast.show();
                            if (a[ansClicked] != null) {
                                a[ansClicked].setBackgroundResource(R.drawable.image_border_red);
                            }
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    toast.cancel();
                                }
                            }, 500);
                            vib.vibrate(500);
                        }

                    }
                });
                SystemClock.sleep(1000);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(qIndex>=NUM_PAGES){
                            Util.setNextLevel(Level11_3.this,mscore,3,11,true);
                        }
                        else{
                            Util.setImageFromPath(q, Environment.getExternalStorageDirectory() + "/GujaratiMitra/l11/2/img" + Integer.toString(qIndex) + "_1.png");

                            Collections.shuffle(indexes);
                            int random1=indexes.get(0);
                            correct=random1;


                            for(int j=0;j<4;j++){
                                if(j==correct){
                                    Util.setImageFromPath(a[j], Environment.getExternalStorageDirectory() + "/GujaratiMitra/l11/2/img" + String.valueOf(qIndex) + "_2"+".png");
                                }
                                else{
                                    int ind = (qIndex+indexes.get(j)+1)%22;
                                    if (ind == qIndex)
                                        ind = (qIndex+1)%22;
                                    Util.setImageFromPath(a[j], Environment.getExternalStorageDirectory() + "/GujaratiMitra/l11/2/img"+ String.valueOf(ind) + "_2.png");
                                }
                                a[j].setBackgroundResource(R.drawable.image_border_black);
                            }
                            sleeping = false;
                        }

                    }
                });
            }
        }.start();

    }
}
