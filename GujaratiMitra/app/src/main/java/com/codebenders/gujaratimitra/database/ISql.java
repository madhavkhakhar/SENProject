package com.codebenders.gujaratimitra.database;

public interface ISql {

    String INSERT_STUDENT = "INSERT INTO student (roll_no, standard, first_name, last_name) VALUES (%d,%d,'%s','%s')";
    String GET_STUDENT = "SELECT * FROM student";
    String REMOVE_STUDENT = "DELETE FROM tblStudent WHERE RollNo = %d";
    String COUNT_STUDENT = "SELECT count(RollNo) from tblStudent";
    String ADD_SUB_LEVEL_SCORE = "INSERT INTO level (student_id,sublevel_id, level_id,sublevel_score) VALUES (%d,%d,%d,%d)";
    String GET_SUB_LEVEL_SCORE = "SELECT sublevel_score from level WHERE student_id = %d AND sublevel_id = %d";
    String GET_LEVEL_SCORE = "SELECT COUNT(sublevel_score) from level WHERE level_id = %d AND student_id = %d";
    String GET_LAST_SUB_LEVEL_UNLOCKED = "SELECT current_level from student WHERE id = %d";
    String GET_LEVEL_FROM_SUB_LEVEL = "SELECT DISTINCT level_id from level WHERE student_id = %d AND sublevel_id";
    String SET_CURRENT_LEVEL = "UPDATE INTO student (current_level) VALUES (%d) WHERE id = %d";
    String GET_LAST_INSERTED_STUDENT_ID = "SELECT last_insert_rowid() FROM student LIMIT 1";
    //String GET_STUDENTS = "Select * from tblStudent";
}
