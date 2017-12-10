package com.epam.courses.jf.practice.NikDevyatyi.second;

import com.epam.courses.jf.practice.common.second.ITestableTask4;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class TestableTask4 implements ITestableTask4 {
    @Override
    public Set<Integer> intersection(Set<Integer> first, Set<Integer> second) {
        Set<Integer> result = new HashSet<>();
        result.addAll(first);
        if(!result.isEmpty()){
            result.retainAll(second);
        }
        return (Set<Integer>) result;
    }

    @Override
    public Set<Integer> union(Set<Integer> first, Set<Integer> second) {
        Set<Integer> result = new HashSet<>();
        result.addAll(first);
        if(first.isEmpty()&&second.isEmpty()){
            return Collections.EMPTY_SET;
        }
        else if(!first.isEmpty()){
            result.addAll(second);
            return (Set<Integer>) result;
        }
        return second;
    }
}
