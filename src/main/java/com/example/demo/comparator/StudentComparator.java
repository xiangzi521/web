package com.example.demo.comparator;

import java.util.Comparator;

/**
 * Created by Administrator on 2020/4/30.
 */
public class StudentComparator implements Comparator<Student> {
    public static final StudentComparator COMPARATOR = new StudentComparator();
    @Override
    public int compare(Student o1, Student o2) {
        if (o1.getId()>o2.getId()){
            return -1;
        }else {
            return 1;
        }
    }
}
