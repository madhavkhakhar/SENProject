package com.codebenders.gujaratimitra;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class Level5_1 extends ActionBarActivity {

    protected int count=0;
    private int correctans=0;
    public MediaPlayer mp;
    public int []numbers;
    ImageView[]image;
    final List<Integer> rand_array_sound=new ArrayList<Integer>();
    final List<Integer> rand_array=new ArrayList<Integer>(6);
    RelativeLayout al;
    private Intent i;
    private int levelNo;
    public int disable=0;
    final ArrayList<Integer> disabled=new ArrayList<Integer>();
    private ImageView lSpeaker,speaker;
    private TextView txtscore;
    private ImageView queImage;
    int SCORE=0,TOTAL_SCORE=27;
    String sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level5_1);
        al = (RelativeLayout)findViewById(R.id.rlayout);
        i=getIntent();
        levelNo=i.getExtras().getInt("LevelNo");
        if(levelNo==5){
            numbers=new int[]{4,5,5,5,4,4};
        }
        else if(levelNo==6){
            numbers=new int[]{5,4,3,5,5,5};
        }
        else if(levelNo==7){
            numbers=new int[]{5,5,5,5,5,5};
        }

        image = new ImageView[5];
        speaker = new ImageView(this);
        speaker.setImageResource(R.drawable.loudspeaker);


        txtscore = (TextView)findViewById(R.id.txtScore);
        txtscore.setText("SCORE:"+String.valueOf(SCORE)+"/"+String.valueOf(TOTAL_SCORE));
        lSpeaker = (ImageView)findViewById(R.id.lspeaker);
        lSpeaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.playMediaFromPath("/l5/1/aud_que_5_1.wav");
            }
        });

        queImage = (ImageView)findViewById(R.id.que_statement);
        Util.setImageFromPath(queImage,"/l5/1/que_5_1.png");
        for (int i = 0; i < 6; i++) {
            rand_array.add(i + 1);
        }

        Collections.shuffle(rand_array);

        for (int i = 0; i < numbers[rand_array.get(count)-1]; i++) {
            image[i]=new ImageView(this);
            Util.setImageFromPath(image[i],"/l"+String.valueOf(levelNo)+"/1/img_e"+String.valueOf(rand_array.get(count)) + "_" + String.valueOf(i + 1) + ".png");
        }


        if(numbers[rand_array.get(count)-1]==4){
            image[4]=new ImageView(this);
        }
        if(numbers[rand_array.get(count)-1]==3){
            image[3]=new ImageView(this);
            image[4]=new ImageView(this);
        }

        for (int i = 0; i < numbers[rand_array.get(count)-1]; i++) {
            rand_array_sound.add(i);
        }
        Collections.shuffle(rand_array_sound);
        sound=String.valueOf(rand_array.get(count))+"_"+String.valueOf(rand_array_sound.get(correctans)+1);
        speaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (disable == 0) {
                    Util.playMediaFromPath("/l" + String.valueOf(levelNo) + "/1/aud_" + sound + ".wav");
                }//Set the random sound from the array here.
            }
        });

        for (int i = 0; i < 5; i++) {
            final int finalI = i;

            image[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (disable == 0) {
                        nextQues(finalI);
                    }
                }
            });
        }
        // Change the radius to change size of cirlce
        display();
    }

      public void display(){
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int height=metrics.heightPixels;
        int width= metrics.widthPixels;
        double cy = ((double)height/2)*(0.6);
        double cx = ((double)width/2)*(0.7);


        int index=0;
        for(double angle = 0; angle < 360; angle += (360/(numbers[rand_array.get(count)-1]))) {
            double radAngle = Math.toRadians(angle);
            double x = ((Math.cos(radAngle)) * cx) + cx;
            double y = (( Math.sin(radAngle)) * cy) + cy;

            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(300,100);

            //the margins control where each of textview is placed
            lp.leftMargin = (int) x;
            lp.topMargin = (int) y;

            if(count==0) {
                al.addView(image[index], lp);
                index++;
                if(numbers[rand_array.get(count)-1]==3 && index==2){
                    al.addView(image[3],lp);
                    image[3].setVisibility(View.GONE);
                    al.addView(image[4],lp);
                    image[4].setVisibility(View.GONE);
                }
                if(numbers[rand_array.get(count)-1]==4 && index==3){
                    al.addView(image[4],lp);
                    image[4].setVisibility(View.GONE);
                }
            }
            else{
                al.updateViewLayout(image[index],lp);
                if(index<numbers[rand_array.get(count)-1]-1)
                    index++;
            }
        }
        RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams(300,100);

        //the margins control where each of textview is placed
        lp1.leftMargin = (int)cx;
        lp1.topMargin = (int) cy;
        txtscore.setText("SCORE:" + String.valueOf(SCORE) + "/" + String.valueOf(TOTAL_SCORE));
        RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams lp3 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

          lp2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
          lp3.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
          lp3.addRule(RelativeLayout.CENTER_HORIZONTAL);
          if(count==0) {
            al.addView(speaker, lp1);
          //  al.addView(score_text,lp2);
           // al.addView(ques,lp3);
        }
        else {
              speaker.setLayoutParams(lp1);
              //score_text.setLayoutParams(lp2);
              //ques.setLayoutParams(lp3);
          }
    }


    public void nextQues(final int image_no){
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
                            if(image_no==rand_array_sound.get(correctans)) {
                                image[rand_array_sound.get(correctans)].setColorFilter(Color.argb(255, 0, 255, 0));
                                SCORE++;
                                txtscore.setText("SCORE:"+String.valueOf(SCORE)+"/"+String.valueOf(TOTAL_SCORE));
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

                                image[image_no].setColorFilter(Color.argb(255, 255, 0, 0));
                                image[rand_array_sound.get(correctans)].setColorFilter(Color.argb(255, 0, 255, 0));
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
                            if(correctans<(numbers[rand_array.get(count)-1]-1)){
                                //image[rand_array_sound.get(correctans)].setColorFilter(Color.argb(255,32, 178,170));
                                //disabled.add(rand_array_sound.get(correctans));

                                //image[rand_array_sound.get(correctans)].setEnabled(false);

                                for(int i=0;i<numbers[rand_array.get(count)-1];i++){
                                   // if(disabled.contains(rand_array_sound.get(i))==false){
                                        image[rand_array_sound.get(i)].setColorFilter(Color.argb(255, 0, 0, 0));
                                    //}
                                }
                                correctans++;
                                sound=String.valueOf(rand_array.get(count))+"_"+String.valueOf(rand_array_sound.get(correctans)+1);
                            }
                            else if(correctans==numbers[rand_array.get(count)-1]-1) {
                                count++;
                                //disabled.clear();
                                if (count == 6) {
                                    Util.setNextLevel(Level5_1.this,SCORE,1,levelNo,false,false);
                                }
                                else{
                                    for (int i = 0; i < numbers[rand_array.get(count) - 1]; i++) {
                                        Util.setImageFromPath(image[i], "/l"+String.valueOf(levelNo)+"/1/img_e" + String.valueOf(rand_array.get(count)) + "_" + String.valueOf(i + 1) + ".png");
                                    }

                                    for (int i = 0; i < numbers[rand_array.get(count) - 1]; i++) {
                                        image[i].setColorFilter(Color.argb(255, 0, 0, 0));
                                        image[i].setEnabled(true);
                                    }
                                    if (numbers[rand_array.get(count) - 1] == 4) {
                                        image[3].setVisibility(View.VISIBLE);
                                        image[4].setVisibility(View.GONE);
                                    }
                                    else if(numbers[rand_array.get(count) - 1] == 3){
                                        image[3].setVisibility(View.GONE);
                                        image[4].setVisibility(View.GONE);

                                    }
                                    else {
                                        image[3].setVisibility(View.VISIBLE);
                                        image[4].setVisibility(View.VISIBLE);
                                    }
                                    rand_array_sound.clear();
                                    for (int i = 0; i < numbers[rand_array.get(count) - 1]; i++) {
                                        rand_array_sound.add(i);

                                    }
                                    Collections.shuffle(rand_array_sound);
                                    correctans = 0;

                                    display();
                                    sound=String.valueOf(rand_array.get(count))+"_"+String.valueOf(rand_array_sound.get(correctans)+1);

                                }
                                disable = 0;
                            }
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
