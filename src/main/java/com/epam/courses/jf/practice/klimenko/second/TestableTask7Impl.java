package com.epam.courses.jf.practice.klimenko.second;

import com.epam.courses.jf.practice.common.second.ITestableTask7;

import java.util.ArrayList;
import java.util.List;

public class TestableTask7Impl implements ITestableTask7 {
    @Override
    public List<Integer> multiplyPolynomials(List<Integer> first, List<Integer> second) {
        List<Integer> ret = new ArrayList<>();
        for (int firstPower = 0; firstPower < first.size(); ++firstPower) {
            for (int secondPower = 0; secondPower < second.size(); ++secondPower) {
                int power = firstPower + secondPower;
                int value = first.get(firstPower) * second.get(secondPower);
                if (value != 0) {
                    while (ret.size() <= power) {
                        ret.add(0);
                    }
                    ret.set(power, ret.get(power) + value);
                }
            }
        }
        if (ret.isEmpty()) {
            ret.add(0);
        }
        return ret;
    }
}
