package com.epam.courses.jf.practice.akunats.second;

import com.epam.courses.jf.practice.common.second.I2DPoint;
import com.epam.courses.jf.practice.common.second.ITestableTask17;

import java.util.*;

public class Task17 implements ITestableTask17 {
    @Override
    public Set<I2DPoint> analyze(Set<ISegment> segments) {
        if (segments.size() < 2 || segments.size() > 20) {
            throw new IllegalArgumentException("You enter wrong data.");
        }
        SortedMap<Double, Set<Double>> interMap = new TreeMap<>();
        Set<I2DPoint> result = new HashSet<>();
        segments.forEach(line -> segments.stream()
                .filter(nextLine -> !nextLine.equals(line))
                .forEach(nextLine -> {
                    I2DPoint point = getPoints(line, nextLine);
                    if (point != null) {
                        if (interMap.containsKey(point.getX())) {
                            interMap.get(point.getX()).add(point.getY());
                        } else {
                            interMap.put(point.getX(), new HashSet<>());
                            interMap.get(point.getX()).add(point.getY());
                        }
                    }
                }));
        Map.Entry<Double, Set<Double>> minXpoint = interMap.entrySet()
                .stream()
                .min(Comparator.comparingDouble(Map.Entry::getKey)).get();
        minXpoint.getValue().forEach(Y -> result.add(new Point(minXpoint.getKey(), Y)));

        return result;
    }

    private I2DPoint getPoints(ISegment line, ISegment nextLine) {
        I2DPoint result = null;
        // Считаем коэффициенты уравнение прямой Ax + By + C = 0
        double A1 = line.second().getY() - line.first().getY();
        double B1 = line.first().getX() - line.second().getX();
        double C1 = line.second().getY() * line.first().getX() - line.second().getX() * line.first().getY();

        double A2 = nextLine.second().getY() - nextLine.first().getY();
        double B2 = nextLine.first().getX() - nextLine.second().getX();
        double C2 = nextLine.second().getY() * nextLine.first().getX() - nextLine.second().getX() * nextLine.first().getY();
        // точка пересечения прямых
        double pointIntersectionX;
        double pointIntersectionY;
        if (B1 != 0) {
            pointIntersectionX = (C2 * B1 - B2 * C1) / (B1 * A2 - A1 * B2);
            pointIntersectionY = (C1 - A1 * pointIntersectionX) / B1;
        } else {
            pointIntersectionY = (C2 * A1 - A2 * C1) / (B2 * A1 - B1 * A2);
            pointIntersectionX = (C1 - B1 * pointIntersectionY) / A1;
        }

        double minX1 = line.second().getX() < line.first().getX()
                ? line.second().getX() : line.first().getX();
        double maxX1 = line.second().getX() < line.first().getX()
                ? line.first().getX() : line.second().getX();
        double minY1 = line.second().getY() < line.first().getY()
                ? line.second().getY() : line.first().getY();
        double maxY1 = line.second().getY() < line.first().getY()
                ? line.first().getY() : line.second().getY();

        double minX2 = nextLine.second().getX() < nextLine.first().getX()
                ? nextLine.second().getX() : nextLine.first().getX();
        double maxX2 = nextLine.second().getX() < nextLine.first().getX()
                ? nextLine.first().getX() : nextLine.second().getX();
        double minY2 = nextLine.second().getY() < nextLine.first().getY()
                ? nextLine.second().getY() : nextLine.first().getY();
        double maxY2 = nextLine.second().getY() < nextLine.first().getY()
                ? nextLine.first().getY() : nextLine.second().getY();
        // Лежит ли точка в отрезке
        if (((pointIntersectionX >= minX1 && pointIntersectionX <= maxX1)
                && (pointIntersectionY >= minY1 && pointIntersectionY <= maxY1))
                && ((pointIntersectionX >= minX2 && pointIntersectionX <= maxX2)
                && (pointIntersectionY >= minY2 && pointIntersectionY <= maxY2))) {
            result = new Point(Math.round(pointIntersectionX), Math.round(pointIntersectionY));
        }
        return result;
    }
}
