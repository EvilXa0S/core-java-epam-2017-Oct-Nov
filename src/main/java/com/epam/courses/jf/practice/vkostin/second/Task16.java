package com.epam.courses.jf.practice.vkostin.second;

import com.epam.courses.jf.practice.common.second.I2DPoint;
import com.epam.courses.jf.practice.common.second.ITestableTask16;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.SortedMap;
import java.util.TreeMap;

public class Task16 implements ITestableTask16 {

    private double checkDistance(I2DPoint center, I2DPoint current) {
        return Math.sqrt((center.getX() - current.getX()) * (center.getX() - current.getX())
                + (center.getY() - current.getY()) * (center.getY() - current.getY()));
    }

    @Override
    public IFileWithPoints analyze(I2DPoint center, int radius, File output) {

        SortedMap<I2DPoint, Double> points = new TreeMap<>((o1, o2) ->
                (checkDistance(o1, center) < checkDistance(o2, center)) ? -1 : 1);

        Double xCenter = center.getX();
        Double yCenter = center.getY();
        Double distance;

        for (double x = (int) (xCenter - radius); x <= xCenter + radius; ++x) {
            for (double y = (int) (yCenter + radius); y >= yCenter - radius; --y) {
                distance = Math.sqrt(((x - xCenter) * (x - xCenter))
                        + ((y - yCenter) * (y - yCenter)));
                if (distance < radius) {
                    points.put(new TwoDPoint(x, y), distance);
                }
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(output))) {
            for (I2DPoint point : points.keySet()) {
                writer.write("" + point.getX() + " " + point.getY());
                writer.newLine();
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new FileWithPoints(output, points);
    }


    private static class FileWithPoints implements IFileWithPoints {

        private File file;
        private SortedMap<I2DPoint, Double> points;

        FileWithPoints(File file, SortedMap<I2DPoint, Double> points) {
            this.file = file;
            this.points = points;
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
