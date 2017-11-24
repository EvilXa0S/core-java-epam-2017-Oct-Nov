package com.epam.courses.jf.practice.hkryzhik.second;

import com.epam.courses.jf.practice.common.second.ITestableTask12;

import java.util.Comparator;
import java.util.List;

public class Task12 implements ITestableTask12{
    @Override
    public List<Integer> transform(List<Integer> integers, int value) {

        //can i do this? :D
        integers.sort(Comparator.comparingInt(Integer::intValue));

        return integers;
    }
}
