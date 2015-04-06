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
                System.out.println("Level no==>"+levelNo);
                switch(levelNo){
                    case 1:
                        if (position == 0) {
                            i = new Intent(SubLevelsActivity.this, Level1_1.class);
                            i.putExtra("level_no", 1);
                            i.putExtra("Sublevel", 1);
                            startActivity(i);
                        } else if (position == 1) {
                            i = new Intent(SubLevelsActivity.this, Level1_2.class);
                            startActivity(i);
                        }
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
                    case 8:
                        if(position==0){
                            //Set your intent here
                            i = new Intent(SubLevelsActivity.this,Level8_1.class);
                            i.putExtra("LevelNo",8);
                            startActivity(i);
                        }
                        else if(position==1){
                            
                        }
                        else if(position==2){
                        }
                        break;
                    case 10:
                        if(position==0){
                            i = new Intent(SubLevelsActivity.this,Level10_1.class);
                            i.putExtra("LevelNo",10);
                            startActivity(i);
                        }
                        else if(position==2) {
                            //i = new Intent(SubLevelsActivity.this, Level11_3.class);
                            //i.putExtra("LevelNo", 10);
                        }
                        break;
                    case 11:
                        if(position == 0){
                            i = new Intent(SubLevelsActivity.this,Level11_1.class);
                            i.putExtra("LevelNo",11);
                            startActivity(i);
                        }
                        else if(position==1){
                            i = new Intent(SubLevelsActivity.this,Level11_2.class);
                            i.putExtra("LevelNo",11);
                            startActivity(i);
                        }
                        else if(position==2) {
                            i = new Intent(SubLevelsActivity.this, Level11_3.class);
                            i.putExtra("LevelNo", 11);
                            startActivity(i);
                        }
                            break;
                    case 12:
                        if(position==0){
                            i = new Intent(SubLevelsActivity.this,Level12_1.class);
                            i.putExtra("LevelNo",12);
                            startActivity(i);
                        }
                        break;
                    case 14:
                        if(position==0){
//                            i = new Intent(SubLevelsActivity.this,Level14_1.class);
//                            startActivity(i);
                        }
                        else if(position==1){
                            i = new Intent(SubLevelsActivity.this,Level14_2.class);
                            i.putExtra("LevelNo",14);
                            startActivity(i);
                        }
                        else if(position==2){
                            i = new Intent(SubLevelsActivity.this,Level14_3.class);
                            i.putExtra("LevelNo",14);
                            startActivity(i);
                        }
                        break;
                    case 15:
                        if(position==0){
//                            i = new Intent(SubLevelsActivity.this,Level14_1.class);
//                            startActivity(i);
                        }
                        else if(position==1){
                            i = new Intent(SubLevelsActivity.this,Level15_2.class);
                            startActivity(i);
                        }
                        else if(position==2){
                            i = new Intent(SubLevelsActivity.this,Level15_3.class);
                            i.putExtra("Sublevel",15);
                            startActivity(i);
                        }
                        break;
                    case 16:
                        if(position==0) {
                            i = new Intent(SubLevelsActivity.this, Level16_1.class);
                            i.putExtra("Sublevel",1);
                            i.putExtra("Level",16);
                            startActivity(i);
                        }
                        else if(position==1){
                            i = new Intent(SubLevelsActivity.this, Level16_1.class);
                            i.putExtra("Sublevel",2);
                            i.putExtra("Level",16);
                            startActivity(i);
                        }
                        else if(position==2){
                            i = new Intent(SubLevelsActivity.this, Level16_1.class);
                            i.putExtra("Sublevel",3);
                            i.putExtra("Level",16);
                            startActivity(i);
                        }
                        break;
                    case 17:
                        if(position==0) {
                            i = new Intent(SubLevelsActivity.this, Level1_1.class);
                            i.putExtra("level_no",17);
                            startActivity(i);
                        }
                        break;
                    case 18:
                        if(position==0) {
                            i = new Intent(SubLevelsActivity.this, Level16_1.class);
                            i.putExtra("Sublevel",1);
                            i.putExtra("Level",18);
                            startActivity(i);
                        }
                        else if(position==1){
                            i = new Intent(SubLevelsActivity.this, Level16_1.class);
                            i.putExtra("Sublevel",2);
                            i.putExtra("Level",18);
                            startActivity(i);
                        }
                        else if(position==2){
                            i = new Intent(SubLevelsActivity.this, Level18_3.class);
                            startActivity(i);
                        }
                        break;
                    case 20:
                        if(position==0){
                            i = new Intent(SubLevelsActivity.this,Level20_1.class);
                            startActivity(i);
                        }
                        else if(position==1){
                            i = new Intent(SubLevelsActivity.this,Level20_2.class);
                            startActivity(i);
                        }
                        else if(position==2){

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
