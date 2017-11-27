package com.epam.courses.jf.practice.SigarevVL.second;

import com.epam.courses.jf.practice.common.second.ITestableTask6;

import java.util.HashMap;
import java.util.Map;

/**
 * Adds two polynomials whose coefficients are stored
 * in the HashMap object. The degree of the element of the polynomial
 * is the key, the coefficient of the element of the polynomial
 * is the value. The coefficients of polynomials whose keys
 * are absent in the map are zero.
 */
public class Task6 implements ITestableTask6 {

    /**
     * Adds two polynomials.
     *
     * @param first  The first polynomial.
     * @param second The second polynomial.
     * @return The polynomial obtained as a result of addition.
     */
    @Override
    public HashMap<Integer, Integer> addPolynomials(HashMap<Integer,
            Integer> first, HashMap<Integer, Integer> second) {

        HashMap<Integer, Integer> resultPolynomial = new HashMap<>(first);

        for (Map.Entry<Integer, Integer> entry : second.entrySet()) {
            if (resultPolynomial.containsKey(entry.getKey())) {
                int sum = resultPolynomial.get(entry.getKey())
                        + entry.getValue();

                resultPolynomial.put(entry.getKey(), sum);
            } else {
                resultPolynomial.put(entry.getKey(), entry.getValue());
            }
        }
        return resultPolynomial;
    }
}
