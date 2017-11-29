package com.epam.courses.jf.practice.vakhonin.second;

import com.epam.courses.jf.practice.common.second.I2DPoint;
import com.epam.courses.jf.practice.common.second.ITestableTask17;

import java.util.*;
import java.util.logging.Logger;

/**
 * На плоскости задано N отрезков.
 * Найти точку (возможно несколько) пересечения двух отрезков, имеющую минимальную абсциссу.
 * Использовать класс TreeMap.
 */

public class Task17 implements ITestableTask17 {

    /**
     * Точка, заданная координатами X, Y.
     */

    class Point implements I2DPoint{
        private double x,y;

        Point(double x, double y){
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

    /**
     * Класс для описания прямых на плоскости
     */

    class Line {
        private double a, b, c;

        double getA(){
            return a;
        }

        double getB(){
            return b;
        }

        double getC(){
            return c;
        }

        Line(ISegment segment) {
            double x1,x2,y1,y2;
            x1 = segment.first().getX();
            x2 = segment.second().getX();
            y1 = segment.first().getY();
            y2 = segment.second().getY();

            if ((x2-x1) == 0) {
                this.b = 0;
                this.a = 1;
                this.c = -x1;
            }
            else {
                this.b = -1;
                this.a = (y2 - y1) / (x2 - x1);
                this.c = y1 - a * x1;
            }
        }

        /**
         * Вычисление точки пересечения прямых
         * @param line2 объект Line, прямая, с которой будет вычисляться точка пересечения
         * @return Точка пересечения
         */

        I2DPoint pointOfIntersection(Line line2) {
            double a1,a2,b1,b2, c1, c2, x0,y0;
            a1 = this.getA();
            a2 = line2.getA();
            b1 = this.getB();
            b2 = line2.getB();
            c1 = this.getC();
            c2 = line2.getC();
            y0 = (c2*a1 - a2*c1)/(b1*a2-b2*a1);
            x0 = (-c1-b1*y0)/a1;
            I2DPoint result = new Point(x0,y0);

            return result;
        }
    }

    /**
     * Принадлежит ли точка отрезку
     * @param point
     * @param segment
     * @return принадлежит или нет
     */

    static boolean isPointOfSegment(I2DPoint point, ISegment segment){

        if(     (point.getX() >= Math.min(segment.first().getX(), segment.second().getX())) &&
                (point.getX() <= Math.max(segment.first().getX(), segment.second().getX())) &&
                (point.getY() >= Math.min(segment.first().getY(), segment.second().getY())) &&
                (point.getY() <= Math.max(segment.first().getY(), segment.second().getY())))
        {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Осуществляет анализ переданных отрезков.
     * @param segments Множество отрезков.
     * @return Множество точек пересечения, имеющих минимальную абсциссу.
     */

    @Override
    public Set<I2DPoint> analyze(Set<ISegment> segments) {
        List<ISegment> segmentsList = new ArrayList<>(segments);
        List<Line> linesList = new ArrayList<>();
        Map<Double, Set<I2DPoint>> pointsMap= new TreeMap<>();

        for (ISegment segment: segmentsList) {
            linesList.add(new Line(segment));
        }

        int size = segmentsList.size();
        I2DPoint point;
        Line lineJ, lineK;
        ISegment segmentJ, segmentK;

        for (int j = 0; j < size-1; j++) {
            lineJ = linesList.get(j);
            segmentJ = segmentsList.get(j);

            for (int k = j+1; k<size; k++) {
                lineK = linesList.get(k);
                segmentK = segmentsList.get(k);
                point = lineJ.pointOfIntersection(lineK);

                if (isPointOfSegment(point, segmentJ) && isPointOfSegment(point, segmentK)) {

                    if (pointsMap.containsKey(point.getX())) {
                        pointsMap.get(point.getX()).add(point);
                    }
                    else {
                        pointsMap.put(point.getX(), new HashSet<>());
                        pointsMap.get(point.getX()).add(point);
                    }
                }
            }
        }

        Iterator<Map.Entry<Double, Set<I2DPoint>>> it = pointsMap.entrySet().iterator();
        Set<I2DPoint> result = it.next().getValue();

        return result;
    }
}