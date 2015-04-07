package com.codebenders.gujaratimitra.database;

public interface ISql {

    String INSERT_STUDENT = "INSERT INTO student (roll_no, standard, first_name, last_name) VALUES (%d,%d,'%s','%s')";
    String GET_STUDENT = "SELECT roll_no, standard, first_name, last_name FROM student";
    String REMOVE_STUDENT = "DELETE FROM tblStudent WHERE RollNo = %d";
    String COUNT_STUDENT = "SELECT count(RollNo) from tblStudent";
    String ADD_SUB_LEVEL_SCORE = "INSERT INTO level (student_id,sublevel_id, level_id,sublevel_score) VALUES (%d,%d,%d,%d)";
    String GET_SUB_LEVEL_SCORE = "SELECT sublevel_score from level WHERE student_id = %d && sublevel_id = %d";
    String GET_LEVEL_SCORE = "SELECT count(sublevel_score) from level WHERE level_id = %d && student_id = %d";
    String GET_LAST_LEVEL_UNLOCKED = "SELECT last(level_id) from level WHERE student_id = %d";
    String GET_LAST_SUB_LEVEL_UNLOCKED = "SELECT last(sublevel_id) from level WHERE student_id = %d";
    //String GET_STUDENTS = "Select * from tblStudent";
}
