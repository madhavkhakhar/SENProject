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
import java.util.Collections;
import java.util.List;


public class Level20_1 extends ActionBarActivity {

    protected  int count=0;
    ImageView[]image;
    ImageView window;
    final List<Integer> rand_array=new ArrayList<Integer>();
    public int disable=-1;
    private int[] correctans={2,3,3,4,3,2,3,3,3,2,3,5,2,4,2};
    TextView score_text;
    private int score;
    boolean sleeping;
    private int MAX_SCORE = 15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level20_1);

        sleeping = false;
        image=new ImageView[4];
        for(int i=0;i<15;i++){
            rand_array.add(i+1);
        }
        Collections.shuffle(rand_array);

        score_text=(TextView) findViewById(R.id.score);
        score_text.setText("SCORE "+String.valueOf(score)+"/"+String.valueOf(MAX_SCORE));

        image[0] = (ImageView) findViewById(R.id.imageView);
        image[1] = (ImageView) findViewById(R.id.imageView2);
        image[2] = (ImageView) findViewById(R.id.imageView3);
        image[3] = (ImageView) findViewById(R.id.imageView4);

        window= (ImageView) findViewById(R.id.window);

        final ImageView speaker=(ImageView) findViewById(R.id.imageView6);
        final ImageView check= (ImageView) findViewById(R.id.imageView7);

        set();

        for (int i = 0; i <4 ; i++) {
            final int finalI = i;
            image[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    nextQues(finalI);

                }
            });
        }

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!sleeping)
                    check();
            }
        });

        speaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private void nextQues(int image_no) {

        for(int i=0;i<4;i++){
            image[i].setColorFilter(Color.argb(255,0,0,0));
        }
        image[image_no].setColorFilter(Color.argb(255, 32, 178, 170));
        disable=image_no;
    }

    private void check() {
        sleeping = true;
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
                            int temp=-1;
                            if(rand_array.get(count)-1>-1){
                                temp=correctans[rand_array.get(count)-1]-2;
                            }
                            image[temp].setBackgroundResource(R.drawable.image_border_green);
                            Vibrator v = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
                            if(disable==temp) {
                                score++;
                                score_text.setText("SCORE "+String.valueOf(score)+"/"+String.valueOf(MAX_SCORE));
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
                            else if(disable!=temp){
                                image[disable].setBackgroundResource(R.drawable.image_border_red);
                                image[temp].setColorFilter(Color.argb(255, 32, 178, 170));
                                if(disable!=-1) {
                                    image[disable].setColorFilter(Color.argb(255, 0, 0, 0));
                                }
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
                            count++;
                            if(count==15){
                                //count=0;
                                Util.setNextLevel(Level20_1.this,score,1,20,false);
                            }
                            else {
                                set();
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

    private void set() {

        for(int i=0;i<4;i++){
            Util.setImageFromPath(image[i], Environment.getExternalStorageDirectory() + "/GujaratiMitra/l20/1/img_" + Integer.toString(rand_array.get(count))+"_"+ Integer.toString(i+2) + ".png");
            image[i].setColorFilter(Color.argb(255,0,0,0));
            image[i].setBackgroundResource(R.drawable.image_border_black);
        }
        Util.setImageFromPath(window, Environment.getExternalStorageDirectory() + "/GujaratiMitra/l20/1/img_" + Integer.toString(rand_array.get(count))+"_"+ Integer.toString(1) + ".png");
        sleeping = false;
    }


}
