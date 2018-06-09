package com.epam.courses.jf.practice.klimenko.second;

import com.epam.courses.jf.practice.common.second.I2DPoint;
import com.epam.courses.jf.practice.common.second.ITestableTask17;

import java.util.*;

public class TestableTask17Impl implements ITestableTask17 {
    private double product(double a, double b, double c, double d) {
        return a * d - b * c;
    }

    private boolean between(double val, double low, double high) {
        return val >= Math.min(low, high) && val <= Math.max(low, high);
    }

    private I2DPoint intersection(ISegment a, ISegment b) {
        double A1 = a.first().getY() - a.second().getY();
        double B1 = a.second().getX() - a.first().getX();
        double C1 = -A1 * a.first().getX() - B1 * a.first().getY();
        double A2 = b.first().getY() - b.second().getY();
        double B2 = b.second().getX() - b.first().getX();
        double C2 = -A2 * b.first().getX() - B2 * b.first().getY();
        double det = product(A1, B1, A2, B2);
        if (det == 0) {
            return null;
        }
        double x = -product(C1, B1, C2, B2) / det;
        double y = -product(A1, C1, A2, C2) / det;
        if (between(x, a.first().getX(), a.second().getX())
                && between(y, a.first().getY(), a.second().getY())
                && between(x, b.first().getX(), b.second().getX())
                && between(y, b.first().getY(), b.second().getY())) {
            return new Point2DImpl(x, y);
        }
        return null;
    }

    @Override
    public Set<I2DPoint> analyze(Set<ISegment> segments) {
        SortedMap<Double, List<I2DPoint>> points = new TreeMap<>();
        ISegment[] arraySegments = segments.toArray(new ISegment[segments.size()]);

        for (int i = 0; i < arraySegments.length; ++i) {
            for (int j = i + 1; j < arraySegments.length; ++j) {
                I2DPoint intPoint = intersection(arraySegments[i], arraySegments[j]);
                if (intPoint != null) {
                    points.putIfAbsent(intPoint.getX(), new ArrayList<>());
                    points.get(intPoint.getX()).add(intPoint);
                }
            }
        }

        return new HashSet<>(points.get(points.firstKey()));
    }
}
