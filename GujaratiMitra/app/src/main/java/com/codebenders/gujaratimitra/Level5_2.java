package com.codebenders.gujaratimitra;

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


public class Level5_2 extends ActionBarActivity {

    private static final int NUM_PAGES = 5;
    private ViewPager mPager;
    private PagerAdapter mAdapter;
    private ImageView leftArrow;
    private ImageView rightArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level5_2);
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
                mPager.getChildAt(i).findViewById(R.id.imageView2).setVisibility(View.INVISIBLE);
                mPager.getChildAt(i).findViewById(R.id.imageView4).setVisibility(View.INVISIBLE);
                mPager.getChildAt(i).invalidate();
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
            System.out.println("Instantiating " + position);
            final ImageView v1 = (ImageView) view.findViewById(R.id.imageView1);
            final ImageView v2 = (ImageView) view.findViewById(R.id.imageView2);
            final ImageView v3 = (ImageView) view.findViewById(R.id.imageView3);
            final ImageView v4 = (ImageView) view.findViewById(R.id.imageView4);

            v2.setVisibility(View.INVISIBLE);
            v4.setVisibility(View.INVISIBLE);

            Util.setImageFromPath(v1, Environment.getExternalStorageDirectory() + "/GujaratiMitra/l5/2/img_" + Integer.toString(2 * position) + ".png");
            Util.setImageFromPath(v3, Environment.getExternalStorageDirectory() + "/GujaratiMitra/l5/2/img_" + Integer.toString((2 * position) + 1) + ".png");
            Util.setImageFromPath(v2, Environment.getExternalStorageDirectory() + "/GujaratiMitra/l5/2/img_" + Integer.toString(2 * position) + "_n.png");
            Util.setImageFromPath(v4, Environment.getExternalStorageDirectory() + "/GujaratiMitra/l5/2/img_" + Integer.toString((2 * position) + 1) + "_n.png");
            //v1.setTag(Environment.getExternalStorageDirectory() + "/GujaratiMitra/l4/1/aud_" + Integer.toString(2 * position) + ".mp3");
            //v3.setTag(Environment.getExternalStorageDirectory() + "/GujaratiMitra/l4/1/aud_" + Integer.toString((2 * position) + 1) + ".mp3");
            v1.setTag(Environment.getExternalStorageDirectory() + "/GujaratiMitra/l5/2/aud_0.mp3");
            v3.setTag(Environment.getExternalStorageDirectory() + "/GujaratiMitra/l5/2/aud_1.mp3");

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
