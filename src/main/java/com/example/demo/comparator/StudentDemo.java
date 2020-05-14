package com.example.demo.comparator;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2020/4/30.
 */
public class StudentDemo {
    public static void main(String[] args) {

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(6,9);
        map.put(1,2);
        map.put(5,3);
        map.put(9,3);
        List<Integer> collect = map.keySet().stream().sorted().collect(Collectors.toList());
        System.out.println(collect.get(0));

       /* HashMap<Integer, List<Integer>> map = new HashMap<>();
//        List<Integer> list = map.get(1);
        List<Integer> list = map.getOrDefault(6, new ArrayList<>());
        System.out.println(list);
        list.add(3);
        list.add(14);
        map.put(6,list);
        System.out.println(map);*/

      /*  HashMap<Long, List> longListHashMap = new HashMap<>();

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

        System.out.println(System.currentTimeMillis());

        ZonedDateTime zonedDateTime = Instant.now().atZone(ZoneId.systemDefault());
        System.out.println(zonedDateTime);
        int value = zonedDateTime.getDayOfWeek().getValue();
        System.out.println(value);*/

    }
}
