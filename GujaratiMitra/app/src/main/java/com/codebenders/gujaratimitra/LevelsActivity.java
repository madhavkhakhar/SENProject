package com.codebenders.gujaratimitra;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.codebenders.gujaratimitra.profile.CreateProfileDialog;
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
    String imagePath = "/Start/";
    CustomPagerAdapter adapter;
    private Button profiles, aboutUs;
    private TextView currentProfile;

    AudioManager audioManager;
    int mediaVolume;
    AlertDialog.Builder builder;
    ObbExpansionsManager instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);
        viewPager = (ViewPager) findViewById(R.id.pager);
        adapter = new CustomPagerAdapter();
        currentProfile = (TextView) findViewById(R.id.current_profile);
        viewPager.setOffscreenPageLimit(NUM_PAGES);

        ObbExpansionsManager.ObbListener listener = new ObbExpansionsManager.ObbListener() {
            @Override
            public void onFilesNotFound() {
                System.out.println("No obb files found");
            }

            @Override
            public void onMountSuccess() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        viewPager.setAdapter(adapter);
                    }
                });
            }
        };

        instance = ObbExpansionsManager.createNewInstance(LevelsActivity.this,listener);

        lastLevelUnlocked = appDB.getLastLevelUnlocked(prefs.getStudentId());
        //lastLevelUnlocked = 20;
        //System.out.println("prefs student id" +prefs.getStudentId());
        profiles = (Button) findViewById(R.id.profiles);
        aboutUs = (Button) findViewById(R.id.about_us);
        if (Util.appDB.getStudentById(0) == null) {
            Util.appDB.insertStudent(new Student(0, 0, "Guest", "", 1, 0), 0);
        }
        if (Util.appDB.getStudentById(1) == null) {
            Util.appDB.insertStudent(new Student(1, 0, "Test", "", 20, 0), 1);
        }
        if (prefs.getStudentId() == 1) {
            lastLevelUnlocked = 20;
        }
        profiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LevelsActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
        aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisplayMetrics metrics = LevelsActivity.this.getResources()
                        .getDisplayMetrics();
                int width = metrics.widthPixels;
                int height = metrics.heightPixels;
                AboutUs aboutUs = new AboutUs(
                        LevelsActivity.this);
                aboutUs.setTitle("About us");
                aboutUs.setCanceledOnTouchOutside(true);
                aboutUs.show();
                aboutUs.getWindow().setLayout((4 * width) / 7,
                        (6 * height) / 7);
            }
        });
        currentProfile.setText(appDB.getStudentById(prefs.getStudentId()).getFirstName());


        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        mediaVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        builder = new AlertDialog.Builder(LevelsActivity.this);
        if (mediaVolume <= 1) {
            builder.setIcon(R.drawable.alert);
            builder.setTitle("Media Volume");
            builder.setCancelable(false);
            builder.setMessage("Media volume is too low, do you want to increase?");
            builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,10,0);
                    startActivityForResult(new Intent(Settings.ACTION_SOUND_SETTINGS), 0);
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            builder.show();
        }


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
            for (int i = 1; i <= images.size(); i++) {
                if ((position * 7) + i <= lastLevelUnlocked)
                    Util.setImageFromPath(images.get(i - 1), imagePath + "/Stage" + (position + 1) + "/level" + i + ".png");
                else
                    Util.setImageFromPath(images.get(i - 1), imagePath + "/Stage" + (position + 1) + "/Locked/level" + i + ".png");
            }

            ArrayList<Integer> pathsToHomeBg = new ArrayList<Integer>();
            pathsToHomeBg.add(R.drawable.home_bg_s1);
            pathsToHomeBg.add(R.drawable.home_bg_s2);
            pathsToHomeBg.add(R.drawable.home_bg_s3);

            Drawable drawable = getResources().getDrawable(pathsToHomeBg.get(position));
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

            if (position != 2) {
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
            }
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            container.removeView((View) object);
        }
    }

    protected void onResume() {
        super.onResume();
        lastLevelUnlocked = appDB.getLastLevelUnlocked(prefs.getStudentId());
        if (prefs.getStudentId() == 1) {
            lastLevelUnlocked = 20;
        }
        currentProfile.setText(appDB.getStudentById(prefs.getStudentId()).getFirstName());
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            builder
                    .setIcon(R.drawable.ic_dialog_alert_1)
                    .setTitle("Confirm Exit")
                    .setMessage("Do you want to quit the game?")
                    .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            LevelsActivity.this.finish();
                        }
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }

    }


}
