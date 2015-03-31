package com.codebenders.gujaratimitra.profile;

/**
 * Created by nihartrivedi810 on 31/3/15.
 */
public class Student {
    int id, roll, standard;
    String firstName, lastName;

    public Student(int roll, int standard, String lastName, String firstName) {
        this.roll = roll;
        this.standard = standard;
        this.lastName = lastName;
        this.firstName = firstName;
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
}
