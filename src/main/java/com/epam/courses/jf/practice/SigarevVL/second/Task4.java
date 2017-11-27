package com.epam.courses.jf.practice.SigarevVL.second;

import com.epam.courses.jf.practice.common.second.ITestableTask4;

import java.util.HashSet;
import java.util.Set;

/**
 * Define a set based on a set of integers.
 * Create methods for defining the intersection and union of sets.
 */

public class Task4 implements ITestableTask4 {

    /**
     * The operation of intersection of integer sets.
     *
     * @param first  The first set.
     * @param second The second set.
     * @return The result of intersection of sets.
     */

    @Override
    public Set<Integer> intersection(Set<Integer> first, Set<Integer> second) {
        Set<Integer> intersectionOfMany = new HashSet<>(first);
        intersectionOfMany.retainAll(second);
        return intersectionOfMany;
    }

    /**
     * The operation of intersection of integer sets.
     *
     * @param first  The first set.
     * @param second The second set.
     * @return The result of intersection of sets.
     */

    @Override
    public Set<Integer> union(Set<Integer> first, Set<Integer> second) {
        Set<Integer> unionOfMany = new HashSet<>(first);
        unionOfMany.addAll(second);
        return unionOfMany;
    }
}
