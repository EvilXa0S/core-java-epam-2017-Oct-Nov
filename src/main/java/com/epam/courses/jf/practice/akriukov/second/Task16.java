package com.epam.courses.jf.practice.akriukov.second;

import com.epam.courses.jf.practice.common.second.I2DPoint;
import com.epam.courses.jf.practice.common.second.ITestableTask16;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * На клетчатой бумаге нарисован круг (задается центром и радиусом).
 * Координаты центра круга могут быть дробными числами (например [0.3, 0.7]).
 * Вывести в файл все точки с целочисленными координатами, лежащие внутри круга (лежащие на окружности не учитывать).
 * Выводить в порядке возрастания расстояния от точки до центра круга.
 * Использовать класс PriorityQueue.
 */
public class Task16 implements ITestableTask16 {

    /**
     * Осуществляет анализ точек, находя среди них попавших внутрь круга.
     * @param center Точка, в которой расположен центр круга.
     * @param radius Радиус круга.
     * @param output Файл для вывода результатов.
     * @return Файл с результатами анализа.
     */
    @Override
    public IFileWithPoints analyze(I2DPoint center, int radius, File output) {
        double centerY = center.getY();
        double centerX = center.getX();
        SortedMap<I2DPoint, Double> sortedMap = new TreeMap<>(new Comparator<I2DPoint>() {

            @Override
            public int compare(I2DPoint o1, I2DPoint o2) {
                double o1Radius = Math.sqrt( Math.pow(o1.getX() - centerX, 2) + Math.pow(o1.getY() - centerY, 2));
                double o2Radius = Math.sqrt( Math.pow(o2.getX() - centerX, 2) + Math.pow(o2.getY() - centerY, 2));
                return o1Radius > o2Radius ? 1 : -1;
            }
        });

        for (int i = (int) Math.floor(radius + centerY); i >= (int) Math.floor( -(radius + centerY)); i--) {

            for (int j = (int) Math.floor(radius + centerX); j >= (int) Math.floor( - (radius + centerX)); j--)  {
                double radiusToPoint = Math.sqrt( Math.pow(i - centerX, 2) + Math.pow(j - centerY, 2));
                if ( radiusToPoint < radius) {
                    sortedMap.put(new Point2D(i, j), radiusToPoint);
                }
            }
        }

        try (PrintWriter writer = new PrintWriter(output)) {

            for (Map.Entry<I2DPoint, Double> entry : sortedMap.entrySet()) {
                writer.write(entry.getKey().toString() + entry.getValue() + "\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new IFileWithPointsImpl(output, sortedMap);
    }

    class IFileWithPointsImpl implements IFileWithPoints {
        private File file;
        private SortedMap<I2DPoint, Double> sortedMap;

        public IFileWithPointsImpl(File file, SortedMap<I2DPoint, Double> sortedMap) {
            this.file = file;
            this.sortedMap = new TreeMap<>(sortedMap);
        }

        @Override
        public File getFile() {
            return file;
        }

        @Override
        public SortedMap<I2DPoint, Double> getPoints() {
            return sortedMap;
        }
    }
}
