package com.epam.courses.jf.practice.vplaksin.second;

import com.epam.courses.jf.practice.common.second.I2DPoint;
import com.epam.courses.jf.practice.common.second.ITestableTask15;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.util.HashSet;
import java.io.IOException;
import java.util.Set;

/**
 * На плоскости задано N точек.
 * Вывести в файл описания всех прямых, которые проходят более чем через 2 точки из заданных.
 */
public class Task15 implements ITestableTask15 {

    /**
     * Осуществляет анализ переданных точек, вычисляя линии, которые проходят более чем через 2 точки.
     *
     * @param points Множество точек на плоскости.
     * @param output Файл для вывода результатов.
     * @return Файл с результатами анализа.
     */
    @Override
    public IFileWithLines analyze(Set<I2DPoint> points, File output) {
        Set<ILine> lines = new HashSet<>();

        for (I2DPoint firstPoint : points) {
            Set<I2DPoint> set = new HashSet<>();
            set.add(firstPoint);
            double x1 = firstPoint.getX();
            double y1 = firstPoint.getY();

            for (I2DPoint secondPoint : points) {
                double x2 = secondPoint.getX();
                double y2 = secondPoint.getY();
                if (x1 != x2 || y1 != y2) {
                    set.add(secondPoint);

                    for (I2DPoint thirdPoint : points) {
                        double x3 = thirdPoint.getX();
                        double y3 = thirdPoint.getY();
                        if ((x1 != x3 || y1 != y3) && (x2 != x3 || y2 != y3) &&
                                ((x3 - x1) * (y2 - y1) == (x2 - x1) * (y3 - y1))) {
                            set.add(thirdPoint);
                        }
                    }
                }
            }

            if (set.size() > 2) {
                lines.add(new Line(set));
            }
        }

        return new FileWithLines(output, lines);
    }

    //Файл, содержащий результаты анализа.
    private class FileWithLines implements IFileWithLines {

        private File file;

        private FileWithLines(File file) {
            this.file = file;
        }

        private FileWithLines(File file, Set<ILine> lines) {
            this(file);
            writeLinesToFile(lines);
        }

        //Запись линий в файл
        private void writeLinesToFile(Set<ILine> lines) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (ILine line : lines) {
                    writer.write("line:[");
                    for (I2DPoint point : line.getPoints()) {
                        writer.write("point:{ ");
                        writer.write(String.valueOf(point.getX()));
                        writer.write(" ");
                        writer.write(String.valueOf(point.getY()));
                        writer.write(" }");
                    }
                    writer.write("]\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public File getFile() {
            return file;
        }

        //Извлекает из файла информацию о хранящихся в нем линиях.
        @Override
        public Set<ILine> getLines() {
            Set<ILine> result = new HashSet<>();

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String s;
                while ((s = reader.readLine()) != null) {
                    Set<I2DPoint> points = new HashSet<>();
                    String[] splited = s.split("\\s");
                    for (int i = 1; i < splited.length; i += 3) {
                        Double x = Double.parseDouble(splited[i]);
                        Double y = Double.parseDouble(splited[i + 1]);
                        points.add(new Point2D(x, y));
                    }
                    result.add(new Line(points));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;
        }
    }

    //Прямая, заданная точками, входящими в исходное множество.
    private class Line implements ILine {

        private Set<I2DPoint> set = new HashSet<>();

        private Line(Set<I2DPoint> set) {
            this.set = set;
        }

        @Override
        public Set<I2DPoint> getPoints() {
            return set;
        }
    }

}
