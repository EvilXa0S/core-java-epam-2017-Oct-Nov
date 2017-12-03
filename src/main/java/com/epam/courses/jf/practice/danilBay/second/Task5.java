package com.epam.courses.jf.practice.danilBay.second;

import com.epam.courses.jf.practice.common.second.ITestableTask5;

import java.math.BigDecimal;
import java.text.Bidi;
import java.util.List;

public class Task5 implements ITestableTask5 {
    @Override
    public double calcResistance(List<IMeasurement> measurements) {
        BigDecimal sum1 = new BigDecimal(0.0);

        BigDecimal sum2 = new BigDecimal(0.0);

        for (IMeasurement measurement : measurements) {

            sum1 = sum1.add((new BigDecimal(measurement.getCurrent())).multiply(new BigDecimal(measurement.getVoltage())));
            sum2 = sum2.add((new BigDecimal(measurement.getCurrent())).multiply(new BigDecimal(measurement.getCurrent())));
        }
        double result=(sum1.divide(sum2, BigDecimal.ROUND_HALF_UP)).doubleValue();
        return result;

    }
}
