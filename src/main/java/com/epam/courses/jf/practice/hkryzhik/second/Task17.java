package com.epam.courses.jf.practice.hkryzhik.second;

import com.epam.courses.jf.practice.common.second.I2DPoint;
import com.epam.courses.jf.practice.common.second.ITestableTask17;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

public class Task17 implements ITestableTask17 {

    @Override
    public Set<I2DPoint> analyze(Set<ISegment> segments) {

        TreeMap<Double, HashSet<I2DPoint>> points = new TreeMap<>(new MyComparator());

        segments.stream().forEach((s1) -> {

            Line line1 = new Line(s1.first(), s1.second());

            segments.stream().forEach((s2) -> {

                Line line2 = new Line(s2.first(), s2.second());

                if (line1.first() != line2.first()) {

                    Point2D point = new Point2D((line2.second() - line1.second()) / (line1.first() - line2.first()),
                            (line2.second() * line1.first() - line2.first() * line1.second()) / (line1.first() - line2.first()));

                    double maxX = Math.max(s1.first().getX(), Math.max(s1.second().getX(),
                            Math.max(s2.first().getX(), s2.second().getX())));

                    double minX = Math.min(s1.first().getX(), Math.min(s1.second().getX(),
                            Math.min(s2.first().getX(), s2.second().getX())));

                    double maxY = Math.max(s1.first().getY(), Math.max(s1.second().getY(),
                            Math.max(s2.first().getY(), s2.second().getY())));

                    double minY = Math.min(s1.first().getY(), Math.min(s1.second().getY(),
                            Math.min(s2.first().getY(), s2.second().getY())));

                    if (point.getX() >= minX && point.getX() <= maxX && point.getY() >= minY && point.getY() <= maxY) {

                        if (points.containsKey(point.getX())) {

                            points.get(point.getX()).add(point);

                        } else {
                            HashSet<I2DPoint> pointsSet = new HashSet<>();

                            pointsSet.add(point);

                            points.put(point.getX(), pointsSet);
                        }
                    }
                }
            });
        });

        if (!points.isEmpty()) return points.firstEntry().getValue();

                          else return new HashSet<>();
    }

    private class MyComparator implements Comparator<Double> {

        @Override
        public int compare(Double x1, Double x2) {
            if (x1 > x2) return 1;
            else if (x1 < x2) return -1;
            else return 0;

        }

        @Override
        public boolean equals(Object obj) {
            return false;
        }
    }

    private class Line {

        private Double a;

        private Double b;

        private HashSet<I2DPoint> points;

        public Line(I2DPoint first, I2DPoint second) {

            this.a = (first.getY() - second.getY())/(first.getX() - second.getX());

            this.b = (second.getY() * first.getX() - first.getY() * second.getX())/(first.getX() - second.getX());

            points = new HashSet<>();

            points.add(first);
            points.add(second);
        }

        public Double first() {
            return a;
        }

        public Double second() {
            return b;
        }
    }
}
