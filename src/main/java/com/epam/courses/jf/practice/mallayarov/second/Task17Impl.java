package com.epam.courses.jf.practice.mallayarov.second;

import com.epam.courses.jf.practice.common.second.I2DPoint;
import com.epam.courses.jf.practice.common.second.ITestableTask17;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

/**
 * На плоскости задано N отрезков.
 * Найти точку (возможно несколько) пересечения двух отрезков, имеющую минимальную абсциссу.
 * Использовать класс TreeMap.
 */
public class Task17Impl implements ITestableTask17 {

    /**
     * Осуществляет анализ переданных отрезков.
     * @param segments Множество отрезков.
     * @return Множество точек пересечения, имеющих минимальную абсциссу.
     */
    @Override
    public Set<I2DPoint> analyze(Set<ISegment> segments) {
        TreeMap<Integer, SinglePoint> crossedSegments = new TreeMap<>();
        ISegment[] segmentsArray = segments.toArray(new ISegment[segments.size()]);

        for (int i = 0; i < segmentsArray.length - 1; ++i) {
            for (int j = 1; j < segmentsArray.length; ++j) {
                SinglePoint crossPoint = crossPoint(segmentsArray[i], segmentsArray[j]);
                if (crossPoint != null) {
                    crossedSegments.put(i * segmentsArray.length + j, crossPoint);
                }
            }
        }

        double minAbs = Double.MAX_VALUE;
        for (SinglePoint point : crossedSegments.values()) {
            minAbs = Math.min(minAbs, point.getX());
        }

        Set<SinglePoint> pointsWithMinAbs = new HashSet<>();
        for (SinglePoint point : crossedSegments.values()) {
            if (point.getX() == minAbs) {
                pointsWithMinAbs.add(point);
            }
        }

        return new HashSet<>(pointsWithMinAbs);
    }

    /**
     * Create cross point between two segments
     * @param firstSegment first segment
     * @param secondSegment second segment
     * @return cross point between two segments if exists, else null
     */
    private SinglePoint crossPoint(ISegment firstSegment, ISegment secondSegment) {
        // https://en.wikipedia.org/wiki/Line%E2%80%93line_intersection

        double x1 = firstSegment.first().getX();
        double y1 = firstSegment.first().getY();
        double x2 = firstSegment.second().getX();
        double y2 = firstSegment.second().getY();

        double x3 = secondSegment.first().getX();
        double y3 = secondSegment.first().getY();
        double x4 = secondSegment.second().getX();
        double y4 = secondSegment.second().getY();

        double denominator = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
        if (denominator != 0) {
            double firstXDelta = x1 - x2;
            double firstYDelta = y1 - y2;
            double firstAdditionalPart = x1 * y2 - y1 * x2;

            double secondXDelta = x3 - x4;
            double secondYDelta = y3 - y4;
            double secondAdditionalPart = x3 * y4 - y3 * x4;

            double x = (firstAdditionalPart * secondXDelta - firstXDelta * secondAdditionalPart) / denominator;
            double y = (firstAdditionalPart * secondYDelta - firstYDelta * secondAdditionalPart) / denominator;

            if (isPointBelongsToSegment(firstSegment, x, y) && isPointBelongsToSegment(secondSegment, x, y)) {
                return new SinglePoint(x + 0d, y + 0d);
            }
        }
        return null;
    }

    /**
     * Checks if point belongs to segment
     * @param segment checking segment
     * @param x x coordinate of point
     * @param y y coordinate of point
     * @return result of check
     */
    private boolean isPointBelongsToSegment(ISegment segment, double x, double y) {
        return (segment.first().getX() <= x && segment.second().getX() >= x) ||
                (segment.first().getY() <= y && segment.second().getY() >= y);
    }
}