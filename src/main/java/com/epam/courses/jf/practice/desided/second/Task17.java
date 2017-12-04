package com.epam.courses.jf.practice.desided.second;

import com.epam.courses.jf.practice.common.second.I2DPoint;
import com.epam.courses.jf.practice.common.second.ITestableTask17;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


/**
 * На плоскости задано N отрезков (2 <= N <= 20).
 * Найти точку (возможно несколько) пересечения двух отрезков, имеющую минимальную абсциссу.
 * Использовать класс TreeMap.
 */
public class Task17 implements ITestableTask17{


    /**
     * Осуществляет анализ переданных отрезков.
     *
     * @param segments Множество отрезков.
     * @return Множество точек пересечения, имеющих минимальную абсциссу.
     */
    @Override
    public Set<I2DPoint> analyze(Set<ISegment> segments) {
        Set<I2DPoint> resultSet = new HashSet<>();

        ArrayList<ISegment> iSegments = new ArrayList<>(segments);

        double minAbscissa = Double.MAX_VALUE;

        for (int i = 0; i < iSegments.size() - 1; i++) {
            I2DPoint firstPoint = iSegments.get(i).first();
            I2DPoint secondPoint = iSegments.get(i).second();
            double A1 = firstPoint.getY() - secondPoint.getY();
            double B1 = secondPoint.getX() - firstPoint.getX();
            double C1 = firstPoint.getX() * secondPoint.getY() - secondPoint.getX() * firstPoint.getY();


            for (int j = i + 1; j < iSegments.size(); j++) {
                I2DPoint thirdPoint = iSegments.get(j).first();
                I2DPoint forthPoint = iSegments.get(j).second();
                double A2 = thirdPoint.getY() - forthPoint.getY();
                double B2 = forthPoint.getX() - thirdPoint.getX();
                double C2 = thirdPoint.getX() * forthPoint.getY() - forthPoint.getX() * thirdPoint.getY();

                double x = (B1 * C2 - B2 * C1) / (A1 * B2 - A2 * B1);
                double y = (C1 * A2 - C2 * A1) / (A1 * B2 - A2 * B1);

                if (x >= Math.min(firstPoint.getX(), secondPoint.getX()) &&
                        x <= Math.max(firstPoint.getX(), secondPoint.getX()) &&
                        y >= Math.min(firstPoint.getY(), secondPoint.getY()) &&
                        y <= Math.max(firstPoint.getY(), secondPoint.getY()) &&
                        x >= Math.min(thirdPoint.getX(), forthPoint.getX()) &&
                        x <= Math.max(thirdPoint.getX(), forthPoint.getX()) &&
                        y >= Math.min(thirdPoint.getY(), forthPoint.getY()) &&
                        y <= Math.max(thirdPoint.getY(), forthPoint.getY())) {

                    if (x < minAbscissa) {
                        minAbscissa = x;
                        resultSet.clear();
                        resultSet.add(new Point2D(x, y));
                    } else if (x == minAbscissa) {
                        resultSet.add(new Point2D(x, y));
                    }
                }
            }
        }

        return resultSet;
    }

    class Segment implements ISegment {
        I2DPoint first;
        I2DPoint second;

        public Segment(I2DPoint first, I2DPoint second) {
            this.first = first;
            this.second = second;
        }

        /** @return Первая точка отрезка. */
        @Override
        public I2DPoint first() {
            return first;
        }

        /** @return Вторая точка отрезка. */
        @Override
        public I2DPoint second() {
            return second;
        }
    }

}
