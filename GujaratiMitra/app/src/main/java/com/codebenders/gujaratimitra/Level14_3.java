package com.codebenders.gujaratimitra;

import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.color.transparent;


public class Level14_3 extends ActionBarActivity {
    private ImageView queImg;
    private int NUM_QUE=10;
    private LinearLayout l1;
    private ImageView check, speaker;
    private int currentQueIndex;
    private int correctAns[][];
    private int pos[];
    private int numberOfLetters[];
    private String imagePrefix[][];
    private CustomImageView cv1;
    private CustomImageView cv2;
    private CustomImageView cv3;
    private CustomImageView cv4;
    private TextView score_text;
    private int score=0;
    private boolean wait=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level14_3);

        queImg = (ImageView) findViewById(R.id.que_statement);
        l1 = (LinearLayout) findViewById(R.id.linearLayout1);
        check = (ImageView) findViewById(R.id.check);
        score_text=(TextView) findViewById(R.id.score);
        score_text.setText("SCORE "+String.valueOf(score)+"/"+String.valueOf(NUM_QUE));
        speaker = (ImageView)findViewById(R.id.lspeaker);
        speaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.playMediaFromPath(Environment.getExternalStorageDirectory()+"/GujaratiMitra/l14/3/aud_que_14_3.wav");
            }
        });
        Util.setImageFromPath(queImg, Environment.getExternalStorageDirectory() + "/GujaratiMitra/l14/3/que_14_3.png");

        currentQueIndex = 0;
        pos = new int[4];
        for (int i = 0; i < 4; i++) {
            pos[i] = 0;
        }
        numberOfLetters = new int[10];
        for (int i = 0; i < 10; i++) {
            numberOfLetters[i] = 3;
        }
        numberOfLetters[0] = 2;
        numberOfLetters[1] = 2;
        numberOfLetters[8] = 4;
        numberOfLetters[9] = 4;

        imagePrefix = new String[10][4];
        imagePrefix[0][0] = "img_p_";
        imagePrefix[0][1] = "img_j_";
        imagePrefix[1][0] = "img_g_";
        imagePrefix[1][1] = "img_ta_";
        imagePrefix[2][0] = "img_m_";
        imagePrefix[2][1] = "img_t_";
        imagePrefix[2][2] = "img_l_";
        imagePrefix[3][0] = "img_k_";
        imagePrefix[3][1] = "img_k_";
        imagePrefix[3][2] = "img_d_";
        imagePrefix[4][0] = "img_ch_";
        imagePrefix[4][1] = "img_k_";
        imagePrefix[4][2] = "img_l_";
        imagePrefix[5][0] = "img_p_";
        imagePrefix[5][1] = "img_sh_";
        imagePrefix[5][2] = "img_k_";
        imagePrefix[6][0] = "img_ta_";
        imagePrefix[6][1] = "img_r_";
        imagePrefix[6][2] = "img_na_";
        imagePrefix[7][0] = "img_g_";
        imagePrefix[7][1] = "img_j_";
        imagePrefix[7][2] = "img_r_";
        imagePrefix[8][0] = "img_ch_";
        imagePrefix[8][1] = "img_g_";
        imagePrefix[8][2] = "img_d_";
        imagePrefix[8][3] = "img_la_";
        imagePrefix[9][0] = "img_b_";
        imagePrefix[9][1] = "img_l_";
        imagePrefix[9][2] = "img_b_";
        imagePrefix[9][3] = "img_l_";

        correctAns = new int[10][4];
        correctAns[0][0] = 1;
        correctAns[0][1] = 1;
        correctAns[0][2] = 0;
        correctAns[0][3] = 0;
        correctAns[1][0] = 2;
        correctAns[1][1] = 2;
        correctAns[1][2] = 0;
        correctAns[1][3] = 0;
        correctAns[2][0] = 1;
        correctAns[2][1] = 0;
        correctAns[2][2] = 3;
        correctAns[2][3] = 0;
        correctAns[3][0] = 1;
        correctAns[3][1] = 2;
        correctAns[3][2] = 1;
        correctAns[3][3] = 0;
        correctAns[4][0] = 0;
        correctAns[4][1] = 2;
        correctAns[4][2] = 3;
        correctAns[4][3] = 0;
        correctAns[5][0] = 2;
        correctAns[5][1] = 1;
        correctAns[5][2] = 2;
        correctAns[5][3] = 0;
        correctAns[6][0] = 1;
        correctAns[6][1] = 0;
        correctAns[6][2] = 0;
        correctAns[6][3] = 0;
        correctAns[7][0] = 1;
        correctAns[7][1] = 0;
        correctAns[7][2] = 0;
        correctAns[7][3] = 0;
        correctAns[8][0] = 0;
        correctAns[8][1] = 0;
        correctAns[8][2] = 1;
        correctAns[8][3] = 0;
        correctAns[9][0] = 1;
        correctAns[9][1] = 0;
        correctAns[9][2] = 1;
        correctAns[9][3] = 0;

        loadInitialImagesOfQuestion();
        setClickListenersForImageViews();

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(wait==false)
                    loadNextQuestion();
            }
        });
        Util.playMediaFromPath(Environment.getExternalStorageDirectory() + "/GujaratiMitra/l14/3/aud_1.wav");
    }

    public void loadNextQuestion() {
        final ImageView green_tick=new ImageView(this);
        final ImageView red_cross=new ImageView(this);
        final Toast toast = new Toast(this);

        green_tick.setImageResource(R.drawable.greentick);
        red_cross.setImageResource(R.drawable.redcross);

        new Thread(){
            public void run(){
                try{
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            wait=true;
                            Vibrator v = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
                            int i;
                            for (i = 0; i < 4; i++) {
                                if (correctAns[currentQueIndex][i] != pos[i])
                                    break;
                            }
                            if (i == 4) {
                                score++;
                                toast.setView(green_tick);
                                toast.show();
                                score_text.setText("SCORE "+String.valueOf(score)+"/"+String.valueOf(NUM_QUE));
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
                            loadCorrectAnsOfQuestion();
                        }
                    });
                    Thread.sleep(1500);
                    wait=false;
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            currentQueIndex = (currentQueIndex+1);
                            if(currentQueIndex>=NUM_QUE){
                                Util.setNextLevel(Level14_3.this,score,3,14,true);
                            }

//                            v1.setBackgroundColor(transparent);
//                            v2.setBackgroundColor(transparent);
//                            v3.setBackgroundColor(transparent);
//                            v4.setBackgroundColor(transparent);
                            else{
                            pos = new int[4];
                            for (int i = 0; i < 4; i++) {
                                pos[i] = 0;
                            }
                            loadInitialImagesOfQuestion();
                            Util.playMediaFromPath(Environment.getExternalStorageDirectory() + "/GujaratiMitra/l14/3/aud_"+(currentQueIndex+1)+".wav");
                        }}
                    });
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void loadInitialImagesOfQuestion () {
        l1.setGravity(Gravity.LEFT);
        ViewGroup.LayoutParams params;
        switch (numberOfLetters[currentQueIndex]) {
            case 2:
                l1.removeAllViews();
                cv1 = new CustomImageView(this, l1);
                cv2 = new CustomImageView(this, l1);
                Util.setImageFromPath(cv1.getImageView(), Environment.getExternalStorageDirectory() + "/GujaratiMitra/l14/3/" + imagePrefix[currentQueIndex][0] + Integer.toString(pos[0] + 1) + ".png");
                Util.setImageFromPath(cv2.getImageView(), Environment.getExternalStorageDirectory() + "/GujaratiMitra/l14/3/" + imagePrefix[currentQueIndex][1] + Integer.toString(pos[1] + 1) + ".png");
                l1.setGravity(Gravity.CENTER);
                params = new ViewGroup.LayoutParams(0, 0);
                cv1.setLayoutParams(params);
                cv2.setLayoutParams(params);
                l1.addView(cv1.getInflatedView());
                l1.addView(cv2.getInflatedView());
                break;
            case 3:
                l1.removeAllViews();
                cv1 = new CustomImageView(this, l1);
                cv2 = new CustomImageView(this, l1);
                cv3 = new CustomImageView(this, l1);
                Util.setImageFromPath(cv1.getImageView(), Environment.getExternalStorageDirectory() + "/GujaratiMitra/l14/3/" + imagePrefix[currentQueIndex][0] + Integer.toString(pos[0]+1) + ".png");
                Util.setImageFromPath(cv2.getImageView(), Environment.getExternalStorageDirectory() + "/GujaratiMitra/l14/3/" + imagePrefix[currentQueIndex][1] + Integer.toString(pos[1]+1) + ".png");
                Util.setImageFromPath(cv3.getImageView(), Environment.getExternalStorageDirectory() + "/GujaratiMitra/l14/3/" + imagePrefix[currentQueIndex][2] + Integer.toString(pos[2] + 1) + ".png");
                params = new ViewGroup.LayoutParams(0, 0);
                cv1.setLayoutParams(params);
                cv2.setLayoutParams(params);
                cv3.setLayoutParams(params);
                l1.addView(cv1.getInflatedView());
                l1.addView(cv2.getInflatedView());
                l1.addView(cv3.getInflatedView());
                break;
            case 4:
                l1.removeAllViews();
                cv1 = new CustomImageView(this, l1);
                cv2 = new CustomImageView(this, l1);
                cv3 = new CustomImageView(this, l1);
                cv4 = new CustomImageView(this, l1);
                Util.setImageFromPath(cv1.getImageView(), Environment.getExternalStorageDirectory() + "/GujaratiMitra/l14/3/" + imagePrefix[currentQueIndex][0] + Integer.toString(pos[0]+1) + ".png");
                Util.setImageFromPath(cv2.getImageView(), Environment.getExternalStorageDirectory() + "/GujaratiMitra/l14/3/" + imagePrefix[currentQueIndex][1] + Integer.toString(pos[1]+1) + ".png");
                Util.setImageFromPath(cv3.getImageView(), Environment.getExternalStorageDirectory() + "/GujaratiMitra/l14/3/" + imagePrefix[currentQueIndex][2] + Integer.toString(pos[2]+1) + ".png");
                Util.setImageFromPath(cv4.getImageView(), Environment.getExternalStorageDirectory() + "/GujaratiMitra/l14/3/" + imagePrefix[currentQueIndex][3] + Integer.toString(pos[3]+1) + ".png");
                params = new ViewGroup.LayoutParams(0, 0);
                cv1.setLayoutParams(params);
                cv2.setLayoutParams(params);
                cv3.setLayoutParams(params);
                cv4.setLayoutParams(params);
                l1.addView(cv1.getInflatedView());
                l1.addView(cv2.getInflatedView());
                l1.addView(cv3.getInflatedView());
                l1.addView(cv4.getInflatedView());
                break;
            default:
                break;
        }
        setClickListenersForImageViews();
    }

    public void loadCorrectAnsOfQuestion () {
        switch (numberOfLetters[currentQueIndex]) {
            case 2:
                Util.setImageFromPath(cv1.getImageView(), Environment.getExternalStorageDirectory() + "/GujaratiMitra/l14/3/" + imagePrefix[currentQueIndex][0] + Integer.toString(correctAns[currentQueIndex][0] + 1) + ".png");
                Util.setImageFromPath(cv2.getImageView(), Environment.getExternalStorageDirectory() + "/GujaratiMitra/l14/3/" + imagePrefix[currentQueIndex][1] + Integer.toString(correctAns[currentQueIndex][1] + 1) + ".png");
                cv1.getImageView().setBackgroundResource(R.drawable.image_border_green);
                cv2.getImageView().setBackgroundResource(R.drawable.image_border_green);
                break;
            case 3:
                Util.setImageFromPath(cv1.getImageView(), Environment.getExternalStorageDirectory() + "/GujaratiMitra/l14/3/" + imagePrefix[currentQueIndex][0] + Integer.toString(correctAns[currentQueIndex][0]+1) + ".png");
                Util.setImageFromPath(cv2.getImageView(), Environment.getExternalStorageDirectory() + "/GujaratiMitra/l14/3/" + imagePrefix[currentQueIndex][1] + Integer.toString(correctAns[currentQueIndex][1]+1) + ".png");
                Util.setImageFromPath(cv3.getImageView(), Environment.getExternalStorageDirectory() + "/GujaratiMitra/l14/3/" + imagePrefix[currentQueIndex][2] + Integer.toString(correctAns[currentQueIndex][2] + 1) + ".png");
                cv1.getImageView().setBackgroundResource(R.drawable.image_border_green);
                cv2.getImageView().setBackgroundResource(R.drawable.image_border_green);
                cv3.getImageView().setBackgroundResource(R.drawable.image_border_green);
                break;
            case 4:
                Util.setImageFromPath(cv1.getImageView(), Environment.getExternalStorageDirectory() + "/GujaratiMitra/l14/3/" + imagePrefix[currentQueIndex][0] + Integer.toString(correctAns[currentQueIndex][0]+1) + ".png");
                Util.setImageFromPath(cv2.getImageView(), Environment.getExternalStorageDirectory() + "/GujaratiMitra/l14/3/" + imagePrefix[currentQueIndex][1] + Integer.toString(correctAns[currentQueIndex][1]+1) + ".png");
                Util.setImageFromPath(cv3.getImageView(), Environment.getExternalStorageDirectory() + "/GujaratiMitra/l14/3/" + imagePrefix[currentQueIndex][2] + Integer.toString(correctAns[currentQueIndex][2]+1) + ".png");
                Util.setImageFromPath(cv4.getImageView(), Environment.getExternalStorageDirectory() + "/GujaratiMitra/l14/3/" + imagePrefix[currentQueIndex][3] + Integer.toString(correctAns[currentQueIndex][3]+1) + ".png");
                cv1.getImageView().setBackgroundResource(R.drawable.image_border_green);
                cv2.getImageView().setBackgroundResource(R.drawable.image_border_green);
                cv3.getImageView().setBackgroundResource(R.drawable.image_border_green);
                cv4.getImageView().setBackgroundResource(R.drawable.image_border_green);
                break;
            default:
                break;
        }
    }

    public void setClickListenersForImageViews () {
        if (numberOfLetters[currentQueIndex] >= 2) {
            cv1.getImageView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pos[0] = (pos[0] + 1) % 4;
                    Util.setImageFromPath(cv1.getImageView(), Environment.getExternalStorageDirectory() + "/GujaratiMitra/l14/3/" + imagePrefix[currentQueIndex][0] + Integer.toString(pos[0] + 1) + ".png");
                }
            });
            cv2.getImageView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pos[1] = (pos[1] + 1) % 4;
                    Util.setImageFromPath(cv2.getImageView(), Environment.getExternalStorageDirectory() + "/GujaratiMitra/l14/3/" + imagePrefix[currentQueIndex][1] + Integer.toString(pos[1] + 1) + ".png");
                }
            });
        }
        if (numberOfLetters[currentQueIndex] >= 3) {
            cv3.getImageView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pos[2] = (pos[2] + 1) % 4;
                    Util.setImageFromPath(cv3.getImageView(), Environment.getExternalStorageDirectory() + "/GujaratiMitra/l14/3/" + imagePrefix[currentQueIndex][2] + Integer.toString(pos[2] + 1) + ".png");
                }
            });
        }
        if (numberOfLetters[currentQueIndex] >= 4) {
            cv4.getImageView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pos[3] = (pos[3] + 1) % 4;
                    Util.setImageFromPath(cv4.getImageView(), Environment.getExternalStorageDirectory() + "/GujaratiMitra/l14/3/" + imagePrefix[currentQueIndex][3] + Integer.toString(pos[3] + 1) + ".png");
                }
            });
        }
    }

}
