package com.epam.courses.jf.practice.asgalantsev.second;

import com.epam.courses.jf.practice.common.second.I2DPoint;
import com.epam.courses.jf.practice.common.second.ITestableTask17;

import java.util.*;

/**
 * На плоскости задано N отрезков.
 * Найти точку (возможно несколько) пересечения двух отрезков, имеющую минимальную абсциссу.
 * Использовать класс TreeMap.
 */

public class Task17 implements ITestableTask17 {

    /**
     * Осуществляет анализ переданных отрезков.
     * @param segments Множество отрезков.
     * @return Множество точек пересечения, имеющих минимальную абсциссу.
     */

    @Override
    public TreeSet<I2DPoint> analyze(Set<ISegment> segments) {
        TreeSet<I2DPoint> result = new TreeSet<>();
        TreeSet<I2DPoint> set = new TreeSet<>(new Comparator<I2DPoint>() {
            @Override
            public int compare(I2DPoint o1, I2DPoint o2) {
                double X1 = o1.getX();
                double X2 = o2.getX();
                if(X1 < X2)
                    return -1;
                else
                    return 1;
            }
        });
        List<ISegment> list = new ArrayList<>(segments);

        for (int i=0; i < list.size() - 1; i++) {
            for(int j = i+1; j < list.size(); j++) {
                ISegment segm1 = list.get(i);
                ISegment segm2 = list.get(j);

                double a1 = a(segm1.first().getX(), segm1.second().getX(), segm1.first().getY(), segm1.second().getY());
                double b1 = b(segm1.first().getX(), segm1.second().getX(), segm1.first().getY(), segm1.second().getY());
                double a2 = a(segm2.first().getX(), segm2.second().getX(), segm2.first().getY(), segm2.second().getY());
                double b2 = b(segm2.first().getX(), segm2.second().getX(), segm2.first().getY(), segm2.second().getY());

                double X = 0.0;
                double Y = 0.0;

                if(a1 != a1 && a2 != a2)
                    continue;

                if(a1 != a1) {
                    X = segm1.first().getX();
                    Y = a2 * X + b2;
                    System.out.println(X + " " + Y);
                } else if(a2 != a2) {
                    X = segm2.first().getX();
                    Y = a1 * X + b1;
                } else {
                    X = X(a1, a2, b1, b2);
                    Y = Y(a1, a2, b1, b2);
                }

                set.add(new Point2D(X, Y));
            }
        }
        if(set.size() == 1)
            return set;

        I2DPoint min = set.pollFirst();
        double minX = min.getX();
        result.add(min);

        for(int i=0; i < set.size(); i++) {
            I2DPoint elem = set.pollFirst();
            if(elem.getX() == minX)
                result.add(elem);
            else
                break;
        }

        return result;
    }

    private double a(double x1, double x2, double y1, double y2) {
        if(x1 == x2)
            return Double.NaN;
        double res = (y2 - y1) / (x2 - x1);
        return res;
    }

    private double b(double x1, double x2, double y1, double y2) {
        double a = a(x1, x2, y1, y2);
        double res = y1 - a * x1;
        return res;
    }

    private double X(double a1, double a2, double b1, double b2) {
        if(a1 == a2 || a1 != a1 || a2 != a2)
            return Double.NaN;
        double res = -(b2 - b1) / (a2 - a1);
        return res;
    }

    private double Y(double a1, double a2, double b1, double b2) {
        double X = X(a1, a2, b1, b2);
        double res = b1 + a1 * X;
        return res;
    }
}
