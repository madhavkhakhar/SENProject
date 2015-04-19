package com.codebenders.gujaratimitra;

import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Level9_1 extends ActionBarActivity {

    protected  int count=0;
    public  int score=0;
    public MediaPlayer mp;
    public TextView score_text;
    public boolean[] disable=new boolean[8];
    public int number=0,temp=0;
    ImageView[]image;
    ImageView[] window;
    int []empty={-1,-1};
    final List<Integer> rand_array=new ArrayList<Integer>();
    int Total_ques=6;
    private ViewPager mPager;
    private PagerAdapter mAdapter;
    private ImageView leftArrow;
    private ImageView rightArrow;
    public int NUM_PAGES=2;
    private boolean sleeping;
    final List<String> correct_ans=new ArrayList<String>();
    //final List<ImageView> temp_images=new ArrayList<ImageView>();
    ImageView []temp_images=new ImageView[2];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level9_1);
        sleeping = false;
        image = new ImageView[4];
        window=new ImageView[2];
        for(int i=0;i<disable.length;i++){
            disable[i]=false;
        }

        mp=new MediaPlayer();

        correct_ans.add("12");
        correct_ans.add("34");
        correct_ans.add("56");
        correct_ans.add("78");
        correct_ans.add("52");

        mPager = (ViewPager) findViewById(R.id.pager);
        mAdapter = new SimplePagerAdapter();
        mPager.setAdapter(mAdapter);
        mPager.setOffscreenPageLimit(2);

        leftArrow = (ImageView) findViewById(R.id.imageView);
        rightArrow = (ImageView) findViewById(R.id.imageView1);
        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPager.setCurrentItem(mPager.getCurrentItem()-1);
            }
        });
        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPager.setCurrentItem(mPager.getCurrentItem()+1);
            }
        });

        for(int i=0;i<8;i++){
            rand_array.add(i+1);
        }
        Collections.shuffle(rand_array);



        window[0]= (ImageView) findViewById(R.id.window1);
        window[1]= (ImageView) findViewById(R.id.window2);

        score_text=(TextView) findViewById(R.id.score);
        score_text.setText("SCORE "+String.valueOf(score)+"/6");
        final ImageView speaker=(ImageView) findViewById(R.id.imageView6);
        final ImageView check= (ImageView) findViewById(R.id.imageView7);


        for(int i=0;i<window.length;i++){
            Util.setImageFromPath(window[i], Environment.getExternalStorageDirectory() + "/GujaratiMitra/l10/1/level10_1_img1_1.png");
            window[i].setColorFilter(Color.argb(255, 255, 255, 0));
        }


        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {

            }

            @Override
            public void onPageSelected(int i) {
                if (i == 0) {
                    leftArrow.setVisibility(View.INVISIBLE);
                    rightArrow.setVisibility(View.VISIBLE);
                }
                else if(i == NUM_PAGES-1){
                    rightArrow.setVisibility(View.INVISIBLE);
                    leftArrow.setVisibility(View.VISIBLE);
                }
                else {
                    leftArrow.setVisibility(View.VISIBLE);
                    rightArrow.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        speaker.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //int temp=rand_array.get(count)-1;
                //String temp1=(Environment.getExternalStorageDirectory() + "/GujaratiMitra/l10/1/"+sound_file).toString();
                //Util.playMediaFromPath(temp1);
            }
        });
        check.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                check();
            }

        });
    }

    class SimplePagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return NUM_PAGES;
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return o == view;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            View view = getLayoutInflater().inflate(R.layout.unit_layout_level9_1, container, false);

            image[0] = (ImageView) view.findViewById(R.id.img1);
            image[1] = (ImageView) view.findViewById(R.id.img2);
            image[2] = (ImageView) view.findViewById(R.id.img3);
            image[3] = (ImageView) view.findViewById(R.id.img4);
            //image[4] = (ImageView) view.findViewById(R.id.img5);
            Util.setImageFromPath(image[0], Environment.getExternalStorageDirectory() + "/GujaratiMitra/l9/1/img_" + rand_array.get((4 * position)) + ".png");
            Util.setImageFromPath(image[1], Environment.getExternalStorageDirectory() + "/GujaratiMitra/l9/1/img_" + rand_array.get((4 * position)+1) + ".png");
            Util.setImageFromPath(image[2], Environment.getExternalStorageDirectory() + "/GujaratiMitra/l9/1/img_" + rand_array.get((4 * position)+2) + ".png");
            Util.setImageFromPath(image[3], Environment.getExternalStorageDirectory() + "/GujaratiMitra/l9/1/img_" + rand_array.get((4 * position)+3) + ".png");
            //Util.setImageFromPath(image[4], Environment.getExternalStorageDirectory() + "/GujaratiMitra/l2/1/img_" + ((4 * position) + 4) + ".png");

            /*image[0].setTag(((4 * position)));
            image[1].setTag(((4 * position) + 1));
            image[2].setTag(((4 * position) + 2));
            image[3].setTag(((4 * position) + 3));*/
            //image[4].setTag(((5 * position) + 4));

            for(int i=0;i<image.length;i++) {
                final int finalI = i;
                image[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!sleeping) {
                            nextQues(mPager.getCurrentItem(),(ImageView)v,(4*mPager.getCurrentItem())+ finalI);
                        }
                    }
                });
            }
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

    }

    public void nextQues( final int page_no, final ImageView image,int image_no){
        final int imageno=image_no;

        new Thread(){
            public void run(){
                try{
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(disable[imageno]==false){
                                // Vibrator v = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
                                temp=0;
                                while(empty[temp]!=-1){
                                    temp++;
                                    if(temp>=2){
                                        break;
                                    }
                                }
                                if(temp < 2) {
                                    Util.setImageFromPath(window[temp], Environment.getExternalStorageDirectory() + "/GujaratiMitra/l9/1/img_" + String.valueOf(rand_array.get(imageno)) + ".png");
                                    window[temp].setColorFilter(Color.argb(255, 0, 0, 0));
                                    empty[temp] = imageno;
                                    //temp_images.add(temp,image);
                                    temp_images[temp]=image;
                                    disable[imageno] = true;
                                    image.setColorFilter(Color.argb(255, 32, 178, 170));
                                }
                            }
                            else{
                                temp=0;
                                while(empty[temp]!=imageno){
                                    temp++;
                                    if(temp>=2){
                                        break;
                                    }
                                }
                                if(temp < 2){
                                    window[temp].setImageResource(android.R.color.transparent);
                                    window[temp].setColorFilter(Color.argb(255,0,0,0));
                                    image.setColorFilter(Color.argb(255, 0, 0, 0));
                                    disable[imageno]=false;
                                    empty[temp]=-1;
                                   // temp_images.remove(temp);
                                    temp_images[temp]=null;
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
                            for(int i=0;i<temp_images.length;i++){
                                if(temp_images[i]!=null)
                                    temp_images[i].setColorFilter(Color.argb(255,0,0,0));
                            }
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


                                score_text.setText("SCORE "+String.valueOf(score) + "/6");
                            }
                            else{

                                /*for(int i=0;i<2;i++){
                                    Util.setImageFromPath(window[i], Environment.getExternalStorageDirectory() + "/GujaratiMitra/l10/1/level10_1_img" + String.valueOf(rand_array.get(count)) + "_"  + ".png");
                                    window[i].setColorFilter(Color.argb(255,0,0,0));
                                }
                                    */

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
                    sleeping = true;
                    Thread.sleep(1000);
                    sleeping = false;

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            count++;
                            if (count == 6) {

                                Util.setNextLevel(Level9_1.this,score,1,9,false);

                            } else {
                                number = 2;
                                for (int i = 0; i < empty.length; i++) {
                                    empty[i] = -1;
                                }

                                for (int i = 0; i < disable.length; i++) {
                                    disable[i] = false;
                                }

                                for (int i = 0; i < window.length; i++) {
                                    window[i].setImageResource(android.R.color.transparent);
                                }
                                for (int i = 0; i < number; i++) {
                                    //for(int i=0;i<sequence[rand_array.get(count)-1].length;i++){
                                    Util.setImageFromPath(window[i], Environment.getExternalStorageDirectory() + "/GujaratiMitra/l10/1/level10_1_img1_1.png");
                                    window[i].setColorFilter(Color.argb(255, 255, 255, 0));
                                    //}
                                }
                                for (int i = 0; i < image.length; i++) {
                                    image[i].setColorFilter(Color.argb(255, 0, 0, 0));
                                }

                            }
                        }


                    });
                    //Thread.sleep(1000);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }.start();


    }

    public boolean isCorrect(){
        if(empty[0]==-1 || empty[1]==-1)
            return false;
        String temp=String.valueOf(rand_array.get(empty[0]))+String.valueOf(rand_array.get(empty[1]));
        System.out.println(">>>>>>>>>>>>"+temp);
        if(correct_ans.contains(temp)){
            return true;
        }
        return false;
    }


}
