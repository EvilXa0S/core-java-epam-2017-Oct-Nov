package com.epam.courses.jf.practice.mallayarov.second;

import com.epam.courses.jf.practice.common.second.I2DPoint;
import com.epam.courses.jf.practice.common.second.ITestableTask15;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * На плоскости задано N точек.
 * Вывести в файл описания всех прямых, которые проходят более чем через 2 точки из заданных.
 */
public class Task15Impl implements ITestableTask15 {

    /**
     * Осуществляет анализ переданных точек, вычисляя линии, которые проходят более чем через 2 точки.
     * @param points Множество точек на плоскости.
     * @param output Файл для вывода результатов.
     * @return Файл с результатами анализа.
     */
    @Override
    public IFileWithLines analyze(Set<I2DPoint> points, File output) {
        if (points.size() <= 2) {
            return new MyFile(output, new HashSet<>());
        }

        Set<Line> allLines = new HashSet<>();

        for (I2DPoint firstPoint : points) {
           for (I2DPoint secondPoint : points) {
               if (firstPoint != secondPoint) {
                   allLines.add(new Line(firstPoint, secondPoint));
               }
           }
       }

        // (y2 - y1) / (x2 - x1) = (y3 - y1) / (x3 - x1) => (y2 - y1) (x3 - x1) = (y3 - y1) (x2 - x1)
        for (Line line : allLines) {
           for (I2DPoint thirdPoint : points) {
               if (!line.getPoints().contains(thirdPoint)) {
                   I2DPoint first = line.getFirstPoint();
                   I2DPoint second = line.getSecondPoint();
                   double firstPartOfEqualisation = (second.getY() - first.getY()) * (thirdPoint.getX() - first.getX());
                   double secondPartOfEqualisation = (thirdPoint.getY() - first.getY()) * (second.getX() - first.getX());

                   if (firstPartOfEqualisation == secondPartOfEqualisation) {
                       line.addPoint(thirdPoint);
                   }
               }
           }
       }

       try (FileWriter fileWriter = new FileWriter(output)) {
           for (Line line : allLines) {
               Set<I2DPoint> pointSet = line.getPoints();
               for (I2DPoint point : pointSet) {
                   fileWriter.write(point.getX() + " " + point.getY() + " ");
               }
               fileWriter.write("\n");
           }
       } catch (IOException e) {
           e.printStackTrace();
       }

        return new MyFile(output, allLines);
    }

    /**
     * Представляет файл, содержащий информацию о найденных линиях.
     */
    public class MyFile implements IFileWithLines {

        private File file;
        private Set<Line> allLines;

        public MyFile(File file, Set<Line> allLines) {
            this.file = file;
            this.allLines = allLines;
        }

        /**
         * @return Файл с результатами анализа.
         */
        @Override
        public File getFile() {
            return file;
        }

        /**
         * Извлекает из файла информацию о хранящихся в нем линиях.
         * @return Множество линий, найденных в результате анализа.
         */
        @Override
        public Set<ILine> getLines() {
            return new HashSet<>(allLines);
        }
    }
}
