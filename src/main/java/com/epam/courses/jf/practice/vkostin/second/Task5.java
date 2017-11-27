package com.epam.courses.jf.practice.vkostin.second;

import com.epam.courses.jf.practice.common.second.ITestableTask5;

import static java.math.BigDecimal.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class Task5 implements ITestableTask5 {

    @Override
    public double calcResistance(List<IMeasurement> measurements) {

        // + use zero as BD constant
        BigDecimal activePowerSum = new BigDecimal(String.valueOf(ZERO));
        BigDecimal squaredCurrentSum = new BigDecimal(String.valueOf(ZERO));

        for (IMeasurement m : measurements) {
            // + use static import
            activePowerSum = activePowerSum.add(valueOf(m.getCurrent() * m.getVoltage()));
            squaredCurrentSum = squaredCurrentSum.add(valueOf(m.getCurrent() * m.getCurrent()));
        }

        return activePowerSum
                .divide(squaredCurrentSum, 10, RoundingMode.CEILING)
                .doubleValue();

    }


    public static class Measure implements IMeasurement {

        private double current;
        private double voltage;

        public double getCurrent() {
            return current;
        }

        public double getVoltage() {
            return voltage;
        }
    }
}
