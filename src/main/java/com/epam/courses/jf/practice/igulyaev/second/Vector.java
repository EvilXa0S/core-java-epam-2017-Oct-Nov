package com.epam.courses.jf.practice.igulyaev.second;

import com.epam.courses.jf.practice.common.second.I2DPoint;

class Vector{
    private double x;
    private double y;

    public Vector(I2DPoint a, I2DPoint b){
        this.x = b.getX() - a.getX();
        this.y = b.getY() - a.getY();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double dotProduct(Vector v){
        return x * v.x + y * v.y;
    }

    public double crossProduct(Vector v){
        return x * v.y - y * v.x;
    }

    public boolean isCollinear(Vector v){
        return crossProduct(v) == 0.0D;
    }
}
