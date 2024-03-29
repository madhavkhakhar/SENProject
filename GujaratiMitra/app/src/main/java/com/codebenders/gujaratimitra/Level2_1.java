package com.codebenders.gujaratimitra;

import android.content.Context;
import android.os.Bundle;
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


public class Level2_1 extends ActionBarActivity {

    private static final int NUM_PAGES = 4;
    private static final int NUM_QUE=16;
    private static final int TOTAL_SCORE=16;
    private int SCORE=0;
    private ViewPager mPager;
    private PagerAdapter mAdapter;
    private ImageView leftArrow;
    private ImageView rightArrow;
    private ImageView queImage;
    private ImageView queStatement;
    private boolean sleeping;
    private ArrayList<Integer> queImageIndex;
    private int currentQueIndex;
    private ImageView lSpeaker;
    private TextView txtscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level2_1);

        sleeping = false;
        queImageIndex = new ArrayList<Integer>();
        for (int i = 1; i <= 16; i++) {
            queImageIndex.add(i);
        }
        Collections.shuffle(queImageIndex);
        txtscore = (TextView)findViewById(R.id.txtScore);
        txtscore.setText("SCORE:"+String.valueOf(SCORE)+"/"+String.valueOf(TOTAL_SCORE));
        lSpeaker = (ImageView)findViewById(R.id.lspeaker);
        lSpeaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.playMediaFromPath("/l2/1/aud_que_2_1.wav");
            }
        });


        mPager = (ViewPager) findViewById(R.id.pager);
        mAdapter = new SimplePagerAdapter();
        mPager.setAdapter(mAdapter);

        queStatement = (ImageView) findViewById(R.id.que_statement);
        Util.setImageFromPath(queStatement, "/l2/1/img_que.png");

        queImage = (ImageView) findViewById(R.id.que_image);
        Util.setImageFromPath(queImage, "/l2/1/img_" + queImageIndex.get(currentQueIndex) + ".png");
        queImage.setTag(queImageIndex.get(currentQueIndex));
        currentQueIndex = (currentQueIndex+1);

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
                else if (i == NUM_PAGES-1){
                    rightArrow.setVisibility(View.INVISIBLE);
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
            View view = getLayoutInflater().inflate(R.layout.unit_layout_level2_1, container, false);

            final ImageView v1 = (ImageView) view.findViewById(R.id.img1);
            final ImageView v2 = (ImageView) view.findViewById(R.id.img2);
            final ImageView v3 = (ImageView) view.findViewById(R.id.img3);
            final ImageView v4 = (ImageView) view.findViewById(R.id.img4);

            Util.setImageFromPath(v1, "/l2/1/img_"+((4*position)+1)+".png");
            Util.setImageFromPath(v2, "/l2/1/img_"+((4*position)+2)+".png");
            Util.setImageFromPath(v3, "/l2/1/img_"+((4*position)+3)+".png");
            Util.setImageFromPath(v4, "/l2/1/img_"+((4*position)+4)+".png");

            v1.setTag(((4*position)+1));
            v2.setTag(((4*position)+2));
            v3.setTag(((4*position)+3));
            v4.setTag(((4*position)+4));

            v1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!sleeping) {
                        loadNextImage(v1.getTag().toString());
                    }
                }
            });
            v2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!sleeping) {
                        loadNextImage(v2.getTag().toString());
                    }
                }
            });
            v3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!sleeping) {
                        loadNextImage(v3.getTag().toString());
                    }
                }
            });
            v4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!sleeping) {
                        loadNextImage(v4.getTag().toString());
                    }
                }
            });
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        public void loadNextImage (final String pos) {
            final ImageView green_tick=new ImageView(Level2_1.this);
            final ImageView red_cross=new ImageView(Level2_1.this);
            final Toast toast = new Toast(Level2_1.this);

            green_tick.setImageResource(R.drawable.greentick);
            red_cross.setImageResource(R.drawable.redcross);
            new Thread() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Vibrator v = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
                            if(queImage.getTag().toString().equals(pos)) {
                                SCORE++;
                                txtscore.setText("SCORE:"+String.valueOf(SCORE)+"/"+String.valueOf(TOTAL_SCORE));
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
                    try {
                        sleeping = true;
                        Thread.sleep(1000);
                        sleeping = false;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Util.setImageFromPath(queImage, "/l2/1/img_" + queImageIndex.get(currentQueIndex) + ".png");
                            queImage.setTag(queImageIndex.get(currentQueIndex));
                            currentQueIndex = (currentQueIndex+1);
                            if(currentQueIndex >= NUM_QUE-1){
                                Util.setNextLevel(Level2_1.this,SCORE,1,2,false,false);
                            }
                        }
                    });
                }
            }.start();
        }
    }
}
