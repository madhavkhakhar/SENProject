package com.codebenders.gujaratimitra.profile;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.codebenders.gujaratimitra.R;
import com.codebenders.gujaratimitra.Util;

import static com.codebenders.gujaratimitra.Util.appDB;

/**
 * Created by nihartrivedi810 on 18/4/15.
 */
public class ViewProfileDialog extends Dialog {

    Student student;
    Context context;

    public ViewProfileDialog(Context context, Student student) {
        super(context);
        this.student = student;
        this.context = context;
    }

    TextView firstName, lastName, standard, rollNo, currentLevel, score;
    Button edit, delete;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_profile_dialog);
        firstName = (TextView) findViewById(R.id.firstname);
        lastName = (TextView) findViewById(R.id.lastname);
        standard = (TextView) findViewById(R.id.standard);
        rollNo = (TextView) findViewById(R.id.roll_no);
        //firstName = (TextView) findViewById(R.id.score);
        currentLevel = (TextView) findViewById(R.id.currentlevel);
        score = (TextView) findViewById(R.id.score);
        delete = (Button) findViewById(R.id.delete);
        edit = (Button) findViewById(R.id.edit);
        firstName.setText(student.getFirstName());
        lastName.setText(student.getLastName());
        currentLevel.setText("Current level: " + student.getCurrentLevel());
        standard.setText("Standard: " + student.getStandard());
        rollNo.setText("Roll no: " + student.getRoll());
        score.setText("Score: " + student.getScore());
        if(student.getId()==0){
            delete.setVisibility(View.GONE);
            edit.setVisibility(View.GONE);
            standard.setVisibility(View.GONE);
            rollNo.setVisibility(View.GONE);
            lastName.setVisibility(View.GONE);
        }
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Delete");
                builder.setMessage("Are you sure you want to delete the entry?");
                builder.setPositiveButton("Yes",new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Util.appDB.removeStudent(student.getId());
                        dismiss();
                        ((ProfileActivity)context).refreshListView();
                    }
                });
                builder.setNegativeButton("No", new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                    }
                });
                builder.show();
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                DisplayMetrics metrics = context.getResources()
                        .getDisplayMetrics();
                int width = metrics.widthPixels;
                int height = metrics.heightPixels;
                CreateProfileDialog createProfileDialog = new CreateProfileDialog(
                        context,student,true);
                createProfileDialog.setCanceledOnTouchOutside(true);
                createProfileDialog.show();
                createProfileDialog.getWindow().setLayout((4 * width) / 7,
                        (6 * height) / 7);
                createProfileDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        ((ProfileActivity)context).refreshListView();
                    }
                });
            }
        });

    }
}
