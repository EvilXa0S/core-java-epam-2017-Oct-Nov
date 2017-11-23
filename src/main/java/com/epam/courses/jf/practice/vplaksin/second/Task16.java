package com.epam.courses.jf.practice.vplaksin.second;

import com.epam.courses.jf.practice.common.second.I2DPoint;
import com.epam.courses.jf.practice.common.second.ITestableTask16;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.SortedMap;
import java.util.TreeMap;


import static java.lang.Math.round;
import static java.lang.Math.sqrt;

/**
 * На клетчатой бумаге нарисован круг (задается центром и радиусом).
 * Координаты центра круга могут быть дробными числами (например [0.3, 0.7]).
 * Вывести в файл все точки с целочисленными координатами, лежащие внутри круга (лежащие на окружности не учитывать).
 * Выводить в порядке возрастания расстояния от точки до центра круга.
 * Использовать класс PriorityQueue.
 */
public class Task16 implements ITestableTask16 {

    //Расстояние от точки до центра
    private static double distance(I2DPoint center, I2DPoint point) {
        double x = center.getX() - point.getX();
        double y = center.getY() - point.getY();
        return sqrt(x * x + y * y);
    }

    //Сравнение расстояний от точек до центра
    private static int compare(I2DPoint point1, I2DPoint point2, I2DPoint center) {
        double distance1 = distance(center, point1);
        double distance2 = distance(center, point2);
        double x1 = point1.getX();
        double y1 = point1.getY();
        double x2 = point2.getX();
        double y2 = point2.getY();

        if (distance1 == distance2) {
            if (x1 == x2) {
                return Double.compare(y1, y2);
            } else {
                return Double.compare(x1, x2);
            }
        } else {
            return Double.compare(distance1, distance2);
        }
    }

    /**
     * Осуществляет анализ точек, находя среди них попавших внутрь круга.
     *
     * @param center Точка, в которой расположен центр круга.
     * @param radius Радиус круга.
     * @param output Файл для вывода результатов.
     * @return Файл с результатами анализа.
     */
    @Override
    public IFileWithPoints analyze(I2DPoint center, int radius, File output) {
        double xCenter = center.getX();
        double yCenter = center.getY();
        FileWithPoints result = new FileWithPoints(output);
        Queue<I2DPoint> queue = new PriorityQueue<>((point1, point2) -> compare(point1, point2, center));

        for (int x = (int) round(xCenter - radius); x < (int) round(xCenter + radius); x++) {
            for (int y = (int) round(yCenter - radius); y < (int) round(yCenter + radius); y++) {
                I2DPoint point = new Point2D(x, y);
                Double distance = distance(center, point);
                if (distance < radius) {
                    queue.add(point);
                }
            }
        }

        result.writePointsToFile(center, queue);
        return result;
    }

    //Файл, содержащий результаты анализа.
    private class FileWithPoints implements IFileWithPoints {

        private File file;

        private FileWithPoints(File file) {
            this.file = file;
        }

        //Запись точек вместе с расстоянием до центра в файл
        private void writePointsToFile(I2DPoint center, Queue<I2DPoint> queue) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(String.valueOf(center.getX()) + " ");
                writer.write(String.valueOf(center.getY()) + "\n");

                for (I2DPoint point : queue) {
                    writer.write(String.valueOf(point.getX()));
                    writer.write(' ');
                    writer.write(String.valueOf(point.getY()));
                    writer.write(' ');
                    writer.write(String.valueOf(distance(center, point)));
                    writer.write('\n');
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public File getFile() {
            return file;
        }

        //Считать точки из файла
        @Override
        public SortedMap<I2DPoint, Double> getPoints() {
            SortedMap<I2DPoint, Double> result = new TreeMap<>();

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String s = reader.readLine();
                String[] splited = s.split("\\s");
                final I2DPoint center = new Point2D(Double.parseDouble(splited[0]), Double.parseDouble(splited[1]));
                result = new TreeMap<>((point1, point2) -> compare(point1, point2, center));

                while ((s = reader.readLine()) != null) {
                    splited = s.split("\\s");
                    I2DPoint point = new Point2D(Double.parseDouble(splited[0]), Double.parseDouble(splited[1]));
                    Double value = Double.parseDouble(splited[2]);
                    result.put(point, value);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;
        }
    }
}
