package com.epam.courses.jf.practice.common.second;

import java.util.List;

/**
 * Интерфейс для юнит-тестирования задания №5.
 *
 * Список содержит результаты измерений тока и напряжения на неизвестном сопротивлении R.
 * Найти приближенное число R методом наименьших квадратов.
 * Для повышения точности вычислений использовать класс {@link java.math.BigDecimal}
 * Гарантировать точность не менее 1e-6.
 */
public interface ITestableTask5 extends ITaskStorage.ITestableTask {

    /**
     * Вычисляет сопротивление методом наименьших квадратов.
     * @param measurements Измерения в ходе эксперимента.
     * @return Вычисленное по исходным данным сопротивление.
     */
    double calcResistance(List<IMeasurement> measurements);

    /** Измерение. */
    interface IMeasurement {

        /** @return Измеренное значение тока. */
        double getCurrent();

        /** @return Измеренное значение напряжения. */
        double getVoltage();
    }
}
