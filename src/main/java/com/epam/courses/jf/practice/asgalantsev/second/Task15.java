package com.epam.courses.jf.practice.asgalantsev.second;

import com.epam.courses.jf.practice.common.second.I2DPoint;
import com.epam.courses.jf.practice.common.second.ITestableTask15;

import java.io.File;
import java.util.*;

public class Task15 implements ITestableTask15{
    @Override
    public IFileWithLines analyze(Set<I2DPoint> points, File output) {
        File result = output;
        Set<ILine> lineSet = new HashSet<>();
        List<I2DPoint> listPoints = new ArrayList<>();

        for(I2DPoint point: points)
            listPoints.add(point);

        for(int i=0; i < listPoints.size()-2; i++)
            for(int j=i+1; j < listPoints.size()-1; j++)
                for(int k=j+1; k < listPoints.size(); k++) {
                    I2DPoint first = listPoints.get(i);
                    I2DPoint second = listPoints.get(j);
                    I2DPoint third = listPoints.get(k);

                    double x1 = first.getX();
                    double y1 = first.getY();
                    double x2 = second.getX();
                    double y2 = second.getY();
                    double x3 = third.getX();
                    double y3 = third.getY();

                    double left = (x1 - x3) / (x2 - x3);
                    double right = (y1 - y3) / (y2 - y3);

                    if(left == right || (x1 == x2 && x2 == x3) || (y1 == y2 && y2 == y3)) {
                        Set<I2DPoint> line = new HashSet<>();
                        line.add(first);
                        line.add(second);
                        line.add(third);

                        lineSet.add(new ILine() {
                            @Override
                            public Set<I2DPoint> getPoints() {
                                return line;
                            }
                        });
                    }

                }


        return new IFileWithLines() {
            @Override
            public File getFile() {
                return result;
            }

            @Override
            public Set<ILine> getLines() {
                return lineSet;
            }
        };
    }
}
