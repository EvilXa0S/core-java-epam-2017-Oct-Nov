package com.epam.courses.jf.practice.asunyaev.second;

import com.epam.courses.jf.practice.common.second.I2DPoint;
import com.epam.courses.jf.practice.common.second.ITestableTask17;

import java.util.*;

public class Task17 implements ITestableTask17 {

    @Override
    public Set<I2DPoint> analyze(Set<ISegment> segments) {

        SortedMap<Double, Set<I2DPoint>> intersectionPoints = new TreeMap<>();

        for (ISegment segment1 : segments) {
            for (ISegment segment2 : segments) {
                if(segment1.equals(segment2)) {
                    continue;
                }
                I2DPoint currentPoint = getIntersectionPoint(segment1, segment2);
                if (currentPoint == null) {
                    continue;
                }
                double currentX = currentPoint.getX();
                if (!intersectionPoints.containsKey(currentX)) {
                    Set<I2DPoint> points = new HashSet<>();
                    points.add(currentPoint);
                    intersectionPoints.put(currentX, points);
                } else {
                    intersectionPoints.get(currentX).add(currentPoint);
                }
            }
        }

        return intersectionPoints.get(intersectionPoints.firstKey());
    }


    private I2DPoint getIntersectionPoint(ISegment segment1, ISegment segment2) {
        I2DPoint p1 = segment1.first();
        I2DPoint p2 = segment1.second();
        I2DPoint p3 = segment2.first();
        I2DPoint p4 = segment2.second();

        double x1 = segment1.first().getX();
        double x2 = segment1.second().getX();
        double y1 = segment1.first().getY();
        double y2 = segment1.second().getY();

        double x3 = segment2.first().getX();
        double x4 = segment2.second().getX();
        double y3 = segment2.first().getY();
        double y4 = segment2.second().getY();

        double x1min = Math.min(x1, x2);
        double x2max = Math.max(x1, x2);
        double y1min = Math.min(y1, y2);
        double y2max = Math.max(y1, y2);
        double x3min = Math.min(x3, x4);
        double x4max = Math.max(x3, x4);
        double y3min = Math.min(y3, y4);
        double y4max = Math.max(y3, y4);


        if ( ((x2max >= x3min) && (x4max >= x1min) && (y2max >= y3min) && (y4max >= y1min)) &&
                ((vectorMultiply(vectorMinus(p3, p1), vectorMinus(p2, p1)) * vectorMultiply(vectorMinus(p4, p1), vectorMinus(p2, p1))) <=0) &&
                ((vectorMultiply(vectorMinus(p1, p3), vectorMinus(p4, p3)) * vectorMultiply(vectorMinus(p2, p3), vectorMinus(p4, p3))) <=0) ) {
            return generateIntersectingPoint(x1, x2, x3, x4, y1, y2, y3, y4);
        }

        return null;
    }

    private I2DPoint vectorMinus(I2DPoint v1, I2DPoint v2) {
        return new Point2D(v1.getX() - v2.getX(), v1.getY() - v2.getY());
    }

    private double vectorMultiply(I2DPoint v1, I2DPoint v2) {
        return v1.getX()*v2.getY() - v2.getX()*v1.getY();
    }

    private I2DPoint generateIntersectingPoint (double x1, double x2, double x3, double x4, double y1, double y2, double y3, double y4) {
        double u = ((x4 - x3)*(y1 - y3) - (y4 - y3)*(x1 - x3))/((y4 - y3)*(x2 - x1) - (x4 - x3)*(y2 - y1));
        return new Point2D(x1 + u*(x2 - x1), y1 + u*(y2 - y1));
    }


}
