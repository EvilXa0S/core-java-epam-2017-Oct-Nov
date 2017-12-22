package com.epam.courses.jf.practice.vkostin.second;

import com.epam.courses.jf.practice.common.second.ITestableTask7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Task7 implements ITestableTask7 {

    @Override
    public List<Integer> multiplyPolynomials(List<Integer> first, List<Integer> second) {

        // explain numbers logic
        //      [ The element of the list with the index i corresponds to the coefficient of the i-th power.
        //        The .size() method will give the number of items in the list,
        //        which is 1 more than the index of the last element,
        //        which corresponds to the maximum degree of the polynomial.
        //        The size of the resultant polynomial will be equal to one folded with
        //        the maximum degree of the resultant polynomial,
        //        which in turn is equal to the sum of the maximal elements of the polynomials to be multiplied. ]
        List<Integer> resultPoly = new ArrayList<>(Collections
                .nCopies((first.size() - 1) + (second.size() - 1) + 1, 0));

        for (int i = 0; i < first.size(); ++i) {
            for (int j = 0; j < second.size(); ++j) {
                resultPoly.set(i + j, resultPoly.get(i + j) + first.get(i) * second.get(j));
            }
        }

        return resultPoly;
    }
}
