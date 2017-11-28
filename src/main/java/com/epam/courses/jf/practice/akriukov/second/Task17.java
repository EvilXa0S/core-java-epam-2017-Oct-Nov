package com.epam.courses.jf.practice.akriukov.second;

import com.epam.courses.jf.practice.common.second.I2DPoint;
import com.epam.courses.jf.practice.common.second.ITestableTask17;

import java.util.*;

/**
 * На плоскости задано N отрезков (2 <= N <= 20).
 * Найти точку (возможно несколько) пересечения двух отрезков, имеющую минимальную абсциссу.
 * Использовать класс TreeMap.
 */
public class Task17 implements ITestableTask17 {

    /**
     * Осуществляет анализ переданных отрезков.
     * @param segments Множество отрезков.
     * @return Множество точек пересечения, имеющих минимальную абсциссу.
     */
    @Override
    public Set<I2DPoint> analyze(Set<ISegment> segments) {
        TreeMap<Double, I2DPoint> map = new TreeMap<>();
        List<ISegment> list = new ArrayList<>(segments);
        double minX = Integer.MAX_VALUE;

        for (int i=0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                ISegment firstSegment = list.get(i);
                ISegment secondSegment = list.get(j);
                double slope1 = slope(firstSegment.first().getX(), firstSegment.second().getX(), firstSegment.first().getY(), firstSegment.second().getY());
                double intercept1 = intercept(firstSegment.first().getX(), firstSegment.second().getX(), firstSegment.first().getY(), firstSegment.second().getY());
                double slope2 = slope(secondSegment.first().getX(), secondSegment.second().getX(), secondSegment.first().getY(), secondSegment.second().getY());
                double intercept2 = intercept(secondSegment.first().getX(), secondSegment.second().getX(), secondSegment.first().getY(), secondSegment.second().getY());
                double x = x(slope1, slope2, intercept1, intercept2);
                double y = y(slope1, slope2, intercept1, intercept2);

                if(x < minX)
                    map.put(x, new I2DPoint() {
                        @Override
                        public double getX() {
                            return x;
                        }

                        @Override
                        public double getY() {
                            return y;
                        }
                    });
            }
        }

        return new HashSet<I2DPoint>(map.values());
    }

    private double slope(double x1, double x2, double y1, double y2) {
        if (x1 == x2)
            return 0;
        return  (y2 - y1) / (x2 - x1);
    }

    private double intercept(double x1, double x2, double y1, double y2) {
        double slope = slope(x1, x2, y1, y2);
        return y1 - slope * x1;
    }

    private double x(double slope1, double slope2, double intercept1, double intercept2) {
        if (slope1 == slope2)
            return Integer.MAX_VALUE;
        return (intercept1 - intercept2) / (slope2 - slope1);
    }

    private double y(double slope1, double slope2, double intercept1, double intercept2) {
        double x = x(slope1, slope2, intercept1, intercept2);
        return intercept1 + slope1 * x;
    }
}