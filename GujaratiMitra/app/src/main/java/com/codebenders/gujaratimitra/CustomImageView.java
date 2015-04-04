package com.codebenders.gujaratimitra;

import android.content.Context;
import android.os.Environment;
import android.util.AttributeSet;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;

/**
 * Created by saurabh on 4/4/15.
 */
public class CustomImageView extends View {
    ImageView imageView;
    public CustomImageView(Context context, ViewGroup container) {
        super(context);
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(inflater != null){
            View v = inflater.inflate(R.layout.custom_image_view, container, false);
            imageView = (ImageView)v.findViewById(R.id.img1);
            container.addView(v);
        }
    }

    public ImageView getImageView(){
        return imageView;
    }

}
