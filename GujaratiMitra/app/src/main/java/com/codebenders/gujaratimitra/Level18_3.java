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


public class Level18_3 extends ActionBarActivity {
    private static final int NUM_PAGES = 7;
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private ImageView leftArrow;
    private ImageView rightArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level18_3);
        mPager = (ViewPager) findViewById(R.id.pager_18_3);
        mPagerAdapter = new SimplePagerAdapter();
        mPager.setAdapter(mPagerAdapter);
        mPager.setOffscreenPageLimit(10);
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
                else if (i == NUM_PAGES)
                    rightArrow.setVisibility(View.INVISIBLE);
                else {
                    leftArrow.setVisibility(View.VISIBLE);
                    rightArrow.setVisibility(View.VISIBLE);
                }
                if(i>=NUM_PAGES-1){
                    Util.setNextLevel(Level18_3.this,0,3,18,true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }

    class SimplePagerAdapter extends PagerAdapter{
        public int getCount() {
            return NUM_PAGES;
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return o == view;
        }

        public Object instantiateItem(ViewGroup container, final int position){
            final View view =getLayoutInflater().inflate(R.layout.unit_layout_level1_1, container,false);
            final ImageView v1 = (ImageView) view.findViewById(R.id.imageView7);
            Util.setImageFromPath(v1, Environment.getExternalStorageDirectory() + "/GujaratiMitra/l18/3/"+"img_e1_"+Integer.toString(position+1)+".png");

            v1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            container.addView(view);
            return view;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_level18_3, menu);
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
