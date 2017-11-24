package com.epam.courses.jf.practice.bborzdov;

import com.epam.courses.jf.practice.common.second.I2DPoint;
import com.epam.courses.jf.practice.common.second.ITestableTask17;

import java.util.*;

/**
 * Created by bogdan on 23.11.17.
 */
public class Task17 implements ITestableTask17{
    @Override
    public Set<I2DPoint> analyze(Set<ISegment> segments) {
        TreeMap<I2DPoint, Set<ISegment>> map = new TreeMap<>();
        for (ISegment segment1 : segments) {
            for (ISegment segment2 : segments) {
                if(segment1.first().equals(segment2.first()) &&
                        segment1.second().equals(segment2.second())){
                    continue;
                }
                I2DPoint p = getPoint(segment1, segment2);
                if(p == null){
                    continue;
                }
                if(map.isEmpty()){
                    map.put(p, new HashSet<>(Arrays.asList(segment1,segment2)));
                    continue;
                }
                if(map.firstKey().getX() > p.getX()){
                    map.clear();
                    map.put(p, new HashSet<>(Arrays.asList(segment1,segment2)));
                }else if (map.firstKey().getX() == p.getX()){
                    map.put(p, new HashSet<>(Arrays.asList(segment1,segment2)));
                }

            }
        }
        return map.keySet();
    }
    private double[] getSegmentEquation(ISegment segment){
        double k;
        double[] equation = new double[3];
        if(segment.second().getX() - segment.first().getX() == 0){
            k = segment.second().getX() - segment.first().getX();
            k /= segment.second().getY() - segment.first().getY();
            equation[0] = 1;
            equation[1] = -k;
            equation[2] = k * segment.first().getY() - segment.first().getX();
        }else {
            k = segment.second().getY() - segment.first().getY();
            k /= segment.second().getX() - segment.first().getX();
            equation[0] = -k;
            equation[1] = 1;
            equation[2] = k * segment.first().getX() - segment.first().getY();
        }
        return equation;
    }
    private double getCoordinate(boolean isXZero, double[] equation){
        double xy;
        if(isXZero) {
            xy = equation[1];
        }else {
            xy = equation[0];
        }
        if(xy == 0){
            return -equation[2];
        }
        return -(equation[2] / xy);
    }
    private Point2D getPoint(ISegment first, ISegment second) {
        double[] firstEquation = getSegmentEquation(first);
        double[] secondEquation = getSegmentEquation(second);
        double[] result = new double[3];
        double y;
        double x;
        if (firstEquation[0] == 0 && secondEquation[1] == 0) {
            y = -firstEquation[2];
            x = -secondEquation[2];
        } else if (firstEquation[1] == 0 && secondEquation[0] == 0) {
            x = -firstEquation[2];
            y = -secondEquation[2];
        } else if ((firstEquation[0] == 0 && secondEquation[0] == 0 && firstEquation[1] == secondEquation[1] &&
                firstEquation[2] != secondEquation[2] ||
                (firstEquation[1] == 0 && secondEquation[1] == 0 && firstEquation[0] == secondEquation[0] &&
                        firstEquation[2] != secondEquation[2]))) {
            return null;
        } else {
            result[0] = secondEquation[0] - firstEquation[0];
            result[1] = secondEquation[1] - firstEquation[1];
            result[2] = secondEquation[2] - firstEquation[2];
            if (result[0] == 0d) {
                y = getCoordinate(true, result);
                firstEquation[2] += (y * firstEquation[1]);
                firstEquation[1] = 0;
                x = getCoordinate(false, firstEquation);
            } else {
                x = getCoordinate(false, result);
                firstEquation[2] += (x * firstEquation[0]);
                firstEquation[0] = 0;
                y = getCoordinate(true, firstEquation);
            }
        }
        return new Point2D(x,y);
    }
}
