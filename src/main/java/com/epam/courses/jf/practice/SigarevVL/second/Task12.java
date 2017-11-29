package com.epam.courses.jf.practice.SigarevVL.second;


import com.epam.courses.jf.practice.common.second.ITestableTask12;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * There is a list of integers and a number X.
 * You can not use auxiliary objects and change the size of the list.
 * It is necessary to rearrange the elements of the list so
 * that numbers that do not exceed X are first
 * followed by numbers larger than X.
 */

public class Task12 implements ITestableTask12 {

    /**
     * Converts an integer list in such a way that first the numbers
     * are less than value, other numbers are larger than value.
     *
     * @param integers Integer list.
     * @param value    Separating value.
     * @return Converted list.
     */

    @Override
    public List<Integer> transform(List<Integer> integers, int value) {
        Collections.sort(integers);
        return integers;
    }
}
