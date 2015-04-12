package com.codebenders.gujaratimitra;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class AppPreferences {
    private static final String APP_SHARED_PREFS = AppPreferences.class
            .getSimpleName();
    private static String STUDENT_ID = "student_id";
    private static SharedPreferences sharedPrefs;
    private static Editor prefsEditor;

    public AppPreferences(Context context) {
        AppPreferences.sharedPrefs = context.getSharedPreferences(
                APP_SHARED_PREFS, Activity.MODE_PRIVATE);
        AppPreferences.prefsEditor = sharedPrefs.edit();
    }

    public static void saveStudentId (int studentId) {
        System.out.println(studentId);
        prefsEditor.putInt(STUDENT_ID, studentId);
        prefsEditor.commit();
    }

    public int getStudentId() {
        return sharedPrefs.getInt(STUDENT_ID, -1);
    }

}
