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
    int mscore=0,correct=0,queIndex=1;
    TextView score;
    Vibrator vib;
    public MediaPlayer mp;
    int[] questions;
    int noOfQues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level4_2);

        q=new ImageView[4];
        a=new ImageView[4];

        int[] qId={R.id.imgViewq1,R.id.imgViewq2, R.id.imgViewq3, R.id.imgViewq4};
        int[] aId={R.id.imgViewa1, R.id.imgViewa2, R.id.imgViewa3, R.id.imgViewa4};

        vib = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

        for(int i=0;i<4;i++){
            q[i]=(ImageView)findViewById(qId[i]);
            q[i].setVisibility(View.INVISIBLE);
        }

        for(int i=0;i<4;i++){
            a[i]=(ImageView)findViewById(aId[i]);
        }

        score = (TextView)findViewById(R.id.score);

        questions= new int[]{4, 3, 3, 4, 3, 3, 3, 3, 4, 4, 3};


        noOfQues = questions[queIndex];
        for(int j=0;j<noOfQues;j++){
            q[j].setVisibility(View.VISIBLE);
            Util.setImageFromPath(q[j], Environment.getExternalStorageDirectory() + "/GujaratiMitra/l4/2/"+"level4_2_img_e"+String.valueOf(queIndex)+"_"+String.valueOf(j+1)+".png");
            //q[j].setImageResource(getResources().getIdentifier("level4_2_img_e"+String.valueOf(queIndex)+"_"+String.valueOf(j+1), "drawable", getPackageName()));
            //q[j].setBackgroundResource(dQId[j]);
        }

        Random r=new Random();
        int random1=r.nextInt(4);
        correct=random1;
        int wrongIndex=1;
        for(int j=0;j<4;j++){
            if(j==correct){
                Util.setImageFromPath(a[j], Environment.getExternalStorageDirectory() + "/GujaratiMitra/l4/2/"+"level4_2_img_e" + String.valueOf(queIndex) + "_correct"+".png");
                //a[j].setImageResource(getResources().getIdentifier("level4_2_img_e" + String.valueOf(queIndex) + "_correct", "drawable", getPackageName()));
            }
            else{
                Util.setImageFromPath(a[j], Environment.getExternalStorageDirectory() + "/GujaratiMitra/l4/2/"+"level4_2_img_e" + String.valueOf(queIndex) + "_wrong" + String.valueOf(wrongIndex++)+".png");
                //a[j].setImageResource(getResources().getIdentifier("level4_2_img_e" + String.valueOf(queIndex) + "_wrong" + String.valueOf(wrongIndex++), "drawable", getPackageName()));
            }
        }

        q[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        q[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        q[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        q[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        a[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queIndex++;
                nextQues(0);
            }
        });

        a[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queIndex++;
                nextQues(1);
            }
        });

        a[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queIndex++;
                nextQues(2);
            }
        });

        a[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queIndex++;
                nextQues(3);
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
                            //a[0].setColorFilter(Color.argb(255, 0, 255, 0));
                            mscore++;
                            //a[correct].setColorFilter(Color.argb(255, 0, 255, 0));
                            score.setText(String.valueOf(mscore) + "/10");
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
                            //a[ansClicked].setColorFilter(Color.argb(255, 255, 0, 0));
                            //a[correct].setColorFilter(Color.argb(255, 0, 255, 0));

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
                        }else{
                            noOfQues = questions[queIndex];
                            for(int j=0;j<noOfQues;j++){
                                q[j].setVisibility(View.VISIBLE);
                                Util.setImageFromPath(q[j], Environment.getExternalStorageDirectory() + "/GujaratiMitra/l4/2/"+"level4_2_img_e"+String.valueOf(queIndex)+"_"+String.valueOf(j+1)+".png");
                                //q[j].setImageResource(getResources().getIdentifier("level4_2_img_e"+String.valueOf(queIndex)+"_"+String.valueOf(j+1), "drawable", getPackageName()));
                                //q[j].setBackgroundResource(dQId[j]);
                            }

                            Random r=new Random();
                            int random1=r.nextInt(4);
                            correct=random1;
                            int wrongIndex=1;
                            for(int j=0;j<4;j++){
                                if(j==correct){
                                    Util.setImageFromPath(a[j], Environment.getExternalStorageDirectory() + "/GujaratiMitra/l4/2/"+"level4_2_img_e" + String.valueOf(queIndex) + "_correct"+".png");
                                    //a[j].setImageResource(getResources().getIdentifier("level4_2_img_e" + String.valueOf(queIndex) + "_correct", "drawable", getPackageName()));
                                }
                                else{
                                    Util.setImageFromPath(a[j], Environment.getExternalStorageDirectory() + "/GujaratiMitra/l4/2/"+"level4_2_img_e" + String.valueOf(queIndex) + "_wrong" + String.valueOf(wrongIndex++)+".png");
                                    //a[j].setImageResource(getResources().getIdentifier("level4_2_img_e" + String.valueOf(queIndex) + "_wrong" + String.valueOf(wrongIndex++), "drawable", getPackageName()));
                                }
                                //a[j].setColorFilter(Color.argb(255, 0, 0, 0));
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
