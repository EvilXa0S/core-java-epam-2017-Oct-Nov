package com.epam.courses.jf.practice.asgalantsev.second;

import com.epam.courses.jf.practice.common.second.I2DPoint;
import com.epam.courses.jf.practice.common.second.ITestableTask17;

import java.util.*;

public class Task17 implements ITestableTask17 {
    @Override
    public TreeSet<I2DPoint> analyze(Set<ISegment> segments) {
        TreeMap<Double, I2DPoint> map = new TreeMap<>();
        List<ISegment> list = new ArrayList<>(segments);
        double minX = Integer.MAX_VALUE;

        for (int i=0; i < list.size() - 1; i++) {
            for(int j = i+1; j < list.size(); j++) {
                ISegment segm1 = list.get(i);
                ISegment segm2 = list.get(j);

                double a1 = a(segm1.first().getX(), segm1.second().getX(), segm1.first().getY(), segm1.second().getY());
                double b1 = b(segm1.first().getX(), segm1.second().getX(), segm1.first().getY(), segm1.second().getY());
                double a2 = a(segm2.first().getX(), segm2.second().getX(), segm2.first().getY(), segm2.second().getY());
                double b2 = b(segm2.first().getX(), segm2.second().getX(), segm2.first().getY(), segm2.second().getY());

                double X = X(a1, a2, b1, b2);
                double Y = Y(a1, a2, b1, b2);


                if(X < minX)
                    map.put(X, new I2DPoint() {
                        @Override
                        public double getX() {
                            return X;
                        }

                        @Override
                        public double getY() {
                            return Y;
                        }
                    });
            }
        }

        return new TreeSet<I2DPoint>(map.values());
    }

    private double a(double x1, double x2, double y1, double y2) {
        if(x1 == x2)
            return 0;
        double res = (y2 - y1) / (x2 - x1);
        return res;
    }

    private double b(double x1, double x2, double y1, double y2) {
        double a = a(x1, x2, y1, y2);
        double res = y1 - a * x1;
        return res;
    }

    private double X(double a1, double a2, double b1, double b2) {
        if(a1 == a2)
            return Integer.MAX_VALUE;
        double res = -(b2 - b1) / (a2 - a1);
        return res;
    }

    private double Y(double a1, double a2, double b1, double b2) {
        double X = X(a1, a2, b1, b2);
        double res = b1 + a1 * X;
        return res;
    }
}
