package com.epam.courses.jf.practice.SigarevVL.second;

import com.epam.courses.jf.practice.common.second.I2DPoint;
import com.epam.courses.jf.practice.common.second.ITestableTask16;

import java.io.File;
import java.util.*;

public class Task16 implements ITestableTask16 {

    @Override
    public IFileWithPoints analyze(I2DPoint center, int radius, File output) {

        Queue<I2DPoint> queue = new PriorityQueue<>();
        SortedMap<I2DPoint, Double> sortedMap = new TreeMap<>();

        double centerY = center.getY();
        double centerX = center.getX();

        for (int i = (int) Math.floor(radius + centerY); i
                >= (int) Math.floor(- (radius + centerY)); i--) {

            for (int j = (int) Math.floor(radius + centerX); j
                    >= (int) Math.floor( - (radius + centerX)); j--)  {

                double radiusToPoint = Math.sqrt( Math.pow(i - centerX, 2)
                        + Math.pow(j - centerY, 2));

                if ( radiusToPoint < radius) {
                    sortedMap.put(new I2DPointImpl(i, j), radiusToPoint);
                }
            }
        }
        
        return new IFileWithPointsImpl(output, sortedMap);
    }

    class IFileWithPointsImpl implements IFileWithPoints {

        private File file;
        private SortedMap<I2DPoint, Double> sortedMap;

        public IFileWithPointsImpl(File file, SortedMap<I2DPoint, Double> sortedMap) {
            this.file = file;
            this.sortedMap = new TreeMap<>(sortedMap);
        }

        @Override
        public File getFile() {
            return file;
        }

        @Override
        public SortedMap<I2DPoint, Double> getPoints() {
            return null;
        }
    }


}
