package com.epam.courses.jf.practice.nbikbaev.second;

import com.epam.courses.jf.practice.common.second.I2DPoint;
import com.epam.courses.jf.practice.common.second.ITestableTask15;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Task15 implements ITestableTask15 {

    @Override
    public IFileWithLines analyze(Set<I2DPoint> points, File output) {
        Set<ILine> lines = new HashSet<>();
        for (I2DPoint point1 : points) {
            for (I2DPoint point2 : points) {
                if (point1.equals(point2)) {
                    continue;
                }
                lines.add(new Line(point1, point2));
            }
            for (ILine line : lines) {
                for (I2DPoint testingPoint : points) {
                    if (line.getPoints().contains(testingPoint)) {
                        continue;
                    }
                    if (isBelongsToLine(testingPoint, line)) {
                        line.getPoints().add(testingPoint);
                    }
                }
            }
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(output))) {
            for (ILine line : lines) {
                writer.write(line.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new FileWithLines(output, lines);
    }

    private class FileWithLines implements IFileWithLines {

        private File file;
        private Set<ILine> lines;

        public FileWithLines(File file, Set<ILine> lines) {
            this.file = file;
            this.lines = lines;
        }

        @Override
        public File getFile() {
            return file;
        }

        @Override
        public Set<ILine> getLines() {
            return lines;
        }
    }

    private class Line implements ILine {

        private Set<I2DPoint> points;

        public Line(I2DPoint point1, I2DPoint point2) {
            this.points = new HashSet<>();
            points.add(point1);
            points.add(point2);
        }

        @Override
        public Set<I2DPoint> getPoints() {
            return points;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Line)) return false;
            Line line = (Line) o;
            return Objects.equals(points, line.points);
        }

        @Override
        public int hashCode() {
            return Objects.hash(points);
        }

        @Override
        public String toString() {
            return "Line{" +
                    "points=" + points +
                    '}';
        }
    }

    private boolean isBelongsToLine(I2DPoint testingPoint, ILine line) {
        List<I2DPoint> pointList = new ArrayList<>(line.getPoints());
        I2DPoint point1 = pointList.get(0);
        I2DPoint point2 = pointList.get(1);
        return (testingPoint.getY() - point1.getY()) -
                ((point2.getY() - point1.getY()) / (point2.getX() - point1.getX())
                        * (testingPoint.getX() - point1.getX())) == 0;
    }


}
