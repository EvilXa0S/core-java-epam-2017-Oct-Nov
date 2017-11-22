package com.epam.courses.jf.practice.bborzdov;

import com.epam.courses.jf.practice.common.second.I2DPoint;

import java.io.Serializable;

/**
 * Created by bogdan on 22.11.17.
 */
public class Point2D implements I2DPoint, Serializable, Comparable<Point2D> {
    private double x;
    private double y;
    private double distance = 0;
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

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof I2DPoint)) return false;
        I2DPoint point2D = (I2DPoint) obj;
        return Math.abs(point2D.getX() -  x) < 0.0001 && Math.abs(point2D.getY() - y) < 0.0001;
    }

    @Override
    public int compareTo(Point2D o) {
        if(distance > o.getDistance()){
            return 1;
        }
        return -1;
    }
}
