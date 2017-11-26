package com.epam.courses.jf.practice.vakhonin.second;

import com.epam.courses.jf.practice.common.second.I2DPoint;
import com.epam.courses.jf.practice.common.second.ITestableTask15;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by igorvahonin on 13.11.17.
 */
public class Task15 implements ITestableTask15{




    class Line implements ILine{

        Set<I2DPoint> pointsSet;

        Line (Set<I2DPoint> pointsSet) {
            this.pointsSet = pointsSet;
        }

        @Override
        public Set<I2DPoint> getPoints() {
            return pointsSet;
        }
    }

    class FileWithLines implements IFileWithLines {

        private File file;

        FileWithLines (Set<ILine> lines, File file) {
            this.file = file;
            putLines(lines);
        }

        @Override
        public File getFile() {
            return file;
        }


        public void putLines(Set<ILine> lines) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (ILine line : lines) {
                    for (I2DPoint point : line.getPoints()) {
                        writer.write("" + point.getX());
                        writer.write(" ");
                        writer.write("" + point.getY());
                        writer.write("\t");
                    }
                    writer.write("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public Set<ILine> getLines() {
            Set<ILine> lines = new HashSet<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))){
                String string;
                while ((string = reader.readLine()) != null) {
                    String[] row = string.split("\\s");
                    Set<I2DPoint> currentPoints = new HashSet<>();
                    for (int i = 0; i < row.length; i+=2) {
                        Point currentPoint = new Point(Double.parseDouble(row[i]), Double.parseDouble(row[i+1]));
                        currentPoints.add(currentPoint);
                    }
                    lines.add(new Line(currentPoints));
                }
                return lines;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return lines;
        }
    }

    class Point implements I2DPoint{

        private double x,y;

        Point(double x, double y){
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


    @Override
    public IFileWithLines analyze(Set<I2DPoint> points, File output) {

        List<I2DPoint> pointsList = new ArrayList<>(points);
        Set<I2DPoint> pointsSet;
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
