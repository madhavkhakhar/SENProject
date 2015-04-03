package com.codebenders.gujaratimitra;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class SubLevelsActivity extends ActionBarActivity {
    ListView lvSublevels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_levels);
        lvSublevels = (ListView) findViewById(R.id.listViewSublevels);
        ArrayList<String> list = new ArrayList<String>();
        list.add("Pravruti 1");
        list.add("Pravruti 2");
        list.add("Pravruti 3");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        lvSublevels.setAdapter(adapter);
        lvSublevels.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i;
                int levelNo = getIntent().getExtras().getInt("Level");
                System.out.println("Leve no==>"+levelNo);
                switch(levelNo){
                    case 1:

                        break;
                    case 2:
                        if (position == 0) {
                            i = new Intent(SubLevelsActivity.this, Level2_1.class);
                            startActivity(i);
                        } else if (position == 2) {
                            i = new Intent(SubLevelsActivity.this, Level2_3.class);
                            startActivity(i);
                        }
                        break;
                    case 3:
                        if(position==0){
                            //Set your intent here
                            i = new Intent(SubLevelsActivity.this,Level3_1.class);
                            startActivity(i);
                        }
                        break;
                    case 4:
                        if(position==0){
                            //Set your intent here
                            i = new Intent(SubLevelsActivity.this,Level4_1.class);
                            startActivity(i);
                        }
                        else if(position==1){
                            i = new Intent(SubLevelsActivity.this,Level4_2.class);
                            startActivity(i);
                        }
                        else if(position==2){
                        }
                        break;
                    case 5:
                        if(position==0){
                            //Set your intent here
                            i = new Intent(SubLevelsActivity.this,Level5_1.class);
                            i.putExtra("LevelNo",5);
                            startActivity(i);
                        }
                        else if(position==1){
                            i = new Intent(SubLevelsActivity.this,Level5_2.class);
                            i.putExtra("LevelNo",5);
                            startActivity(i);
                        }
                        else if(position==2){
                        }
                        break;
                    case 6:
                        if(position==0){
                            //Set your intent here
                            i = new Intent(SubLevelsActivity.this,Level5_1.class);
                            i.putExtra("LevelNo",6);
                            startActivity(i);
                        }
                        else if(position==1){
                            i = new Intent(SubLevelsActivity.this,Level5_2.class);
                            i.putExtra("LevelNo",6);
                            startActivity(i);
                        }
                        else if(position==2){
                        }
                        break;
                    case 7:
                        if(position==0){
                            //Set your intent here
                            i = new Intent(SubLevelsActivity.this,Level5_1.class);
                            i.putExtra("LevelNo",7);
                            startActivity(i);
                        }
                        else if(position==1){
                            i = new Intent(SubLevelsActivity.this,Level5_2.class);
                            i.putExtra("LevelNo",7);
                            startActivity(i);
                        }
                        else if(position==2){
                        }
                        break;
                    case 14:
                        if(position==0){
//                            i = new Intent(SubLevelsActivity.this,Level14_1.class);
//                            startActivity(i);
                        }
                        else if(position==1){
//                            i = new Intent(SubLevelsActivity.this,Level14_3.class);
//                            startActivity(i);
                        }
                        else if(position==2){
                            i = new Intent(SubLevelsActivity.this,Level14_3.class);
                            startActivity(i);
                        }
                        break;
                }

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sub_levels, menu);
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
