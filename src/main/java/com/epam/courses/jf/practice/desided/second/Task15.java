package com.epam.courses.jf.practice.desided.second;

import com.epam.courses.jf.practice.common.second.I2DPoint;
import com.epam.courses.jf.practice.common.second.ITestableTask15;
import com.epam.courses.jf.practice.common.second.ITestableTask16;

import java.awt.*;
import java.io.*;
import java.util.HashSet;
import java.util.LinkedList;
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

        for (I2DPoint point1 : points) {
            Set<I2DPoint> setLines = new HashSet<>();
            double x1 = point1.getX();
            double y1 = point1.getY();

            for (I2DPoint point2 : points) {
                double x2 = point2.getX();
                double y2 = point2.getY();

                if (x1 != x2 || y1 != y2) {
                    for (I2DPoint point3 : points) {
                        double x3 = point3.getX();
                        double y3 = point3.getY();
                        if ((x3 - x1) / (x2 - x1) == (y3 - y1) / (y2 - y1)) {
                            setLines.add(point1);
                            setLines.add(point2);
                            setLines.add(point3);
                        }
                    }
                    lines.add(new Line(setLines));
                }
            }
            if (setLines.size() > 2) {
                lines.add(new Line(setLines));
            }
        }


        return new FileWithLines(output, lines);
    }


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


    private class FileWithLines implements IFileWithLines {

        private File file;

        private FileWithLines(File file) {
            this.file = file;
        }

        private FileWithLines(File file, Set<ILine> lines) {
            this(file);
            writeLinesToFile(lines);
        }


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
}