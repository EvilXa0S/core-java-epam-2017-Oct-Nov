package com.epam.courses.jf.practice.hkryzhik.second;

import com.epam.courses.jf.practice.common.second.I2DPoint;
import com.epam.courses.jf.practice.common.second.ITestableTask15;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class Task15 implements ITestableTask15 {

    private class Line implements ITestableTask15.ILine {

        private Double a;

        private Double b;

        private HashSet<I2DPoint> points;

        public Line(I2DPoint first, I2DPoint second) {

            this.a = (first.getY() - second.getY())/(first.getX() - second.getX());

            this.b = (second.getY() * first.getX() - first.getY() * second.getX())/(first.getX() - second.getX());

            points = new HashSet<>();

            points.add(first);

            points.add(second);
        }

        @Override
        public Set<I2DPoint> getPoints() {
            return points;
        }

        public boolean contains(I2DPoint point){

            if (a * point.getX() + b == point.getY()) return true;
            return false;
        }

        public void addPoint(I2DPoint point){
            points.add(point);
        }
    }

    private class FileWithLines implements ITestableTask15.IFileWithLines{

        private File file;

        private Set<ILine> lines;

        public FileWithLines(File file) {

            lines = new HashSet<>();

            this.file = file;
        }

        public void addLine(Line line){

            lines.add(line);
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

    @Override
    public IFileWithLines analyze(Set<I2DPoint> points, File output) {

        HashSet<Line> lines = new HashSet<>();

        FileWithLines file = new FileWithLines(output);

        points.stream().forEach((p1) -> {

            points.stream().forEach((p2) -> {

                if (!p1.equals(p2)){

                    lines.add(new Line(p1, p2));
                }
            });
        });

        lines.stream().forEach((l) -> {

            points.stream().forEach((p) -> {

                if (l.contains(p)) l.addPoint(p);
            });

            if (l.getPoints().size() > 2) file.addLine(l);
        });

        return file;
    }
}
