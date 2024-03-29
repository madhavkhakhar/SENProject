package com.codebenders.gujaratimitra;

import android.content.Intent;
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


public class Level19_1 extends ActionBarActivity {

    private int NUM_PAGES ;
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private ImageView leftArrow;
    private ImageView rightArrow;
    Intent i;
    private int sublevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level19_1);

        i=getIntent();
        sublevel=i.getExtras().getInt("Sublevel");



        if(sublevel==1){
            NUM_PAGES=7;
            Util.setImageFromPath((ImageView) findViewById(R.id.que_statement), "/l19/1/que_19_1.png");

        }
        else if(sublevel==2){
            NUM_PAGES=8;
            Util.setImageFromPath((ImageView) findViewById(R.id.que_statement), "/l19/2/que_19_2.png");
        }

        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new SimplePagerAdapter();
        mPager.setAdapter(mPagerAdapter);
        mPager.setOffscreenPageLimit(6);

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
                else if (i == NUM_PAGES-2)
                    rightArrow.setVisibility(View.INVISIBLE);
                else {
                    leftArrow.setVisibility(View.VISIBLE);
                    rightArrow.setVisibility(View.VISIBLE);
                }
                if (i == NUM_PAGES-1){
                    Util.setNextLevel(Level19_1.this,0,sublevel,19,false,true);
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
            View view = getLayoutInflater().inflate(R.layout.activity_level19_1view, container, false);
            ImageView v1 = (ImageView) view.findViewById(R.id.imageview);
            ImageView v2 = (ImageView) view.findViewById(R.id.imageview2);
            //ImageView v3 = (ImageView) view.findViewById(R.id.imageView3);
            //ImageView v4 = (ImageView) view.findViewById(R.id.imageView4);

            if(sublevel==1) {
                Util.setImageFromPath(v1,  "/l19/1/img1_" + String.valueOf((2 * position) + 1) + ".png");
                Util.setImageFromPath(v2,  "/l19/1/img1_" + String.valueOf((2 * position) + 2) + ".png");
            }
            else if(sublevel==2){
                Util.setImageFromPath(v1,  "/l19/2/img_" + String.valueOf(position + 1) + "_1.png");
                Util.setImageFromPath(v2,  "/l19/2/img_" + String.valueOf(position+ 1) + "_2.png");
            }

            v1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // Util.playMediaFromPath(v.getTag().toString());
                }
            });
            v2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // Util.playMediaFromPath(v.getTag().toString());
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




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_level19, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
