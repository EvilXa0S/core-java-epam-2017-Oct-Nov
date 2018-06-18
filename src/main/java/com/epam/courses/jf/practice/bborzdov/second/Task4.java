package com.epam.courses.jf.practice.bborzdov.second;

import com.epam.courses.jf.practice.common.second.ITestableTask4;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by bogdan on 15.11.17.
 */

/**
 * Определить множество на основе множества целых чисел.
 * Создать методы для определения пересечения и объединения множеств.
 */
public class Task4 implements ITestableTask4 {

    /**
     * Операция пересечения целочисленных множеств.
     * @param first Первое множество.
     * @param second Второе множество.
     * @return Результат пересечения множеств.
     */
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

    /**
     * Операция объединения целочисленных множеств.
     * @param first Первое множество.
     * @param second Второе множество.
     * @return Результат объединения множеств.
     */
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
