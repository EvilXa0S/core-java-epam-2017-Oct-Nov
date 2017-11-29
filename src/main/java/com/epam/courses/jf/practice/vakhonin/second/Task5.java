package com.epam.courses.jf.practice.vakhonin.second;

import com.epam.courses.jf.practice.common.second.ITestableTask5;

import java.util.List;

import java.math.BigDecimal;

/**
 * Список содержит результаты измерений тока и напряжения на неизвестном сопротивлении R.
 * Найти приближенное число R методом наименьших квадратов.
 * Для повышения точности вычислений использовать класс {@link java.math.BigDecimal}
 */

public class Task5 implements ITestableTask5 {

    /**
     * Вычисляет сопротивление методом наименьших квадратов.
     * @param measurements Измерения в ходе эксперимента.
     * @return Вычисленное по исходным данным сопротивление.
     */

    @Override
    public double calcResistance(List<IMeasurement> measurements) {
        double averageResistance;
        BigDecimal voltage, current;
        BigDecimal sumOfII = BigDecimal.ZERO;
        BigDecimal sumOfUI = BigDecimal.ZERO;

        for (IMeasurement measurement: measurements) {
            voltage = BigDecimal.valueOf(measurement.getVoltage());
            current = BigDecimal.valueOf(measurement.getCurrent());
            sumOfII = sumOfII.add(current.pow(2));
            sumOfUI = sumOfUI.add(voltage.multiply(current));
        }

        averageResistance = sumOfUI.divide(sumOfII, 6, BigDecimal.ROUND_HALF_UP).doubleValue();

        return averageResistance;
    }

}
