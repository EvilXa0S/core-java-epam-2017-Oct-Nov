package com.epam.courses.jf.practice.klimenko.second;

import com.epam.courses.jf.practice.common.second.I2DPoint;
import com.epam.courses.jf.practice.common.second.ITestableTask16;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.SortedMap;
import java.util.TreeMap;

public class TestableTask16Impl implements ITestableTask16 {
    private double distance2(I2DPoint a, I2DPoint b) {
        double dx = a.getX() - b.getX();
        double dy = a.getY() - b.getY();
        return dx * dx + dy * dy;
    }

    @Override
    public IFileWithPoints analyze(I2DPoint center, int radius, File output) {
        int left = (int) Math.floor(center.getX() - radius);
        int right = (int) Math.ceil(center.getX() + radius);
        int bottom = (int) Math.floor(center.getY() - radius);
        int top = (int) Math.ceil(center.getY() + radius);
        double radius2 = radius * radius;
        SortedMap<I2DPoint, Double> points = new TreeMap<>(
                (a, b) -> {
                    int cmp = Double.compare(distance2(a, center), distance2(b, center));
                    if (cmp == 0) {
                        cmp = Double.compare(a.getX(), b.getX());
                    }
                    if (cmp == 0) {
                        cmp = Double.compare(a.getY(), b.getY());
                    }
                    return cmp;
                }
        );

        for (int ix = left; ix <= right; ++ix) {
            for (int iy = bottom; iy <= top; ++iy) {
                I2DPoint point = new Point2DImpl(ix, iy);
                double dist2 = distance2(point, center);
                if (dist2 < radius2) {
                    points.put(point, Math.sqrt(dist2));
                }
            }
        }

        FileImpl file = new FileImpl(output, points);
        try {
            file.writeOut();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return file;
    }

    private class FileImpl implements IFileWithPoints {
        File file;
        SortedMap<I2DPoint, Double> points;

        FileImpl(File file, SortedMap<I2DPoint, Double> points) {
            this.file = file;
            this.points = points;
        }

        void writeOut() throws IOException {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (I2DPoint point : points.keySet()) {
                    writer.write(point.getX() + " " + point.getY());
                    writer.newLine();
                }
            }
        }

        @Override
        public File getFile() {
            return file;
        }

        @Override
        public SortedMap<I2DPoint, Double> getPoints() {
            return points;
        }
    }
}
