package com.epam.courses.jf.practice.SigarevVL.second;

import com.epam.courses.jf.practice.common.second.I2DPoint;
import com.epam.courses.jf.practice.common.second.ITestableTask17;

import java.util.HashSet;
import java.util.Set;

/**
 * There are N segments on the plane.
 * finds a point (possibly several) of the intersection
 * of two segments, having a minimal abscissa.
 */
public class Task17 implements ITestableTask17 {

    /**
     * Carries out the analysis of the transferred segments.
     *
     * @param segments The set of Line segments.
     * @return A set of intersection points having minimal abscissa.
     */
    @Override
    public Set<I2DPoint> analyze(Set<ISegment> segments) {

        Set<I2DPoint> crossPointsSet = new HashSet<>();
        double minX = 0.0;
        boolean firstTime = true;

        for (ISegment segment : segments) {
            for (ISegment anotherSegment : segments) {
                if (!segment.equals(anotherSegment)) {
                    double x1 = segment.first().getX();
                    double y1 = segment.first().getY();
                    double x2 = segment.second().getX();
                    double y2 = segment.second().getY();
                    double x3 = anotherSegment.first().getX();
                    double y3 = anotherSegment.first().getY();
                    double x4 = anotherSegment.second().getX();
                    double y4 = anotherSegment.second().getY();

                    double divider = (y4 - y3) * (x2 - x1)
                            - (x4 - x3) * (y2 - y1);

                    double numenatorA = (x4 - x3) * (y1 - y3)
                            - (y4 - y3) * (x1 - x3);

                    double numenatorB = (x2 - x1) * (y1 - y3)
                            - (y2 - y1) * (x1 - x3);

                    if (divider == 0) {
                        continue;
                    }

                    double kA = numenatorA / divider;
                    double kB = numenatorB / divider;

                    if (0 <= kA && kA <= 1 && 0 <= kB && kB <= 1) {
                        double X = x1 + kA * (x2 - x1);
                        double Y = y1 + kA * (y2 - y1);

                        if (firstTime) {
                            minX = X;
                            crossPointsSet.add(new I2DPointImpl(X, Y));
                            firstTime = false;
                        } else if (minX > X) {
                            minX = X;
                            crossPointsSet.clear();
                            crossPointsSet.add(new I2DPointImpl(X, Y));
                        } else if (minX == X) {
                            crossPointsSet.add(new I2DPointImpl(X, Y));
                        }
                    }
                }
            }
        }
        return crossPointsSet;
    }

    /**
     * Line segment.
     */
    public class ISegmentImpl implements ISegment {

        private I2DPoint firstPoint;
        private I2DPoint secondPoint;

        public ISegmentImpl(I2DPoint firstPoint, I2DPoint secondPoint) {
            this.firstPoint = firstPoint;
            this.secondPoint = secondPoint;
        }

        /**
         * @return The first point of the segment.
         */
        @Override
        public I2DPoint first() {
            return firstPoint;
        }

        /**
         * @return The second point of the segment.
         */
        @Override
        public I2DPoint second() {
            return secondPoint;
        }
    }
}
