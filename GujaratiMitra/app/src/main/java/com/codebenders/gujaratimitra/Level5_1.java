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

    protected static int count=0;
    private int correctans=0;
    public static int score=0;
    public MediaPlayer mp;
   // public TextView score_text;
    public int []numbers;
    ImageView[]image;
    final List<Integer> rand_array_sound=new ArrayList<Integer>();
    final List<Integer> rand_array=new ArrayList<Integer>(6);
    RelativeLayout al;
    private Intent i;
    private int levelNo;
    ImageView speaker,ques;
    public int disable=0;
    TextView score_text;
    final ArrayList<Integer> disabled=new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level5_1);


        al = new RelativeLayout(this);
        setContentView(al);

//        al = (RelativeLayout) findViewById(R.id.rlayout);

//        Util.setImageFromPath((ImageView)findViewById(R.id.que_image), Environment.getExternalStorageDirectory()+"/GujaratiMitra/l5/1/que_5_1.png");

        i=getIntent();
        levelNo=i.getExtras().getInt("LevelNo");
        if(levelNo==5){
            numbers=new int[]{4,5,5,5,4,4};
        }
        else if(levelNo==6){
            numbers=new int[]{5,4,4,5,5,5};
        }

        image = new ImageView[5];

        speaker = new ImageView(this);
        speaker.setImageResource(R.drawable.loudspeaker);

        ques=new ImageView(this);
        Util.setImageFromPath(ques,Environment.getExternalStorageDirectory() + "/GujaratiMitra/l5/1/que_5_1" + ".png");
        for (int i = 0; i < 6; i++) {
            rand_array.add(i + 1);
        }
        Collections.shuffle(rand_array);
       // final int[] resources = new int[5];

        score_text=new TextView(this);
        score_text.setTextColor(Color.RED);

        for (int i = 0; i < numbers[rand_array.get(count)-1]; i++) {
            //resources[i] = getResources().getIdentifier("q" + String.valueOf(rand_array.get(count)) + "_" + String.valueOf(i + 1), "drawable", getPackageName());
            image[i]=new ImageView(this);
            Util.setImageFromPath(image[i],Environment.getExternalStorageDirectory() + "/GujaratiMitra/l"+String.valueOf(levelNo)+"/1/img_e"+String.valueOf(rand_array.get(count)) + "_" + String.valueOf(i + 1) + ".png");
            //System.out.println("HERE" + resources[i]);
        }


        if(numbers[rand_array.get(count)-1]==4){
            image[4]=new ImageView(this);
        }

        //final List<Integer> rand_array_sound=new ArrayList<Integer>();
        for (int i = 0; i < numbers[rand_array.get(count)-1]; i++) {
            rand_array_sound.add(i);
        }
        Collections.shuffle(rand_array_sound);

        for (int i = 0; i < 5; i++) {
            final int finalI = i;

            image[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   if(disable==0) {
                       nextQues(finalI);
                   }
                }
            });
        }


        speaker.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                try {
                    mp.setDataSource(Environment.getExternalStorageDirectory() + "/sample.mp3");//Write your location here
                    mp.prepare();
                    mp.start();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
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


            //the 150 is the width of the actual text view and the 50 is the height
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(300,100);

            //the margins control where each of textview is placed
            lp.leftMargin = (int) x;
            lp.topMargin = (int) y;

            if(count==0) {
                al.addView(image[index], lp);
                index++;
                if(numbers[rand_array.get(count)-1]==4 && index==3){
                    al.addView(image[4],lp);
                    image[4].setVisibility(View.GONE);
                }
            }
            else{
               /* image[index].setLayoutParams(lp);
                index++;*/
                al.updateViewLayout(image[index],lp);
                if(index<numbers[rand_array.get(count)-1]-1)
                    index++;
            }
        }
        RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams(300,100);

        //the margins control where each of textview is placed
        lp1.leftMargin = (int)cx;
        lp1.topMargin = (int) cy;
        score_text.setText("SCORE " + String.valueOf(score) + "/27");
          score_text.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
        RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams lp3 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

          //lp2.leftMargin=(int)
          lp2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
          lp3.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
          lp3.addRule(RelativeLayout.CENTER_HORIZONTAL);
          if(count==0) {
            al.addView(speaker, lp1);
            al.addView(score_text,lp2);
            al.addView(ques,lp3);
        }
        else{
            speaker.setLayoutParams(lp1);
            score_text.setLayoutParams(lp2);
            ques.setLayoutParams(lp3);
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
                                score++;
                                score_text.setText("SCORE "+String.valueOf(score)+"/27");

                                toast.setView(green_tick);
                                toast.show();

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        toast.cancel();
                                    }
                                }, 500);


                                //score_text.setText(String.valueOf(score) + "/10");
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
                                image[rand_array_sound.get(correctans)].setColorFilter(Color.argb(255,32, 178,170));
                                disabled.add(rand_array_sound.get(correctans));

                                image[rand_array_sound.get(correctans)].setEnabled(false);

                                for(int i=0;i<numbers[rand_array.get(count)-1];i++){
                                    if(disabled.contains(rand_array_sound.get(i))==false){
                                        image[rand_array_sound.get(i)].setColorFilter(Color.argb(255, 0, 0, 0));
                                    }
                                }
                                correctans++;


                            }
                            else if(correctans==numbers[rand_array.get(count)-1]-1) {
                                count++;
                                disabled.clear();
                                if (count == 6) {
                                    count=0;
                                    score=0;
                                    Util.setNextLevel(Level5_1.this);

                                }
                                //final int[] resources=new int[5];
                                //System.out.println(">>>>>>>>>>>>>" + count);
                                else{
                                    for (int i = 0; i < numbers[rand_array.get(count) - 1]; i++) {
                                        Util.setImageFromPath(image[i], Environment.getExternalStorageDirectory() + "/GujaratiMitra/l"+String.valueOf(levelNo)+"/1/img_e" + String.valueOf(rand_array.get(count)) + "_" + String.valueOf(i + 1) + ".png");
                                        // resources[i]= getResources().getIdentifier("q"+String.valueOf(rand_array.get(count))+"_"+String.valueOf(i+1), "drawable", getPackageName());
                                        // System.out.println("HERE"+resources[i]);
                                    }

                                    for (int i = 0; i < numbers[rand_array.get(count) - 1]; i++) {
                                        image[i].setColorFilter(Color.argb(255, 0, 0, 0));
                                        image[i].setEnabled(true);
                                    }
                                    if (numbers[rand_array.get(count) - 1] == 4) {
                                        image[4].setVisibility(View.GONE);
                                    } else {
                                        image[4].setVisibility(View.VISIBLE);
                                    }
                                    rand_array_sound.clear();
                                    for (int i = 0; i < numbers[rand_array.get(count) - 1]; i++) {
                                        rand_array_sound.add(i);

                                    }
                                    Collections.shuffle(rand_array_sound);
                                    correctans = 0;

                                    display();
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
