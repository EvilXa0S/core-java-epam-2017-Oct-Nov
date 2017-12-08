package com.epam.courses.jf.practice.nbikbaev.second;

import com.epam.courses.jf.practice.common.second.ITestableTask5;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class Task5 implements ITestableTask5 {
    @Override
    public double calcResistance(List<IMeasurement> measurements) {
        BigDecimal currentSum = new BigDecimal(0.0);
        BigDecimal power = new BigDecimal(0.0);
        for (IMeasurement measurement : measurements) {
            power = power.add(new BigDecimal(measurement.getCurrent() * measurement.getVoltage()));
            currentSum = currentSum.add(new BigDecimal(measurement.getCurrent() * measurement.getCurrent()));
        }
        BigDecimal res = power.divide(currentSum, 10, RoundingMode.CEILING);
        return res.doubleValue();
    }
}
