package com.codebenders.gujaratimitra;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.codebenders.gujaratimitra.database.AppDB;
import com.codebenders.gujaratimitra.profile.ProfileActivity;


public class HomePage extends ActionBarActivity {

    Button profiles,play;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Util.mediaPlayer = new MediaPlayer();
        Util.appDB = new AppDB(HomePage.this);
        setContentView(R.layout.activity_home_page);
        profiles = (Button)findViewById(R.id.profile_button);
        play = (Button)findViewById(R.id.play_button);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this,LevelsActivity.class);
                startActivity(intent);
            }
        });
        profiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this,ProfileActivity.class);
                startActivity(intent);
            }
        });
    }

}