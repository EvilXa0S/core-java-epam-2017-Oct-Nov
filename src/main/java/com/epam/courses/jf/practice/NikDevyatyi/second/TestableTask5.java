package com.epam.courses.jf.practice.NikDevyatyi.second;

import com.epam.courses.jf.practice.common.second.ITestableTask5;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class TestableTask5 implements ITestableTask5 {
    @Override
    public double calcResistance(List<IMeasurement> measurements) {
        BigDecimal SumII =BigDecimal.ZERO;
        BigDecimal SumUI = BigDecimal.ZERO;

        for(IMeasurement item: measurements){
            BigDecimal tempI = BigDecimal.valueOf(item.getCurrent());
            BigDecimal tempU = BigDecimal.valueOf(item.getVoltage());
            SumII = SumII.add(tempI.multiply(tempI));
            SumUI = SumUI.add(tempI.multiply(tempU));
        }
        if(SumII.equals(BigDecimal.ZERO)){
            return 0.;
        }
        return SumUI.divide(SumII,6, RoundingMode.HALF_UP).doubleValue();

    }
}
