package com.epam.courses.jf.practice.asgalantsev.second;

import com.epam.courses.jf.practice.common.second.ITestableTask5;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Task5 implements ITestableTask5{
    @Override
    public double calcResistance(List<IMeasurement> measurements) {
        List<BigDecimal> currents = new ArrayList<>(measurements.size());
        List<BigDecimal> voltages = new ArrayList<>(measurements.size());
        double result = 0.0;

        ListIterator<IMeasurement> it = measurements.listIterator();
        while(it.hasNext()) {
            IMeasurement next = it.next();
            currents.add(new BigDecimal(next.getCurrent()));
            voltages.add(new BigDecimal(next.getVoltage()));
        }
         
        return  result;
    }
}
