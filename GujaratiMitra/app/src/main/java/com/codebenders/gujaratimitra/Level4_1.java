package com.codebenders.gujaratimitra;

import android.os.Environment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import static com.codebenders.gujaratimitra.Util.playMediaFromPath;


public class Level4_1 extends ActionBarActivity {

    private static final int NUM_PAGES = 6;
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
        ImageView que = (ImageView) findViewById(R.id.imageView_que);
        Util.setImageFromPath(que, Environment.getExternalStorageDirectory() + "/GujaratiMitra/l4/1/que_4_1.png");
        ImageView speaker = (ImageView) findViewById(R.id.speaker);
        speaker.setTag(Environment.getExternalStorageDirectory() + "/GujaratiMitra/l4/1/aud_0.mp3");
        speaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Util.playMediaFromPath(v.getTag().toString());

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

                if(i==NUM_PAGES-1){
                    Util.setNextLevel(Level4_1.this);
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
            ImageView v1 = (ImageView) view.findViewById(R.id.imageView1);
            ImageView v2 = (ImageView) view.findViewById(R.id.imageView2);
            ImageView v3 = (ImageView) view.findViewById(R.id.imageView3);
            ImageView v4 = (ImageView) view.findViewById(R.id.imageView4);


            if (position < NUM_PAGES-2) {
                Util.setImageFromPath(v1, Environment.getExternalStorageDirectory() + "/GujaratiMitra/l4/1/img_" + Integer.toString(2 * position) + ".png");
                Util.setImageFromPath(v3, Environment.getExternalStorageDirectory() + "/GujaratiMitra/l4/1/img_" + Integer.toString((2 * position) + 1) + ".png");
                Util.setImageFromPath(v2, Environment.getExternalStorageDirectory() + "/GujaratiMitra/l4/1/img_" + Integer.toString(2 * position) + "_n.png");
                Util.setImageFromPath(v4, Environment.getExternalStorageDirectory() + "/GujaratiMitra/l4/1/img_" + Integer.toString((2 * position) + 1) + "_n.png");
                //v1.setTag(Environment.getExternalStorageDirectory() + "/GujaratiMitra/l4/1/aud_" + Integer.toString(2 * position) + ".mp3");
                //v3.setTag(Environment.getExternalStorageDirectory() + "/GujaratiMitra/l4/1/aud_" + Integer.toString((2 * position) + 1) + ".mp3");
                v1.setTag(Environment.getExternalStorageDirectory() + "/GujaratiMitra/l4/1/aud_0.mp3");
                v3.setTag(Environment.getExternalStorageDirectory() + "/GujaratiMitra/l4/1/aud_1.mp3");
            } else if(position==NUM_PAGES-2){
                Util.setImageFromPath(v1, Environment.getExternalStorageDirectory()+"/GujaratiMitra/l4/1/img_8.png");
                Util.setImageFromPath(v2, Environment.getExternalStorageDirectory() + "/GujaratiMitra/l4/1/img_8_n.png");
                v1.setTag(Environment.getExternalStorageDirectory()+"/GujaratiMitra/l4/1/aud_0.mp3");
            }

            v1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    playMediaFromPath(v.getTag().toString());
                }
            });
            v3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    playMediaFromPath(v.getTag().toString());
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
