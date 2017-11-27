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
        int k = 0;
        for(int j = integers.size()-1; j > k; j--) {
            while(integers.get(j) <= value){
//                System.out.println("integers[j] = " + integers.get(integers.size() - 1 - j));
//                System.out.println("j = " + j + "  k = " + k);
//                System.out.println(integers);
                integers.set(j, integers.get(j) ^ integers.get(k));
                integers.set(k, integers.get(j) ^ integers.get(k));
                integers.set(j, integers.get(j) ^ integers.get(k));
//                System.out.println(integers);
                k++;
            }
        }

//        integers.sort(Comparator.naturalOrder()); // Maybe this method (in one line) also fits to do this task... :-)

        return integers;
    }
}
