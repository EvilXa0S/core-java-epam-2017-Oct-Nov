package com.epam.courses.jf.practice.igulyaev.second;

import com.epam.courses.jf.practice.common.second.I2DPoint;
import com.epam.courses.jf.practice.common.second.ITestableTask17;
import com.epam.courses.jf.practice.igulyaev.model.Point2D;
import com.epam.courses.jf.practice.igulyaev.model.Vector;

import java.awt.geom.Line2D;
import java.util.*;
import java.util.stream.Collectors;

public class TestableTask17 implements ITestableTask17 {
    @Override
    public Set<I2DPoint> analyze(Set<ISegment> segments) {
        Set<I2DPoint> pointSet = new HashSet<>();
        List<ISegment> segmentList = new ArrayList<>(segments);
        for (ListIterator<ISegment> iterator1 = segmentList.listIterator(); iterator1.hasNext(); ){
            ISegment segment1 = iterator1.next();
            Line2D firstLine2D = new Line2D.Double(segment1.first().getX(), segment1.first().getY(),
                    segment1.second().getX(), segment1.second().getY());
            for (Iterator<ISegment> iterator2 = segmentList.listIterator(iterator1.nextIndex()); iterator2.hasNext();){
                ISegment segment2 = iterator2.next();
                Line2D secondLine2D = new Line2D.Double(segment2.first().getX(), segment2.first().getY(),
                        segment2.second().getX(), segment2.second().getY());
                if(firstLine2D.intersectsLine(secondLine2D)){
                    I2DPoint point = intersection(segment1, segment2);
                    pointSet.add(point);
                }
            }
        }
        return pointSet.stream()
                .filter(p -> p.getX() ==
                        (pointSet.stream()
                                .mapToDouble(I2DPoint::getX)
                                .min().orElse(Double.MIN_EXPONENT)))
                .collect(Collectors.toSet());
    }
    private I2DPoint intersection(ISegment a, ISegment b) {
        double d = new com.epam.courses.jf.practice.igulyaev.model.Vector(a.first(), a.second()).crossProduct(new Vector(b.first(), b.second()));
        if (d == 0){
            return null;
        }
        double x1 = a.first().getX();
        double y1 = a.first().getY();
        double x2 = a.second().getX();
        double y2 = a.second().getY();
        double x3 = b.first().getX();
        double y3 = b.first().getY();
        double x4 = b.second().getX();
        double y4 = b.second().getY();

        double xi = ((x3-x4)*(x1*y2-y1*x2)-(x1-x2)*(x3*y4-y3*x4))/d;
        double yi = ((y3-y4)*(x1*y2-y1*x2)-(y1-y2)*(x3*y4-y3*x4))/d;
        if(xi == -0.0D){
            xi = 0.0D;
        }
        if(yi == -0.0D){
            yi = 0.0D;
        }
        return new Point2D(xi,yi);
    }
}
