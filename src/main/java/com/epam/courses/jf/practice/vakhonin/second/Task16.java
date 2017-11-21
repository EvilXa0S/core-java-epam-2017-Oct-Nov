//package com.epam.courses.jf.practice.vakhonin.second;
//
//import com.epam.courses.jf.practice.common.second.I2DPoint;
//import com.epam.courses.jf.practice.common.second.ITestableTask16;
//
//import java.io.*;
//import java.util.Comparator;
//import java.util.Map;
//import java.util.SortedMap;
//import java.util.TreeMap;
//
///**
// * Created by igorvahonin on 13.11.17.
// */
//public class Task16 implements ITestableTask16{
//
//    class Point implements I2DPoint{
//        private double x, y;
//
//        Point(double x, double y){
//            this.x = x;
//            this.y = y;
//        }
//
//        public void setX(double x) {
//            this.x = x;
//        }
//
//        public void setY(double y) {
//            this.y = y;
//        }
//
//        public double getX() {
//            return x;
//        }
//
//        public double getY() {
//            return y;
//        }
//
//
//
//    }
//
//    public double distance(I2DPoint point1, I2DPoint point2){
//        double result;
//        result = Math.sqrt(Math.pow(point1.getX() - point2.getX(), 2) + Math.pow(point1.getY() - point2.getY(), 2));
//        return result;
//    }
//
//    public boolean isPointInCircle(I2DPoint point, I2DPoint center, int radius){
//
//        double distance = distance(point, center);
//
//        if(distance < Double.valueOf(radius)){
//            return true;
//        }
//        else {
//            return false;
//        }
//
//    }
//
//    @Override
//    public IFileWithPoints analyze(I2DPoint center, int radius, File output) {
//        int integerCenterX = (int) center.getX();
//        int integerCenterY = (int) center.getY();
//        int j = integerCenterX;
//        int k = integerCenterY;
//
//        Comparator<I2DPoint> comparator = new Comparator<I2DPoint>() {
//            @Override
//            public int compare(I2DPoint p1, I2DPoint p2) {
//                double r1 = distance(p1, center);
//                double r2 = distance(p2, center);
//                return Double.compare(r1, r2);
//            }
//        };
//
//
//        SortedMap<I2DPoint, Double> map = new TreeMap<>(comparator);
//
//        Point point = new Point(j,k);
//
//        while(isPointInCircle(point, center, radius)){
//            while(isPointInCircle(point, center, radius)){
//                map.put(point, distance(point, center));
//                point.setX(point.getX()+1);
//            }
//            point.setX(integerCenterX);
//            while(isPointInCircle(point, center, radius)){
//                map.put(point, distance(point, center));
//                point.setX(point.getX()-1);
//            }
//            point.setY(point.getY()+1);
//            point.setX(integerCenterX);
//        }
//
//        while(isPointInCircle(point, center, radius)){
//            while(isPointInCircle(point, center, radius)){
//                map.put(point, distance(point, center));
//                point.setX(point.getX()+1);
//            }
//            point.setY(integerCenterY);
//            while(isPointInCircle(point, center, radius)){
//                map.put(point, distance(point, center));
//                point.setX(point.getX()-1);
//            }
//            point.setX(integerCenterX);
//            point.setY(point.getY()-1);
//        }
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(output))) {
//            if(!map.isEmpty()) {
//                for(I2DPoint p: map.keySet()) {
//                    writer.write("" + p.getX());
//                    writer.write(" ");
//                    writer.write("" + p.getY());
//                    writer.write(" ");
//                    writer.write("" + map.get(p));
//                    writer.write("\n");
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return new FileWithPoints(output, comparator);
//    }
//
//
//    public static class FileWithPoints implements IFileWithPoints{
//        private final File file;
//        private SortedMap<I2DPoint, Double> points;
//        private Comparator<I2DPoint> comparator;
//
//        FileWithPoints(File file, Comparator<I2DPoint> comparator) {
//            this.file = file;
//            this.comparator = comparator;
//        }
//
//        public File getFile(){
//            return file;
//        }
//
//        public SortedMap<I2DPoint, Double> getPoints(){
//            if(points == null){
//                points = new TreeMap<>(comparator);
//                try(FileInputStream fileInputStream = new FileInputStream(file);
//                    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)){
//                    Map.Entry<I2DPoint, Double> entry;
//                    while ((entry = (Map.Entry<I2DPoint, Double>) objectInputStream.readObject()) != null){
//                        points.put(entry.getKey(), entry.getValue());
//                    }
//                } catch (IOException | ClassNotFoundException e) {
//                    e.printStackTrace();
//                }
//            }
//            return points;
//        }
//    }
//
//}


//



package com.epam.courses.jf.practice.vakhonin.second;

import com.epam.courses.jf.practice.common.second.I2DPoint;
import com.epam.courses.jf.practice.common.second.ITestableTask16;

import java.io.*;
import java.util.*;

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

        void setX(double x){
            this.x = x;
        }

        void setY(double y){
            this.y = y;
        }
    }

//    TODO: SQRT!!!


    public double distance(I2DPoint point1, I2DPoint point2){
        double result;
        result = Math.sqrt(Math.pow(point1.getX() - point2.getX(), 2) + Math.pow(point1.getY() - point2.getY(), 2));
        return result;
    }


    public boolean isPointInCircle(I2DPoint point, I2DPoint center, int radius){

        double distance = distance(point, center);

        if(distance < Double.valueOf(radius)){
            return true;
        }
        else {
            return false;
        }

    }


    @Override
    public IFileWithPoints analyze(I2DPoint center, int radius, File output) {
        int integerCenterX = (int) center.getX();
        int integerCenterY = (int) center.getY();
        int j = integerCenterX;
        int k = integerCenterY;

        Comparator<I2DPoint> comparator = (p1, p2) -> {
            Double distance1 = dist(p1, center);
            Double distance2 = dist(p2, center);
            return distance1.compareTo(distance2);
        };

        SortedMap<I2DPoint, Double> map = new TreeMap<>(comparator);
        Queue<I2DPoint> pointsFound = new PriorityQueue<>(comparator);

        Point2D point = new Point2D(j,k);

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