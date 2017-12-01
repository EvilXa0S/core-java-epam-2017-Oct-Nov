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
     *
     * @param segments Множество отрезков.
     * @return Множество точек пересечения, имеющих минимальную абсциссу.
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
                    double divider = (y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1);
                    double numenatorA = (x4 - x3) * (y1 - y3) - (y4 - y3) * (x1 - x3);
                    double numenatorB = (x2 - x1) * (y1 - y3) - (y2 - y1) * (x1 - x3);

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
                            crossPointsSet.add(new Point2D(X, Y));
                            firstTime = false;
                        } else if (minX > X) {
                            minX = X;
                            crossPointsSet.clear();
                            crossPointsSet.add(new Point2D(X, Y));
                        } else if (minX == X) {
                            crossPointsSet.add(new Point2D(X, Y));
                        }
                    }
                }
            }
        }

        return crossPointsSet;
    }
}