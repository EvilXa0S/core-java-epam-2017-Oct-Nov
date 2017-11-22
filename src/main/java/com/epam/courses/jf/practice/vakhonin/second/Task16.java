package com.epam.courses.jf.practice.vakhonin.second;

import com.epam.courses.jf.practice.common.second.I2DPoint;
import com.epam.courses.jf.practice.common.second.ITestableTask16;

import java.io.*;
import java.util.*;
import java.util.logging.Logger;

public class Task16 implements ITestableTask16 {

    class Point2D implements I2DPoint{
        double x, y;

        Point2D(double x, double y){
            this.x = x;
            this.y = y;
        }

        @Override
        public double getX() {
            return x;
        }

        @Override
        public double getY() {
            return y;
        }
    }

    public double calculateDistance(I2DPoint point1, I2DPoint point2){
        double dist = Math.sqrt(Math.pow(point1.getX() - point2.getX(), 2) + Math.pow(point1.getY() - point2.getY(), 2));
        return dist;
    }


    public boolean isPointInCircle(I2DPoint point, I2DPoint center, int radius){

        double distance = calculateDistance(point, center);

        if(distance < Double.valueOf(radius)){
            return true;
        }
        else {
            return false;
        }

    }
//    static Logger log = Logger.getLogger(Task16.class.getName());

    String stringOfPointForFile(I2DPoint point, I2DPoint center){
        String str = String.valueOf(point.getX()) + " " + String.valueOf(point.getY()) + " " + String.valueOf(calculateDistance(point, center) + "\n");
        return str;
    }


    @Override
    public IFileWithPoints analyze(I2DPoint center, int radius, File output) {

        double xCenter = (int) center.getX();
        double yCenter = (int) center.getY();

        Comparator<I2DPoint> comparator = (p1, p2) -> {
            Double distance1 = calculateDistance(p1, center);
            Double distance2 = calculateDistance(p2, center);
            int comparison = distance1.compareTo(distance2);
            if(comparison == 0){
                return -1;
            }
            else{
                return comparison;
            }
        };

        SortedMap<I2DPoint, Double> map = new TreeMap<>(comparator);

        I2DPoint point;

        int xFirst = (int) (xCenter - radius - 1);
        int xEnd = (int) (xCenter + radius + 1);
        int yFirst = (int) (yCenter - radius - 1);
        int yEnd = (int) (yCenter + radius + 1);

        for (int j = xFirst; j <= xEnd; j++) {
            for (int k = yFirst; k <= yEnd; k++) {
                point = new Point2D(j, k);
                if (isPointInCircle(point, center, radius)) {
                    map.put(point, calculateDistance(point, center));
                }
            }
        }



        try (BufferedWriter writer = new BufferedWriter(new FileWriter(output))) {



            writer.write(String.valueOf(center.getX()));
            writer.write(" ");
            writer.write(String.valueOf(center.getY()));
            writer.write("\n");

            for (Map.Entry<I2DPoint, Double> entry : map.entrySet()) {
                point = entry.getKey();
                writer.write(stringOfPointForFile(point, center));

//                writer.write(String.valueOf(point.getX()));
//                writer.write(" ");
//                writer.write(String.valueOf(point.getY()));
//                writer.write(" ");
//                writer.write(String.valueOf(calculateDistance(point, center)));
//                writer.write("\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return new FileWithPoints(output, comparator);
    }


    private class FileWithPoints implements IFileWithPoints {

        private File file;

        private Comparator<I2DPoint> comparator;

        FileWithPoints (File file, Comparator<I2DPoint> comparator) {
            this.file = file;
            this.comparator = comparator;
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

                SortedMap<I2DPoint, Double> points = new TreeMap<>(comparator);
                String string;
                while ((string = reader.readLine()) != null) {
                    String[] row = string.split("\\s");
                    I2DPoint currentPoint = new Point2D(Double.parseDouble(row[0]), Double.parseDouble(row[1]));
                    points.put(currentPoint, Double.parseDouble(row[2]));
                }
//                log.info(mapString(points));
                return points;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}