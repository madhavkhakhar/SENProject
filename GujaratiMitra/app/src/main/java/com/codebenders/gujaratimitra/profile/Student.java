package com.codebenders.gujaratimitra.profile;

/**
 * Created by nihartrivedi810 on 31/3/15.
 */
public class Student {
    int id, roll, standard, currentLevel, score;
    String firstName, lastName;

    public void setId(int id) {
        this.id = id;
    }

    public void setRoll(int roll) {
        this.roll = roll;
    }

    public void setStandard(int standard) {
        this.standard = standard;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Student(int id, int roll, int standard, String firstName, String lastName, int currentLevel, int score) {
        this.id = id;
        this.roll = roll;
        this.standard = standard;
        this.lastName = lastName;
        this.firstName = firstName;
        this.currentLevel = currentLevel;
        this.score = score;
    }

    public Student(int roll, int standard, String firstName, String lastName, int currentLevel, int score) {
        this.roll = roll;
        this.standard = standard;
        this.lastName = lastName;
        this.firstName = firstName;
        this.currentLevel = currentLevel;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public int getRoll() {
        return roll;
    }

    public int getStandard() {
        return standard;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public int getScore() {
        return score;
    }

}
