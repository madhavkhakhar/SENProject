package com.codebenders.gujaratimitra;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;


public class Level4_2 extends ActionBarActivity {
    //ListView lv;
    int mFlipping = 0;
    int countexample=5;
    ViewFlipper flipper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level4_2);
        /*lv = (ListView) findViewById(R.id.listView_l4_2);

        ListItem_level4[] items = new ListItem_level4[5];
        for(int i=0;i<5;i++){
            items[i]=new ListItem_level4();
        }
        items[0].addImage(R.drawable.level4_2_img_e1_1);
        items[1].addImage(R.drawable.level4_2_img_e1_2);
        items[2].addImage(R.drawable.level4_2_img_e1_3);
        items[3].addImage(R.drawable.level4_2_img_e1_4);
        items[4].addImage(R.drawable.level4_2_img_e1_5);

        ItemsAdapter adapter = new ItemsAdapter(this, R.layout.listitem_l4_2, items);
        lv.setAdapter(adapter);*/

        flipper = (ViewFlipper) findViewById(R.id.flipper1);

        flipper.getInAnimation().setAnimationListener(new Animation.AnimationListener() {

            public void onAnimationStart(Animation animation) {}
            public void onAnimationRepeat(Animation animation) {}
            public void onAnimationEnd(Animation animation) {

                int displayedChild = flipper.getDisplayedChild();
                int childCount = flipper.getChildCount();

                if (displayedChild == childCount - 1) {
                    flipper.stopFlipping();
                }
            }
        });



        /*mButton = (Button) findViewById(R.id.btnSlide);
        View.OnClickListener listener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ViewFlipper flipper = (ViewFlipper) findViewById(R.id.flipper1);

                if(mFlipping==0){
                    *//** Start Flipping *//*
                    flipper.startFlipping();

                    mFlipping=1;
                    mButton.setText("Stop Sliding");
                }
                else{
                    *//** Stop Flipping *//*
                    flipper.stopFlipping();
                    mFlipping=0;
                    mButton.setText("Start Sliding");
                }
            }
        };
        mButton.setOnClickListener(listener);*/

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_level4_2, menu);
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
