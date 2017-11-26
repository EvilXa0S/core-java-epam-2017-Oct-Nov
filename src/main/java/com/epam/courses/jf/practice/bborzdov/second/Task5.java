package com.epam.courses.jf.practice.bborzdov.second;

import com.epam.courses.jf.practice.common.second.ITestableTask5;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * Created by bogdan on 16.11.17.
 */

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
        BigDecimal voltAmper = new BigDecimal(0);
        BigDecimal sqrAmper = new BigDecimal(0);
        for (IMeasurement measurement : measurements) {
            BigDecimal current = new BigDecimal(measurement.getCurrent());
            BigDecimal volt = new BigDecimal(measurement.getVoltage());
            volt = current.multiply(volt);
            voltAmper = voltAmper.add(volt);
            sqrAmper = sqrAmper.add(current.pow(2));
        }
        return voltAmper.divide(sqrAmper, RoundingMode.HALF_UP).doubleValue();
    }
}
