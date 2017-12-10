package com.epam.courses.jf.practice.NikDevyatyi.second;

import com.epam.courses.jf.practice.common.second.I2DPoint;

public class D2Point implements I2DPoint {
    private double coordX;
    private double coordY;
    public D2Point(double coordX, double coordY){
        this.coordX = coordX;
        this.coordY = coordY;
    }
    @Override
    public double getX() {
        return coordX;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        D2Point d2Point = (D2Point) o;

        if (Double.compare(d2Point.coordX, coordX) != 0) return false;
        return Double.compare(d2Point.coordY, coordY) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(coordX);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(coordY);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public double getY() {
        return coordY;

    }
}
