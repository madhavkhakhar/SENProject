package com.codebenders.gujaratimitra;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;


/**
 * Created by raxit on 20/4/15.
 */
public class AboutUs extends Dialog {

    public AboutUs(Context context) {
        super(context);
    }

    protected void onCreate(Context context) {
        super.onCreate(onSaveInstanceState());
        setContentView(R.layout.activity_aboutus);

    }
}

