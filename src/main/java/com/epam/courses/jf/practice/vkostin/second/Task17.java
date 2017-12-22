package com.epam.courses.jf.practice.vkostin.second;

import com.epam.courses.jf.practice.common.second.I2DPoint;
import com.epam.courses.jf.practice.common.second.ITestableTask17;

import java.util.*;

public class Task17 implements ITestableTask17 {

    private static TwoDPoint findIntersection(ISegment line1, ISegment line2) {
        /**
         * Search for the point of intersection of two straight lines.
         */
        if ((line1.first().getX() == line2.first().getX())
                && (line1.first().getY() == line2.first().getY())) {
            return new TwoDPoint(line1.first().getX(), line1.first().getY());
        }
        if ((line1.first().getX() == line2.second().getX())
                && (line1.first().getY() == line2.second().getY())) {
            return new TwoDPoint(line1.first().getX(), line1.first().getY());
        }
        if ((line1.second().getX() == line2.first().getX())
                && (line1.second().getY() == line2.first().getY())) {
            return new TwoDPoint(line1.second().getX(), line1.second().getY());
        }
        if ((line1.second().getX() == line2.second().getX())
                && (line1.second().getY() == line2.second().getY())) {
            return new TwoDPoint(line1.second().getX(), line1.second().getY());
        }

        double[] coefsLine1 = getLineEquation(line1.first(), line1.second());
        double[] coefsLine2 = getLineEquation(line2.first(), line2.second());

        double x = (coefsLine1[1] - coefsLine2[1])
                / (coefsLine2[0] - coefsLine1[0]);
        double y = coefsLine1[0] * x + coefsLine1[1];
//
        return new TwoDPoint(x, y);
    }

    private static double[] getLineEquation(I2DPoint pA, I2DPoint pB) {
        // y = [0]*x + [1];
        double[] coefficients = new double[2];

        coefficients[0] = (pB.getY() - pA.getY()) / (pB.getX() - pA.getX());
        coefficients[1] = pA.getY() - pA.getX() * coefficients[0];

        return coefficients;
    }


    @Override
    public Set<I2DPoint> analyze(Set<ISegment> segments) {

        Set<I2DPoint> pointsWithMinAbscissa = new HashSet<>();
        // abscissas of all points of intersection
        Map<Double,ArrayList<TwoDPoint>> abscissas = new TreeMap<>();

        List<ISegment> segmArray = new ArrayList<>(segments);

        for (int i = 0; i < segmArray.size(); ++i) {
            for (int j = i + 1; j < segmArray.size(); ++j) {
                if (!segmArray.get(i).equals(segmArray.get(j))) {
                    // current intersection point
                    TwoDPoint curPoint = findIntersection(segmArray.get(i), segmArray.get(j));

                    double limit1XExt = (segmArray.get(i)).first().getX();
                    double limit1YExt = (segmArray.get(i)).first().getY();
                    double limit2XExt = (segmArray.get(i)).second().getX();
                    double limit2YExt = (segmArray.get(i)).second().getY();

                    double limit1XInt = (segmArray.get(j)).first().getX();
                    double limit1YInt = (segmArray.get(j)).first().getY();
                    double limit2XInt = (segmArray.get(j)).second().getX();
                    double limit2YInt = (segmArray.get(j)).second().getY();

                    if ((Math.min(limit1XExt, limit2XExt) <= curPoint.getX())
                            && (curPoint.getX() <= Math.max(limit1XExt, limit2XExt))
                            && ((Math.min(limit1YExt, limit2YExt) <= curPoint.getY())
                            && (curPoint.getY() <= Math.max(limit1YExt, limit2YExt)))) {

                        if ((Math.min(limit1XInt, limit2XInt) <= curPoint.getX())
                                && (curPoint.getX() <= Math.max(limit1XInt, limit2XInt))
                                && ((Math.min(limit1YInt, limit2YInt) <= curPoint.getY())
                                && (curPoint.getY() <= Math.max(limit1YInt, limit2YInt)))) {

                            if (abscissas.containsKey(curPoint.getX())) {
                                abscissas.get(curPoint.getX()).add(curPoint);
                            } else {
                                abscissas.put(curPoint.getX(), new ArrayList<>());
                                abscissas.get(curPoint.getX()).add(curPoint);
                            }
                        }
                    }
                }
            }
        }

        for (Double aDouble : abscissas.keySet()) {
            for (TwoDPoint point : abscissas.get(aDouble)) {
                System.out.println(point);
                    if (abscissas.get(aDouble).contains(point)) {
                        pointsWithMinAbscissa.add(point);
                }
            }
            break;
        }

        return pointsWithMinAbscissa;
    }


    private static class Segment implements ISegment {

        private I2DPoint first;
        private I2DPoint second;

        Segment(I2DPoint first, I2DPoint second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public I2DPoint first() {
            return first;
        }

        @Override
        public I2DPoint second() {
            return second;
        }
    }
}
