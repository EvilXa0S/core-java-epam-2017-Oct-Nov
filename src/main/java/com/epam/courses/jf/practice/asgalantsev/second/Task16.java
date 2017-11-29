package com.epam.courses.jf.practice.asgalantsev.second;

import com.epam.courses.jf.practice.common.second.I2DPoint;
import com.epam.courses.jf.practice.common.second.ITestableTask16;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Task16 implements ITestableTask16{
    @Override
    public IFileWithPoints analyze(I2DPoint center, int radius, File output) {
        SortedMap<I2DPoint, Double> map = new TreeMap<>(new Comparator<I2DPoint>() {
            @Override
            public int compare(I2DPoint o1, I2DPoint o2) {
                if(distance(o1.getY(), o1.getX(), center.getY(), center.getX()) < distance(o2.getY(), o2.getX(), center.getY(), center.getX()))
                    return -1;
                else
                    return 1;
            }
        });

        double centerX = center.getX();
        double centerY = center.getY();

        double startX = centerX - radius;
        double startY = centerY - radius;

        for(int y=0; y <= 2*radius; y++)
            for(int x=0; x <= 2*radius; x++) {
                double newY = (int)(startY) + y;
                double newX = (int)(startX) + x;
                double dist = distance(newY, newX, centerY, centerX);
                if(dist < radius) {

                    I2DPoint point = new I2DPoint() {
                        @Override
                        public double getX() {
                            return newX;
                        }

                        @Override
                        public double getY() {
                            return newY;
                        }
                    };
                    map.put(point, dist);
                }
            }

        try {
            FileWriter writer = new FileWriter(output, true);
            for(I2DPoint point: map.keySet())
                writer.append("" + point.getY() + " " + point.getX() + "\n");
            writer.flush();
            writer.close();
        } catch (IOException e) { }

        return new IFileWithPoints() {
            @Override
            public File getFile() {
                return output;
            }

            @Override
            public SortedMap<I2DPoint, Double> getPoints() {
                return map;
            }
        };
    }
    private double distance(double y, double x, double centerY, double centerX) {
        double distance = Math.sqrt(Math.pow(y - centerY, 2) + Math.pow(x - centerX, 2));
        return distance;
    }
}
