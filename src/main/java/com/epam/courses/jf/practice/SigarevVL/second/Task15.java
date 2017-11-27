package com.epam.courses.jf.practice.SigarevVL.second;

import com.epam.courses.jf.practice.common.second.I2DPoint;
import com.epam.courses.jf.practice.common.second.ITestableTask15;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * There are N points on the plane.
 * Outputs in the file descriptions of all the lines that pass more
 * than 2 points from the specified.
 */
public class Task15 implements ITestableTask15 {

    /**
     * Performs analysis of the transmitted points,
     * calculating lines that pass more than 2 points.
     *
     * @param points The set of points on the plane.
     * @param output File for outputting results.
     * @return File with the results of the analysis.
     */
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

            //((x3 - x1) * (y2 - y1) == (x2 - x1) * (y3 - y1))
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
     * Represents a file containing information about found lines.
     */
    public class IFileWithLinesImpl implements IFileWithLines {

        private File file;
        private Set<ILine> set;

        public IFileWithLinesImpl(File file, Set<ILine> set) {
            this.file = file;
            this.set = new HashSet<>(set);
        }

        /**
         * @return File with the results of the analysis.
         */
        @Override
        public File getFile() {
            return file;
        }

        /**
         * Extracts from the file information about the lines.
         *
         * @return The set of lines found after the analysis.
         */
        @Override
        public Set<ILine> getLines() {
            return set;
        }
    }

    /**
     * A straight line defined by points in the original set.
     */
    public class ILineImpl implements ILine {

        Set<I2DPoint> setLinePoint = new HashSet<>();

        public ILineImpl(I2DPoint firstPoint, I2DPoint secondPoint) {

            setLinePoint.add(firstPoint);
            setLinePoint.add(secondPoint);
        }

        /**
         * @return Points through which the straight line passes.
         */
        @Override
        public Set<I2DPoint> getPoints() {
            return setLinePoint;
        }

    }
}
