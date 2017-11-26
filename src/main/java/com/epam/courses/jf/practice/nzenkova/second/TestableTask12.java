package com.epam.courses.jf.practice.nzenkova.second;

import com.epam.courses.jf.practice.common.second.ITestableTask12;

import java.util.Collections;
import java.util.List;

/**
 * Specify a list of integer numbers and a number X.
 * Rearrange the elements of the list without using the auxiliary objects and without changing the size of the list
 *   so that the numbers that do not exceed X will be first, and they will be followed by the numbers larger than X.
 */
public class TestableTask12 implements ITestableTask12 {
    /**
     * Transform the integer list so that the numbers that < value will be first,
     *                          and they will be followed by the numbers > value.
     * @param integers The integer list.
     * @param value The separating value.
     * @return The resultant list.
     */
    @Override
    public List<Integer> transform(List<Integer> integers, int value) {
     /*  int last = integers.size() - 1;
        int i = 0;
        //all integers  >= value -- at the end of the list
        while (i <= last) {
            if (integers.get(i) > value) {
                swap(integers, i, last);
                last--;
                i--;
            }
            i++;
        }
        i = 0;
        // all integers  == value -- in the middle of the list
        while (i < last) {
            if (integers.get(i) == value) {
                swap(integers, i, last);
                last--;
                i--;
            }
            i++;
        }*/
         Collections.sort(integers);
        return integers;
    }

    /**
     * Swap two elements.
     * @param integers The integer list.
     * @param i The index of the element.
     * @param j The index of the element.
     */
   /* private void swap(List<Integer> integers, int i, int j) {
        Integer temp = integers.get(i);
        integers.set(i, integers.get(j));
        integers.set(j, temp);
    }*/

}
