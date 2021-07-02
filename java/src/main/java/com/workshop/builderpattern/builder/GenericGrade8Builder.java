package com.workshop.builderpattern.builder;

import java.util.ArrayList;

public class GenericGrade8Builder extends StudentBuilder {
    public GenericGrade8Builder(String firstName, String lastName){
        super(firstName, lastName, "8");
        ArrayList<String> subjects= new ArrayList<String>();
        subjects.add("Painting");
        subjects.add("Basketball");
        this.setAge("12").setGender("Male").setEmail("-").setContactAddress("-").setExtraSubjects(subjects);
    }
}
