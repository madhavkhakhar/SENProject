package com.codebenders.gujaratimitra;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
import android.widget.Toast;

import java.util.Collections;


public class Level13_2 extends ActionBarActivity {
    private static final int NUM_PAGES = 5;
    private ViewPager mPager;
    private PagerAdapter mAdapter;
    private ImageView leftArrow;
    private ImageView rightArrow;
    private Intent i;
    private int levelNo;
    private int queNum;
    private ImageView speaker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level13_2);
        i=getIntent();
        queNum = 1;
        levelNo=i.getExtras().getInt("LevelNo");
        mPager = (ViewPager) findViewById(R.id.pager);
        mAdapter = new SimplePagerAdapter();
        mPager.setAdapter(mAdapter);
        mPager.setOffscreenPageLimit(NUM_PAGES);
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

        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {

            }

            @Override
            public void onPageSelected(int i) {
                if (i == 0)
                    leftArrow.setVisibility(View.INVISIBLE);
                else if (i == 4)
                    rightArrow.setVisibility(View.INVISIBLE);
                else {
                    leftArrow.setVisibility(View.VISIBLE);
                    rightArrow.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        speaker = (ImageView) findViewById(R.id.imageView6);

        speaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //play media file for current question
            }
        });

        // play media file
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
        public Object instantiateItem(ViewGroup container, int position) {
            View view = getLayoutInflater().inflate(R.layout.unit_layout_level_13_2, container, false);

            final ImageView v1 = (ImageView) view.findViewById(R.id.imageView1);
            final ImageView v2 = (ImageView) view.findViewById(R.id.imageView2);
            final ImageView v3 = (ImageView) view.findViewById(R.id.imageView3);
            final ImageView v4 = (ImageView) view.findViewById(R.id.imageView4);

            Util.setImageFromPath(v1, Environment.getExternalStorageDirectory() + "/GujaratiMitra/l"+String.valueOf(levelNo)+"/2/"+"img_" + Integer.toString((4 * position) + 1) +".png");
            Util.setImageFromPath(v2, Environment.getExternalStorageDirectory() + "/GujaratiMitra/l"+String.valueOf(levelNo)+"/2/"+"img_" + Integer.toString((4 * position) + 2) + ".png");
            Util.setImageFromPath(v3, Environment.getExternalStorageDirectory() + "/GujaratiMitra/l"+String.valueOf(levelNo)+"/2/"+"img_" + Integer.toString((4 * position) + 3) + ".png");
            Util.setImageFromPath(v4, Environment.getExternalStorageDirectory() + "/GujaratiMitra/l"+String.valueOf(levelNo)+"/2/"+"img_" + Integer.toString((4 * position) + 4) + ".png");

            v1.setTag((4 * position) + 1);
            v2.setTag((4 * position) + 2);
            v3.setTag((4 * position) + 3);
            v4.setTag((4 * position) + 4);

            v1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    nextQues(v.getTag().toString());
                }
            });
            v1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    nextQues(v.getTag().toString());
                }
            });
            v1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    nextQues(v.getTag().toString());
                }
            });
            v3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    nextQues(v.getTag().toString());
                }
            });
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    public void nextQues(final String image_no){
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
                            Vibrator v = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
                            if(image_no.equals(Integer.toString(queNum))) {
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
                            queNum = ((queNum + 1)%20)+1;
                            //play media for audio file
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
