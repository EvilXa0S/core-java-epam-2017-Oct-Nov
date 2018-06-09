package com.epam.courses.jf.practice.klimenko.second;

import com.epam.courses.jf.practice.common.second.ITestableTask5;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class TestableTask5Impl implements ITestableTask5 {
    @Override
    public double calcResistance(List<IMeasurement> measurements) {
        BigDecimal sumPower = new BigDecimal(0);
        BigDecimal sumCurrentSquared = new BigDecimal(0);

        for (IMeasurement m : measurements) {
            sumPower = sumPower.add(new BigDecimal(m.getCurrent() * m.getVoltage()));
            sumCurrentSquared = sumCurrentSquared.add(new BigDecimal(m.getCurrent() * m.getCurrent()));
        }

        return sumPower.divide(sumCurrentSquared, 12, RoundingMode.HALF_UP).doubleValue();
    }
}
