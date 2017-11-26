package com.epam.courses.jf.practice.nzenkova.second;

import com.epam.courses.jf.practice.common.second.ITestableTask4;

import java.util.HashSet;
import java.util.Set;

/**
 * Define a set, which is based on a set of the integers.
 * Create the methods for definition of the intersection and the union of the sets.
 * It's forbidden to modify the original sets.
 */
public class TestableTask4 implements ITestableTask4 {

    /**
     * The intersection of the integer sets.
     * @param first The first set.
     * @param second The second set.
     * @return The result of the intersection.
     */
    @Override
    public Set<Integer> intersection(Set<Integer> first, Set<Integer> second) {
        Set<Integer> result = new HashSet<>(first);
        result.retainAll(second);

        return result;
    }

    /**
     * The union of the integer sets.
     * @param first The first set.
     * @param second The second set.
     * @return The result of the union.
     */
    @Override
    public Set<Integer> union(Set<Integer> first, Set<Integer> second) {
        Set<Integer> result = new HashSet<>(first);
        result.addAll(second);

        return result;
    }

}
