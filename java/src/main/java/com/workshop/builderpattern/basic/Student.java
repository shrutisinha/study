package com.workshop.builderpattern.basic;

import com.workshop.builderpattern.builder.StudentBuilder;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Field;
import java.util.ArrayList;

@Getter
@Setter
public class Student {
    private String firstName;
    private String lastName;
    private String age;
    private String gender;
    private String grade;
    private String contactAddress;
    private String zipcode;
    private String email;
    private ArrayList<String> extraSubjects;

    //basic form
    public Student(String firstName, String lastName, String grade) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.grade = grade;
    }

    //detailed form
    public Student(String firstName, String lastName, String grade, String age, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.grade = grade;
        this.age = age;
        this.gender = gender;
    }

    public Student(StudentBuilder builder) {
        this.firstName = builder.getFirstName();
        this.lastName = builder.getLastName();
        this.grade = builder.getGrade();
        this.age = builder.getAge();
        this.gender = builder.getGender();
        this.contactAddress = builder.getContactAddress();
        this.zipcode = builder.getZipcode();
        this.email = builder.getEmail();
        this.extraSubjects = builder.getExtraSubjects();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        String newLine = System.getProperty("line.separator");

        result.append( " {" );
        result.append(newLine);

        //determine fields declared in this class only (no fields of superclass)
        Field[] fields = this.getClass().getDeclaredFields();

        //print field names paired with their values
        for ( Field field : fields  ) {
            try {
                if(field.get(this) == null) continue;
                result.append("  ");
                result.append( field.getName() );
                result.append(": ");
                //requires access to private field:
                result.append( field.get(this) );
            } catch ( IllegalAccessException ex ) {
                System.out.println(ex);
            }
            result.append(newLine);
        }
        result.append("}");

        return result.toString();
    }

}