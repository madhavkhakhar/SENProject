package com.codebenders.gujaratimitra.profile;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.test.RenamingDelegatingContext;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.codebenders.gujaratimitra.R;

import java.util.ArrayList;

import static com.codebenders.gujaratimitra.Util.appDB;

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
            itemHolder.score = (TextView) view.findViewById(R.id.score);
            itemHolder.viewProfile = (Button) view.findViewById(R.id.view);
            view.setTag(itemHolder);
        } else {
            itemHolder = (ItemHolder) view.getTag();
        }
        Student student = students.get(position);

        itemHolder.firstname.setText(student.getFirstName()+" "+student.getLastName());
        itemHolder.score.setText(student.getScore() + "");
        itemHolder.viewProfile.setTag(position);
        itemHolder.viewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisplayMetrics metrics = context.getResources()
                        .getDisplayMetrics();
                int width = metrics.widthPixels;
                int height = metrics.heightPixels;
                ViewProfileDialog createProfileDialog = new ViewProfileDialog(
                        context,students.get(Integer.parseInt(v.getTag()+"")));
                createProfileDialog.setCanceledOnTouchOutside(true);
                createProfileDialog.show();
                createProfileDialog.getWindow().setLayout((4 * width) / 7,
                        (6 * height) / 7);
                createProfileDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                       /* students = appDB.getStudents();
                        adapter = new ProfileListAdapter(context, R.layout.profile_list_view_item, students);
                        listView.setAdapter(adapter);
                    */}
                });
            }
        });
        return view;
    }

    class ItemHolder{
        TextView firstname, score;
        Button viewProfile;
    }
}

