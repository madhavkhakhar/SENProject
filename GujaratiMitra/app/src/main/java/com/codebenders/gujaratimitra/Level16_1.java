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


public class Level16_1 extends ActionBarActivity {
    ImageView q;
    ImageView[] a;
    Vibrator vib;
    public MediaPlayer mp;
    private int qIndex=1,correct=0;
    TextView score;
    private  int mscore=0,sublevel,level;
    int modArray[];
    ArrayList<Integer> wrongImg;
    Intent i;
    ImageView ques;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level16_1);



        a=new ImageView[4];
        i=getIntent();
        sublevel=i.getExtras().getInt("Sublevel");
        level=i.getExtras().getInt("Level");

        modArray=new int[] {11,16,28};
        wrongImg=new ArrayList<>();

        vib = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        ques=(ImageView) findViewById(R.id.que_statement);
        Util.setImageFromPath(ques,Environment.getExternalStorageDirectory() + "/GujaratiMitra/l16/1/que_16_1.png");

        int[] aId={R.id.imgViewa1, R.id.imgViewa2, R.id.imgViewa3, R.id.imgViewa4};
        for(int i=0;i<4;i++){
            a[i]=(ImageView)findViewById(aId[i]);
        }

        q=(ImageView)findViewById(R.id.imgViewq1);

        score = (TextView)findViewById(R.id.txtScore);
        score.setText("SCORE "+String.valueOf(mscore) + "/"+String.valueOf(modArray[sublevel-1]));


        Util.setImageFromPath(q, Environment.getExternalStorageDirectory() + "/GujaratiMitra/l"+String.valueOf(level)+"/"+String.valueOf(sublevel)+"/"+"img_e1_" + Integer.toString(qIndex) + ".png");

       for(int i=0;i<4;i++){
           a[i].setVisibility(View.INVISIBLE);
       }
        a[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qIndex++;
                nextQues(0);
            }
        });

        a[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qIndex++;
                nextQues(1);
            }
        });

        a[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qIndex++;
                nextQues(2);
            }
        });

        a[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qIndex++;
                nextQues(3);
            }
        });

        firstQues();

    }

    public void nextQues(final int ansClicked) {
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
                            //a[0].setColorFilter(Color.argb(255, 0, 255, 0));

                            mscore++;
                            a[correct].setColorFilter(Color.argb(255, 0, 255, 0));

                            score.setText("SCORE "+String.valueOf(mscore) + "/"+String.valueOf(modArray[sublevel-1]));
                            toast.setView(green_tick);
                            toast.show();

                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    toast.cancel();
                                }
                            }, 500);
                        } else {
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
                        if(qIndex>modArray[sublevel-1]){
                            SystemClock.sleep(500);
                            Util.setNextLevel(Level16_1.this);
                        }
                        else {
                            Util.setImageFromPath(q, Environment.getExternalStorageDirectory() + "/GujaratiMitra/l" + String.valueOf(level) + "/" + String.valueOf(sublevel) + "/" + "img_e1_" + Integer.toString(qIndex) + ".png");
                            q.setVisibility(View.VISIBLE);
                            for (int i = 0; i < 4; i++) {
                                a[i].setVisibility(View.INVISIBLE);
                                a[i].setColorFilter(Color.argb(255, 0, 0, 0));
                            }
                        }
                    }
                });

                SystemClock.sleep(3000);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        q.setVisibility(View.INVISIBLE);
                        Random r=new Random();
                        int random1=r.nextInt(4);
                        wrongImg=new ArrayList<>();
                        correct=random1;
                        int temp;
                        while(wrongImg.size()<3){
                            temp=r.nextInt(modArray[sublevel-1])+1;
                            if(temp!=qIndex && !wrongImg.contains(temp))
                                wrongImg.add(temp);

                        }
                        System.out.println(wrongImg.toString());
                        Collections.shuffle(wrongImg);

                        int wrongIndex=0;
                        for(int j=0;j<4;j++){
                            if(j==correct){
                                Util.setImageFromPath(a[j], Environment.getExternalStorageDirectory() +"/GujaratiMitra/l"+String.valueOf(level)+"/"+String.valueOf(sublevel)+"/"+"img_e1_" + Integer.toString(qIndex) + ".png");
                            }
                            else{
                                Util.setImageFromPath(a[j], Environment.getExternalStorageDirectory() + "/GujaratiMitra/l"+String.valueOf(level)+"/"+String.valueOf(sublevel)+"/"+"img_e1_" + String.valueOf(wrongImg.get(wrongIndex++)) + ".png");

                            }
                            a[j].setVisibility(View.VISIBLE);
                        }

                    }
                });
            }
        }.start();

    }


    public void firstQues() {

        new Thread() {
            public void run() {


                SystemClock.sleep(3000);


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        //Util.setImageFromPath(q, Environment.getExternalStorageDirectory() +  "/GujaratiMitra/l16/1/level16_1_img_e1_" + Integer.toString(qIndex) + ".png");
                        q.setVisibility(View.INVISIBLE);
                        Random r=new Random();
                        int random1=r.nextInt(4);
                        correct=random1;
                        wrongImg=new ArrayList<>();
                        int temp;
                        while(wrongImg.size()<3){
                            temp=r.nextInt(modArray[sublevel-1])+1;
                            if(temp!=qIndex && !wrongImg.contains(temp))
                                wrongImg.add(temp);

                        }
                        Collections.shuffle(wrongImg);
                        int wrongIndex=0;
                        for(int j=0;j<4;j++){
                            if(j==correct){
                                Util.setImageFromPath(a[j], Environment.getExternalStorageDirectory() +"/GujaratiMitra/l"+String.valueOf(level)+"/"+String.valueOf(sublevel)+"/"+"img_e1_" + Integer.toString(qIndex) + ".png");
                                //a[j].setImageResource(getResources().getIdentifier("level4_2_img_e" + String.valueOf(queIndex) + "_correct", "drawable", getPackageName()));
                            }
                            else{
                                Util.setImageFromPath(a[j], Environment.getExternalStorageDirectory() + "/GujaratiMitra/l"+String.valueOf(level)+"/"+String.valueOf(sublevel)+"/"+"img_e1_" +String.valueOf(wrongImg.get(wrongIndex++)) + ".png");
                                //a[j].setImageResource(getResources().getIdentifier("level4_2_img_e" + String.valueOf(queIndex) + "_wrong" + String.valueOf(wrongIndex++), "drawable", getPackageName()));
                            }
                            a[j].setVisibility(View.VISIBLE);
                            //a[j].setColorFilter(Color.argb(255, 0, 0, 0));
                        }
                    }
                });
            }
        }.start();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_level16_1, menu);
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
