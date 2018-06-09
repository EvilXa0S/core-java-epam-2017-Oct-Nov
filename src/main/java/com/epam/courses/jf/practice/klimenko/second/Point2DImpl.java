package com.epam.courses.jf.practice.klimenko.second;

import com.epam.courses.jf.practice.common.second.I2DPoint;

public class Point2DImpl implements I2DPoint {
    private double x, y;

    Point2DImpl() {
        x = y = 0;
    }

    public Point2DImpl(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }
}
