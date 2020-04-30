package com.example.demo.comparator;

import java.util.ArrayList;

/**
 * Created by Administrator on 2020/4/30.
 */
public class StudentDemo {
    public static void main(String[] args) {
        Student student = new Student(9, "小芳9");
        Student student2 = new Student(5, "小芳5");
        Student student3 = new Student(12, "小芳12");
        ArrayList<Student> list = new ArrayList<>();
        list.add(student);
        list.add(student2);
        list.add(student3);
        System.out.println(list);
        list.sort(StudentComparator.COMPARATOR);
        System.out.println(list);
    }
}
