package com.codebenders.gujaratimitra.profile;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.codebenders.gujaratimitra.R;

import static com.codebenders.gujaratimitra.Util.appDB;

/**
 * Created by nihartrivedi810 on 31/3/15.
 */
public class CreateProfileDialog extends Dialog {

    EditText firstNameEditText, lastNameEditText, rollNoEditText, standardEditText;
    Button ok, cancel;
    int rollNo, standard;
    String firstName, lastName;
    public CreateProfileDialog(Context context){
        super(context);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_profile);
        firstNameEditText = (EditText)findViewById(R.id.firstname);
        lastNameEditText = (EditText)findViewById(R.id.lastname);
        rollNoEditText = (EditText)findViewById(R.id.roll_no);
        standardEditText = (EditText)findViewById(R.id.standard);
        ok = (Button)findViewById(R.id.button_ok);
        cancel = (Button)findViewById(R.id.button_cancel);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollNo = Integer.parseInt(rollNoEditText.getText().toString());
                standard = Integer.parseInt(standardEditText.getText().toString());
                firstName = firstNameEditText.getText().toString();
                lastName = lastNameEditText.getText().toString();
                appDB.insertStudent(new Student(rollNo, standard, firstName, lastName));
                dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               dismiss();
            }
        });
    }
}
