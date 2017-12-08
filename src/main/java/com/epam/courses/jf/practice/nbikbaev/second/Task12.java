package com.epam.courses.jf.practice.nbikbaev.second;

import com.epam.courses.jf.practice.common.second.ITestableTask12;

import java.util.List;

public class Task12 implements ITestableTask12 {
    @Override
    public List<Integer> transform(List<Integer> integers, int value) {
        int index = integers.indexOf(value);
        for (int i = 0; i < integers.size(); i++) {
            int tt = integers.get(i);
            if (integers.get(i) > value) {
                for (int j = index + 1; j < integers.size(); j++) {
                    int tmp = integers.get(j);
                    if (integers.get(j) <= value) {
                        integers.remove(i);
                        integers.add(i, tmp);
                        integers.remove(j);
                        integers.add(j, tt);
                    }
                }
            }
        }
        return integers;
    }
}

