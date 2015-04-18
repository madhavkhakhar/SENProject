package com.codebenders.gujaratimitra.profile;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.codebenders.gujaratimitra.LevelsActivity;
import com.codebenders.gujaratimitra.R;

import java.util.ArrayList;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static com.codebenders.gujaratimitra.Util.appDB;
import static com.codebenders.gujaratimitra.Util.prefs;

public class ProfileActivity extends ActionBarActivity {

    Button addProfile;
    ListView listView;
    ArrayList<Student> students;
    ProfileListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        students = appDB.getStudents();
        addProfile = (Button) findViewById(R.id.add_profile_button);
        listView = (ListView) findViewById(R.id.listView);
        adapter = new ProfileListAdapter(ProfileActivity.this, R.layout.profile_list_view_item, students);
        listView.setAdapter(adapter);
        addProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisplayMetrics metrics = ProfileActivity.this.getResources()
                        .getDisplayMetrics();
                int width = metrics.widthPixels;
                int height = metrics.heightPixels;
                CreateProfileDialog createProfileDialog = new CreateProfileDialog(
                        ProfileActivity.this);
                createProfileDialog.setCanceledOnTouchOutside(true);
                createProfileDialog.show();
                createProfileDialog.getWindow().setLayout((4 * width) / 7,
                        (6 * height) / 7);
                createProfileDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        students = appDB.getStudents();
                        adapter = new ProfileListAdapter(ProfileActivity.this, R.layout.profile_list_view_item, students);
                        listView.setAdapter(adapter);
                    }
                });

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                prefs.saveStudentId(students.get(position).getId());
                startActivity(new Intent(ProfileActivity.this, LevelsActivity.class).addFlags(FLAG_ACTIVITY_CLEAR_TASK | FLAG_ACTIVITY_NEW_TASK));

                finish();
            }
        });

    }

}
