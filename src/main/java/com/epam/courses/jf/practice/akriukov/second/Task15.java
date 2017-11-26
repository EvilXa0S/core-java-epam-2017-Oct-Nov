package com.epam.courses.jf.practice.akriukov.second;

import com.epam.courses.jf.practice.common.second.I2DPoint;
import com.epam.courses.jf.practice.common.second.ITestableTask15;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Task15 implements ITestableTask15{

    @Override
    public IFileWithLines analyze(Set<I2DPoint> points, File output) {
        Set<ILine> setLines = new HashSet<>();

        for (I2DPoint point : points) {
            for (I2DPoint anotherPoint : points) {
                if (!point.equals(anotherPoint)) {
                    setLines.add(new ILineImpl(point, anotherPoint));
                }
            }
        }

        for (ILine line : setLines) {
            List<I2DPoint> linePoints = new ArrayList<>(line.getPoints());
            double x1 = linePoints.get(0).getX();
            double x2 = linePoints.get(1).getX();
            double y1 = linePoints.get(0).getY();
            double y2 = linePoints.get(1).getY();

            for (I2DPoint point : points) {
                if ((point.getX() - x1) * (y2 - y1)
                        == (x2 - x1) * (point.getY() - y1)) {
                    line.getPoints().add(point);

                }
            }
        }

        return new IFileWithLinesImpl(output, setLines);
    }

    /**
     * File with info of found lines
     */
    public class IFileWithLinesImpl implements IFileWithLines {

        private File file;
        private Set<ILine> set;

        public IFileWithLinesImpl(File file, Set<ILine> set) {
            this.file = file;
            this.set = new HashSet<>(set);
        }

        /**
         * @return file with analyzed result
         */
        @Override
        public File getFile() {
            return file;
        }

        /**
         * Extracts info of stored lines
         * @return found lines
         */
        @Override
        public Set<ILine> getLines() {
            return set;
        }
    }

    /**
     * Line parametrised with 2 points from set
     */
    public class ILineImpl implements ILine {

        Set<I2DPoint> setLinePoint = new HashSet<>();

        public ILineImpl(I2DPoint firstPoint, I2DPoint secondPoint) {

            setLinePoint.add(firstPoint);
            setLinePoint.add(secondPoint);
        }

        /**
         * @return Points which parametrize line
         */
        @Override
        public Set<I2DPoint> getPoints() {
            return setLinePoint;
        }
    }
}
