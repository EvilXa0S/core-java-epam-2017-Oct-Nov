package com.epam.courses.jf.practice.sskovalevskiy.second;

import com.epam.courses.jf.practice.common.second.ITestableTask5;

import java.util.List;

/**
 * Created by asus on 01.11.2017.
 * Решено, как описано в  http://yun.moluch.ru/archive/5/287/
 *
 * Список содержит результаты измерений тока и напряжения на неизвестном сопротивлении R.
 * Найти приближенное число R методом наименьших квадратов.
 * Для повышения точности вычислений использовать класс {@link java.math.BigDecimal}
 */
public class ITestableTask5Impl implements ITestableTask5 {

    /**
     * Вычисляет сопротивление методом наименьших квадратов.
     * @param measurements Измерения в ходе эксперимента.
     * @return Вычисленное по исходным данным сопротивление.
     */
    @Override
    public double calcResistance(List<IMeasurement> measurements) {

        double UI = 0;
        double I2 = 0;

        for (IMeasurement measurement : measurements) {
            UI += measurement.getVoltage() * measurement.getCurrent();
            I2 += Math.pow(measurement.getCurrent(), 2);
        }

        return UI/I2;
    }
}
