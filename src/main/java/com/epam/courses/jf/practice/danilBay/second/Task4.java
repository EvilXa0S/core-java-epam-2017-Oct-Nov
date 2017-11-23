package com.epam.courses.jf.practice.danilBay.second;

import com.epam.courses.jf.practice.common.second.ITestableTask4;

import java.util.HashSet;
import java.util.Set;

public class Task4 implements ITestableTask4 {
    @Override
    public Set<Integer> intersection(Set<Integer> first, Set<Integer> second) {
        Set<Integer> result=new HashSet<>();
        for(Integer x: first)
            if(second.contains(x)){
                result.add(x);
            }
        return result;
    }

    @Override
    public Set<Integer> union(Set<Integer> first, Set<Integer> second) {
        Set<Integer> result=new HashSet<>();
        for(Integer x: first)
            result.add(x);
        for(Integer x: second)
            result.add(x);
        return result;
    }
}
