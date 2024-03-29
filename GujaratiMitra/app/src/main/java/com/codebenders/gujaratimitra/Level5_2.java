package com.codebenders.gujaratimitra;

import android.content.Intent;
import android.os.Environment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class Level5_2 extends ActionBarActivity {

    private static int NUM_PAGES = 6;
    private ViewPager mPager;
    private PagerAdapter mAdapter;
    private ImageView leftArrow;
    private ImageView rightArrow;
    private Intent i;
    private int levelNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level5_2);
        i=getIntent();
        levelNo=i.getExtras().getInt("LevelNo");
        if (levelNo == 6) {
            NUM_PAGES = 10;
        } else if (levelNo == 7) {
            NUM_PAGES = 11;
        }
        mPager = (ViewPager) findViewById(R.id.pager);
        mAdapter = new SimplePagerAdapter();
        mPager.setAdapter(mAdapter);
        mPager.setOffscreenPageLimit(NUM_PAGES);
        ImageView que = (ImageView) findViewById(R.id.imageView_que);
        Util.setImageFromPath(que, "/l5/2/que_5_2.png");
        ImageView speaker = (ImageView) findViewById(R.id.speaker);
        speaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.playMediaFromPath("/l5/2/aud_que_5_2.wav");
            }
        });

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
                else if (i == NUM_PAGES-1)
                    rightArrow.setVisibility(View.INVISIBLE);
                else {
                    leftArrow.setVisibility(View.VISIBLE);
                    rightArrow.setVisibility(View.VISIBLE);
                }

                if(i>=NUM_PAGES-1){
                    Util.setNextLevel(Level5_2.this,0,2,levelNo,true,true);
                }
                else{
                    mPager.getChildAt(i).findViewById(R.id.imageView2).setVisibility(View.INVISIBLE);
                    mPager.getChildAt(i).findViewById(R.id.imageView4).setVisibility(View.INVISIBLE);
                    mPager.getChildAt(i).invalidate();
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
        public Object instantiateItem(ViewGroup container, int position) {
            View view = getLayoutInflater().inflate(R.layout.unit_layout_level_4_1_5_2_6_2_7_2, container, false);

            final ImageView v1 = (ImageView) view.findViewById(R.id.imageView1);
            final ImageView v2 = (ImageView) view.findViewById(R.id.imageView2);
            final ImageView v3 = (ImageView) view.findViewById(R.id.imageView3);
            final ImageView v4 = (ImageView) view.findViewById(R.id.imageView4);
            if(position>=NUM_PAGES-1){
                LinearLayout ll = new LinearLayout(Level5_2.this);
                return ll;
            }
            v2.setVisibility(View.INVISIBLE);
            v4.setVisibility(View.INVISIBLE);

            Util.setImageFromPath(v1, "/l"+String.valueOf(levelNo)+"/2/"+"img_e" + Integer.toString((2 * position) + 1) +"_1.png");
            Util.setImageFromPath(v3, "/l"+String.valueOf(levelNo)+"/2/"+"img_e" + Integer.toString((2 * position) + 2) + "_1.png");
            Util.setImageFromPath(v2,  "/l"+String.valueOf(levelNo)+"/2/"+"img_e" + Integer.toString((2 * position) + 1) + "_2.png");
            Util.setImageFromPath(v4, "/l"+String.valueOf(levelNo)+"/2/"+"img_e" +Integer.toString((2 * position) + 2) + "_2.png");
            v1.setTag("/l"+String.valueOf(levelNo)+"/2/aud_"+Integer.toString((position*2)+1)+".wav");
            v3.setTag("/l"+String.valueOf(levelNo)+"/2/aud_"+Integer.toString((position*2)+2)+".wav");

            v1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v2.setVisibility(View.VISIBLE);
                    Util.playMediaFromPath(v.getTag().toString());
                }
            });
            v3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v4.setVisibility(View.VISIBLE);
                    Util.playMediaFromPath(v.getTag().toString());
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
}
