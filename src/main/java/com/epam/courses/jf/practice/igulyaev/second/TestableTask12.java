package com.epam.courses.jf.practice.igulyaev.second;

import com.epam.courses.jf.practice.common.second.ITestableTask12;

import java.util.List;

public class TestableTask12 implements ITestableTask12 {
    @Override
    public List<Integer> transform(List<Integer> integers, int value) {
        integers.sort(Integer::compareTo);
        return integers;
    }
}
