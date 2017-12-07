package com.epam.courses.jf.practice.akunats.second;

import com.epam.courses.jf.practice.common.second.ITestableTask5;

import java.math.BigDecimal;
import java.util.List;

public class Task5 implements ITestableTask5 {
    public double calcResistance(List<IMeasurement> measurements) {
        double multiplyCurrentAndVoltage = 0;
        double squareCurrent = 0;
        for (IMeasurement measurement : measurements) {
            multiplyCurrentAndVoltage += measurement.getCurrent() * measurement.getVoltage();
            squareCurrent += Math.pow(measurement.getCurrent(), 2);
        }
        BigDecimal interResult = new BigDecimal(multiplyCurrentAndVoltage / squareCurrent);
        interResult = interResult.setScale(6, BigDecimal.ROUND_HALF_UP);
        return Double.parseDouble(interResult.toString());
    }
}
