package com.epam.courses.jf.practice.SigarevVL.second;

import com.epam.courses.jf.practice.common.second.ITestableTask5;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * The list contains the results of current and voltage measurements
 * on the unknown resistance R.
 * Find the approximate number R by the method of least squares.
 * Use the class {@link java.math.BigDecimal}
 * Guaranteed accuracy no less than 1e-6.
 */
public class Task5 implements ITestableTask5 {

    /**
     * Calculation resistance by least squares.
     *
     * @param measurements Measurements during the experiment.
     * @return The resistance calculated from the initial data.
     */
    @Override
    public double calcResistance(List<IMeasurement> measurements) {

        BigDecimal voltageAndCurrentMultiplection = new BigDecimal(0);
        BigDecimal currentSquare = new BigDecimal(0);

        for (IMeasurement measurement : measurements) {
            voltageAndCurrentMultiplection = voltageAndCurrentMultiplection.
                    add(BigDecimal.valueOf(measurement.getCurrent()
                    * measurement.getVoltage()));

            currentSquare = currentSquare.add(BigDecimal.
                    valueOf(measurement.getCurrent() * measurement.getCurrent()));
        }
        return voltageAndCurrentMultiplection
                .divide(currentSquare, 6, RoundingMode.HALF_UP).doubleValue();
    }
}
