package com.codebenders.gujaratimitra.profile;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.codebenders.gujaratimitra.R;

import java.util.ArrayList;

/**
 * Created by nihartrivedi810 on 31/3/15.
 */
public class ProfileListAdapter extends ArrayAdapter {

    Context context;
    int layoutId;
    ArrayList<Student> students;

    public ProfileListAdapter(Context context, int resource, ArrayList<Student> objects) {
        super(context, resource, objects);
        students = objects;
        this.context = context;
        layoutId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ItemHolder itemHolder;
        if (view == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            itemHolder = new ItemHolder();
            view = inflater.inflate(layoutId, null);
            itemHolder.firstname = (TextView) view.findViewById(R.id.name);
            itemHolder.rollNo = (TextView) view.findViewById(R.id.roll_no);
            itemHolder.standard= (TextView) view.findViewById(R.id.standard);
            view.setTag(itemHolder);
        } else {
            itemHolder = (ItemHolder) view.getTag();
        }
        Student student = students.get(position);
        itemHolder.firstname.setText(student.getFirstName()+" "+student.getLastName());
        itemHolder.rollNo.setText(student.getRoll()+"");
        itemHolder.standard.setText(student.getStandard()+"");
        return view;
    }

    class ItemHolder{
        TextView firstname, standard,rollNo;
    }
}
