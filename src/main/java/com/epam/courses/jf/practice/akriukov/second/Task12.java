package com.epam.courses.jf.practice.akriukov.second;

import com.epam.courses.jf.practice.common.second.ITestableTask12;

import java.util.Collections;
import java.util.List;

public class Task12 implements ITestableTask12 {
    @Override
    public List<Integer> transform(List<Integer> integers, int value) {

        Collections.sort(integers);

        return integers;
    }
}
