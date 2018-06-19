package com.epam.courses.jf.practice.mallayarov.second;

import com.epam.courses.jf.practice.common.second.I2DPoint;
import com.epam.courses.jf.practice.common.second.ITestableTask16;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * На клетчатой бумаге нарисован круг.
 * Вывести в файл описания всех клеток, целиком лежащих внутри круга.
 * Выводить в порядке возрастания расстояния от клетки до центра круга.
 * Использовать класс SortedMap.
 */
public class Task16Impl implements ITestableTask16 {

    // sqrt ((x - center_x)^2 + (y - center_y)^2) < radius => inside

    /**
     * Осуществляет анализ переданных точек, находя среди них попавших внутрь круга.
     * @param center Точка, в которой расположен центр круга.
     * @param radius Радиус круга.
     * @param output Файл для вывода результатов.
     * @return Файл с результатами анализа.
     */
    @Override
    public IFileWithPoints analyze(I2DPoint center, int radius, File output) {
        // Map of points sorted by distance to center
        SortedMap<I2DPoint, Double> map = new TreeMap<>((o1, o2) -> {
            double distanceFirst = countDistance(o1.getX(), o1.getY(), center.getX(), center.getY());
            double distanceSecond = countDistance(o2.getX(), o2.getY(), center.getX(), center.getY());
            return distanceFirst > distanceSecond ? 1 : -1; });

        for (double i = (int) (center.getX() - radius); i <= (int) (center.getX() + radius); ++i) { // for x
            for (double j = (int) (center.getY() - radius); j <= (int) (center.getY() + radius); ++j) {// for y
                double distance = countDistance(i, j, center.getX(), center.getY());
                if (distance < radius) {
                    map.put(new SinglePoint(i, j), distance);
                }
            }
        }

        try (FileWriter fileWriter = new FileWriter(output)) {
            for (I2DPoint key : map.keySet()) {
                fileWriter.write(key.getX() + " " + key.getY() + " " + map.get(key));
                fileWriter.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new MyFile(output, map);
    }

    private double countDistance(double firstX, double firstY, double secondX, double secondY) {
        return Math.sqrt(Math.pow(firstX - secondX, 2) + Math.pow(firstY - secondY, 2));
    }

    /**
     * Представляет файл, содержащий информацию о найденных точках.
     */
    public class MyFile implements IFileWithPoints {

        private File file;
        private SortedMap<I2DPoint, Double> map;

        public MyFile(File file, SortedMap<I2DPoint, Double> map) {
            this.file = file;
            this.map = map;
        }

        /**
         * @return Файл с результатами анализа.
         */
        @Override
        public File getFile() {
            return file;
        }

        /**
         * Извлекает из файла информацию о хранящихся в нем точках.
         * @return Множество пар: точка + расстояние до центра.
         */
        @Override
        public SortedMap<I2DPoint, Double> getPoints() {
            return map;
        }
    }
}
