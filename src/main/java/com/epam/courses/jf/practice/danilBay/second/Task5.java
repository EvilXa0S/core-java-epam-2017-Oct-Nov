package com.epam.courses.jf.practice.danilBay.second;

import com.epam.courses.jf.practice.common.second.ITestableTask5;

import java.math.BigDecimal;
import java.text.Bidi;
import java.util.List;

public class Task5 implements ITestableTask5 {
    @Override
    public double calcResistance(List<IMeasurement> measurements) {
        BigDecimal voltage;
        BigDecimal cur;
        BigDecimal resistance=new BigDecimal(0);
        for(IMeasurement x: measurements){
            cur=new BigDecimal(x.getCurrent());
            voltage=new BigDecimal(x.getVoltage());
            resistance.add(voltage.divide(cur));

        }
        resistance=resistance.divide(new BigDecimal(measurements.size()));
        resistance.setScale(6, BigDecimal.ROUND_HALF_UP);
        double res=resistance.doubleValue();
        return res;

    }
}
