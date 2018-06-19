package com.epam.courses.jf.practice.mallayarov.second;

import com.epam.courses.jf.practice.common.second.ITestableTask5;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * Список содержит результаты измерений тока и напряжения на неизвестном сопротивлении R.
 * Найти приближенное число R методом наименьших квадратов.
 * Для повышения точности вычислений использовать класс {@link java.math.BigDecimal}
 */
public class Task5Impl implements ITestableTask5 {

    /**
     * Вычисляет сопротивление методом наименьших квадратов.
     * @param measurements Измерения в ходе эксперимента.
     * @return Вычисленное по исходным данным сопротивление.
     */
    @Override
    public double calcResistance(List<IMeasurement> measurements) {
        BigDecimal result;
        BigDecimal numerator = new BigDecimal(0.0);
        BigDecimal denominator = new BigDecimal(0.0);
        for (IMeasurement measurement : measurements) {
            numerator = numerator.add(new BigDecimal(measurement.getCurrent() * measurement.getVoltage())); // sum of current * voltage
            denominator = denominator.add(new BigDecimal(measurement.getCurrent() * measurement.getCurrent())); // sum of current^2
        }

        result = numerator.divide(denominator, RoundingMode.HALF_UP);
        return result.doubleValue();
    }
}
