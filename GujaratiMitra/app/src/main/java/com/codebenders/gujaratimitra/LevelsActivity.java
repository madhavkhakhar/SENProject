package com.codebenders.gujaratimitra;

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


    ListView listView;
    ArrayList<String> listItems= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);
        Util.mediaPlayer = new MediaPlayer();
        listView = (ListView)findViewById(R.id.listView);
        for(int i=1;i<=20;i++)
            listItems.add(i+"");
        ArrayAdapter adapter = new ArrayAdapter(LevelsActivity.this,android.R.layout.simple_list_item_1,listItems);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(LevelsActivity.this,"position"+position,Toast.LENGTH_SHORT).show();
                if (position == 3) {
                    Intent i = new Intent(LevelsActivity.this, Level4_1.class);
                    startActivity(i);
                }
            }
        });
    }

}
