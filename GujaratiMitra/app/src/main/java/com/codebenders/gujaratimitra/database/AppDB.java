package com.codebenders.gujaratimitra.database;

import android.content.Context;
import android.database.Cursor;

import com.codebenders.gujaratimitra.profile.Student;

import java.util.ArrayList;

public class AppDB extends DBConnect {

    public AppDB(Context context) {
        super(context);
    }


    public void insertStudent(Student student) {
        String query;
        //int id = -1;

        query = String.format(ISql.INSERT_STUDENT, student.getRoll(),
                student.getStandard(), student.getFirstName(), student.getLastName());
        execNonQuery(query);
        /*Cursor cursor = execQuery(ISql.GET_LAST_INSERTED_STUDENT_ID);
        if (cursor != null && cursor.getCount() > 0) {

            if (cursor.moveToNext()) {
                id = cursor.getInt(0);
            }
        }*/
       /* if(id!=-1)
            this.addSubLevelScore(1,1,0,id);*/
    }

    public void insertStudent(Student student, int id) {
        String query;
        //int id = -1;

        query = String.format(ISql.INSERT_STUDENT_BY_ID, id, student.getRoll(),
                student.getStandard(), student.getFirstName(), student.getLastName());
        execNonQuery(query);
    }

    public void updateStudent(Student student) {
        String query;
        query = String.format(ISql.UPDATE_STUDENT_BY_ID, student.getRoll(),
                student.getStandard(), student.getFirstName(), student.getLastName(), student.getId());
        execNonQuery(query);
    }

    /**
     * To remove Student
     */
    public void removeStudent(int id) {
        if (id > 0) {
            String sqlRemoveRegCard = String.format(ISql.DELETE_STUDENT, id);
            execNonQuery(sqlRemoveRegCard);
        }
    }

    public Student getStudentById(int id) {
        String query = String.format(ISql.GET_STUDENT_BY_ID, id);
        Cursor cursor = execQuery(query);
        Student student = null;
        if (cursor != null && cursor.getCount() > 0) {

            if (cursor.moveToNext()) {
                student = new Student(cursor.getInt(cursor.getColumnIndex("id")), cursor.getInt(cursor.getColumnIndex("roll_no")), cursor.getInt(cursor.getColumnIndex("standard")), cursor.getString(cursor.getColumnIndex("first_name")), cursor.getString(cursor.getColumnIndex("last_name")), cursor.getInt(cursor.getColumnIndex("current_level")), cursor.getInt(cursor.getColumnIndex("total_score")));
            }
        }
        return student;
    }

    /**
     * To count no. of Students
     *
     * @return
     */
    public int countStudent() {
        Cursor cursor = execQuery(ISql.COUNT_STUDENT);
        int cntCards = 0;

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToNext();
            cntCards = Integer.parseInt(cursor.getString(0));
        }

        if (cursor != null) {
            cursor.close();
        }

        return cntCards;
    }

    /**
     * To get all the Students
     *
     * @return
     */
    public ArrayList<Student> getStudents() {
        Cursor cursor = execQuery(ISql.GET_STUDENT);

        ArrayList<Student> listStudent = new ArrayList<Student>();
        Student student;
        System.out.println("columns" + cursor.getColumnCount());
        if (cursor != null && cursor.getCount() > 0) {

            if (cursor.moveToNext()) {

                do {
                    student = new Student(cursor.getInt(cursor.getColumnIndex("id")), cursor.getInt(cursor.getColumnIndex("roll_no")), cursor.getInt(cursor.getColumnIndex("standard")), cursor.getString(cursor.getColumnIndex("first_name")), cursor.getString(cursor.getColumnIndex("last_name")), cursor.getInt(cursor.getColumnIndex("current_level")), cursor.getInt(cursor.getColumnIndex("total_score")));
                    listStudent.add(student);
                } while (cursor.moveToNext());
            }
        }

        if (cursor != null) {
            cursor.close();
        }

        return listStudent;
    }

    public void addSubLevelScore(int levelNo, int subLevelNo, int score, int studentId) {
        execNonQuery(String.format(ISql.ADD_SUB_LEVEL_SCORE,
                subLevelNo, levelNo, score, studentId));
        execNonQuery(String.format(ISql.UPDATE_STUDENT_SCORE, score, studentId));
    }

    public int getSubLevelScore(int studentId, int subLevelNo, int levelNo) {
        String query = String.format(ISql.GET_SUB_LEVEL_SCORE, studentId,
                subLevelNo, levelNo);
        Cursor cursor = execQuery(query);
        int score = 0;
        if (cursor != null && cursor.getCount() > 0) {

            if (cursor.moveToNext()) {
                score = cursor.getInt(0);
            }
        }
        return score;
    }

    public int getLevelScore(int studentId, int levelNo) {
        String query = String.format(ISql.GET_LEVEL_SCORE, levelNo, studentId);
        Cursor cursor = execQuery(query);
        int score = 0;
        if (cursor != null && cursor.getCount() > 0) {

            if (cursor.moveToNext()) {
                score = cursor.getInt(0);
            }
        }
        return score;
    }

    public int getLastLevelUnlocked(int studentId) {
        String query = String.format(ISql.GET_LAST_LEVEL_UNLOCKED, studentId);
        Cursor cursor = execQuery(query);
        int level = 1;
        if (cursor != null && cursor.getCount() > 0) {

            if (cursor.moveToNext()) {
                level = cursor.getInt(0);
            }
        }
        return level;
    }
    /*public int getLastSubLevelUnlocked(int studentId){
        String query = String.format(ISql.GET_LAST_SUB_LEVEL_UNLOCKED, studentId);
        Cursor cursor = execQuery(query);
        int subLevel = 1;
        if (cursor != null && cursor.getCount() > 0) {

            if (cursor.moveToNext()) {
                subLevel = cursor.getInt(0);
            }
        }
        return subLevel;
    }*/

    public void setCurrentLevel(int studentId, int levelNo) {
        System.out.println(">>" + levelNo);
        String query = String.format(ISql.SET_CURRENT_LEVEL, levelNo, studentId);
        execNonQuery(query);
    }
}
