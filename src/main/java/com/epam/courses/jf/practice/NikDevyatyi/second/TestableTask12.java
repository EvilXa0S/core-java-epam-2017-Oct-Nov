package com.epam.courses.jf.practice.NikDevyatyi.second;

import com.epam.courses.jf.practice.common.second.ITestableTask12;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TestableTask12 implements ITestableTask12 {
    @Override
    public List<Integer> transform(List<Integer> integers, int value) {
        Collections.sort(integers);
        return integers;
    }
    public List<Integer> transform2(List<Integer> integers, int value) {
        integers.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        return integers;
    }
}
