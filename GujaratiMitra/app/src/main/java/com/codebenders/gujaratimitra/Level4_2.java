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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.Random;


public class Level4_2 extends ActionBarActivity {
    ImageView[] q,a;
    private int NUM_QUE=11;
    int SCORE=0,correct=0,queIndex=1,TOTAL_SCORE=11;
    Vibrator vib;
    int[] questions;
    int noOfQues;
    public int disable=0;
    private ImageView lSpeaker;
    private TextView txtscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level4_2);

        q=new ImageView[4];
        a=new ImageView[4];

        int[] qId={R.id.imgViewq1,R.id.imgViewq2, R.id.imgViewq3, R.id.imgViewq4};
        int[] aId={R.id.imgViewa1, R.id.imgViewa2, R.id.imgViewa3, R.id.imgViewa4};

        vib = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        txtscore = (TextView)findViewById(R.id.txtScore);
        txtscore.setText("SCORE:"+String.valueOf(SCORE)+"/"+String.valueOf(TOTAL_SCORE));
        lSpeaker = (ImageView)findViewById(R.id.lspeaker);
        lSpeaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.playMediaFromPath(Environment.getExternalStorageDirectory()+"/GujaratiMitra/l4/1/aud_0.mp3");
            }
        });

        for(int i=0;i<4;i++){
            q[i]=(ImageView)findViewById(qId[i]);
            q[i].setVisibility(View.INVISIBLE);
        }

        for(int i=0;i<4;i++){
            a[i]=(ImageView)findViewById(aId[i]);
        }


        questions= new int[]{4, 3, 3, 4, 3, 3, 3, 3, 4, 4, 3};


        noOfQues = questions[queIndex];
        for(int j=0;j<noOfQues;j++){
            q[j].setVisibility(View.VISIBLE);
            Util.setImageFromPath(q[j], Environment.getExternalStorageDirectory() + "/GujaratiMitra/l4/2/"+"level4_2_img_e"+String.valueOf(queIndex)+"_"+String.valueOf(j+1)+".png");
        }

        Random r=new Random();
        int random1=r.nextInt(4);
        correct=random1;
        int wrongIndex=1;
        for(int j=0;j<4;j++){
            if(j==correct){
                Util.setImageFromPath(a[j], Environment.getExternalStorageDirectory() + "/GujaratiMitra/l4/2/"+"level4_2_img_e" + String.valueOf(queIndex) + "_correct"+".png");
            }
            else{
                Util.setImageFromPath(a[j], Environment.getExternalStorageDirectory() + "/GujaratiMitra/l4/2/"+"level4_2_img_e" + String.valueOf(queIndex) + "_wrong" + String.valueOf(wrongIndex++)+".png");
            }
        }

        q[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.playMediaFromPath(Environment.getExternalStorageDirectory()+"/GujaratiMitra/l4/1/aud_0.mp3");
            }
        });

        q[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.playMediaFromPath(Environment.getExternalStorageDirectory()+"/GujaratiMitra/l4/1/aud_0.mp3");
            }
        });

        q[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.playMediaFromPath(Environment.getExternalStorageDirectory()+"/GujaratiMitra/l4/1/aud_0.mp3");
            }
        });

        q[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.playMediaFromPath(Environment.getExternalStorageDirectory()+"/GujaratiMitra/l4/1/aud_0.mp3");
            }
        });


        a[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(disable==0) {
                    queIndex++;
                    nextQues(0);
                }
            }
        });

        a[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (disable == 0) {
                    queIndex++;
                    nextQues(1);
                }
            }
        });

        a[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(disable==0) {
                    queIndex++;
                    nextQues(2);
                }
            }
        });

        a[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(disable==0) {
                    queIndex++;
                    nextQues(3);
                }
            }
        });
    }

    public void nextQues(final int ansClicked){
        final ImageView green_tick=new ImageView(this);
        final ImageView red_cross=new ImageView(this);
        final Toast toast = new Toast(this);

        green_tick.setImageResource(R.drawable.greentick);
        red_cross.setImageResource(R.drawable.redcross);

        new Thread(){
            public void run(){
                runOnUiThread(new Runnable(){

                    @Override
                    public void run() {
                        if(correct==ansClicked){
                            SCORE++;
                            txtscore.setText("SCORE:"+String.valueOf(SCORE)+"/"+String.valueOf(TOTAL_SCORE));
                            a[correct].setColorFilter(Color.argb(255, 0, 255, 0));
                            toast.setView(green_tick);
                            toast.show();

                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    toast.cancel();
                                }
                            }, 500);
                        }
                        else{
                            a[ansClicked].setColorFilter(Color.argb(255, 255, 0, 0));
                            a[correct].setColorFilter(Color.argb(255, 0, 255, 0));

                            toast.setView(red_cross);
                            toast.show();
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
                        for(int i=0;i<4;i++){
                            q[i].setVisibility(View.INVISIBLE);
                        }
                        if(queIndex>=NUM_QUE){
                           Util.setNextLevel(Level4_2.this,SCORE,2,4);
                        }else{
                            noOfQues = questions[queIndex];
                            for(int j=0;j<noOfQues;j++){
                                q[j].setVisibility(View.VISIBLE);
                                Util.setImageFromPath(q[j], Environment.getExternalStorageDirectory() + "/GujaratiMitra/l4/2/"+"level4_2_img_e"+String.valueOf(queIndex)+"_"+String.valueOf(j+1)+".png");
                            }

                            Random r=new Random();
                            int random1=r.nextInt(4);
                            correct=random1;
                            int wrongIndex=1;
                            for(int j=0;j<4;j++){
                                if(j==correct){
                                    Util.setImageFromPath(a[j], Environment.getExternalStorageDirectory() + "/GujaratiMitra/l4/2/"+"level4_2_img_e" + String.valueOf(queIndex) + "_correct"+".png");
                            }
                                else{
                                    Util.setImageFromPath(a[j], Environment.getExternalStorageDirectory() + "/GujaratiMitra/l4/2/"+"level4_2_img_e" + String.valueOf(queIndex) + "_wrong" + String.valueOf(wrongIndex++)+".png");
                                }
                                a[j].setColorFilter(Color.argb(255, 0, 0, 0));
                            }
                        }
                        }

                });
            }
        }.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_level4_2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
