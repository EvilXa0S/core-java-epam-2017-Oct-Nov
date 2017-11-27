package com.epam.courses.jf.practice.vkostin.second;

import com.epam.courses.jf.practice.common.second.I2DPoint;
import com.epam.courses.jf.practice.common.second.ITestableTask15;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Task15 implements ITestableTask15 {

    @Override
    public IFileWithLines analyze(Set<I2DPoint> points, File output) {
        Set<ILine> lines = new HashSet<>();

        for (I2DPoint firstPoint : points){
            Set<I2DPoint> currentSet = new HashSet<>();
            double x1 = firstPoint.getX();
            double y1 = firstPoint.getY();

            for (I2DPoint secondPoint : points){
                double x2 = secondPoint.getX();
                double y2 = secondPoint.getY();

                if((x1 != x2) || (y1 != y2)){

                    for (I2DPoint thirdPoint : points){
                        double x3 = thirdPoint.getX();
                        double y3 = thirdPoint.getY();

                        if(((x1 != x3) || (y1 != y3)) && ((x2 != x3) || (y2 != y3))) {

                            if (((x1 - x3) / (x2 - x3) == (y1 - y3) / (y2 - y3))
                                    || ((x1 == x2) && (x2 == x3))) {

                                currentSet.add(firstPoint);
                                currentSet.add(secondPoint);
                                currentSet.add(thirdPoint);
                            }
                        }
                    }
                }
            }

            if (!currentSet.isEmpty()) {
                ILine currentLine = new Line(currentSet);
                boolean flag = true;

                for (ILine line : lines) {
                    if (line.equals(currentLine)) {
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    lines.add(currentLine);
                }
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(output))) {
            for (ILine line : lines) {
                writer.write("" + line);
                writer.newLine();
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return new FileWithLines(output,lines);
    }


    class FileWithLines implements IFileWithLines {

        private File file;
        private Set<ILine> lineSet;

        FileWithLines(File file, Set<ILine> lineSet) {
            this.file = file;
            this.lineSet = lineSet;
        }

        @Override
        public File getFile() {
            return file;
        }

        @Override
        public Set<ILine> getLines() {
            return lineSet;
        }
    }


    class Line implements ILine {

        private Set<I2DPoint> points;

        Line(Set<I2DPoint> points) {
            this.points = points;
        }

        @Override
        public Set<I2DPoint> getPoints() {
            return points;
        }

        @Override
        public String toString() {
            return "Line [Points: " + points + "];";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Line line = (Line) o;
            if(this.getPoints().size() == line.getPoints().size()){
                Set<I2DPoint> points = this.getPoints();
                Set<I2DPoint> point = line.getPoints();
                for(I2DPoint thispoint : points){
                    if (!point.contains(thispoint)){
                        return false;
                    }
                }
                return true;
            } else {
                return false;
            }
        }
    }
}
