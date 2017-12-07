package com.epam.courses.jf.practice.akunats.second;

import com.epam.courses.jf.practice.common.second.I2DPoint;
import com.epam.courses.jf.practice.common.second.ITestableTask15;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Task15 implements ITestableTask15 {
    @Override
    public IFileWithLines analyze(Set<I2DPoint> points, File output) {
        List<I2DPoint> previousStep = new ArrayList<>(); // Исключенные точки из перебора
        Set<ILine> lines = new HashSet<>();

        Iterator<I2DPoint> iter = points.iterator();
        while (iter.hasNext()) { // первый шаг
            I2DPoint point = iter.next();

            Set<I2DPoint> interList = new HashSet<>(); // Исключенные элементы из выборки на втором шаге

            for (I2DPoint pointForSearchEquation : points) { // второй шаг
                Set<I2DPoint> interSet = new HashSet<>();
                if (!pointForSearchEquation.equals(point)) {
                    // Считаем коэффициенты уравнение прямой Ax + By + C = 0
                    double A = pointForSearchEquation.getY() - point.getY();
                    double B = pointForSearchEquation.getX() - point.getX();
                    double C = point.getY() * (pointForSearchEquation.getX() - point.getX())
                            - point.getX() * (pointForSearchEquation.getY() - point.getY());
                    int countTrue = 0; // Подсчет колическтва точек удовлетворяющих уравнению
                    for (I2DPoint pointForSearch : points) {
                        if (!interList.contains(pointForSearch)) {
                            if (previousStep.size() == 0) {
                                if (!pointForSearch.equals(point) // Элемент не равный точке с первого шага
                                        && !pointForSearch.equals(pointForSearchEquation) // Элемент не равный точке со второго шага
                                        && (A * pointForSearch.getX() - B * pointForSearch.getY() + C) == 0) { // Лежит ли точка на прямой
                                    interSet.add(pointForSearch); // точка для добавления в ILine
                                    interList.add(pointForSearch); // исключаем элемент из поиска
                                    countTrue++;
                                }
                            } else {
                                for (I2DPoint p : previousStep) { // Перебираем обработанные элементы
                                    // Ограничение выборки
                                    if ((A * p.getX() - B * p.getY() + C) != 0) { // обработанные точки не должны лежать на прямой, для предотвращения дублирования
                                        if (!pointForSearch.equals(point)// Элемент не равный точке с первого шага
                                                && !pointForSearch.equals(pointForSearchEquation)// Элемент не равный точке со второго шага
                                                && (A * pointForSearch.getX() - B * pointForSearch.getY() + C) == 0) {// Лежит ли точка на прямой
                                            interSet.add(pointForSearch);
                                            interList.add(pointForSearch);
                                            countTrue++;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (countTrue > 0) { // если точек больше 2 то все ОК
                        interSet.add(point);
                        interSet.add(pointForSearchEquation);
                    }
                    interList.add(pointForSearchEquation);// Исключаем элемент из проверки
                }
                if (!interSet.isEmpty()) {
                    lines.add(new Line(interSet)); // добавляем элементы в коллекцию результата
                }
            }
            previousStep.add(point);// Исключаем элемент из проверки
            iter.remove();//удаляем исключенный элемент
        }
        return new IFileWithLinesImpl(lines, output);
    }

    private class IFileWithLinesImpl implements IFileWithLines {
        private File file;
        Set<ILine> lines;

        IFileWithLinesImpl(Set<ILine> points, File file) {
            this.file = file;
            this.lines = points;
            try (PrintWriter writer = new PrintWriter(file)) {
                for (ILine line : points) {
                    for (I2DPoint point : line.getPoints()) {
                        writer.write(point.getX() + " " + point.getY() + "\t");
                    }
                    writer.write("\n");
                }
            } catch (IOException e) {
                System.out.println("Error write file.");
            }
        }

        @Override
        public File getFile() {
            return file;
        }

        @Override
        public Set<ILine> getLines() {
            Set<ILine> result = new HashSet<>();
            try {
                List<String> interList = Files
                        .lines(Paths.get(file.getPath()), Charset.forName("ISO-8859-1"))
                        .collect(Collectors.toList());
                for (String s : interList) {
                    Set<I2DPoint> interSet = new HashSet<>();
                    String[] interMas = s.split("\t");
                    for (String inter : interMas) {
                        String[] nextMas = inter.split(" ");
                        interSet.add(new Point(Double.parseDouble(nextMas[0]), Double.parseDouble(nextMas[1])));
                    }
                    result.add(new Line(interSet));
                }
            } catch (Exception e) {
                System.out.println("Error read file");
                return null;
            }
            return result;
        }
    }

    private class Line implements ILine {
        Set<I2DPoint> set = new HashSet<>();

        Line(Set<I2DPoint> set) {
            this.set = set;
        }

        @Override
        public Set<I2DPoint> getPoints() {
            return set;
        }
    }
}
