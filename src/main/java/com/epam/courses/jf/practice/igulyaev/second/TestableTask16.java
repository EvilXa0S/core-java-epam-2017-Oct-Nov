package com.epam.courses.jf.practice.igulyaev.second;

import com.epam.courses.jf.practice.common.second.I2DPoint;
import com.epam.courses.jf.practice.common.second.ITestableTask16;

import java.io.*;
import java.util.*;

public class TestableTask16 implements ITestableTask16 {

    @Override
    public IFileWithPoints analyze(I2DPoint center, int radius, File output) {
        SortedMap<I2DPoint, Double> points = new TreeMap<>((p1, p2) -> {
            double r1 = Math.pow(p1.getX() - center.getX(), 2) + Math.pow(p1.getY() - center.getY(), 2);
            double r2 = Math.pow(p2.getX() - center.getX(), 2) + Math.pow(p2.getY() - center.getY(), 2);
            int result = Double.compare(r1, r2);
            if(result == 0){
                result =  Double.compare(p1.getX(), p2.getX());
            }
            if(result == 0){
                result =  Double.compare(p1.getY(), p2.getY());
            }
            return result;
        });
        for(long x = (long)(center.getX() - radius); x < Math.ceil(center.getX() + radius); ++x){
            for(long y = (long)(center.getY() - radius); y < Math.ceil(center.getY() + radius); ++y){
                double distance = Math.sqrt(Math.pow(x - center.getX(), 2) + Math.pow(y - center.getY(), 2));
                if(distance < radius){
                    points.put(new Point2D(x, y), distance);
                }
            }
        }
        try(FileOutputStream fileOutputStream = new FileOutputStream(output);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)){
            points.forEach((key, value) -> {
                try {
                    objectOutputStream.writeObject(key);
                    objectOutputStream.writeDouble(value);
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
                    while (true){
                        I2DPoint key = (I2DPoint) objectInputStream.readObject();
                        Double value = objectInputStream.readDouble();
                        points.put(key,value);
                    }
                }catch (EOFException e){

                } catch (IOException | ClassNotFoundException e) {
                    e.fillInStackTrace();
                }
            }
            return points;
        }
    }
}
