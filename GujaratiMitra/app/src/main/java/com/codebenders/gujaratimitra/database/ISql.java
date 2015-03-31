package com.codebenders.gujaratimitra.database;

public interface ISql {
	
	String INSERT_STUDENT = "INSERT INTO student (roll_no, standard, first_name, last_name) VALUES (%d,%d,'%s','%s')";
	String GET_STUDENT = "SELECT roll_no, standard, first_name, last_name FROM student";
	String REMOVE_STUDENT = "DELETE FROM tblStudent WHERE RollNo = %d";
	String COUNT_STUDENT = "SELECT count(RollNo) from tblStudent";
	
	//String GET_STUDENTS = "Select * from tblStudent";
}
