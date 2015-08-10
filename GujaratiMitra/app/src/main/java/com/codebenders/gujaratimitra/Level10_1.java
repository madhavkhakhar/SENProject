package com.codebenders.gujaratimitra;

import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.opengl.Visibility;
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
import java.util.Random;


public class Level10_1 extends ActionBarActivity {
    protected  int count=0;
    protected  int correctans=2;
    public  int score=0;
    public MediaPlayer mp;
    public TextView score_text;
    public boolean[] disable={false,false,false,false,false,};
    public int number=0,temp=0,disable1;
    ImageView []image;
    ImageView[] window;
    int []empty={-1,-1,-1};
    final int[] number_images = {5,4,5,4,4};
    final List<Integer> rand_array=new ArrayList<Integer>();
    String sound_files[];
    int [][]sequence=new int[5][];
    int Total_ques=5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level10_1);

        image = new ImageView[5];
        window=new ImageView[3];
        sound_files=new String[5];
        mp=new MediaPlayer();

        for(int i=0;i<5;i++){
            rand_array.add(i+1);
        }
        Collections.shuffle(rand_array);

        //final int resource_s = getResources().getIdentifier("s"+String.valueOf(rand_array.get(count)), "drawable", getPackageName());
        //final int resource_d = getResources().getIdentifier("d"+String.valueOf(rand_array.get(count)), "drawable", getPackageName());
        Util.setImageFromPath((ImageView)findViewById(R.id.que_statement), "/l10/1/que_10_1.png");
        image[0] = (ImageView) findViewById(R.id.imageView);
        image[1] = (ImageView) findViewById(R.id.imageView2);
        image[2] = (ImageView) findViewById(R.id.imageView3);
        image[3] = (ImageView) findViewById(R.id.imageView4);
        image[4]=  (ImageView) findViewById(R.id.imageView5);

        window[0]= (ImageView) findViewById(R.id.window1);
        window[1]= (ImageView) findViewById(R.id.window2);
        window[2]= (ImageView) findViewById(R.id.window3);


        sound_files[0]="aud1_3_1_4_.wav";
        sound_files[1]="aud2_3_2_.wav";
        sound_files[2]="aud3_3_2_4_.wav";
        sound_files[3]="aud4_1_2_.wav";
        sound_files[4]="aud5_3_2_.wav";

        score_text=(TextView) findViewById(R.id.score);
        score_text.setText("SCORE "+String.valueOf(score)+"/5");
        final ImageView speaker=(ImageView) findViewById(R.id.imageView6);
        final ImageView check= (ImageView) findViewById(R.id.imageView7);
        final ImageView qspeaker=(ImageView) findViewById(R.id.speaker);
        for(int i=0;i<5;i++) {
            String[] tokens = sound_files[i].split("_");
            sequence[i] = new int[tokens.length - 2];
            for(int j=0;j<sequence[i].length;j++){

                sequence[i][j]=Integer.parseInt(tokens[j+1]);
                System.out.println(">>>>>>>>>>>>>"+sequence[i][j]);
            }
        }

        for (int i = 0; i < number_images[rand_array.get(count)-1]; i++) {
            //resources[i] = getResources().getIdentifier("q" + String.valueOf(rand_array.get(count)) + "_" + String.valueOf(i + 1), "drawable", getPackageName());
           // System.out.println(">>>>>>>>>>>" + Integer.toString(i+1));

            Util.setImageFromPath(image[i],  "/l10/1/level10_1_img" + Integer.toString(rand_array.get(count))+"_"+ Integer.toString(i+1) + ".png");

        }
        if(sequence[rand_array.get(count)-1].length<3){
            window[2].setVisibility(View.GONE);
        }
        for(int i=0;i<sequence[rand_array.get(count)-1].length;i++){
            Util.setImageFromPath(window[i],  "/l10/1/level10_1_img1_1.png");
            window[i].setColorFilter(Color.argb(255,255,255,0));
        }

        for (int i = 0; i <5 ; i++) {
            final int finalI = i;
            image[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //if(disable==0) {

                        nextQues(finalI);
                    //}
                }
            });
        }
        qspeaker.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //int temp=rand_array.get(count)-1;
                //String temp1=( "/l10/1/"+sound_files[temp]).toString();
                //Util.playMediaFromPath(temp1);
            }
        });

        speaker.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                int temp=rand_array.get(count)-1;
                String temp1=( "/l10/1/"+sound_files[temp]).toString();
                Util.playMediaFromPath(temp1);
            }
        });
        check.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(disable1==0)
                    check();
            }

        });
      }

    public void nextQues( final int image_no){


        new Thread(){
            public void run(){
                try{
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(disable[image_no]==false){
                           // Vibrator v = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
                                 temp=0;
                                while(empty[temp]!=-1){
                                    temp++;
                                    if(temp>=sequence[rand_array.get(count)-1].length){
                                        break;
                                    }
                                }
                                if(temp < sequence[rand_array.get(count)-1].length) {
                                    Util.setImageFromPath(window[temp],  "/l10/1/level10_1_img" + String.valueOf(rand_array.get(count)) + "_" + String.valueOf(image_no + 1) + ".png");
                                    window[temp].setColorFilter(Color.argb(255,0,0,0));
                                    empty[temp] = image_no;
                                    disable[image_no] = true;
                                    image[image_no].setColorFilter(Color.argb(255, 32, 178, 170));
                                }
                            }
                            else{
                                temp=0;
                                while(empty[temp]!=image_no){
                                    temp++;
                                    if(temp>=sequence[rand_array.get(count)-1].length){
                                        break;
                                    }
                                }
                                if(temp < sequence[rand_array.get(count)-1].length){
                                    window[temp].setImageResource(android.R.color.transparent);
                                    window[temp].setColorFilter(Color.argb(255,0,0,0));
                                    image[image_no].setColorFilter(Color.argb(255, 0, 0, 0));
                                    disable[image_no]=false;
                                    empty[temp]=-1;
                                }
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

    public void check(){
        disable1=1;
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
                            //disable=1;
                            Vibrator v = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
                            if(isCorrect()==true) {
                                score++;
                                toast.setView(green_tick);
                                toast.show();

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        toast.cancel();
                                    }
                                }, 500);


                                score_text.setText("SCORE "+String.valueOf(score) + "/5");
                            }
                            else{

                                for(int i=0;i<sequence[rand_array.get(count)-1].length;i++){
                                    Util.setImageFromPath(window[i],  "/l10/1/level10_1_img" + String.valueOf(rand_array.get(count)) + "_" + String.valueOf(sequence[rand_array.get(count)-1][i] ) + ".png");
                                    window[i].setColorFilter(Color.argb(255,0,0,0));
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
                            if(count==5){
                                //count=0;
                                //score=0;
                                Util.setNextLevel(Level10_1.this,score,1,10,true,false);

                            }
                            else{
                            number = sequence[rand_array.get(count) - 1].length;
                            for (int i=0;i<empty.length;i++) {
                                empty[i]=-1;
                            }

                            for (int i=0;i<disable.length;i++) {
                                disable[i]=false;
                            }
                            if(number==3){
                                window[2].setVisibility(View.VISIBLE);
                            }
                            else if(number==2){
                                window[2].setVisibility(View.GONE);
                            }

                            for (int i = 0; i < number_images[rand_array.get(count) - 1]; i++) {
                                //resources[i] = getResources().getIdentifier("q" + String.valueOf(rand_array.get(count)) + "_" + String.valueOf(i + 1), "drawable", getPackageName());
                                //System.out.println(String.valueOf(rand_array.get(count))+">>>>>>>>>>>>>>>>>>");
                                if(number_images[rand_array.get(count)-1] == 4){
                                    image[4].setVisibility(View.GONE);
                                }
                                else{
                                    image[4].setVisibility(View.VISIBLE);
                                }
                                 Util.setImageFromPath(image[i],  "/l10/1/level10_1_img" + String.valueOf(rand_array.get(count)) + "_" + String.valueOf(i + 1) + ".png");

                            }
                            for (int i = 0; i < 3; i++) {
                                window[i].setImageResource(android.R.color.transparent);
                            }
                            for(int i=0;i<number;i++){
                                //for(int i=0;i<sequence[rand_array.get(count)-1].length;i++){
                                    Util.setImageFromPath(window[i],  "/l10/1/level10_1_img1_1.png");
                                    window[i].setColorFilter(Color.argb(255,255,255,0));
                                //}
                            }
                            for(int i=0;i<5;i++){
                                image[i].setColorFilter(Color.argb(255,0,0,0));
                            }

                            }
                            disable1=0;
                        }


                    });
                    Thread.sleep(1000);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }.start();


    }

    public boolean isCorrect(){
        if(sequence[rand_array.get(count)-1].length==2){
            if(empty[0]==sequence[rand_array.get(count)-1][0]-1 && empty[1]==sequence[rand_array.get(count)-1][1]-1){
                return true;
            }
        }
        else if(sequence[rand_array.get(count)-1].length==3){
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>yes"+empty[0]+empty[1]+empty[2]);
            if(empty[0]==sequence[rand_array.get(count)-1][0]-1  && empty[1]==sequence[rand_array.get(count)-1][1]-1 && empty[2]==sequence[rand_array.get(count)-1][2]-1){
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>no");
                return true;
            }
        }
        return false;
    }

 }



