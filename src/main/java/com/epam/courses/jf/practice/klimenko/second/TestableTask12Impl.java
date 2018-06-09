package com.epam.courses.jf.practice.klimenko.second;

import com.epam.courses.jf.practice.common.second.ITestableTask12;

import java.util.List;

public class TestableTask12Impl implements ITestableTask12 {
    private void swap(List<Integer> list, int i, int j) {
        if (i != j) {
            Integer tmp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, tmp);
        }
    }

    @Override
    public List<Integer> transform(List<Integer> integers, int value) {
        int less = 0, equals = 0;
        for (int i = 0; i < integers.size(); ++i) {
            if (integers.get(i) < value) {
                swap(integers, less, equals);
                if (equals != i) {
                    swap(integers, less, i);
                }
                ++equals;
                ++less;
            } else if (integers.get(i) == value) {
                swap(integers, equals, i);
                ++equals;
            }
        }
        return integers;
    }
}
