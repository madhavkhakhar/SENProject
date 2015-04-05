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
import java.util.Random;


public class Level11_3 extends ActionBarActivity {
    ImageView q;
    ImageView[] a;
    Vibrator vib;
    public MediaPlayer mp;
    private int qIndex=1,correct=0;
    TextView score;
    private  int mscore=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level11_3);
        a=new ImageView[4];

        vib = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

        int[] aId={R.id.imgViewa1, R.id.imgViewa2, R.id.imgViewa3, R.id.imgViewa4};
        for(int i=0;i<4;i++){
            a[i]=(ImageView)findViewById(aId[i]);
        }
        q=(ImageView)findViewById(R.id.imgViewq1);

        score = (TextView)findViewById(R.id.score);

        Util.setImageFromPath(q, Environment.getExternalStorageDirectory() + "/GujaratiMitra/l11/2/img" + Integer.toString(qIndex) + "_1.png");

        Random r=new Random();
        int random1=r.nextInt(4);
        correct=random1;

        for(int j=0;j<4;j++){
            if(j==correct){
                Util.setImageFromPath(a[j], Environment.getExternalStorageDirectory() + "/GujaratiMitra/l11/2/img"+ String.valueOf(qIndex) + "_2"+".png");
                //a[j].setImageResource(getResources().getIdentifier("level4_2_img_e" + String.valueOf(queIndex) + "_correct", "drawable", getPackageName()));
            }
            else{
                Util.setImageFromPath(a[j], Environment.getExternalStorageDirectory() + "/GujaratiMitra/l11/2/img" + String.valueOf((qIndex+r.nextInt(4)+1)%22 +1) + "_2.png");
                //a[j].setImageResource(getResources().getIdentifier("level4_2_img_e" + String.valueOf(queIndex) + "_wrong" + String.valueOf(wrongIndex++), "drawable", getPackageName()));
            }
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
                        } else {
                            //a[ansClicked].setColorFilter(Color.argb(255, 255, 0, 0));
                           // a[correct].setColorFilter(Color.argb(255, 0, 255, 0));

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

                        Util.setImageFromPath(q, Environment.getExternalStorageDirectory() + "/GujaratiMitra/l11/2/img" + Integer.toString(qIndex) + "_1.png");

                        Random r=new Random();
                        int random1=r.nextInt(4);
                        correct=random1;

                        for(int j=0;j<4;j++){
                            if(j==correct){
                                Util.setImageFromPath(a[j], Environment.getExternalStorageDirectory() + "/GujaratiMitra/l11/2/img" + String.valueOf(qIndex) + "_2"+".png");
                                //a[j].setImageResource(getResources().getIdentifier("level4_2_img_e" + String.valueOf(queIndex) + "_correct", "drawable", getPackageName()));
                            }
                            else{
                                Util.setImageFromPath(a[j], Environment.getExternalStorageDirectory() + "/GujaratiMitra/l11/2/img"+ String.valueOf((qIndex+r.nextInt(4)+1)%22 +1) + "_2.png");
                                //a[j].setImageResource(getResources().getIdentifier("level4_2_img_e" + String.valueOf(queIndex) + "_wrong" + String.valueOf(wrongIndex++), "drawable", getPackageName()));
                            }
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
        getMenuInflater().inflate(R.menu.menu_level11_3, menu);
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
