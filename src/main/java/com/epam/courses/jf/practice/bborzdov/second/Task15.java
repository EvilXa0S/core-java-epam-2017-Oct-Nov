package com.epam.courses.jf.practice.bborzdov.second;

import com.epam.courses.jf.practice.common.second.I2DPoint;
import com.epam.courses.jf.practice.common.second.ITestableTask15;

import java.io.*;
import java.util.*;

/**
 * Created by bogdan on 21.11.17.
 */

/**
 * На плоскости задано N точек.
 * Вывести в файл описания всех прямых, которые проходят более чем через 2 точки из заданных.
 */
public class Task15 implements ITestableTask15 {

    /**
     * Осуществляет анализ переданных точек, вычисляя линии, которые проходят более чем через 2 точки.
     * @param points Множество точек на плоскости.
     * @param output Файл для вывода результатов.
     * @return Файл с результатами анализа.
     */
    @Override
    public IFileWithLines analyze(Set<I2DPoint> points, File output) {
        FileWithLines fileWithLines = new FileWithLines(output);
        if(points.size() < 3){
            return fileWithLines;
        }
        Set<Set<I2DPoint>> lines = new HashSet<>();
        for (I2DPoint point1 : points) {
            for (I2DPoint point2 : points) {
                if(point1.equals(point2)){
                    continue;
                }
                for (I2DPoint point3 : points) {
                    if(point2.equals(point3) || point1.equals(point3)){
                        continue;
                    }
                    if(lines.contains(new HashSet<>(Arrays.asList(point1,point2,point3)))){
                        continue;
                    }
                    List<Double> distances = Arrays.asList(distance(point1,point2),
                            distance(point1,point3),distance(point2,point3));
                    Collections.sort(distances);
                    if(distances.get(0) + distances.get(1) == distances.get(2)){
                        lines.add(new HashSet<>(Arrays.asList(point1,point2,point3)));
                    }
                }
            }
        }
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(output));
            objectOutputStream.write(lines.size());
            for (Set<I2DPoint> line : lines) {
                Object[] pointsAr = line.toArray();
                for (Object point : pointsAr) {
                    objectOutputStream.writeObject(point);
                }
            }
            objectOutputStream.close();
        }catch (IOException e){
            System.out.println("Fail when writing to file\n");
            e.printStackTrace();
        }

        return fileWithLines;
    }

    /**
     * Считает расстояние между двумя точками
     * @param a - первая точка
     * @param b - вторая точка
     * @return расстояние между двумя точками
     */
    static double distance(I2DPoint a, I2DPoint b){
        double x = a.getX() - b.getX();
        double y = a.getY() - b.getY();
        double res = Math.abs(Math.pow(x,2) + Math.pow(y,2));
        return Math.sqrt(res);
    }
    /**
     * Представляет файл, содержащий информацию о найденных линиях.
     */
    private class FileWithLines implements IFileWithLines{
        private File output;

        private FileWithLines(File output){
            this.output = output;
        }

        /**
         * @return Файл с результатами анализа.
         */
        @Override
        public File getFile() {
            return output;
        }

        /**
         * Извлекает из файла информацию о хранящихся в нем линиях.
         * @return Множество линий, найденных в результате анализа.
         */
        @Override
        public Set<ILine> getLines() {
            Set<ILine> lines = new HashSet<>();
            try{
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(output));
                int linesCount = ois.read();
                for (int i = 0; i < linesCount; i++) {
                    List<I2DPoint> line = new LinkedList<>();
                    for (int j = 0; j < 3; j++) {
                        line.add((I2DPoint) ois.readObject());
                    }
                    lines.add(new Line(new HashSet<>(line)));
                }
                ois.close();
            }catch (IOException | ClassNotFoundException e){
                System.out.println("Fail when reading from file\n");
                e.printStackTrace();
            }
            return lines;
        }
    }
    /**
     * Прямая, заданная точками, входящими в исходное множество.
     */
    private class Line implements ILine{
        private Set<I2DPoint> points;
        private Line(Set<I2DPoint> points){
            this.points = points;
        }

        /**
         *  @return Точки, через которые проходит прямая
         */
        @Override
        public Set<I2DPoint> getPoints() {
            return points;
        }
    }
}
