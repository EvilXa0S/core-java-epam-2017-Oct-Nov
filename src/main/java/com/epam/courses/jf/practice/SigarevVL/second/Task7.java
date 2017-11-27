package com.epam.courses.jf.practice.SigarevVL.second;

import com.epam.courses.jf.practice.common.second.ITestableTask7;

import java.util.ArrayList;
import java.util.List;

/**
 * Multiply two polynomials of a given degree,
 * the coefficients of polynomials are stored in different lists.
 * The element of the list with the index i
 * corresponds to the coefficient of the i-th power.
 */
public class Task7 implements ITestableTask7 {

    /**
     * Performs multiplication of two polynomials.
     *
     * @param first  The first polynomial.
     * @param second The second polynomial.
     * @return The polynomial obtained as a result of addition
     */
    @Override
    public List<Integer> multiplyPolynomials(List<Integer> first,
                                             List<Integer> second) {

        List<Integer> multiplyResult = new ArrayList<>(first.size()
                + second.size());

        for (int i = 0; i < first.size() + second.size() - 1; i++) {
            multiplyResult.add(0);
        }

        for (int i = 0; i < first.size(); i++) {
            for (int j = 0; j < second.size(); j++) {
                multiplyResult.set((i + j), multiplyResult.get(i + j)
                        + first.get(i) * second.get(j));
            }
        }
        return multiplyResult;
    }
}
