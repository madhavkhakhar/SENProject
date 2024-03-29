package com.codebenders.gujaratimitra;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class Level2_3 extends ActionBarActivity {

    private static final int NUM_PAGES = 5;
    private static final int TOTAL_SCORE=5;
    private int SCORE=0;
    private  int currentQueIndex;
    private boolean selected1, selected2, selected3, selected4;
    private ImageView v1, v2, v3, v4;
    private int totalSelected;
    private TextView txtScore;
    private ImageView lSpeaker;
    private boolean sleeping;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level2_3);

        currentQueIndex = 0;
        sleeping = false;
        selected1 = selected2 = selected3 = selected4 = false;
        totalSelected = 0;
        txtScore = (TextView)findViewById(R.id.txtScore);
        lSpeaker = (ImageView)findViewById(R.id.lspeaker);

        lSpeaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.playMediaFromPath("/l2/3/aud_que_2_3.wav");
            }
        });
        txtScore.setText("SCORE:"+String.valueOf(SCORE)+"/"+String.valueOf(TOTAL_SCORE));

        Util.setImageFromPath((ImageView)findViewById(R.id.que_image), "/l2/3/que_2_3.png");
        v1 = (ImageView) findViewById(R.id.imageView1);
        v2 = (ImageView) findViewById(R.id.imageView2);
        v3 = (ImageView) findViewById(R.id.imageView3);
        v4 = (ImageView) findViewById(R.id.imageView4);

        Util.setImageFromPath(v1, "/l2/3/img_" + Integer.toString(currentQueIndex+1) + "_1.png");
        Util.setImageFromPath(v2, "/l2/3/img_" + Integer.toString(currentQueIndex+1) + "_2.png");
        Util.setImageFromPath(v3, "/l2/3/img_" + Integer.toString(currentQueIndex+1) + "_3.png");
        Util.setImageFromPath(v4, "/l2/3/img_" + Integer.toString(currentQueIndex+1) + "_4.png");

        v1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!sleeping) {
                    if (!selected1) {
                        selected1 = true;
                        totalSelected++;
                        v1.setBackgroundResource(R.drawable.image_border_black);
                        if (totalSelected == 2) {
                            loadNextQuestion();
                        }
                    } else {
                        selected1 = false;
                        v1.setBackgroundColor(Color.WHITE);
                        totalSelected--;
                    }
                }
            }
        });
        v2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!sleeping) {
                    if (!selected2) {
                        selected2 = true;
                        totalSelected++;
                        v2.setBackgroundResource(R.drawable.image_border_black);
                        if (totalSelected == 2) {
                            loadNextQuestion();
                        }
                    } else {
                        selected2 = false;
                        v2.setBackgroundColor(Color.WHITE);
                        totalSelected--;
                    }
                }
            }
        });
        v3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!sleeping) {
                    if (!selected3) {
                        selected3 = true;
                        totalSelected++;
                        v3.setBackgroundResource(R.drawable.image_border_black);
                        if (totalSelected == 2) {
                            loadNextQuestion();
                        }
                    } else {
                        selected3 = false;
                        v3.setBackgroundColor(Color.WHITE);
                        totalSelected--;
                    }
                }
            }
        });
        v4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!sleeping) {
                    if (!selected4) {
                        selected4 = true;
                        totalSelected++;
                        v4.setBackgroundResource(R.drawable.image_border_black);
                        if (totalSelected == 2) {
                            loadNextQuestion();
                        }
                    } else {
                        selected4 = false;
                        v4.setBackgroundColor(Color.WHITE);
                        totalSelected--;
                    }
                }
            }
        });
    }

    public void loadNextQuestion() {
        final ImageView green_tick=new ImageView(this);
        final ImageView red_cross=new ImageView(this);
        final Toast toast = new Toast(this);

        green_tick.setImageResource(R.drawable.greentick);
        red_cross.setImageResource(R.drawable.redcross);
        sleeping = true;
        new Thread(){
            public void run(){
                try{
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            Vibrator v = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
                            switch (currentQueIndex) {
                                case 0:
                                    if (selected1 && selected2) {
                                        SCORE++;
                                        txtScore.setText("SCORE:"+String.valueOf(SCORE)+"/"+String.valueOf(TOTAL_SCORE));
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
                                    v1.setBackgroundResource(R.drawable.image_border_green);
                                    v2.setBackgroundResource(R.drawable.image_border_green);
                                    v3.setBackgroundColor(Color.WHITE);
                                    v4.setBackgroundColor(Color.WHITE);
                                    break;
                                case 1:
                                    if (selected1 && selected2) {
                                        SCORE++;
                                        txtScore.setText("SCORE:"+String.valueOf(SCORE)+"/"+String.valueOf(TOTAL_SCORE));
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
                                    v1.setBackgroundResource(R.drawable.image_border_green);
                                    v3.setBackgroundColor(Color.WHITE);
                                    v4.setBackgroundColor(Color.WHITE);
                                    v2.setBackgroundResource(R.drawable.image_border_green);
                                    break;
                                case 2:
                                    if (selected1 && selected4) {
                                        SCORE++;
                                        txtScore.setText("SCORE:"+String.valueOf(SCORE)+"/"+String.valueOf(TOTAL_SCORE));
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
                                    v1.setBackgroundResource(R.drawable.image_border_green);
                                    v2.setBackgroundColor(Color.WHITE);
                                    v3.setBackgroundColor(Color.WHITE);
                                    v4.setBackgroundResource(R.drawable.image_border_green);
                                    break;
                                case 3:
                                    if (selected1 && selected3) {
                                        SCORE++;
                                        txtScore.setText("SCORE:"+String.valueOf(SCORE)+"/"+String.valueOf(TOTAL_SCORE));
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
                                    v1.setBackgroundResource(R.drawable.image_border_green);
                                    v3.setBackgroundResource(R.drawable.image_border_green);
                                    v2.setBackgroundColor(Color.WHITE);
                                    v4.setBackgroundColor(Color.WHITE);
                                    break;
                                case 4:
                                    if (selected1 && selected3) {
                                        SCORE++;
                                        txtScore.setText("SCORE:"+String.valueOf(SCORE)+"/"+String.valueOf(TOTAL_SCORE));
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
                                    v1.setBackgroundResource(R.drawable.image_border_green);
                                    v3.setBackgroundResource(R.drawable.image_border_green);
                                    v2.setBackgroundColor(Color.WHITE);
                                    v4.setBackgroundColor(Color.WHITE);
                                    break;
                                default:
                                    break;
                            }
                        }
                    });
                    Thread.sleep(1200);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            currentQueIndex = (currentQueIndex+1);
                            selected1 = selected2 = selected3 = selected4 = false;
                            totalSelected = 0;

                            Util.setImageFromPath(v1, "/l2/3/img_" + Integer.toString(currentQueIndex+1) + "_1.png");
                            Util.setImageFromPath(v2, "/l2/3/img_" + Integer.toString(currentQueIndex+1) + "_2.png");
                            Util.setImageFromPath(v3, "/l2/3/img_" + Integer.toString(currentQueIndex+1) + "_3.png");
                            Util.setImageFromPath(v4, "/l2/3/img_" + Integer.toString(currentQueIndex+1) + "_4.png");
                            v1.setBackgroundColor(Color.WHITE);
                            v2.setBackgroundColor(Color.WHITE);
                            v3.setBackgroundColor(Color.WHITE);
                            v4.setBackgroundColor(Color.WHITE);
                            sleeping = false;
                            if(currentQueIndex>=NUM_PAGES-1){
                                Util.setNextLevel(Level2_3.this,SCORE,3,2,true,false);
                            }
                        }
                    });
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
