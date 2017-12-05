package com.epam.courses.jf.practice.asunyaev.second;

import com.epam.courses.jf.practice.common.second.ITestableTask12;

import java.util.Comparator;
import java.util.List;

public class Task12 implements ITestableTask12 {
    //Данная реализация делает именно то, что описано в задании
    //(которая не подразумевает, что если в исходном списке есть элемент, равный value,
    // то в результирующем списке он должен стоять посередине)
    /*public List<Integer> transform(List<Integer> integers, int value) {
        for (int i = 0; i < integers.size(); i++) {
            Integer current = integers.get(i);
            if (current.compareTo(value) > 0) {
                integers.set(i, integers.get(integers.size() - 1));
                integers.set(integers.size() - 1, integers.get(i));
            }
        }
        return integers;
    }*/
    public List<Integer> transform(List<Integer> integers, int value) {
        integers.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        return integers;
    }
}
