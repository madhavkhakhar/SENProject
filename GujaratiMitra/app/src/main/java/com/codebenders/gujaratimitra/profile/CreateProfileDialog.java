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
    boolean edit = false;
    Student student;

    public CreateProfileDialog(Context context) {
        super(context);
    }

    public CreateProfileDialog(Context context, Student student, boolean edit) {
        super(context);
        this.edit = edit;
        this.student = student;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_profile);
        firstNameEditText = (EditText) findViewById(R.id.firstname);
        lastNameEditText = (EditText) findViewById(R.id.lastname);
        rollNoEditText = (EditText) findViewById(R.id.roll_no);
        standardEditText = (EditText) findViewById(R.id.standard);
        ok = (Button) findViewById(R.id.button_ok);
        cancel = (Button) findViewById(R.id.button_cancel);
        if(edit){
            firstNameEditText.setText(student.getFirstName());
            lastNameEditText.setText(student.getLastName());
            rollNoEditText.setText(student.getRoll()+"");
            standardEditText.setText(student.getStandard()+"");
        }
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstNameEditText.setError(null);
                rollNoEditText.setError(null);
                standardEditText.setError(null);
                firstName = firstNameEditText.getText().toString();
                lastName = lastNameEditText.getText().toString();
                if(firstName.isEmpty()){
                    firstNameEditText.setError("Enter first name");
                    firstNameEditText.requestFocus();
                    return;
                }
                try {
                    rollNo = Integer.parseInt(rollNoEditText.getText().toString());
                }
                catch (Exception e){
                    e.printStackTrace();
                    rollNoEditText.setError("Invalid Roll number");
                    rollNoEditText.requestFocus();
                    return;
                }
                try {
                    standard = Integer.parseInt(standardEditText.getText().toString());
                    if(standard>12 || standard<1){
                        standardEditText.setError("Standard - 1 to 12");
                        standardEditText.requestFocus();
                        return;
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                    standardEditText.setError("Invalid standard");
                    standardEditText.requestFocus();
                    return;
                }
                if (!edit)
                    appDB.insertStudent(new Student(rollNo, standard, firstName, lastName, 0, 0));
                else {
                    student.setFirstName(firstNameEditText.getText().toString());
                    student.setLastName(lastNameEditText.getText().toString());
                    student.setRoll(Integer.parseInt(rollNoEditText.getText().toString()));
                    student.setStandard(Integer.parseInt(standardEditText.getText().toString()));
                    appDB.updateStudent(student);
                }
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
