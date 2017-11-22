package com.epam.courses.jf.practice.bborzdov;

import com.epam.courses.jf.practice.common.second.I2DPoint;
import com.epam.courses.jf.practice.common.second.ITestableTask16;

import java.io.*;
import java.math.BigDecimal;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by bogdan on 22.11.17.
 */
public class Task16 implements ITestableTask16 {
    @Override
    public IFileWithPoints analyze(I2DPoint center, int radiusInt, File output) {
        FileWithPoints file = new FileWithPoints(output);
        double radius = radiusInt / 2;
        SortedMap<I2DPoint, Double> points = new TreeMap<>();
        int decimal = (int) center.getY();
        double stepY = 1 - (center.getY() - decimal);
        decimal = (int) center.getX();
        double stepX = 1 - (center.getX() - decimal);
        for (double i = (center.getY() + radius); i > (center.getY() - radius -1) ; i -= stepY) {
            for (double j = (center.getX() - radius); j <(center.getX()+radius+1) ; j += stepX) {
                Point2D point = new Point2D(j,i);
                BigDecimal distance =new BigDecimal(Task15.distance(point,center));
                distance = distance.setScale(4, BigDecimal.ROUND_DOWN);
                point.setDistance(distance.doubleValue());
                points.put(point,distance.doubleValue());
            }
        }
        try{
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(output));
            objectOutputStream.write(points.size());
            for (I2DPoint point : points.keySet()) {
                objectOutputStream.writeObject(point);
            }
        }catch (IOException e){
            System.out.println("Failed when writing to file!\n");
            e.printStackTrace();
        }
        return file;
    }
    private class FileWithPoints implements ITestableTask16.IFileWithPoints{
        private File output;
        public FileWithPoints(File output){
            this.output = output;
        }
        @Override
        public File getFile() {
            return output;
        }

        @Override
        public SortedMap<I2DPoint, Double> getPoints() {
            SortedMap<I2DPoint, Double> points = new TreeMap<>();
            try{
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(output));
                int steps = ois.read();
                for (int i = 0; i < steps; i++) {
                    Point2D point =(Point2D) ois.readObject();
                    points.put(point,point.getDistance());
                }
            }catch (IOException | ClassNotFoundException e){
                System.out.println("Fail when reading from file\n");
            }
            return points;
        }
    }

}
