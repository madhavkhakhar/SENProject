package com.codebenders.gujaratimitra.profile;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.codebenders.gujaratimitra.R;
import com.codebenders.gujaratimitra.Util;

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
                    }
                });
                builder.setNegativeButton("No", new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                    }
                });
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateProfileDialog dialog = new CreateProfileDialog(context,student,true);
                dismiss();
            }
        });
    }
}
