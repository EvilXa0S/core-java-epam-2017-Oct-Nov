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
import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

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

    @Override
    public IFileWithPoints analyze(I2DPoint center, int radius, File output) {
        SortedMap<I2DPoint, Double> points = new TreeMap<>((p1, p2) -> {
            double r1 = Math.pow(p1.getX() - center.getX(), 2) + Math.pow(p1.getY() - center.getY(), 2);
            double r2 = Math.pow(p2.getX() - center.getX(), 2) + Math.pow(p2.getY() - center.getY(), 2);
            return Double.compare(r1, r2);
        });
        for(long x = (long)(center.getX() - radius); x < Math.ceil(center.getX() - radius); ++x){
            for(long y = (long)(center.getY() - radius); y < Math.ceil(center.getY() - radius); ++y){
                double distance = Math.sqrt(Math.pow(x - center.getX(), 2) + Math.pow(y - center.getY(), 2));
                if(distance < radius){
                    points.put(new Point2D(x, y), distance);
                }
            }
        }
        try(FileOutputStream fileOutputStream = new FileOutputStream(output);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)){
            points.entrySet().forEach(obj -> {
                try {
                    objectOutputStream.writeObject(obj);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new FileWithPoints(output, (Comparator<I2DPoint>) points.comparator());

    }

    public static class FileWithPoints implements IFileWithPoints{
        private final File file;
        private final Comparator<I2DPoint> comparator;
        private SortedMap<I2DPoint, Double> points;

        FileWithPoints(File file, Comparator<I2DPoint> comparator) {
            this.file = file;


            this.comparator = comparator;
        }

        public File getFile(){
            return file;
        }

        public SortedMap<I2DPoint, Double> getPoints(){
            if(points == null){
                points = new TreeMap<>(comparator);
                try(FileInputStream fileInputStream = new FileInputStream(file);
                    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)){
                    Map.Entry<I2DPoint, Double> entry;
                    while ((entry = (Map.Entry<I2DPoint, Double>) objectInputStream.readObject()) != null){
                        points.put(entry.getKey(), entry.getValue());
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            return points;
        }
    }
}