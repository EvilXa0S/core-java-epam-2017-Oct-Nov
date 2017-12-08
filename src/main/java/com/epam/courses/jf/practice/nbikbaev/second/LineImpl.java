package com.epam.courses.jf.practice.nbikbaev.second;

import com.epam.courses.jf.practice.common.second.I2DPoint;
import com.epam.courses.jf.practice.common.second.ITestableTask15;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class LineImpl implements ITestableTask15.ILine {

    private Set<I2DPoint> points;

    public LineImpl(I2DPoint point1, I2DPoint point2) {
        this.points = new HashSet<>();
        points.add(point1);
        points.add(point2);
    }

    @Override
    public Set<I2DPoint> getPoints() {
        return points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LineImpl)) return false;
        LineImpl line = (LineImpl) o;
        return Objects.equals(points, line.points);
    }

    @Override
    public int hashCode() {
        return Objects.hash(points);
    }

    @Override
    public String toString() {
        return "Line{" +
                "points=" + points +
                '}';
    }
}