package com.workshop.builderpattern;

import com.workshop.builderpattern.builder.GenericGrade8Builder;
import com.workshop.builderpattern.builder.StudentBuilder;
import com.workshop.builderpattern.basic.Student;

public class Main {
    public static void main(String[] args) {
        // write your code here
        System.out.println("without builder\n");
        withoutBuilder();
        System.out.println();
        System.out.println("with builder\n");
        withBuilder();
    }

    static void withoutBuilder() {
        System.out.print("Basic Form: ");
        Student student1 = new Student("Mary", "Jane", "8");
        System.out.println(student1);

        System.out.print("Detailed Form: ");
        Student student2 = new Student("Mini", "Cooper", "8", "12", "Female");
        System.out.println(student2);
        student2.setEmail("mini@gmail.com");
        System.out.print("Detailed form with extra fields: ");
        System.out.println(student2);
    }

    static void withBuilder() {
        StudentBuilder builder = new StudentBuilder("Mary", "Jane", "8");
        System.out.print("Basic Form with extra fields: ");
        Student student1 = builder.setAge("12").setEmail("mary@gmail.com").build();
        System.out.println(student1);

        System.out.print("Generic Grade 8 Form: ");
        builder = new GenericGrade8Builder("Jack", "Ryan");
        student1 = builder.setEmail("jack@gmail.com").build();
        System.out.println(student1);

    }
}
