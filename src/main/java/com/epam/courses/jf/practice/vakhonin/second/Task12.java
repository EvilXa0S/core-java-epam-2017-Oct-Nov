package com.epam.courses.jf.practice.vakhonin.second;

import com.epam.courses.jf.practice.common.second.ITestableTask12;

import java.util.List;

public class Task12 implements ITestableTask12 {

    //        integers.sort(Comparator.naturalOrder()); // Maybe this method (in one line) also fits to do this task... :-)

    @Override
    public List<Integer> transform(List<Integer> integers, int value) {
        int k = 0;
        for (int j = integers.size() - 1; j > k; j--) {
            while ((integers.get(j) <= value) && (j > k)) {
                integers.set(j, integers.get(j) ^ integers.get(k));
                integers.set(k, integers.get(j) ^ integers.get(k));
                integers.set(j, integers.get(j) ^ integers.get(k));
                k++;
            }

            k--;
        }

        return integers;
    }
}
