package com.epam.courses.jf.practice.igulyaev.model;

import com.epam.courses.jf.practice.common.second.I2DPoint;

import java.io.Serializable;

public class Point2D implements I2DPoint, Serializable {
    private static final long serialVersionUID = 5237650507065955754L;
    private double x;
    private double y;

    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point2D() {
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
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
