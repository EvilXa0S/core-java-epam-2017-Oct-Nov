package com.epam.courses.jf.practice.danilBay.second;

import com.epam.courses.jf.practice.common.second.I2DPoint;

public class Point implements I2DPoint {
    double x;
    double y;

    Point(double x,double y){
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
