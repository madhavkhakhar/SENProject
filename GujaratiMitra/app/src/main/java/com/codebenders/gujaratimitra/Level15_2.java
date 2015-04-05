package com.codebenders.gujaratimitra;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Level15_2 extends ActionBarActivity {

    final List<Integer> rand_array=new ArrayList<Integer>();
    ImageView[]image;
    ImageView []window;
    public int count=0;
    final int[] correctans={2,1,2,3,2};
    final int[][] sizes={{94,113,124},{36,51,275},{287,53,162},{145,73,196},{181,80,135}};
    public int disable=-1;
    ImageView hint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level15_2);

        image = new ImageView[3];
        window=new ImageView[3];
        window[0]=(ImageView) findViewById(R.id.window1);
        window[1]=(ImageView) findViewById(R.id.window2);
        window[2]=(ImageView) findViewById(R.id.window3);
        for(int i=0;i<5;i++){
            rand_array.add(i+1);
        }
        Collections.shuffle(rand_array);


        image[0] = (ImageView) findViewById(R.id.imageView);
        image[1] = (ImageView) findViewById(R.id.imageView2);
        image[2] = (ImageView) findViewById(R.id.imageView3);

        final ImageView speaker=(ImageView) findViewById(R.id.imageView6);
        final ImageView check= (ImageView) findViewById(R.id.imageView7);
        hint= (ImageView) findViewById(R.id.hint);
        hint.setBackgroundResource(R.drawable.image_border_green);

        set();

        for (int i = 0; i <3 ; i++) {
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
                check();

            }
        });

    }



    private void nextQues(int image_no) {
        Util.setImageFromPath(window[1], Environment.getExternalStorageDirectory() + "/GujaratiMitra/l15/2/img_" + String.valueOf(rand_array.get(count)) + "_" + String.valueOf(image_no + 4) + ".png");
        window[1].setColorFilter(Color.argb(255, 0, 0, 0));

        for(int i=0;i<3;i++){
            image[i].setColorFilter(Color.argb(255,0,0,0));
        }
        image[image_no].setColorFilter(Color.argb(255, 32, 178, 170));
        disable=image_no;


    }





    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void set() {


        for (int i = 0; i < 3; i++) {

            Util.setImageFromPath(image[i], Environment.getExternalStorageDirectory() + "/GujaratiMitra/l15/2/img_" + Integer.toString(rand_array.get(count))+"_"+ Integer.toString(i+4) + ".png");
            image[i].setColorFilter(Color.argb(255,0,0,0));
            window[1].setImageResource(android.R.color.transparent);

            if(i==1){
                Bitmap b = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + "/GujaratiMitra/l15/2/img_" + Integer.toString(rand_array.get(count))+"_"+ Integer.toString(i+1) + ".png");
                Drawable ob;
                ob = new BitmapDrawable(getResources(), b);
                //View view = findViewById(R.id.container);
                window[i].setBackground(ob);
            }
            else {
                Util.setImageFromPath(window[i], Environment.getExternalStorageDirectory() + "/GujaratiMitra/l15/2/img_" + Integer.toString(rand_array.get(count)) + "_" + Integer.toString(i + 1) + ".png");
            }
        }
        int sum=sizes[rand_array.get(count)-1][0] + sizes[rand_array.get(count)-1][1] + sizes[rand_array.get(count)-1][2];
        for(int i=0;i<3;i++){
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) window[i].getLayoutParams();
            params.weight = (float)sizes[rand_array.get(count)-1][i]/sum;
            window[i].setLayoutParams(params);
        }

        //for(int i=0;i<.length;i++){
         //Util.setImageFromPath(window[1], Environment.getExternalStorageDirectory() + "/GujaratiMitra/l10/1/level10_1_img1_1.png");
         //window[1].setColorFilter(Color.argb(255, 255, 255, 255));
        //}
        Util.setImageFromPath(hint, Environment.getExternalStorageDirectory() + "/GujaratiMitra/l15/2/img_" + Integer.toString(rand_array.get(count))+"_"+ Integer.toString(7) + ".png");

    }

    public void check(){

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
                            int temp=correctans[rand_array.get(count)-1]-1;
                            System.out.println(">>>>>>>>>>>>>>>>"+temp+disable);
                            Vibrator v = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
                            if(disable==temp) {
                                System.out.println(">>>>>>>>>>>>>>>>-1-1-1");

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
                            else if(disable!=temp){

                                //for(int i=0;i<sequence[rand_array.get(count)-1].length;i++){
                                    Util.setImageFromPath(window[1], Environment.getExternalStorageDirectory() + "/GujaratiMitra/l15/2/img_" + String.valueOf(rand_array.get(count)) + "_" + String.valueOf(correctans[rand_array.get(count)-1] + 3) + ".png");
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
                            count++;


                            set();

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