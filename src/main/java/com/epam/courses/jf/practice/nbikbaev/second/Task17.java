package com.epam.courses.jf.practice.nbikbaev.second;

import com.epam.courses.jf.practice.common.second.I2DPoint;
import com.epam.courses.jf.practice.common.second.ITestableTask17;

import java.util.*;

public class Task17 implements ITestableTask17 {

    @Override
    public Set<I2DPoint> analyze(Set<ISegment> segments) {
        List<ISegment> segmentList = new ArrayList<>(segments);
        Map<Integer, I2DPoint> map = new TreeMap<Integer, I2DPoint>();
        for (int i = 0; i < segments.size(); i++) {
            for (int j = 0; j < segmentList.size(); j++) {
                if (equals(segmentList.get(i), segmentList.get(j))) {
                    continue;
                }
                I2DPoint point = getIntersectionPoint(segmentList.get(i), segmentList.get(j));
                map.put(i + j, point);
            }
        }
        I2DPoint min = Collections.min(map.values(), Comparator.comparingDouble(I2DPoint::getX));
        Set<I2DPoint> points = new HashSet<>();
        for (I2DPoint point : map.values()) {
            if (Double.compare(point.getX(), min.getX()) == 0) {
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
        double x1 = segment1.first().getX();
        double y1 = segment1.first().getY();
        double x2 = segment1.second().getX();
        double y2 = segment1.second().getY();

        double x3 = segment2.first().getX();
        double y3 = segment2.first().getY();
        double x4 = segment2.second().getX();
        double y4 = segment2.second().getY();

        double segment1XY = x1 * y2 - y1 * x2;
        double segment2XY = x3 * y4 - y3 * x4;
        double commonDenominator = ((x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4));
        double pX = (segment1XY * (x3 - x4) - (x1 - x2) * segment2XY) / commonDenominator;
        double pY = (segment1XY * (y3 - y4) - (y1 - y2) * segment2XY) / commonDenominator;
        return new Point2DImpl(pX, pY);
    }

    private static boolean equals(ISegment segment1, ISegment segment2) {
        boolean result;
        result = (segment1.first().getX() == segment2.first().getX())
                && (segment1.first().getY() == segment2.first().getY());
        result = result && (segment1.second().getX() == segment2.second().getX())
                && (segment1.second().getY() == segment2.second().getY());
        return result;
    }

}
