package com.epam.courses.jf.practice.nzenkova.second;

import com.epam.courses.jf.practice.common.second.ITestableTask6;

import java.util.HashMap;


/**
 * Add two polynomials, if the coefficients of the polynomials are kept in HashMap.
 * A degree of the element of the polynomial is a key, a coefficient of the element of the polynomial is a value.
 * The coefficients of the polynomials, whose keys aren't be in a map, are equal to zero.
 */
public class TestableTask6 implements ITestableTask6 {
    /**
     * Add two polynomials.
     * @param first The first polynomial.
     * @param second The second polynomial.
     * @return Sum of two polynomials.
     */
    @Override
    public HashMap<Integer, Integer> addPolynomials(HashMap<Integer, Integer> first, HashMap<Integer, Integer> second) {
        for (Integer integer : second.keySet()) {
            Integer toPut = 0;
            if (first.get(integer) != null) toPut += first.get(integer);
            if (second.get(integer) != null) toPut += second.get(integer);
            first.put(integer, toPut);
        }
        return first;
    }
}
