package com.codebenders.gujaratimitra;

import android.app.PendingIntent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class Level4_1 extends ActionBarActivity {

    private static final int NUM_PAGES = 5;
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private ImageView leftArrow;
    private ImageView rightArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level4_1);
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new SimplePagerAdapter();
        mPager.setAdapter(mPagerAdapter);
        mPager.setOffscreenPageLimit(5);

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
            View view = getLayoutInflater().inflate(R.layout.unit_layout, container, false);
            ImageView v1 = (ImageView) view.findViewById(R.id.imageView1);
            ImageView v2 = (ImageView) view.findViewById(R.id.imageView2);
            ImageView v3 = (ImageView) view.findViewById(R.id.imageView3);
            ImageView v4 = (ImageView) view.findViewById(R.id.imageView4);
            if (position == 0) {
                v1.setImageResource(R.drawable.level_4_1_img_0);
                v3.setImageResource(R.drawable.level_4_1_img_1);
                v2.setImageResource(R.drawable.level_4_1_img_0_n);
                v4.setImageResource(R.drawable.level_4_1_img_1_n);
            } else if (position == 1) {
                v1.setImageResource(R.drawable.level_4_1_img_2);
                v3.setImageResource(R.drawable.level_4_1_img_3);
                v2.setImageResource(R.drawable.level_4_1_img_2_n);
                v4.setImageResource(R.drawable.level_4_1_img_3_n);
            } else if (position == 2) {
                v1.setImageResource(R.drawable.level_4_1_img_4);
                v3.setImageResource(R.drawable.level_4_1_img_5);
                v2.setImageResource(R.drawable.level_4_1_img_4_n);
                v4.setImageResource(R.drawable.level_4_1_img_5_n);
            } else if (position == 3) {
                v1.setImageResource(R.drawable.level_4_1_img_6);
                v3.setImageResource(R.drawable.level_4_1_img_7);
                v2.setImageResource(R.drawable.level_4_1_img_6_n);
                v4.setImageResource(R.drawable.level_4_1_img_7_n);
            } else if (position == 4) {
                v1.setImageResource(R.drawable.level_4_1_img_8);
                v2.setImageResource(R.drawable.level_4_1_img_8_n);
            }
            v1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            v3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

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
