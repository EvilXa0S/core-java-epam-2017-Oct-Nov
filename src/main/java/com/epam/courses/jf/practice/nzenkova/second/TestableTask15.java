package com.epam.courses.jf.practice.nzenkova.second;

import com.epam.courses.jf.practice.common.second.I2DPoint;
import com.epam.courses.jf.practice.common.second.ITestableTask15;

import java.io.*;
import java.util.HashSet;
import java.util.Set;


public class TestableTask15 implements ITestableTask15 {
    @Override
    public IFileWithLines analyze(Set<I2DPoint> points, File output) {
        FileWithLines lines = new FileWithLines(output, points);

        try (BufferedWriter out = new BufferedWriter(new FileWriter(output.getAbsoluteFile()))) {
            for (I2DPoint point : points) {
                for (I2DPoint iPoint : points) {
                    out.write(lines.writeLine(point, iPoint));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    class FileWithLines implements ITestableTask15.IFileWithLines {
        private File output;
        private Set<I2DPoint> points;

        FileWithLines(File output, Set<I2DPoint> points) {
            this.output = output;
            this.points = points;
        }

        public Set<I2DPoint> getPoints() {
            return points;
        }

        @Override
        public File getFile() {
            return output;
        }

        @Override
        public Set<ITestableTask15.ILine> getLines() {
            Set<ITestableTask15.ILine> lines = new HashSet<>();

            try (BufferedReader in = new BufferedReader(new FileReader(output.getAbsoluteFile()))) {
                String line;
                while ((line = in.readLine()) != null) {
                    String[] data = line.split(" ");
                    ITestableTask15.ILine iLine = new Line(Double.parseDouble(data[0]), Double.parseDouble(data[1]), Double.parseDouble(data[2]), points);
                    if (iLine.getPoints().size() > 2) lines.add(iLine);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return lines;
        }

        public String writeLine(I2DPoint point1, I2DPoint point2) {
            StringBuilder result = new StringBuilder();
            //ay=bx+c
            double den = point2.getX() - point1.getX();

            if (Double.compare(den, 0d) == 0) {
                result.append("0 ");
                result.append("1 ");
                result.append(-point1.getX());
            } else {
                result.append("1 ");
                double k = (point2.getY() - point1.getY()) / den;
                result.append(k).append(" ");
                result.append(point1.getY() - k * point1.getX());
            }
            result.append("\n");
            return result.toString();
        }
    }

    class Line implements ITestableTask15.ILine {
        double a;
        double b;
        double c;
        Set<I2DPoint> points;

        //ay=bx+c
        public Line(double a, double b, double c, Set<I2DPoint> points) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.points = points;
        }

        @Override
        public Set<I2DPoint> getPoints() {
            Set<I2DPoint> resPoints = new HashSet<>();
            for (I2DPoint point : points) {
                if (isEqually(a * point.getY(), point.getX() * b + c)) resPoints.add(point);
            }
            return resPoints;
        }

        private boolean isEqually(double a, double b) {
            return Math.abs(a - b) < 0.00000000001d;
        }
    }
}

