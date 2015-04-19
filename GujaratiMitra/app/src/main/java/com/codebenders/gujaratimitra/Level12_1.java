package com.codebenders.gujaratimitra;

import android.media.MediaPlayer;
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


public class Level12_1 extends ActionBarActivity {

    private static final int NUM_PAGES = 6;
    private ViewPager mPager, mPager_1;
    private PagerAdapter mPagerAdapter;
    private ImageView leftArrow;
    private ImageView rightArrow;
    public MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level12_1);

        mPager = (ViewPager) findViewById(R.id.pager_1);
        mPagerAdapter = new SimplePagerAdapter();
        mPager.setAdapter(mPagerAdapter);
        mPager.setOffscreenPageLimit(5);
        final ImageView v1 = (ImageView) findViewById(R.id.imageView_12_1);
        Util.setImageFromPath(v1, Environment.getExternalStorageDirectory() + "/GujaratiMitra/l12/1/"+"img_12_1.jpg");
        ImageView question = (ImageView) findViewById(R.id.imageView5);
        Util.setImageFromPath(question, Environment.getExternalStorageDirectory() + "/GujaratiMitra/All Questions/que_12_1.png");

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
        final ImageView speaker = (ImageView) findViewById(R.id.imageView6);

        speaker.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    Util.mediaPlayer.setDataSource(Environment.getExternalStorageDirectory() + "/sample.mp3");//Write your location here
                    Util.mediaPlayer.prepare();
                    Util.mediaPlayer.start();

                } catch (Exception e) {
                    e.printStackTrace();
                }
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
                    Util.setNextLevel(Level12_1.this,0,1,12,false);
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
            final View view = getLayoutInflater().inflate(R.layout.unit_layout_level_12_1, container, false);

            final ImageView v2 = (ImageView) view.findViewById(R.id.imageView_12_2);




            switch(position) {
                case 0:
                    Util.setImageFromPath(v2, Environment.getExternalStorageDirectory() + "/GujaratiMitra/l12/1/"+"img_12_"+Integer.toString(position+2)+".png");
                    //v2.setImageResource(R.drawable.img_12_2);
                    break;
                case 1:
                    Util.setImageFromPath(v2, Environment.getExternalStorageDirectory() + "/GujaratiMitra/l12/1/"+"img_12_"+Integer.toString(position+2)+".png");
                    //v2.setImageResource(R.drawable.img_12_3);
                    break;
                case 2:
                    Util.setImageFromPath(v2, Environment.getExternalStorageDirectory() + "/GujaratiMitra/l12/1/"+"img_12_"+Integer.toString(position+2)+".png");
                    //v2.setImageResource(R.drawable.img_12_4);
                    break;
                case 3:
                    Util.setImageFromPath(v2, Environment.getExternalStorageDirectory() + "/GujaratiMitra/l12/1/"+"img_12_"+Integer.toString(position+2)+".png");
                    //v2.setImageResource(R.drawable.img_12_5);
                    break;
                case 4:
                    Util.setImageFromPath(v2, Environment.getExternalStorageDirectory() + "/GujaratiMitra/l12/1/"+"img_12_"+Integer.toString(position+2)+".png");
                    //v2.setImageResource(R.drawable.img_12_6);
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
        getMenuInflater().inflate(R.menu.menu_level12_1, menu);
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
