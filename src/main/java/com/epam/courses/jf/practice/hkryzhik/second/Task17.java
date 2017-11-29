package com.epam.courses.jf.practice.hkryzhik.second;

import com.epam.courses.jf.practice.common.second.I2DPoint;
import com.epam.courses.jf.practice.common.second.ITestableTask17;

import java.util.*;

public class Task17 implements ITestableTask17 {
    @Override
    public Set<I2DPoint> analyze(Set<ITestableTask17.ISegment> segments) {

        if (segments == null || segments.size() == 0) return null;

        TreeMap<Double, HashMap<I2DPoint, Integer>> map = new TreeMap<>(Double::compare);

        ArrayList<double[]> lines = new ArrayList<>();
        for (ITestableTask17.ISegment segment : segments) {

            lines.add(findLine(segment.first(), segment.second()));
        }

        for (double[] line1 : lines) {

            for (double[] line2 : lines) {

                I2DPoint point = IntersectionPoint(line1, line2);
                if (point != null) {
                    map.computeIfAbsent(point.getX(), k -> new HashMap<>());

                    Integer num = map.get(point.getX()).get(point);

                    map.get(point.getX()).put(point, num + 1);
                }
            }
        }
        if (map.size() == 0) return null;


        Double minKey = map.firstKey();
        HashSet<I2DPoint> points = new HashSet<>();

        for (I2DPoint i2DPoint : map.get(minKey).keySet()) {

            if (map.get(minKey).get(i2DPoint) >= 2) points.add(i2DPoint);
        }
        return points;
    }


    private I2DPoint IntersectionPoint(double[] line1, double[] line2) {

        double delta = -line1[0] * line2[1] + line1[1] * line2[0];

        if (Double.compare(delta, 0) == 0) return null;

        double delta1 = line1[0] * line2[2] - line1[2] * line2[0];

        double delta2 = - line1[2] * line2[1] + line1[1] * line2[2];

        return new Point2D(delta1 / delta, delta2 / delta);
    }

    private double[] findLine(I2DPoint point1, I2DPoint point2) {

        double a;
        double b;
        double c;

        double[] res = new double[3];

        double den = point2.getX() - point1.getX();
        if (Double.compare(den, 0d) == 0) {
            res[0] = 0;

            res[1] = 1;

            res[2] = -point1.getX();
        } else {
            res[0] = 1;

            res[1] = (point2.getY() - point1.getY()) / den;

            res[2] = point1.getY() - res[1] * point1.getX();
        }
        return res;
    }
}
