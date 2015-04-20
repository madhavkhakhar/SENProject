package com.codebenders.gujaratimitra;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by madhav on 13/2/15.
 */
public class ListAdapterSublevel extends BaseAdapter {

    private Context context;
    private ArrayList<ListItem> listItems;

    public ListAdapterSublevel(Context context, ArrayList<ListItem> listItems){
        this.context = context;
        this.listItems = listItems;
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int position) {
        return listItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.list_item_sublevel, null);
        }

        ImageView imgIcon = (ImageView) convertView.findViewById(R.id.icon);
        TextView score = (TextView) convertView.findViewById(R.id.score);
        imgIcon.setImageResource(listItems.get(position).getIcon());
        score.setText(listItems.get(position).getScore()+"");
        return convertView;
    }

}
