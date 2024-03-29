package com.codebenders.gujaratimitra;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Level15_1 extends ActionBarActivity {

    final List<Integer> rand_array = new ArrayList<Integer>();
    ImageView[] image;
    ImageView[] window;
    public int count = 0,score=0,MAX_SCORE;
    private int levelNumber;
    public int disable = -1;
    ImageView hint;
    int[] correctans;
    int[][] sizes;
    String imagePath;
    TextView score_text;
    boolean wait=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level15_2);
        levelNumber = getIntent().getIntExtra("level_no", 15);
        if (levelNumber == 15) {
            correctans = new int[]{2, 1, 2, 3, 2};
            MAX_SCORE=5;
            sizes = new int[][]{{94, 113, 124}, {36, 51, 275}, {287, 53, 162}, {145, 73, 196}, {181, 80, 135}};
            imagePath =  "/l15/2/img_";
        } else if (levelNumber == 19) {
            correctans = new int[]{2, 1, 3, 2, 3, 2, 3, 2};
            MAX_SCORE=8;
            sizes = new int[][]{{415, 246, 303}, {386, 200, 180}, {180, 190, 561}, {256, 189, 366}, {180, 191, 539}, {588, 190, 269}, {359, 200, 369}, {320, 192, 491}};
            imagePath =  "/l19/3/img_";

        }
        //Util.setImageFromPath((ImageView)findViewById(R.id.que_statement),  "/All Questions/que_15_2.png");
        image = new ImageView[3];
        window = new ImageView[3];
        window[0] = (ImageView) findViewById(R.id.window1);
        window[1] = (ImageView) findViewById(R.id.window2);
        window[2] = (ImageView) findViewById(R.id.window3);
        ImageView ques=(ImageView) findViewById(R.id.que_statement);
        if(levelNumber==15) {
            Util.setImageFromPath(ques,  "/l15/2/que_15_2.png");
            for (int i = 0; i < 5; i++) {
                rand_array.add(i + 1);
            }
        }
        else if(levelNumber==19){
            Util.setImageFromPath(ques, "/l19/3/que_19_3.png");
            for (int i = 0; i < 8; i++) {
                rand_array.add(i + 1);
            }
        }
        Collections.shuffle(rand_array);

        score_text=(TextView) findViewById(R.id.score);
        score_text.setText("SCORE "+String.valueOf(score)+"/"+String.valueOf(MAX_SCORE));
        final ImageView speaker = (ImageView) findViewById(R.id.speaker);
        speaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.playMediaFromPath("/l15/2/aud_que_15_2.wav");
            }
        });


        image[0] = (ImageView) findViewById(R.id.imageView);
        image[1] = (ImageView) findViewById(R.id.imageView2);
        image[2] = (ImageView) findViewById(R.id.imageView3);

        final ImageView check = (ImageView) findViewById(R.id.imageView7);
        hint = (ImageView) findViewById(R.id.hint);
        if (levelNumber == 15)
            hint.setBackgroundResource(R.drawable.image_border_black);

        set();

        for (int i = 0; i < 3; i++) {
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
                if(wait==false)
                    check();

            }
        });
    }


    private void nextQues(int image_no) {
        Util.setImageFromPath(window[1], imagePath + String.valueOf(rand_array.get(count)) + "_" + String.valueOf(image_no + 4) + ".png");
        window[1].setColorFilter(Color.argb(255, 0, 0, 0));

        for (int i = 0; i < 3; i++) {
            image[i].setColorFilter(Color.argb(255, 0, 0, 0));
        }
        image[image_no].setColorFilter(Color.argb(255, 32, 178, 170));
        disable = image_no;


    }


    private void set() {


        for (int i = 0; i < 3; i++) {

            Util.setImageFromPath(image[i], imagePath + Integer.toString(rand_array.get(count)) + "_" + Integer.toString(i + 4) + ".png");
            image[i].setColorFilter(Color.argb(255, 0, 0, 0));
            window[1].setImageResource(android.R.color.transparent);

            if (i == 1) {
                Bitmap b = BitmapFactory.decodeFile(imagePath + Integer.toString(rand_array.get(count)) + "_" + Integer.toString(i + 1) + ".png");
                Drawable ob;
                ob = new BitmapDrawable(getResources(), b);
                //View view = findViewById(R.id.container);
                window[i].setBackgroundDrawable(ob);
            } else {
                Util.setImageFromPath(window[i], imagePath + Integer.toString(rand_array.get(count)) + "_" + Integer.toString(i + 1) + ".png");
            }
        }
        int sum = sizes[rand_array.get(count) - 1][0] + sizes[rand_array.get(count) - 1][1] + sizes[rand_array.get(count) - 1][2];
        for (int i = 0; i < 3; i++) {
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) window[i].getLayoutParams();
            params.weight = (float) sizes[rand_array.get(count) - 1][i] / sum;
            window[i].setLayoutParams(params);
        }

        //for(int i=0;i<.length;i++){
        //Util.setImageFromPath(window[1],  "/l10/1/level10_1_img1_1.png");
        //window[1].setColorFilter(Color.argb(255, 255, 255, 255));
        //}
        if (levelNumber == 15)
            Util.setImageFromPath(hint, imagePath + Integer.toString(rand_array.get(count)) + "_" + Integer.toString(7) + ".png");

    }

    public void check() {

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
                            wait=true;
                            int temp = correctans[rand_array.get(count) - 1] - 1;
                            //System.out.println(">>>>>>>>>>>>>>>>" + temp + disable);
                            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            if (disable == temp) {
                               // System.out.println(">>>>>>>>>>>>>>>>-1-1-1");
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


                                //score_text.setText(String.valueOf(score) + "/10");
                            } else if (disable != temp) {

                                //for(int i=0;i<sequence[rand_array.get(count)-1].length;i++){
                                Util.setImageFromPath(window[1], imagePath + String.valueOf(rand_array.get(count)) + "_" + String.valueOf(correctans[rand_array.get(count) - 1] + 3) + ".png");
                                //window[1].setColorFilter(Color.argb(255,255,255,255));
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
                            wait=false;
                            count++;
                            if(levelNumber==15 && count==5){
                                Util.setNextLevel(Level15_1.this,score,2,15,false,false);
                            }
                            else if(levelNumber==19 && count==8){
                                Util.setNextLevel(Level15_1.this,score,3,19,true,false);
                            }
                            else {

                                set();
                            }
                        }


                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }
}
