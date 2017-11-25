package com.epam.courses.jf.practice.nzenkova.second;

import com.epam.courses.jf.practice.common.second.ITestableTask5;

import java.math.BigDecimal;
import java.util.List;

/**
 * The list contains the result of current and voltage measurements on the unknown resistance R.
 * Find the approximate number R by the method of least squares.
 * Use the class {@link java.math.BigDecimal} for increasing accuracy of the calculation.
 * The accuracy is not less than 1e-6. Guarantee it.
 */
public class TestableTask5 implements ITestableTask5 {
    /**
     * Calculate the resistance with the method of least squares.
     * @param measurements The measurements in the experiment.
     * @return The resistance is calculated from the initial data.
     */
    @Override
    public double calcResistance(List<IMeasurement> measurements) {
        BigDecimal sum1 = new BigDecimal(0d);
        BigDecimal sum2 = new BigDecimal(0d);
        for (IMeasurement measurement : measurements) {
            sum1 = sum1.add((new BigDecimal(measurement.getCurrent())).multiply(new BigDecimal(measurement.getVoltage())));
            sum2 = sum2.add((new BigDecimal(measurement.getCurrent())).multiply(new BigDecimal(measurement.getCurrent())));
        }

        return (sum1.divide(sum2, BigDecimal.ROUND_HALF_UP)).doubleValue();
    }
}
