package com.codebenders.gujaratimitra;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import com.codebenders.gujaratimitra.Util;


public class LevelsActivity extends ActionBarActivity {

    private static int NUM_PAGES = 3;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);
        viewPager= (ViewPager)findViewById(R.id.pager);
        CustomPagerAdapter adapter = new CustomPagerAdapter();
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(NUM_PAGES);
    }
    private class CustomPagerAdapter extends PagerAdapter{

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
            View view = getLayoutInflater().inflate(R.layout.home_screen_page_layout, container, false);
            ImageView level1,level2,level3,level4,level5,level6,level7;
            LinearLayout layout = (LinearLayout)view.findViewById(R.id.main_layout);
            level1 = (ImageView)view.findViewById(R.id.level1);
            level2 = (ImageView)view.findViewById(R.id.level2);
            level3 = (ImageView)view.findViewById(R.id.level3);
            level4 = (ImageView)view.findViewById(R.id.level4);
            level5 = (ImageView)view.findViewById(R.id.level5);
            level6 = (ImageView)view.findViewById(R.id.level6);
            level7 = (ImageView)view.findViewById(R.id.level7);
            level1.setTag(""+position);
            level2.setTag(""+position);
            level3.setTag(""+position);
            level4.setTag(""+position);
            level5.setTag(""+position);
            level6.setTag(""+position);
            level7.setTag(""+position);
           // level1.setTag("");
            if(position==0){
                level1.setImageResource(R.drawable.home_s1_l1);
                level2.setImageResource(R.drawable.home_s1_l2);
                level3.setImageResource(R.drawable.home_s1_l3);
                level4.setImageResource(R.drawable.home_s1_l4);
                level5.setImageResource(R.drawable.home_s1_l5);
                level6.setImageResource(R.drawable.home_s1_l6);
                level7.setImageResource(R.drawable.home_s1_l7);
                level1.setTag(""+position);
                level2.setTag(""+position);
                level3.setTag(""+position);
                level4.setTag(""+position);
                level5.setTag(""+position);
                level6.setTag(""+position);
                level7.setTag(""+position);
                layout.setBackgroundResource(R.drawable.home_bg_s1);
            }
            else if(position==1) {
                level1.setImageResource(R.drawable.home_s2_l1);
                level2.setImageResource(R.drawable.home_s2_l2);
                level3.setImageResource(R.drawable.home_s2_l3);
                level4.setImageResource(R.drawable.home_s2_l4);
                level5.setImageResource(R.drawable.home_s2_l5);
                level6.setImageResource(R.drawable.home_s2_l6);
                level7.setImageResource(R.drawable.home_s2_l7);
                layout.setBackgroundResource(R.drawable.home_bg_s2);
            }

            else if(position==2){
                level1.setImageResource(R.drawable.home_s3_l1);
                level2.setImageResource(R.drawable.home_s3_l2);
                level3.setImageResource(R.drawable.home_s3_l3);
                level4.setImageResource(R.drawable.home_s3_l4);
                level5.setImageResource(R.drawable.home_s3_l5);
                level6.setImageResource(R.drawable.home_s3_l6);
                level7.setImageResource(R.drawable.home_s3_l7);
                layout.setBackgroundResource(R.drawable.home_bg_s3);
            }
            level1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //example for opening a level
                    if(v.getTag().equals("0")){
                        Intent i = new Intent(LevelsActivity.this,Level3_1.class);
                        startActivity(i);
                    }

                }
            });

            level2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Intent intent = new Intent(LevelsActivity.this,Lev)
                }
            });

            level3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Intent intent = new Intent(LevelsActivity.this,Lev)
                }
            });

            level4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Intent intent = new Intent(LevelsActivity.this,Lev)
                }
            });

            level5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Intent intent = new Intent(LevelsActivity.this,Lev)
                }
            });
            level6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Intent intent = new Intent(LevelsActivity.this,Lev)
                }
            });

            level7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Intent intent = new Intent(LevelsActivity.this,Lev)
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
