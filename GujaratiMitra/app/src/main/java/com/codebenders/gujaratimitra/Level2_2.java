package com.codebenders.gujaratimitra;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class Level2_2 extends Activity {

    MyView mview;
    BitmapFactory.Options options;
    private static final int NUM_PAGES = 20;
    private ViewPager mPager;
    private PagerAdapter mAdapter;
    private ImageView leftArrow;
    private ImageView rightArrow;
    AppPreferences prefs;
    ImageView lSpeaker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level2_2);
        prefs = new AppPreferences(Level2_2.this);
        options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;

        lSpeaker = (ImageView)findViewById(R.id.lspeaker);

        lSpeaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.playMediaFromPath(Environment.getExternalStorageDirectory()+"/GujaratiMitra/l4/1/aud_0.mp3");
            }
        });

        Util.setImageFromPath((ImageView)findViewById(R.id.que_image), Environment.getExternalStorageDirectory()+"/GujaratiMitra/l2/2/que_2_2.png");

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
                else if (i == NUM_PAGES-1){
                    rightArrow.setVisibility(View.INVISIBLE);
                }
                else {
                    leftArrow.setVisibility(View.VISIBLE);
                    rightArrow.setVisibility(View.VISIBLE);
                }
                if (i == NUM_PAGES-1){
                    Util.setNextLevel(Level2_2.this,0,2,2);
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
            View view = getLayoutInflater().inflate(R.layout.activity_level1_2view, container, false);
            mview = (MyView) view.findViewById(R.id.mview);
            Drawable drawable = Drawable.createFromPath(Environment.getExternalStorageDirectory() + "/GujaratiMitra/l2/2/dif" + (position + 1) + ".jpg");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
                view.setBackground(drawable);
            else
                view.setBackgroundDrawable(drawable);

            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}


