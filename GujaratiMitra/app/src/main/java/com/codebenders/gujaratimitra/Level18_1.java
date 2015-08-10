package com.codebenders.gujaratimitra;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
import java.util.Random;


public class Level18_1 extends ActionBarActivity {
    ImageView q;
    ImageView[] a;
    Vibrator vib;
    public MediaPlayer mp;
    private int qIndex=1;
    ArrayList<Integer> wrongImg;
    //ImageView check,replay;
    ImageView ques,check,replay;
    private boolean wait=false;
    private final int NUM_QUES=11;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level18_1);



        vib = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        ques=(ImageView) findViewById(R.id.que_statement);
        q=(ImageView) findViewById(R.id.imgViewq1);
        Util.setImageFromPath(ques, "/l16/1/que_16_1.png");

        Util.setImageFromPath(q, "/l16/1/img_e1_"+String.valueOf(qIndex)+".png");

        check=(ImageView) findViewById(R.id.check);
        replay=(ImageView) findViewById(R.id.replay);
        check.setVisibility(View.INVISIBLE);
        replay.setVisibility(View.INVISIBLE);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(wait==false){
                    qIndex++;
                    nextQues();
                }
            }
        });

        replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(wait==false){
                    nextQues();
                }
            }
        });
        final ImageView speaker = (ImageView)findViewById(R.id.speaker);
        speaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.playMediaFromPath("/l16/1/aud_que_16_1_16_2_16_3_18_1_18_2.wav");
            }
        });

        firstQues();

    }

    public void nextQues() {


        new Thread() {
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        wait=true;
                        if(qIndex>NUM_QUES){
                            SystemClock.sleep(500);
                            Util.setNextLevel(Level18_1.this,0,1,18,false,true);
                        }
                        else {
                            Util.setImageFromPath(q,  "/l16/1/img_e1_" +  Integer.toString(qIndex) + ".png");
                            q.setVisibility(View.VISIBLE);
                            check.setVisibility(View.INVISIBLE);
                            replay.setVisibility(View.INVISIBLE);

                        }
                    }
                });

                SystemClock.sleep(3000);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        wait=false;
                        q.setVisibility(View.INVISIBLE);
                        check.setVisibility(View.VISIBLE);
                        replay.setVisibility(View.VISIBLE);
                    }
                });
            }
        }.start();

    }


    public void firstQues() {

        new Thread() {
            public void run() {
                wait=true;

                SystemClock.sleep(3000);


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        wait=false;
                        q.setVisibility(View.INVISIBLE);
                        check.setVisibility(View.VISIBLE);
                        replay.setVisibility(View.VISIBLE);
                    }
                });
            }
        }.start();

    }


}
