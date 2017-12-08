package com.epam.courses.jf.practice.nbikbaev.second;

import com.epam.courses.jf.practice.common.second.I2DPoint;
import com.epam.courses.jf.practice.common.second.ITestableTask17;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Task17 implements ITestableTask17 {


    @Override
    public Set<I2DPoint> analyze(Set<ISegment> segments) {
        List<ISegment> segmentList = new ArrayList<>(segments);
        Map<Integer, I2DPoint> map = new TreeMap<Integer, I2DPoint>();
        for (int i = 0; i < segments.size(); i++) {
            for (int j = 0; j < segmentList.size(); j++) {
                I2DPoint point = getIntersectionPoint(segmentList.get(i), segmentList.get(j));
                if (point != null) {
                    map.put(i + j, point);
                }
            }
        }
        I2DPoint min = Collections.min(map.values(), Comparator.comparingDouble(I2DPoint::getX));
        Set<I2DPoint> points = new HashSet<>();
        for (I2DPoint point : map.values()) {
            if (point.getX() == min.getX()) {
                points.add(point);
            }
        }
        return points;
    }

    /**
     * Returns intersection point of the two intersecting segments
     *
     * @param segment1 First segment
     * @param segment2 Second segment
     * @return Intersection point of the two segments
     */
    private I2DPoint getIntersectionPoint(ISegment segment1, ISegment segment2) {
        BigDecimal x1 = new BigDecimal(segment1.first().getX());
        BigDecimal y1 = new BigDecimal(segment1.first().getY());
        BigDecimal x2 = new BigDecimal(segment1.second().getX());
        BigDecimal y2 = new BigDecimal(segment1.second().getY());

        BigDecimal x3 = new BigDecimal(segment2.first().getX());
        BigDecimal y3 = new BigDecimal(segment2.first().getY());
        BigDecimal x4 = new BigDecimal(segment2.second().getX());
        BigDecimal y4 = new BigDecimal(segment2.second().getY());

        BigDecimal segment1XY = x1.multiply(y2).subtract(y1.multiply(x2));
        BigDecimal segment2XY = x3.multiply(y4).subtract(y3.multiply(x4));
        BigDecimal commonDenominator = x1.subtract(x2).multiply(y3.subtract(y4)).subtract(y1.subtract(y2).multiply(x3.subtract(x4)));
        if (commonDenominator.compareTo(new BigDecimal(0)) == 0) {
            return null;
        }
        BigDecimal pX = segment1XY.multiply(x3.subtract(x4)).subtract(
                (x1.subtract(x2)).multiply(segment2XY)
        ).divide(commonDenominator, RoundingMode.CEILING);
        BigDecimal pY = segment1XY.multiply(y3.subtract(y4)).subtract(
                (y1.subtract(y2)).multiply(segment2XY)
        ).divide(commonDenominator, RoundingMode.CEILING);
        return new Point2DImpl(pX.doubleValue(), pY.doubleValue());
    }


}
