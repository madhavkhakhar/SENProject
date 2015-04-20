package com.codebenders.gujaratimitra.database;

public interface ISql {

    String INSERT_STUDENT = "INSERT INTO student (roll_no, standard, first_name, last_name) VALUES (%d,%d,'%s','%s')";
    String INSERT_STUDENT_BY_ID = "INSERT INTO student (id,roll_no, standard, first_name, last_name) VALUES (%d,%d,%d,'%s','%s')";
    String UPDATE_STUDENT_BY_ID = "UPDATE student SET roll_no = %d, standard = %d, first_name = '%s', last_name='%s' WHERE id = %d";
    String GET_STUDENT = "SELECT * FROM student";
    String GET_STUDENT_BY_ID = "SELECT * FROM student WHERE id = %d";
    String DELETE_STUDENT = "DELETE FROM student WHERE id = %d";
    String COUNT_STUDENT = "SELECT count(RollNo) from tblStudent";
    String ADD_SUB_LEVEL_SCORE = "INSERT OR REPLACE INTO  level (sublevel_id, level_id, sublevel_score, student_id)  VALUES(%d,%d,%d,%d)";
    String GET_SUB_LEVEL_SCORE = "SELECT sublevel_score from level WHERE student_id = %d AND sublevel_id = %d AND level_id = %d";
    String GET_LEVEL_SCORE = "SELECT COUNT(sublevel_score) from level WHERE level_id = %d AND student_id = %d";
    String GET_LAST_LEVEL_UNLOCKED = "SELECT current_level from student WHERE id = %d";
    //String GET_LEVEL_FROM_SUB_LEVEL = "SELECT DISTINCT level_id from level WHERE student_id = %d AND sublevel_id";
    String SET_CURRENT_LEVEL = "UPDATE student SET current_level = %d WHERE id = %d";
    String GET_LAST_INSERTED_STUDENT_ID = "SELECT last_insert_rowid() FROM student LIMIT 1";
    String UPDATE_STUDENT_SCORE = "UPDATE student SET total_score = total_score + %d WHERE id = %d";
    //String GET_STUDENTS = "Select * from tblStudent";
}
