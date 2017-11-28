package com.epam.courses.jf.practice.akriukov.second;

import com.epam.courses.jf.practice.common.second.I2DPoint;

public class Point2D implements I2DPoint{
    private double x;
    private double y;

    /**
     * Point on XY plane
     * @param x
     * @param y
     */
    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Abscissa value
     * @return x
     */
    @Override
    public double getX() {
        return x;
    }

    /**
     * Ordinate value
     * @return
     */
    @Override
    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Point2D{" + "x=" + x +  ", y=" + y + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point2D)) return false;

        Point2D i2DPoint = (Point2D) o;
        if (Double.compare(i2DPoint.x, x) != 0) return false;
        return Double.compare(i2DPoint.y, y) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
