package com.epam.courses.jf.practice.vakhonin.second;

import com.epam.courses.jf.practice.common.second.I2DPoint;
import com.epam.courses.jf.practice.common.second.ITestableTask15;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * На плоскости задано N точек.
 * Вывести в файл описания всех прямых, которые проходят более чем через 2 точки из заданных.
 */

public class Task15 implements ITestableTask15{

    /**
     * Точка, заданная координатами X, Y.
     */

    class Point implements I2DPoint{
        private double x,y;

        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public double getX() {
            return x;
        }

        @Override
        public double getY() {
            return y;
        }
    }

    /**
     * Прямая, заданная точками, входящими в исходное множество.
     */

    class Line implements ILine{
        private Set<I2DPoint> pointsSet;

        Line(Set<I2DPoint> pointsSet) {
            this.pointsSet = pointsSet;
        }

        /** @return Точки, через которые проходит прямая */

        @Override
        public Set<I2DPoint> getPoints() {
            return pointsSet;
        }
    }

    /**
     * Представляет файл, содержащий информацию о найденных линиях.
     */

    class FileWithLines implements IFileWithLines {
        private File file;

        FileWithLines (Set<ILine> lines, File file) {
            this.file = file;
            writeLines(lines);
        }

        /**
         * @return Файл с результатами анализа.
         */

        @Override
        public File getFile() {
            return file;
        }

        /**
         * Осуществляет запись линий в file
         * @param lines Множество линий.
         */

        public void writeLines(Set<ILine> lines) {
            BufferedWriter bw = null;

            try {
                bw = new BufferedWriter(new FileWriter(file));
                for (ILine line : lines) {
                    for (I2DPoint point : line.getPoints()) {
                        bw.write("" + point.getX() + " " + point.getY() + " ");
                    }
                    bw.write("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
              finally {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        /**
         * Извлекает из файла информацию о хранящихся в нем линиях.
         * @return Множество линий, найденных в результате анализа.
         */

        @Override
        public Set<ILine> getLines() {
            Set<ILine> linesSet = new HashSet<>();
            String s;
            String[] strPoints;
            Set<I2DPoint> pointsSet;
            I2DPoint point;

//            FileReader fr = null;
            BufferedReader br = null;

//            try {
//                FileReader fr = new FileReader(file);
//                BufferedReader br = new BufferedReader(fr);
//
//                while ((s = br.readLine()) != null) {
//                    strPoints = s.split("\\s");
//                    pointsSet = new HashSet<>();
//
//                    for (int j = 0; j < strPoints.length; j += 2) {
//                        point = new Point(Double.parseDouble(strPoints[j]), Double.parseDouble(strPoints[j + 1]));
//                        pointsSet.add(point);
//                    }
//
//                    linesSet.add(new Line(pointsSet));
//                }
//
//                return linesSet;
            try {
//                FileReader fr = new FileReader(file);
                br = new BufferedReader(new FileReader(file));

                while ((s = br.readLine()) != null) {
                    strPoints = s.split("\\s");
                    pointsSet = new HashSet<>();

                    for (int j = 0; j < strPoints.length; j += 2) {
                        point = new Point(Double.parseDouble(strPoints[j]), Double.parseDouble(strPoints[j + 1]));
                        pointsSet.add(point);
                    }

                    linesSet.add(new Line(pointsSet));
                }
                return linesSet;
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                try{
//                    fr.close();
                    br.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
            return null;
        }
    }

    /**
     * Осуществляет анализ переданных точек, вычисляя линии, которые проходят более чем через 2 точки.
     * @param points Множество точек на плоскости.
     * @param output Файл для вывода результатов.
     * @return Файл с результатами анализа.
     */

    @Override
    public IFileWithLines analyze(Set<I2DPoint> points, File output) {
        Set<I2DPoint> pointsSet;
        List<I2DPoint> pointsList = new ArrayList<>(points);
        Set<ILine> linesSet = new HashSet<>();
        int size = pointsList.size();
        double jX, jY, kX, kY, mX, mY;

        for (int j = 0; j < size; j++) {
            pointsSet = new HashSet<>();
            jX = pointsList.get(j).getX();
            jY = pointsList.get(j).getY();

            for (int k = j+1; k < size; k++) {
                kX = pointsList.get(k).getX();
                kY = pointsList.get(k).getY();

                for (int m = k+1; m < size; m++) {
                    mX = pointsList.get(m).getX();
                    mY = pointsList.get(m).getY();

                    if((kX-mX) * (kY - jY) == (jX - kX) * (mY - kX)){
                        pointsSet.add(pointsList.get(j));
                        pointsSet.add(pointsList.get(k));
                        pointsSet.add(pointsList.get(m));
                        linesSet.add(new Line(pointsSet));
                    }
                }
            }
        }

        return new FileWithLines(linesSet, output);
    }
}
