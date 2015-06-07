package com.codebenders.gujaratimitra;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


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
    private boolean sleeping;
    private int mscore;
    private TextView score;
    List<Integer> rand_array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level13_2);
        i = getIntent();
        queNum = 1;
        mscore = 0;
        score = (TextView) findViewById(R.id.score);
        sleeping = false;
        Util.setImageFromPath((ImageView) findViewById(R.id.q_image), Environment.getExternalStorageDirectory() + "/GujaratiMitra/l13/2/que_13_2.png");
        ImageView speaker = (ImageView) findViewById(R.id.speaker);
        speaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.playMediaFromPath(Environment.getExternalStorageDirectory() + "/GujaratiMitra/l13/2/aud_" + Integer.toString(rand_array.get(queNum - 1)) + ".wav");
            }
        });

        rand_array = new ArrayList<Integer>(20);
        for (int i = 0; i < 20; i++) {
            rand_array.add(i + 1);
        }
        Collections.shuffle(rand_array);

        levelNo = i.getExtras().getInt("LevelNo");
        mPager = (ViewPager) findViewById(R.id.pager);
        mAdapter = new SimplePagerAdapter();
        mPager.setAdapter(mAdapter);
        mPager.setOffscreenPageLimit(NUM_PAGES);
        leftArrow = (ImageView) findViewById(R.id.imageView);
        rightArrow = (ImageView) findViewById(R.id.imageView1);
        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPager.setCurrentItem(mPager.getCurrentItem() - 1);
            }
        });
        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPager.setCurrentItem(mPager.getCurrentItem() + 1);
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
        Util.playMediaFromPath(Environment.getExternalStorageDirectory() + "/GujaratiMitra/l13/2/aud_que_13_2.wav");

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

            Util.setImageFromPath(v1, Environment.getExternalStorageDirectory() + "/GujaratiMitra/l13/2/" + "img_" + Integer.toString((4 * position) + 1) + ".png");
            Util.setImageFromPath(v2, Environment.getExternalStorageDirectory() + "/GujaratiMitra/l13/2/" + "img_" + Integer.toString((4 * position) + 2) + ".png");
            Util.setImageFromPath(v3, Environment.getExternalStorageDirectory() + "/GujaratiMitra/l13/2/" + "img_" + Integer.toString((4 * position) + 3) + ".png");
            Util.setImageFromPath(v4, Environment.getExternalStorageDirectory() + "/GujaratiMitra/l13/2/" + "img_" + Integer.toString((4 * position) + 4) + ".png");

            v1.setTag((4 * position) + 1);
            v2.setTag((4 * position) + 2);
            v3.setTag((4 * position) + 3);
            v4.setTag((4 * position) + 4);

            v1.setBackgroundResource(R.drawable.image_border_black);
            v2.setBackgroundResource(R.drawable.image_border_black);
            v3.setBackgroundResource(R.drawable.image_border_black);
            v4.setBackgroundResource(R.drawable.image_border_black);

            v1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!sleeping)
                        nextQues(v);
                }
            });
            v2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!sleeping)
                        nextQues(v);
                }
            });
            v3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!sleeping)
                        nextQues(v);
                }
            });
            v4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!sleeping)
                        nextQues(v);
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

    public void nextQues(final View image) {
        System.out.println(image.getTag() + " " + Integer.toString(queNum));
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
                            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            if (image.getTag().toString().equals(Integer.toString(rand_array.get(queNum - 1)))) {
                                mscore++;
                                score.setText(String.valueOf(mscore) + "/20");
                                toast.setView(green_tick);
                                toast.show();
                                image.setBackgroundResource(R.drawable.image_border_green);
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        toast.cancel();
                                    }
                                }, 500);
                            } else {
                                toast.setView(red_cross);
                                toast.show();
                                image.setBackgroundResource(R.drawable.image_border_red);
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
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            image.setBackgroundResource(R.drawable.image_border_black);
                            queNum = (queNum) + 1;
                            if (queNum == 21) {
                                Util.setNextLevel(Level13_2.this, mscore, 2, 13, true,false);
                            }
                            sleeping = false;
                            if (queNum != 21)
                                Util.playMediaFromPath(Environment.getExternalStorageDirectory() + "/GujaratiMitra/l13/2/aud_" + Integer.toString(rand_array.get(queNum - 1)) + ".wav");
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
