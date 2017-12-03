package com.epam.courses.jf.practice.desided.second;

import com.epam.courses.jf.practice.common.second.ITestableTask5;

import java.math.BigDecimal;
import java.util.List;

public class Task5 implements ITestableTask5{

    @Override
    public double calcResistance(List<IMeasurement> measurements) {

        BigDecimal sumU = new BigDecimal(0);
        BigDecimal sumI = new BigDecimal(0);

        for (IMeasurement measurement : measurements){
            BigDecimal U = new BigDecimal(measurement.getVoltage());
            BigDecimal I = new BigDecimal(measurement.getCurrent());
            sumU = sumU.add(U.multiply(I));
            sumI = sumI.add(I.multiply(I));
        }

        return sumU.divide(sumI, 8, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
