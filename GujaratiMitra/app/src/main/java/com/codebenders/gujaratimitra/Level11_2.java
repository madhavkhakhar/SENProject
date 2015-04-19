package com.codebenders.gujaratimitra;

import android.media.Image;
import android.os.Environment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class Level11_2 extends ActionBarActivity {
    private static final int NUM_PAGES = 23;
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private ImageView leftArrow;
    private ImageView rightArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level11_2);
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new SimplePagerAdapter();
        mPager.setAdapter(mPagerAdapter);

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

        Util.setImageFromPath((ImageView)findViewById(R.id.que_image), Environment.getExternalStorageDirectory() + "/GujaratiMitra/l11/2/que_11_2.png");
        ImageView speaker = (ImageView) findViewById(R.id.speaker);
        speaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //play question audio
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
                else if (i == NUM_PAGES-1) {
                    rightArrow.setVisibility(View.INVISIBLE);

                }
                else {
                    leftArrow.setVisibility(View.VISIBLE);
                    rightArrow.setVisibility(View.VISIBLE);
                }
                if(i>=NUM_PAGES-1){
                    Util.setNextLevel(Level11_2.this,0,2,11,false);
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
            View view = getLayoutInflater().inflate(R.layout.unit_layout_level_11_2, container, false);
            ImageView v1 = (ImageView) view.findViewById(R.id.imageView1l11_2);
            final ImageView v2 = (ImageView) view.findViewById(R.id.imageView2l11_2);

            v2.setVisibility(View.INVISIBLE);

            Util.setImageFromPath(v1, Environment.getExternalStorageDirectory() + "/GujaratiMitra/l11/2/img" + Integer.toString(position+1) + "_1.png");
            Util.setImageFromPath(v2, Environment.getExternalStorageDirectory() + "/GujaratiMitra/l11/2/img" + Integer.toString(position+1) + "_2.png");
            v1.setTag(Environment.getExternalStorageDirectory() + "/GujaratiMitra/l4/1/aud_0.mp3");

            v1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v2.setVisibility(View.VISIBLE);
                    //Util.playMediaFromPath(v.getTag().toString());
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
