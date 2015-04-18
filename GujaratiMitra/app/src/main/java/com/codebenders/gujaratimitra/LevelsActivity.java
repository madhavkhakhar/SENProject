package com.codebenders.gujaratimitra;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.codebenders.gujaratimitra.profile.ProfileActivity;
import com.codebenders.gujaratimitra.profile.Student;

import java.util.ArrayList;

import static com.codebenders.gujaratimitra.Util.appDB;
import static com.codebenders.gujaratimitra.Util.prefs;


public class LevelsActivity extends ActionBarActivity {

    private static int NUM_PAGES = 3;
    ViewPager viewPager;
    private int levelNo = 0;
    private int lastLevelUnlocked = 20;
    ListView listView;
    ArrayList<String> listItems = new ArrayList<String>();
    String imagePath = Environment.getExternalStorageDirectory() + "/GujaratiMitra/Start/";
    CustomPagerAdapter adapter;
    private Button profiles, aboutUs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);
        viewPager = (ViewPager) findViewById(R.id.pager);
        adapter = new CustomPagerAdapter();
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(NUM_PAGES);
        lastLevelUnlocked = appDB.getLastLevelUnlocked(prefs.getStudentId());
        //System.out.println("prefs student id" +prefs.getStudentId());
        profiles = (Button)findViewById(R.id.profiles);
        aboutUs = (Button)findViewById(R.id.about_us);
        if(Util.appDB.getStudentById(0)==null){
            Util.appDB.insertStudent(new Student(0,0,"","Guest",1,0),0);
        }
        profiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LevelsActivity.this,ProfileActivity.class);
                startActivity(intent);
            }
        });
        aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private class CustomPagerAdapter extends PagerAdapter {
        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

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
            ImageView level1, level2, level3, level4, level5, level6, level7;
            LinearLayout layout = (LinearLayout) view.findViewById(R.id.main_layout);
            level1 = (ImageView) view.findViewById(R.id.level1);
            level2 = (ImageView) view.findViewById(R.id.level2);
            level3 = (ImageView) view.findViewById(R.id.level3);
            level4 = (ImageView) view.findViewById(R.id.level4);
            level5 = (ImageView) view.findViewById(R.id.level5);
            level6 = (ImageView) view.findViewById(R.id.level6);
            level7 = (ImageView) view.findViewById(R.id.level7);
            ArrayList<ImageView> images = new ArrayList<>(7);
            images.add(level1);
            images.add(level2);
            images.add(level3);
            images.add(level4);
            images.add(level5);
            images.add(level6);
            images.add(level7);
            int page = (lastLevelUnlocked - 1) / 7;
            int tempLevel = (lastLevelUnlocked - 1) % 7 + 1;
            for (int i = 1; i <= images.size(); i++) {
               // System.out.println("level " + tempLevel + " page " + page + " last level" + lastLevelUnlocked);
                if ((i <= tempLevel && page == position) || (i >= tempLevel && page > position))
                    Util.setImageFromPath(images.get(i - 1), imagePath + "/Stage" + (position + 1) + "/level" + i + ".png");
                else
                    Util.setImageFromPath(images.get(i - 1), imagePath + "/Stage" + (position + 1) + "/Locked/level" + i + ".png");
            }
            Drawable drawable = Drawable.createFromPath(imagePath + "/Stage" + (position + 1) + "/home_bg_s" + (position + 1) + ".png");
            drawable.setAlpha(90);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
                layout.setBackground(drawable);
            else
                layout.setBackgroundDrawable(drawable);

            level1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    levelNo = (viewPager.getCurrentItem() * 7) + 1;
                    if (lastLevelUnlocked >= levelNo) {
                        Intent i = new Intent(LevelsActivity.this, SubLevelsActivity.class);
                        i.putExtra("Level", levelNo);
                        startActivity(i);
                    }
                }

            });

            level2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    levelNo = (viewPager.getCurrentItem() * 7) + 2;
                    if (lastLevelUnlocked >= levelNo) {

                        Intent i = new Intent(LevelsActivity.this, SubLevelsActivity.class);
                        i.putExtra("Level", levelNo);
                        startActivity(i);
                    }
                }
            });

            level3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Intent intent = new Intent(LevelsActivity.this,Lev)
                    levelNo = (viewPager.getCurrentItem() * 7) + 3;
                    if (lastLevelUnlocked >= levelNo) {

                        Intent i = new Intent(LevelsActivity.this, SubLevelsActivity.class);
                        i.putExtra("Level", levelNo);
                        startActivity(i);
                    }
                }
            });

            level4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Intent intent = new Intent(LevelsActivity.this,Lev)
                    levelNo = (viewPager.getCurrentItem() * 7) + 4;
                    if (lastLevelUnlocked >= levelNo) {

                        Intent i = new Intent(LevelsActivity.this, SubLevelsActivity.class);
                        i.putExtra("Level", levelNo);
                        startActivity(i);
                    }
                }
            });

            level5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Intent intent = new Intent(LevelsActivity.this,Lev)
                    levelNo = (viewPager.getCurrentItem() * 7) + 5;
                    if (lastLevelUnlocked >= levelNo) {

                        Intent i = new Intent(LevelsActivity.this, SubLevelsActivity.class);
                        i.putExtra("Level", levelNo);
                        startActivity(i);
                    }
                }
            });
            level6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Intent intent = new Intent(LevelsActivity.this,Lev)
                    levelNo = (viewPager.getCurrentItem() * 7) + 6;
                    if (lastLevelUnlocked >= levelNo) {

                        Intent i = new Intent(LevelsActivity.this, SubLevelsActivity.class);
                        i.putExtra("Level", levelNo);
                        startActivity(i);
                    }
                }
            });

            level7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Intent intent = new Intent(LevelsActivity.this,Lev)
                    levelNo = (viewPager.getCurrentItem() * 7) + 7;
                    if (lastLevelUnlocked >= levelNo) {

                        Intent i = new Intent(LevelsActivity.this, SubLevelsActivity.class);
                        i.putExtra("Level", levelNo);
                        startActivity(i);
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
    protected void onResume() {
        super.onResume();
        //lastLevelUnlocked = appDB.getLastLevelUnlocked(prefs.getStudentId());
        adapter.notifyDataSetChanged();
    }
}
