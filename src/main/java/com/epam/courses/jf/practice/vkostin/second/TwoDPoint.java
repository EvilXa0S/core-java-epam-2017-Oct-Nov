package com.epam.courses.jf.practice.vkostin.second;

import com.epam.courses.jf.practice.common.second.I2DPoint;

public class TwoDPoint implements I2DPoint {

    private double x;
    private double y;

    public TwoDPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "" + x + "_" + y;
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
