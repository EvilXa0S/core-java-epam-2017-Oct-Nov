package com.epam.courses.jf.practice.asunyaev.second;

import com.epam.courses.jf.practice.common.second.I2DPoint;
import com.epam.courses.jf.practice.common.second.ITestableTask16;

import java.io.*;
import java.util.*;

public class Task16 implements ITestableTask16 {
    @Override
    public IFileWithPoints analyze(I2DPoint center, int radius, File output) {
        Queue<I2DPoint> pointsFound = new PriorityQueue<>(new Comparator<I2DPoint>() {
            @Override
            public int compare(I2DPoint o1, I2DPoint o2) {
                Double dist1 = dist(o1, center);
                Double dist2 = dist(o2, center);
                return dist1.compareTo(dist2);
            }
        });
        int xStart = (int) (center.getX() - radius);
        int xFinish = (int) (center.getX() + radius);
        int yStart = (int) (center.getY() - radius);
        int yFinish = (int) (center.getY() + radius);
        for (int x = xStart; x <= xFinish; x++) {
            for (int y = yStart; y <= yFinish; y++) {
                I2DPoint currentPoint = new Point2D(x, y);
                if (dist(currentPoint, center) < radius) {
                    pointsFound.offer(currentPoint);
                }
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(output))) {
            writer.write(String.valueOf(center.getX()));
            writer.write(" ");
            writer.write(String.valueOf(center.getY()));
            writer.write("\n");
            while (!pointsFound.isEmpty()) {
                I2DPoint currentPoint = pointsFound.poll();
                writer.write(String.valueOf(currentPoint.getX()));
                writer.write(" ");
                writer.write(String.valueOf(currentPoint.getY()));
                writer.write(" ");
                writer.write(String.valueOf(dist(currentPoint, center)));
                writer.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new FileWithPoints(output);
    }

    private double dist (I2DPoint point1, I2DPoint point2) {
        return Math.sqrt(Math.pow(point1.getX() - point2.getX(), 2) +  Math.pow(point1.getY() - point2.getY(), 2));
    }

    private class FileWithPoints implements IFileWithPoints {

        private File file;

        FileWithPoints (File file) {
            this.file = file;
        }

        public File getFile() {
            return file;
        }

        public SortedMap<I2DPoint, Double> getPoints() {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))){
                String[] centerInfo =  reader.readLine().split("\\s");
                double centerX = Double.parseDouble(centerInfo[0]);
                double centerY = Double.parseDouble(centerInfo[1]);
                I2DPoint center = new Point2D(centerX, centerY);
                SortedMap<I2DPoint, Double> points = new TreeMap<>(new Comparator<I2DPoint>() {
                    @Override
                    public int compare(I2DPoint o1, I2DPoint o2) {
                        Double dist1 = dist(o1, center);
                        Double dist2 = dist(o2, center);
                        if (Double.compare(dist1, dist2) == 0) {
                            return -1;
                        } else {
                            return dist1.compareTo(dist2);
                        }
                    }
                });
                String string;
                while ((string = reader.readLine()) != null) {
                    String[] row = string.split("\\s");
                    I2DPoint currentPoint = new Point2D(Double.parseDouble(row[0]), Double.parseDouble(row[1]));
                    points.put(currentPoint, Double.parseDouble(row[2]));
                }
                return points;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }


}
