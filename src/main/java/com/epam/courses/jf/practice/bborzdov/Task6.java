package com.epam.courses.jf.practice.bborzdov;

import com.epam.courses.jf.practice.common.second.ITestableTask6;

import java.util.AbstractList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Created by bogdan on 17.11.17.
 */
public class Task6 implements ITestableTask6 {
    @Override
    public HashMap<Integer, Integer> addPolynomials(HashMap<Integer, Integer> first, HashMap<Integer, Integer> second) {
        Set<Integer> firstKeys = first.keySet();
        for (Integer firstKey : firstKeys) {
            if(second.containsKey(firstKey)){
                second.put(firstKey, second.get(firstKey) + first.get(firstKey));
            } else{
                second.put(firstKey,first.get(firstKey));
            }
        }
        return second;
    }
}
