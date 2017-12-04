package com.epam.courses.jf.practice.desided.second;

import com.epam.courses.jf.practice.common.second.ITestableTask5;

import java.math.BigDecimal;
import java.util.List;


/**
 * Список содержит результаты измерений тока и напряжения на неизвестном сопротивлении R.
 * Найти приближенное число R методом наименьших квадратов.
 * Для повышения точности вычислений использовать класс {@link java.math.BigDecimal}
 * Гарантировать точность не менее 1e-6.
 */
public class Task5 implements ITestableTask5{


    /**
     * Вычисляет сопротивление методом наименьших квадратов.
     *
     * @param measurements Измерения в ходе эксперимента.
     * @return Вычисленное по исходным данным сопротивление.
     */
    @Override
    public double calcResistance(List<IMeasurement> measurements) {

        BigDecimal sumU = new BigDecimal(0);
        BigDecimal sumI = new BigDecimal(0);

        for (IMeasurement measurement : measurements){
            BigDecimal U = new BigDecimal(measurement.getVoltage());
            BigDecimal I = new BigDecimal(measurement.getCurrent());
            sumU = sumU.add(U.multiply(I));
            sumI = sumI.add(I.multiply(I));
        }

        return sumU.divide(sumI, 8, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
