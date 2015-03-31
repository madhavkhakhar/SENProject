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

import java.util.Random;


public class Level1_1 extends ActionBarActivity {

    private static final int NUM_PAGES = 4;
    private ViewPager mPager;
    private PagerAdapter mAdapter;
    private ImageView leftArrow;
    private ImageView rightArrow;
    private ImageView queImage;
    private Random rn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level1_1);
        mPager = (ViewPager) findViewById(R.id.pager);
        mAdapter = new SimplePagerAdapter();
        mPager.setAdapter(mAdapter);
        mPager.setOffscreenPageLimit(0);
        queImage = (ImageView) findViewById(R.id.que_image);
        rn = new Random();
        int pos = 1+rn.nextInt(16);
        Util.setImageFromPath(queImage, Environment.getExternalStorageDirectory() + "/GujaratiMitra/l1/1/img_" + pos + ".png");
        queImage.setTag(pos);
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
                else if (i == 3)
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
        public Object instantiateItem(ViewGroup container, final int position) {
            View view = getLayoutInflater().inflate(R.layout.unit_layout_level1_1, container, false);

            final ImageView v1 = (ImageView) view.findViewById(R.id.img1);
            final ImageView v2 = (ImageView) view.findViewById(R.id.img2);
            final ImageView v3 = (ImageView) view.findViewById(R.id.img3);
            final ImageView v4 = (ImageView) view.findViewById(R.id.img4);

            Util.setImageFromPath(v1, Environment.getExternalStorageDirectory() + "/GujaratiMitra/l1/1/img_"+((4*position)+1)+".png");
            Util.setImageFromPath(v3, Environment.getExternalStorageDirectory() + "/GujaratiMitra/l1/1/img_"+((4*position)+2)+".png");
            Util.setImageFromPath(v2, Environment.getExternalStorageDirectory() + "/GujaratiMitra/l1/1/img_"+((4*position)+3)+".png");
            Util.setImageFromPath(v4, Environment.getExternalStorageDirectory() + "/GujaratiMitra/l1/1/img_"+((4*position)+4)+".png");
            //v1.setTag(Environment.getExternalStorageDirectory() + "/GujaratiMitra/l4/1/aud_" + Integer.toString(2 * position) + ".mp3");
            //v3.setTag(Environment.getExternalStorageDirectory() + "/GujaratiMitra/l4/1/aud_" + Integer.toString((2 * position) + 1) + ".mp3");

            v1.setTag(((4*position)+1));
            v2.setTag(((4*position)+2));
            v3.setTag(((4*position)+3));
            v4.setTag(((4*position)+4));

            v1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (queImage.getTag().equals(v1.getTag())) {
                        int pos = 1+rn.nextInt(16);
                        System.out.println(">>>>>>>pos"+pos);
                        Util.setImageFromPath(queImage, Environment.getExternalStorageDirectory() + "/GujaratiMitra/l1/1/img_"+pos+".png");
                        queImage.setTag(pos);
                    }
                }
            });
            v2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (queImage.getTag().equals(v2.getTag())) {
                        int pos = 1+rn.nextInt(16);
                        System.out.println(">>>>>>>pos"+pos);
                        Util.setImageFromPath(queImage, Environment.getExternalStorageDirectory() + "/GujaratiMitra/l1/1/img_"+pos+".png");
                        queImage.setTag(pos);
                    }
                }
            });
            v3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (queImage.getTag().equals(v3.getTag())) {
                        int pos = 1+rn.nextInt(16);
                        System.out.println(">>>>>>>pos"+pos);
                        Util.setImageFromPath(queImage, Environment.getExternalStorageDirectory() + "/GujaratiMitra/l1/1/img_"+pos+".png");
                        queImage.setTag(pos);
                    }
                }
            });
            v4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (queImage.getTag().equals(v4.getTag())) {
                        int pos = 1+rn.nextInt(16);
                        System.out.println(">>>>>>>pos"+pos);
                        Util.setImageFromPath(queImage, Environment.getExternalStorageDirectory() + "/GujaratiMitra/l1/1/img_"+pos+".png");
                        queImage.setTag(pos);
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
    }
}
