package com.epam.courses.jf.practice.nzenkova.second;

import com.epam.courses.jf.practice.common.second.ITestableTask5;

import java.math.BigDecimal;
import java.util.List;


public class TestableTask5 implements ITestableTask5 {
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