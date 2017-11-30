package com.epam.courses.jf.practice.desided.second;

import com.epam.courses.jf.practice.common.second.I2DPoint;


public class Point2D implements I2DPoint {
    private double x;
    private double y;

    public Point2D(double x, double y){
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
