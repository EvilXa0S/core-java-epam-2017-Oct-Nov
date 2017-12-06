package com.epam.courses.jf.practice.danilBay.second;

import com.epam.courses.jf.practice.common.second.I2DPoint;
import com.epam.courses.jf.practice.common.second.ITestableTask17;

import java.util.HashSet;
import java.util.Set;

public class Task17 implements ITestableTask17 {

    /**
     * Carries out the analysis of the transferred segments.
     *
     * @param segments The set of Line segments.
     * @return A set of intersection points having minimal abscissa.
     */
    @Override
    public Set<I2DPoint> analyze(Set<ISegment> segments) {

        Set<I2DPoint> resultSet = new HashSet<>();
        double minX = 0.0;
        boolean startFlag = true;

        double x1, y1, x2, y2, x3, y3, x4, y4;
        for (ISegment segment : segments) {
            for (ISegment segment2 : segments) {
                if (!segment.equals(segment2)) {
                    x1 = segment.first().getX();
                    y1 = segment.first().getY();
                    x2 = segment.second().getX();
                    y2 = segment.second().getY();
                    x3 = segment2.first().getX();
                    y3 = segment2.first().getY();
                    x4 = segment2.second().getX();
                    y4 = segment2.second().getY();

                    double d = (y4 - y3) * (x2 - x1)- (x4 - x3) * (y2 - y1);

                    double num1 = (x4 - x3) * (y1 - y3) - (y4 - y3) * (x1 - x3);

                    double num2 = (x2 - x1) * (y1 - y3) - (y2 - y1) * (x1 - x3);

                    if (d == 0) {
                        continue;
                    }

                    double kA = num1 / d;
                    double kB = num2 / d;

                    if (0 <= kA && kA <= 1 && 0 <= kB && kB <= 1) {
                        double X = x1 + kA * (x2 - x1);
                        double Y = y1 + kA * (y2 - y1);

                        if (startFlag) {
                            minX = X;
                            resultSet.add(new Point(X, Y));
                            startFlag = false;
                        }
                        else if (minX > X) {
                            minX = X;
                            resultSet.clear();
                            resultSet.add(new Point(X, Y));
                        }
                        else if (minX == X) {
                            resultSet.add(new Point(X, Y));
                        }
                    }
                }
            }
        }
        return resultSet;
    }


}