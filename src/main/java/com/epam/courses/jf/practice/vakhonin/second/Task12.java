package com.epam.courses.jf.practice.vakhonin.second;

import com.epam.courses.jf.practice.common.second.ITestableTask12;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Task12 implements ITestableTask12{

    @Override
//    public List<Integer> transform(List<Integer> integers, int value) {
//        Set<Integer> set = new TreeSet<>(integers);
//        List<Integer> list = new ArrayList<>(set);
//
//        return list;
//    }

    public List<Integer> transform(List<Integer> integers, int value){
        for(int j = integers.size()-1; j > 0; j--) {
            if(integers.get(j) <= value) {
                integers.set(j, integers.get(j) ^ integers.get(0));
                integers.set(0, integers.get(j) ^ integers.get(0));
                integers.set(j, integers.get(j) ^ integers.get(0));
            }
        }

        return integers;
    }
}
