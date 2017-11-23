package com.epam.courses.jf.practice.vplaksin.second;

import com.epam.courses.jf.practice.common.second.ITestableTask5;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * Список содержит результаты измерений тока и напряжения на неизвестном сопротивлении R.
 * Найти приближенное число R методом наименьших квадратов.
 * Для повышения точности вычислений использовать класс {@link java.math.BigDecimal}
 * Гарантировать точность не менее 1e-6.
 */
public class Task5 implements ITestableTask5 {

    /**
     * Вычисляет сопротивление методом наименьших квадратов.
     *
     * @param measurements Измерения в ходе эксперимента.
     * @return Вычисленное по исходным данным сопротивление.
     */
    @Override
    public double calcResistance(List<IMeasurement> measurements) {
        BigDecimal sumUI = BigDecimal.ZERO;
        BigDecimal sumII = BigDecimal.ZERO;

        for (IMeasurement measurement : measurements) {
            BigDecimal u = BigDecimal.valueOf(measurement.getVoltage());
            BigDecimal i = BigDecimal.valueOf(measurement.getCurrent());
            sumUI = sumUI.add(u.multiply(i));
            sumII = sumII.add(i.multiply(i));
        }

        if (sumII.equals(BigDecimal.ZERO)) {
            return 0;
        }

        return sumUI.divide(sumII, 6, RoundingMode.HALF_UP).doubleValue();
    }

}
