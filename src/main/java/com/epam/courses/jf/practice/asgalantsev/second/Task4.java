package com.epam.courses.jf.practice.asgalantsev.second;

import com.epam.courses.jf.practice.common.second.ITestableTask4;

import java.util.Set;
import java.util.TreeSet;

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
        Set<Integer> firstCopy = new TreeSet<>(first);
        firstCopy.retainAll(second);
        return firstCopy;
    }

    /**
     * Операция объединения целочисленных множеств.
     * @param first Первое множество.
     * @param second Второе множество.
     * @return Результат объединения множеств.
     */

    @Override
    public Set<Integer> union(Set<Integer> first, Set<Integer> second) {
        Set<Integer> firstCopy = new TreeSet<>(first);
        firstCopy.addAll(second);
        return firstCopy;
    }
}
