package com.epam.courses.jf.practice.hkryzhik.second;

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
        if (o == null || !(o instanceof I2DPoint)) return false;
        I2DPoint point2D = (I2DPoint) o;
        return Math.abs(point2D.getX() -  X) < 0.0001 && Math.abs(point2D.getY() - Y) < 0.0001;
    }

    @Override
    public int hashCode() {
        int result;
        long temp = Double.doubleToLongBits(X);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(Y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
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
