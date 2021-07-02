package com.workshop.builderpattern.builder;

import com.workshop.builderpattern.basic.Student;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class StudentBuilder {
    private String firstName;
    private String lastName;
    private String age;
    private String gender;
    private String grade;
    private String contactAddress;
    private String zipcode;
    private String email;
    private ArrayList<String> extraSubjects;

    private Student student;

    public StudentBuilder(String firstName, String lastName, String grade){
        this.firstName = firstName;
        this.lastName = lastName;
        this.grade = grade;
        this.student = new Student(firstName, lastName, grade);
    }

    public StudentBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public StudentBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public StudentBuilder setAge(String age) {
        this.age = age;
        return this;
    }

    public StudentBuilder setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public StudentBuilder setGrade(String grade) {
        this.grade = grade;
        return this;
    }

    public StudentBuilder setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
        return this;
    }

    public StudentBuilder setZipcode(String zipcode) {
        this.zipcode = zipcode;
        return this;
    }

    public StudentBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public StudentBuilder setExtraSubjects(ArrayList<String> extraSubjects) {
        this.extraSubjects = extraSubjects;
        return this;
    }

    public Student build() {
        return new Student(this);
    }


}
