package com.epam.courses.jf.practice.nbikbaev.second;

import com.epam.courses.jf.practice.common.second.I2DPoint;

import java.util.Objects;

public class Point2DImpl implements I2DPoint {
    private double x;
    private double y;


    public Point2DImpl(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public double getX() {
        return this.x;
    }

    @Override
    public double getY() {
        return this.y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point2DImpl)) return false;
        Point2DImpl point2D = (Point2DImpl) o;
        return Double.compare(point2D.x, x) == 0 &&
                Double.compare(point2D.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Point2DImpl{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
