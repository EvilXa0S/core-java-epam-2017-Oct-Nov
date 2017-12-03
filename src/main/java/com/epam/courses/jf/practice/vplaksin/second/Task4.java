package com.epam.courses.jf.practice.vplaksin.second;

import com.epam.courses.jf.practice.common.second.ITestableTask4;

import java.util.HashSet;
import java.util.Set;

/**
 * Определить множество на основе множества целых чисел.
 * Создать методы для определения пересечения и объединения множеств.
 * Запрещена модификация исходных множеств.
 */
public class Task4 implements ITestableTask4 {

    /**
     * Операция пересечения целочисленных множеств.
     *
     * @param first  Первое множество.
     * @param second Второе множество.
     * @return Результат пересечения множеств.
     */
    @Override
    public Set<Integer> intersection(Set<Integer> first, Set<Integer> second) {
        Set<Integer> result = new HashSet<>(first);
        result.retainAll(second);
        return result;
    }

    /**
     * Операция объединения целочисленных множеств.
     *
     * @param first  Первое множество.
     * @param second Второе множество.
     * @return Результат объединения множеств.
     */
    @Override
    public Set<Integer> union(Set<Integer> first, Set<Integer> second) {
        Set<Integer> result = new HashSet<>(first);
        result.addAll(second);
        return result;
    }

}
