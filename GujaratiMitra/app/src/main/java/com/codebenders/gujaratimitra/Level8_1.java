package com.codebenders.gujaratimitra;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.codebenders.gujaratimitra.R;

public class Level8_1 extends ActionBarActivity {

    private static final int NUM_PAGES = 5;
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
            final ImageView v2 = (ImageView) view.findViewById(R.id.imageView_1);
            final ImageView v1 = (ImageView) view.findViewById(R.id.imageView_2);


            switch(position) {
                case 0:
                    v1.setImageResource(R.drawable.img_8_1_1_q);
                    v2.setImageResource(R.drawable.img_8_1_1);
                    break;
                case 1:
                    v1.setImageResource(R.drawable.img_8_1_2_q);
                    v2.setImageResource(R.drawable.img_8_1_2);
                    break;
                case 2:
                    v1.setImageResource(R.drawable.img_8_1_3_q);
                    v2.setImageResource(R.drawable.img_8_1_3);
                    break;
                case 3:
                    v1.setImageResource(R.drawable.img_8_1_4_q);
                    v2.setImageResource(R.drawable.img_8_1_4);
                    break;
                case 4:
                    v1.setImageResource(R.drawable.img_8_1_5_q);
                    v2.setImageResource(R.drawable.img_8_1_5);
                    break;
            }



            question.setOnClickListener(new View.OnClickListener(){

                public void onClick(View v) {
                    if (mPager.getCurrentItem() == 0) {

                        View view = mPager.getChildAt(mPager.getCurrentItem());
                        final ImageView v2 = (ImageView) view.findViewById(R.id.imageView_1);
                        if (question.getText().equals("IMAGE")) {
                            v2.setImageResource(R.drawable.img_8_1_1);

                            question.setText("Question");
                        } else if (question.getText().equals("Question")) {
                            v2.setImageResource(R.drawable.img_8_1_1_n);
                            question.setText("IMAGE");
                        }
                    } else if (mPager.getCurrentItem()  == 1) {

                        View view = mPager.getChildAt(mPager.getCurrentItem());
                        final ImageView v2 = (ImageView) view.findViewById(R.id.imageView_1);
                        if (question.getText().equals("IMAGE")) {
                            v2.setImageResource(R.drawable.img_8_1_2);

                            question.setText("Question");
                        } else if (question.getText().equals("Question")) {
                            v2.setImageResource(R.drawable.img_8_1_2_n);
                            question.setText("IMAGE");
                        }
                    }
                    else if (mPager.getCurrentItem()  == 2) {

                        View view = mPager.getChildAt(mPager.getCurrentItem());
                        final ImageView v2 = (ImageView) view.findViewById(R.id.imageView_1);
                        if (question.getText().equals("IMAGE")) {
                            v2.setImageResource(R.drawable.img_8_1_3);

                            question.setText("Question");
                        } else if (question.getText().equals("Question")) {
                            v2.setImageResource(R.drawable.img_8_1_3_n);
                            question.setText("IMAGE");
                        }
                    }
                    else if (mPager.getCurrentItem()  == 3) {

                        View view = mPager.getChildAt(mPager.getCurrentItem());
                        final ImageView v2 = (ImageView) view.findViewById(R.id.imageView_1);
                        if (question.getText().equals("IMAGE")) {
                            v2.setImageResource(R.drawable.img_8_1_4);

                            question.setText("Question");
                        } else if (question.getText().equals("Question")) {
                            v2.setImageResource(R.drawable.img_8_1_4_n);
                            question.setText("IMAGE");
                        }
                    }
                    else if (mPager.getCurrentItem()  == 4) {

                        View view = mPager.getChildAt(mPager.getCurrentItem());
                        final ImageView v2 = (ImageView) view.findViewById(R.id.imageView_1);
                        if (question.getText().equals("IMAGE")) {
                            v2.setImageResource(R.drawable.img_8_1_5);

                            question.setText("Question");
                        } else if (question.getText().equals("Question")) {
                            v2.setImageResource(R.drawable.img_8_1_5_n);
                            question.setText("IMAGE");
                        }
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_level8_1, menu);
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
