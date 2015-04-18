package com.codebenders.gujaratimitra;

import android.os.Environment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class Level8_1 extends ActionBarActivity {

    private static final int NUM_PAGES = 6;
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private ImageView leftArrow;
    private ImageView rightArrow;
    private Button question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level8_1);

        mPager = (ViewPager) findViewById(R.id.pager_1);
        mPagerAdapter = new SimplePagerAdapter();
        mPager.setAdapter(mPagerAdapter);
        mPager.setOffscreenPageLimit(5);
        question=(Button)findViewById(R.id.question);

        Util.setImageFromPath((ImageView)findViewById(R.id.que_image), Environment.getExternalStorageDirectory() + "/GujaratiMitra/l8/1/que_8_1.png");
        ImageView speaker = (ImageView)findViewById(R.id.speaker);
        speaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.playMediaFromPath(Environment.getExternalStorageDirectory()+"/GujaratiMitra/l8/1/aud_que_8_1.wav");
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
                if (i != NUM_PAGES - 1) {
                    question.setText("Question");
                    ImageView v1 = (ImageView) mPager.getChildAt(i).findViewById(R.id.imageView_1);
                    ImageView v2 = (ImageView) mPager.getChildAt(i).findViewById(R.id.imageView_2);
                    Util.setImageFromPath(v2, Environment.getExternalStorageDirectory() + "/GujaratiMitra/l8/1/"+"img_8_1_"+Integer.toString(i+1)+"_q.png");
                    Util.setImageFromPath(v1, Environment.getExternalStorageDirectory() + "/GujaratiMitra/l8/1/"+"img_8_1_"+Integer.toString(i+1)+".jpg");
                    v2.setVisibility(View.VISIBLE);
                    mPager.getChildAt(i).invalidate();
                }
                if(i>=NUM_PAGES-1)
                    Util.setNextLevel(Level8_1.this,0,1,8, true);
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
            final View view = getLayoutInflater().inflate(R.layout.unit_layout_level_8_1, container, false);
            final ImageView v1 = (ImageView) view.findViewById(R.id.imageView_1);
            final ImageView v2 = (ImageView) view.findViewById(R.id.imageView_2);

            Util.setImageFromPath(v2, Environment.getExternalStorageDirectory() + "/GujaratiMitra/l8/1/"+"img_8_1_"+Integer.toString(position+1)+"_q.png");
            Util.setImageFromPath(v1, Environment.getExternalStorageDirectory() + "/GujaratiMitra/l8/1/"+"img_8_1_"+Integer.toString(position+1)+".jpg");

            question.setOnClickListener(new View.OnClickListener(){

                public void onClick(View v) {
                    View view = mPager.getChildAt(mPager.getCurrentItem());
                    final ImageView v1 = (ImageView) view.findViewById(R.id.imageView_1);
                    final ImageView v2 = (ImageView) view.findViewById(R.id.imageView_2);
                    if (question.getText().equals("IMAGE")) {
                        Util.setImageFromPath(v1, Environment.getExternalStorageDirectory() + "/GujaratiMitra/l8/1/"+"img_8_1_"+Integer.toString(mPager.getCurrentItem()+1)+".jpg");
                        v2.setVisibility(View.VISIBLE);
                        question.setText("Question");
                    } else if (question.getText().equals("Question")) {
                        Util.setImageFromPath(v1, Environment.getExternalStorageDirectory() + "/GujaratiMitra/l8/1/"+"img_8_1_"+Integer.toString(mPager.getCurrentItem()+1)+"_n.png");
                        v2.setVisibility(View.GONE);
                        question.setText("IMAGE");
                    }
                }
            });

            v1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            v1.setOnClickListener(new View.OnClickListener() {
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
