package com.codebenders.gujaratimitra;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import static com.codebenders.gujaratimitra.Util.appDB;


public class Level1_1 extends ActionBarActivity {

    private static int NUM_PAGES = 5;
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private ImageView leftArrow;
    private ImageView rightArrow;
    private Button question;
    private int levelNo;
    private Intent i;
    private String imagePath;
    AppPreferences prefs;
    private ImageView lSpeaker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level1_1);
        prefs = new AppPreferences(Level1_1.this);
        i = getIntent();
        levelNo = i.getExtras().getInt("level_no");

        lSpeaker = (ImageView)findViewById(R.id.lspeaker);
        lSpeaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.playMediaFromPath(Environment.getExternalStorageDirectory()+"/GujaratiMitra/Audio/que1_1.wav");
            }
        });


        if(levelNo == 1){
            Util.setImageFromPath((ImageView)findViewById(R.id.que_image), Environment.getExternalStorageDirectory()+"/GujaratiMitra/l1/1/que_1_1.png");
            imagePath = Environment.getExternalStorageDirectory()+"/GujaratiMitra/l1/1/";
            NUM_PAGES = 6;
            //appDB.addSubLevelScore(levelNo, 1, 0, prefs.getStudentId());
        } else if (levelNo == 17) {
            Util.setImageFromPath((ImageView)findViewById(R.id.que_image), Environment.getExternalStorageDirectory()+"/GujaratiMitra/l17/1/que_17_1.png");
            imagePath = Environment.getExternalStorageDirectory() + "/GujaratiMitra/l17/1/";
            NUM_PAGES = 20;
        }
        mPager = (ViewPager) findViewById(R.id.pager_1);
        mPagerAdapter = new SimplePagerAdapter();
        mPager.setAdapter(mPagerAdapter);
        mPager.setOffscreenPageLimit(NUM_PAGES);
        question = (Button) findViewById(R.id.question);
        question.setText("Question");

        leftArrow = (ImageView) findViewById(R.id.imageView);
        rightArrow = (ImageView) findViewById(R.id.imageView1);
        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPager.setCurrentItem(mPager.getCurrentItem() - 1);
            }
        });
        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPager.setCurrentItem(mPager.getCurrentItem() + 1);
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
                else if (i == NUM_PAGES - 1) {
                    rightArrow.setVisibility(View.INVISIBLE);
                    question.setVisibility(View.INVISIBLE);
                } else {
                    leftArrow.setVisibility(View.VISIBLE);
                    rightArrow.setVisibility(View.VISIBLE);
                }
                if (i != NUM_PAGES - 1) {
                    question.setText("Question");
                    ImageView imageView = (ImageView) mPager.getChildAt(i).findViewById(R.id.imageView7);
                    Util.setImageFromPath(imageView, imagePath + "img_" + Integer.toString(i + 1) + ".png");
                    mPager.getChildAt(i).invalidate();
                }
                if (i >= NUM_PAGES - 1) {
                    Util.setNextLevel(Level1_1.this,0,1,1,true);
                }

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_level2_1, menu);
        return true;
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
            final View view = getLayoutInflater().inflate(R.layout.unit_layout_level1_1, container, false);
            final ImageView v1 = (ImageView) view.findViewById(R.id.imageView7);
            Util.setImageFromPath(v1, imagePath + "img_" + Integer.toString(position + 1) + ".png");

            question.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {

                    View view = mPager.getChildAt(mPager.getCurrentItem());
                    final ImageView v1 = (ImageView) view.findViewById(R.id.imageView7);
                    if (question.getText().equals("Image")) {
                        Util.setImageFromPath(v1, imagePath + "img_" + Integer.toString(mPager.getCurrentItem() + 1) + ".png");
                        question.setText("Question");
                    } else if (question.getText().equals("Question")) {
                        Util.setImageFromPath(v1, imagePath + "que_" + Integer.toString(mPager.getCurrentItem() + 1) + ".png");
                        question.setText("Image");
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
