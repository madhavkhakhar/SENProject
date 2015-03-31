package com.codebenders.gujaratimitra.database;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.database.Cursor;

import com.codebenders.gujaratimitra.profile.Student;

public class AppDB extends DBConnect {
	
	public AppDB(Context context) {
		super(context);
	}


	public void insertStudent(Student student) {
		String query;

        query = String.format(ISql.INSERT_STUDENT, student.getRoll(),
                student.getStandard(),student.getFirstName(),student.getLastName());

        execNonQuery(query);
	}
 
	/**
	 * To remove Student
	 */
	public void removeStudent(int rollNo)
	{
		if(rollNo>0)
		{
			String sqlRemoveRegCard = String.format(ISql.REMOVE_STUDENT, rollNo);
			execNonQuery(sqlRemoveRegCard);
		}
	}
	
	/**
	 * To count no. of Students
	 * @return
	 */
	public int countStudent()
	{
		Cursor cursor = execQuery(ISql.COUNT_STUDENT);
		int cntCards = 0;
		
		if(cursor!=null && cursor.getCount()>0) {
			cursor.moveToNext();
			cntCards = Integer.parseInt(cursor.getString(0));
		}
		
		if(cursor!= null) {
			cursor.close();
		}
			
		return cntCards;
	}
	
	/**
	 * To get all the Students
	 * @return
	 */
	public ArrayList<Student> getStudents() {
		Cursor cursor = execQuery(ISql.GET_STUDENT);

		ArrayList<Student> listStudent = new ArrayList<Student>();
		Student student;
		if (cursor != null && cursor.getCount() > 0) {

			if (cursor.moveToNext()) {

				do {
                        student = new Student(cursor.getInt(cursor.getColumnIndex("roll_no")),cursor.getInt(cursor.getColumnIndex("standard")),cursor.getString(cursor.getColumnIndex("first_name")),cursor.getString(cursor.getColumnIndex("last_name")));
						listStudent.add(student);
				} while (cursor.moveToNext());
			}
		}

		if (cursor != null) {
			cursor.close();
		}

		return listStudent;
	}
}
