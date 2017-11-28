package com.epam.courses.jf.practice.igulyaev.second;

import com.epam.courses.jf.practice.common.second.ITestableTask5;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Arrays;
import java.util.List;

public class TestableTask5 implements ITestableTask5 {
    @Override
    public double calcResistance(List<IMeasurement> measurements) {
        BigDecimal numerator   = new BigDecimal(0);
        BigDecimal denominator = new BigDecimal(0);
        for (IMeasurement m : measurements) {
            BigDecimal I = new BigDecimal(m.getCurrent());
            BigDecimal U = new BigDecimal(m.getVoltage());
            numerator    = numerator.add(I.multiply(U));
            denominator  = denominator.add(I.pow(2));
        }
        return numerator.divide(denominator, MathContext.DECIMAL64).doubleValue();
    }
}
