package com.codebenders.gujaratimitra;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;


public class Level9_2 extends ActionBarActivity {

    private static final int NUM_PAGES = 5;
    private ViewPager mPager;
    private PagerAdapter mAdapter;
    private ImageView leftArrow;
    private ImageView rightArrow;
    private ImageView queImage;
    private ImageView queStatement;
    private boolean sleeping;
    private ArrayList<Integer> queImageIndex;
    private int currentQueIndex,count;
    public int current;
    ImageView v1, next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level9_2);

        sleeping = false;
        queImageIndex = new ArrayList<Integer>();
        for (int i = 1; i <= 5; i++) {
            queImageIndex.add(i);
        }
        Collections.shuffle(queImageIndex);

        mPager = (ViewPager) findViewById(R.id.pager);
        mAdapter = new SimplePagerAdapter();
        mPager.setAdapter(mAdapter);
        mPager.setOffscreenPageLimit(5);

        queStatement = (ImageView) findViewById(R.id.que_statement);
        Util.setImageFromPath(queStatement, Environment.getExternalStorageDirectory() + "/GujaratiMitra/l9/2/img_que.png");

        queImage = (ImageView) findViewById(R.id.que_image);
        Util.setImageFromPath(queImage, Environment.getExternalStorageDirectory() + "/GujaratiMitra/l9/2/img_a_" + String.valueOf(queImageIndex.get(count)) + ".png");
        queImage.setTag(queImageIndex.get(count));
        currentQueIndex = currentQueIndex + 1;
        count=(count+1)%5;

        leftArrow = (ImageView) findViewById(R.id.imageView);
        rightArrow = (ImageView) findViewById(R.id.imageView1);
        next = (ImageView) findViewById(R.id.next);
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
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadNextImage(current);

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
            View view = getLayoutInflater().inflate(R.layout.unit_layout_level9_2, container, false);

            v1 = (ImageView) view.findViewById(R.id.img1);
            //final ImageView v2 = (ImageView) view.findViewById(R.id.img2);
            //final ImageView v3 = (ImageView) view.findViewById(R.id.img3);
            //final ImageView v4 = (ImageView) view.findViewById(R.id.img4);

            Util.setImageFromPath(v1, Environment.getExternalStorageDirectory() + "/GujaratiMitra/l9/2/img_1_" + String.valueOf(position+1) + ".png");
            //Util.setImageFromPath(v2, Environment.getExternalStorageDirectory() + "/GujaratiMitra/l2/1/img_"+((4*position)+2)+".png");
            //Util.setImageFromPath(v3, Environment.getExternalStorageDirectory() + "/GujaratiMitra/l9/1/img_"+((4*position)+3)+".png");
            //Util.setImageFromPath(v4, Environment.getExternalStorageDirectory() + "/GujaratiMitra/l2/1/img_"+((4*position)+4)+".png");

            v1.setTag(position);
            //v2.setTag(((4*position)+2));
            //v3.setTag(((4*position)+3));
            //v4.setTag(((4*position)+4));

            v1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!sleeping) {
                        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>clicked!");
                        current = (int) v.getTag();
                        imageClicked(v.getTag().toString(), (ImageView) v);
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

        public void imageClicked(final String pos, ImageView v) {
            v.setBackgroundResource(R.drawable.image_border_green);
            for (int i = 0; i < NUM_PAGES; i++) {
                if (i != Integer.parseInt(pos))
                    ((ImageView) mPager.getChildAt(i).findViewById(R.id.img1)).setBackgroundResource(android.R.color.transparent);
            }

        }


    }

    public void loadNextImage(final int pos) {
        final ImageView green_tick = new ImageView(Level9_2.this);
        final ImageView red_cross = new ImageView(Level9_2.this);
        final Toast toast = new Toast(Level9_2.this);

        green_tick.setImageResource(R.drawable.greentick);
        red_cross.setImageResource(R.drawable.redcross);
        new Thread() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        System.out.println(">>>>>>>>>>>>>>>>>"+queImage.getTag()+((current)%5)+1);
                        if ((((int)queImage.getTag()%5))==((pos)+1)%5) {
                            toast.setView(green_tick);
                            toast.show();

                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    toast.cancel();
                                }
                            }, 500);
                        } else {
                            toast.setView(red_cross);
                            toast.show();

                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    toast.cancel();
                                }
                            }, 500);
                            v.vibrate(500);
                        }
                    }
                });
                try {
                    sleeping = true;
                    Thread.sleep(1000);
                    sleeping = false;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        set();

                        currentQueIndex = (currentQueIndex + 1);
                        count=(count+1)%5;
                    }
                });
            }
        }.start();
    }

    private void set() {
        if(currentQueIndex<5){
            Util.setImageFromPath(queImage, Environment.getExternalStorageDirectory() + "/GujaratiMitra/l9/2/img_a_" + queImageIndex.get(count) + ".png");
            queImage.setTag(queImageIndex.get(count));
            for(int i=0;i<NUM_PAGES;i++){
                ImageView temp=(ImageView) mPager.getChildAt(i).findViewById(R.id.img1);
                temp.setBackgroundResource(android.R.color.transparent);
                //Util.setImageFromPath(temp, Environment.getExternalStorageDirectory() + "/GujaratiMitra/l9/2/img_2_" + String.valueOf(i+1) + ".png");
            }
            }
        else if(currentQueIndex>=5 && currentQueIndex<10){
            if(currentQueIndex==5){
                Collections.shuffle(queImageIndex);
                for(int i=0;i<NUM_PAGES;i++){
                    ImageView temp=(ImageView) mPager.getChildAt(i).findViewById(R.id.img1);
                    temp.setBackgroundResource(android.R.color.transparent);
                    Util.setImageFromPath(temp, Environment.getExternalStorageDirectory() + "/GujaratiMitra/l9/2/img_2_" + String.valueOf(i+1) + ".png");
                }

            }
            for(int i=0;i<NUM_PAGES;i++){
                ImageView temp=(ImageView) mPager.getChildAt(i).findViewById(R.id.img1);
                temp.setBackgroundResource(android.R.color.transparent);
                //Util.setImageFromPath(temp, Environment.getExternalStorageDirectory() + "/GujaratiMitra/l9/2/img_2_" + String.valueOf(i+1) + ".png");
            }
            Util.setImageFromPath(queImage, Environment.getExternalStorageDirectory() + "/GujaratiMitra/l9/2/img_b_" + queImageIndex.get(count) + ".png");
            queImage.setTag(5+queImageIndex.get(count));
        }
        else if(currentQueIndex>=10 && currentQueIndex<15){
            if(currentQueIndex==10){
                Collections.shuffle(queImageIndex);
                for(int i=0;i<NUM_PAGES;i++){
                    ImageView temp=(ImageView) mPager.getChildAt(i).findViewById(R.id.img1);
                    temp.setBackgroundResource(android.R.color.transparent);
                    Util.setImageFromPath(temp, Environment.getExternalStorageDirectory() + "/GujaratiMitra/l9/2/img_3_" + String.valueOf(i+1) + ".png");
                }
            }
            for(int i=0;i<NUM_PAGES;i++){
                ImageView temp=(ImageView) mPager.getChildAt(i).findViewById(R.id.img1);
                temp.setBackgroundResource(android.R.color.transparent);
                //Util.setImageFromPath(temp, Environment.getExternalStorageDirectory() + "/GujaratiMitra/l9/2/img_2_" + String.valueOf(i+1) + ".png");
            }
            Util.setImageFromPath(queImage, Environment.getExternalStorageDirectory() + "/GujaratiMitra/l9/2/img_c_" + queImageIndex.get(count) + ".png");
            queImage.setTag(10+queImageIndex.get(count));
        }
    }


}
