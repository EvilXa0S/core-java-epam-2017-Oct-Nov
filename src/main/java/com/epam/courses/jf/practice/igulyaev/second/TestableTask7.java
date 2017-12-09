package com.epam.courses.jf.practice.igulyaev.second;

import com.epam.courses.jf.practice.common.second.ITestableTask7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestableTask7 implements ITestableTask7 {
    @Override
    public List<Integer> multiplyPolynomials(List<Integer> first, List<Integer> second) {
        Integer[] array = new Integer[first.size() + second.size()];
        Arrays.fill(array, 0);
        List<Integer> list = Arrays.asList(array);
        for(int i  = 0; i < first.size(); ++i){
            for(int j = 0; j < second.size(); ++j){
                list.set(i+j, list.get(i+j) + (first.get(i) * second.get(j)));
            }
        }
        return list;
    }
}
