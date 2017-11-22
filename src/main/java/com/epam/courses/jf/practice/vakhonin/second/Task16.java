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

    public static double calculateDistance(I2DPoint point1, I2DPoint point2){
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
        String str = String.valueOf(point.getX()) + "\t" + String.valueOf(point.getY()) + "\t" + String.valueOf(calculateDistance(point, center) + "\n");
        return str;
    }

    List<String> readFile(File file){
        try (BufferedReader reader = new BufferedReader(new FileReader(file))){

            String str;
            List<String> result = new ArrayList<>();

            while ((str = reader.readLine()) != null) {
                result.add(str);
            }

            return result;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    I2DPoint stringToPoint(String string){
        String[] splitString = string.split("\\t");
        Double x = Double.parseDouble(splitString[0]);
        Double y = Double.parseDouble(splitString[1]);
        I2DPoint point = new Point2D(x, y);
        return point;
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



//            writer.write(String.valueOf(center.getX()));
//            writer.write(" ");
//            writer.write(String.valueOf(center.getY()));
//            writer.write("\n");

            writer.write(stringOfPointForFile(center, center));

            for (Map.Entry<I2DPoint, Double> entry : map.entrySet()) {
                point = entry.getKey();
                writer.write(stringOfPointForFile(point, center));

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

        public File getFile(){
            return file;
        }

        public SortedMap<I2DPoint, Double> getPoints() {

            List<String> pointsString = readFile(file);
            SortedMap<I2DPoint, Double> pointsMap = new TreeMap<>(comparator);

            I2DPoint center = stringToPoint(pointsString.get(0));
            int size = pointsString.size();
            I2DPoint point;
            for(int j = 1; j < size; j++){
                point = stringToPoint(pointsString.get(j));
                pointsMap.put(point, Task16.calculateDistance(point, center));
            }

            return pointsMap;
        }
    }
}