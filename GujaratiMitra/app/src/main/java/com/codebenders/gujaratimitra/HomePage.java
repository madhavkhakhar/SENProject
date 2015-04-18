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
import com.codebenders.gujaratimitra.profile.Student;


public class HomePage extends ActionBarActivity {

    Button profiles,play;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        profiles = (Button)findViewById(R.id.profile_button);
        play = (Button)findViewById(R.id.play_button);
        if(Util.appDB.getStudentById(0)==null){
            Util.appDB.insertStudent(new Student(0,0,"","Guest",1,0),0);
        }

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.prefs.saveStudentId(0);
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
