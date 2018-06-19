package com.epam.courses.jf.practice.mallayarov.second;

import com.epam.courses.jf.practice.common.second.I2DPoint;
import com.epam.courses.jf.practice.common.second.ITestableTask15;

import java.util.HashSet;
import java.util.Set;

/**
 * Прямая, заданная точками, входящими в исходное множество.
 */
public class Line implements ITestableTask15.ILine {

    /**
     * Set of points what the line contains
     */
    private Set<I2DPoint> allPoints;

    /**
     * Start point of the line
     */
    private I2DPoint firstPoint;

    /**
     * End point of the line
     */
    private I2DPoint secondPoint;

    public Line(I2DPoint firstPoint, I2DPoint secondPoint) {
        allPoints = new HashSet<>();
        this.firstPoint = firstPoint;
        this.secondPoint = secondPoint;
        allPoints.add(firstPoint);
        allPoints.add(secondPoint);
    }

    public void addPoint(I2DPoint point) {
        allPoints.add(point);
    }

    public I2DPoint getFirstPoint() {
        return firstPoint;
    }

    public I2DPoint getSecondPoint() {
        return secondPoint;
    }

    /** @return Точки, через которые проходит прямая */
    @Override
    public Set<I2DPoint> getPoints() {
        return allPoints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Line line = (Line) o;

        if (!allPoints.equals(line.allPoints)) return false;
        if (firstPoint != null ? !firstPoint.equals(line.firstPoint) : line.firstPoint != null) return false;
        return secondPoint != null ? secondPoint.equals(line.secondPoint) : line.secondPoint == null;
    }

    @Override
    public int hashCode() {
        int result = allPoints.hashCode();
        result = 31 * result + (firstPoint != null ? firstPoint.hashCode() : 0);
        result = 31 * result + (secondPoint != null ? secondPoint.hashCode() : 0);
        return result;
    }
}
