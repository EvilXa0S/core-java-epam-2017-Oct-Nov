package com.epam.courses.jf.practice.akriukov.second;

import com.epam.courses.jf.practice.common.second.ITestableTask5;

import java.math.BigDecimal;
import java.util.List;
import java.util.ListIterator;

public class Task5 implements ITestableTask5{

    @Override
    public double calcResistance(List<IMeasurement> measurements) {
        BigDecimal numerator = BigDecimal.ZERO;
        BigDecimal denominator = BigDecimal.ZERO;
        double result = 0.0;

        ListIterator<IMeasurement> it = measurements.listIterator();
        while(it.hasNext()) {
            IMeasurement next = it.next();
            BigDecimal current = new BigDecimal(next.getCurrent());
            BigDecimal voltage = new BigDecimal(next.getVoltage());
            numerator = numerator.add(current.multiply(voltage));
            denominator = denominator.add((current.multiply(current)));
        }

        result = numerator.divide(denominator, 6).doubleValue();

        return result;
    }
}
