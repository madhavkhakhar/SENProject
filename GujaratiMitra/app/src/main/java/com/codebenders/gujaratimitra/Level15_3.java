package com.codebenders.gujaratimitra;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class Level15_3 extends ActionBarActivity {
    private static final int NUM_PAGES = 10;
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private ImageView leftArrow;
    private ImageView rightArrow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level15_3);

        mPager = (ViewPager) findViewById(R.id.pager_15_3);
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
                else if (i == 9)
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

    class SimplePagerAdapter extends PagerAdapter{
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
            final View view = getLayoutInflater().inflate(R.layout.unit_layout_level_15_3, container, false);
            final ImageView v1 = (ImageView) view.findViewById(R.id.imageView_15_3_1);
          //  final ImageView v2 = (ImageView) view.findViewById(R.id.imageView_15_3_2);

            switch(position) {

                case 0:
                    v1.setImageResource(R.drawable.img_15_3_1);
                    break;
                case 1:
                    v1.setImageResource(R.drawable.img_15_3_2);
                    break;
                case 2:
                    v1.setImageResource(R.drawable.img_15_3_3);
                    break;
                case 3:
                    v1.setImageResource(R.drawable.img_15_3_4);
                    break;
                case 4:
                    v1.setImageResource(R.drawable.img_15_3_5);
                    break;
                case 5:
                    v1.setImageResource(R.drawable.img_15_3_6);
                    break;
                case 6:
                    v1.setImageResource(R.drawable.img_15_3_7);
                    break;
                case 7:
                    v1.setImageResource(R.drawable.img_15_3_8);
                    break;
                case 8:
                    v1.setImageResource(R.drawable.img_15_3_9);
                    break;
                case 9:
                    v1.setImageResource(R.drawable.img_15_3_10);
                    break;



            }





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
        getMenuInflater().inflate(R.menu.menu_level15_3, menu);
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
