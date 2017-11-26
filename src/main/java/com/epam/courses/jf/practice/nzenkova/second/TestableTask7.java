package com.epam.courses.jf.practice.nzenkova.second;

import com.epam.courses.jf.practice.common.second.ITestableTask7;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Multiply two polynomials of the default degree.
 * The element of the list with the index i corresponds to the coefficient of the degree i.
 * The degree of the resultant polynomials is equal to the sum of the maximum nonzero degrees of the initial polynomials.
 * If the polynomial degenerates into zero, the state is [0].
 */
public class TestableTask7 implements ITestableTask7 {
    /**
     * Multiply two polynomials.
     * @param first The first polynomial.
     * @param second The second polynomial.
     * @return The product of two polynomials.
     */
    @Override
    public List<Integer> multiplyPolynomials(List<Integer> first, List<Integer> second) {
        int size = first.size() + second.size();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(0);
        }
        for (int i = 0; i < first.size(); i++) {
            for (int j = 0; j < second.size(); j++) {
                list.set(i + j, list.get(i + j) + first.get(i) * second.get(j));
            }
        }
        int down = list.size() - 1;
        while (list.size() > 1 && list.get(down) == 0) {
            list.remove(down);
            down--;
        }

        return list;
    }
}

