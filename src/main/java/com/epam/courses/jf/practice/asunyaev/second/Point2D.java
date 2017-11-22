package com.epam.courses.jf.practice.asunyaev.second;

import com.epam.courses.jf.practice.common.second.I2DPoint;

import java.io.Serializable;

/**
 * Точка на двумерной плоскости.
 */
public class Point2D implements I2DPoint, Comparable<I2DPoint>, Serializable {

    private final double X;

    private final double Y;

    public Point2D(double x, double y) {
        X = x;
        Y = y;
    }

    @Override
    public double getX() {
        return X;
    }

    @Override
    public double getY() {
        return Y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point2D point2D = (Point2D) o;

        if (Double.compare(point2D.getX(), X) != 0) return false;
        return Double.compare(point2D.getY(), Y) == 0;
    }


    @Override
    public int hashCode() {
        int result = (int) X;
        result = 31 * result + (int) Y;
        return result;
    }

    @Override
    public int compareTo(I2DPoint other) {
        return (int)(X + Y - other.getX() - other.getY());
    }

    @Override
    public String toString() {
        return "(" + X + ", " + Y + ")";
    }
}