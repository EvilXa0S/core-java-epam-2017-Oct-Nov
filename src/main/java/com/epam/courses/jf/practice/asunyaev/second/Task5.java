package com.epam.courses.jf.practice.asunyaev.second;

import com.epam.courses.jf.practice.common.second.ITestableTask5;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class Task5 implements ITestableTask5 {
    public double calcResistance(List<IMeasurement> measurements) {
        BigDecimal r = BigDecimal.ZERO;
        BigDecimal iuMean = BigDecimal.ZERO;
        BigDecimal iiMean = BigDecimal.ZERO;
        for (IMeasurement measurement : measurements) {
            BigDecimal i = BigDecimal.valueOf(measurement.getCurrent());
            BigDecimal u = BigDecimal.valueOf(measurement.getVoltage());
            iuMean = iuMean.add(i.multiply(u));
            iiMean = iiMean.add(i.multiply(i));
        }
        //BigDecimal n = BigDecimal.valueOf(measurements.size());
        //iuMean = iuMean.divide(n);
        //iiMean = iiMean.divide(n);

        if (iiMean.equals(BigDecimal.ZERO)) {
            return 0;
        }

        return iuMean.divide(iiMean, 6, RoundingMode.HALF_UP).doubleValue();
    }
}
