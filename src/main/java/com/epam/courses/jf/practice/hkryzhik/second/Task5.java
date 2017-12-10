package com.epam.courses.jf.practice.hkryzhik.second;

import com.epam.courses.jf.practice.common.second.ITestableTask5;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class Task5 implements ITestableTask5{
    @Override
    public double calcResistance(List<IMeasurement> measurements) {

        BigDecimal numerator = new BigDecimal(0);
        BigDecimal denominator = new BigDecimal(0);

        for (IMeasurement measurement : measurements) {
             numerator = numerator.add(new BigDecimal(measurement.getCurrent() * measurement.getVoltage()));
             denominator = denominator.add(new BigDecimal(measurement.getCurrent() * measurement.getCurrent()));
        }

        if(denominator.doubleValue() != 0) {
            return numerator.divide(denominator, RoundingMode.HALF_UP).doubleValue();
        }else {
            return 0;
        }
    }
}
