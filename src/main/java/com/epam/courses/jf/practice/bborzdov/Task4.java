package com.epam.courses.jf.practice.bborzdov;

import com.epam.courses.jf.practice.common.second.ITestableTask4;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by bogdan on 15.11.17.
 */
public class Task4 implements ITestableTask4 {
    @Override
    public Set<Integer> intersection(Set<Integer> first, Set<Integer> second) {
        Set<Integer> result = new HashSet<>();
        for (Integer fromFirst : first) {
            if(second.contains(fromFirst)){
                result.add(fromFirst);
            }
        }
        return result;
    }

    @Override
    public Set<Integer> union(Set<Integer> first, Set<Integer> second) {
        if(first.isEmpty()){
            return second;
        }
        if(second.isEmpty()){
            return first;
        }
        first.addAll(second);
        return first;
    }
}
