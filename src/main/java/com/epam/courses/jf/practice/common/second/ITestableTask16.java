package com.epam.courses.jf.practice.common.second;

import java.io.File;
import java.util.SortedMap;

/**
 * Интерфейс для юнит-тестирования задания №16.
 *
 * На клетчатой бумаге нарисован круг.
 * Вывести в файл описания всех клеток, целиком лежащих внутри круга.
 * Выводить в порядке возрастания расстояния от клетки до центра круга.
 * Использовать класс SortedMap.
 */
public interface ITestableTask16 extends ITaskStorage.ITestableTask {

    /**
     * Осуществляет анализ переданных точек, находя среди них попавших внутрь круга.
     * @param center Точка, в которой расположен центр круга.
     * @param radius Радиус круга.
     * @param output Файл для вывода результатов.
     * @return Файл с результатами анализа.
     */
    IFileWithPoints analyze(I2DPoint center, int radius, File output);

    /**
     * Представляет файл, содержащий информацию о найденных точках.
     */
    interface IFileWithPoints {


        /**
         * @return Файл с результатами анализа.
         */
        File getFile();

        /**
         * Извлекает из файла информацию о хранящихся в нем точках.
         * @return Множество пар: точка + расстояние до центра.
         */
        SortedMap<I2DPoint, Double> getPoints();
    }
}