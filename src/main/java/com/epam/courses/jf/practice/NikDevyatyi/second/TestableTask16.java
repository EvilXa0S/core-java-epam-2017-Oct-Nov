package com.epam.courses.jf.practice.NikDevyatyi.second;

import com.epam.courses.jf.practice.common.second.I2DPoint;
import com.epam.courses.jf.practice.common.second.ITestableTask16;

import java.io.*;
import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class TestableTask16 implements ITestableTask16 {
    @Override
    public IFileWithPoints analyze(I2DPoint center, int radius, File output) {
        int startPointX = (int) (center.getX() - radius);
        int endPointX = (int) (center.getX() + radius);
        int startPointY = (int) (center.getY() - radius);
        int endPointY = (int) (center.getY() + radius);
        SortedMap<I2DPoint, Double> result = new TreeMap<>(new Comparator<I2DPoint>() {
            @Override
            public int compare(I2DPoint o1, I2DPoint o2) {
                Double first = distance(o1, center);
                Double second = distance(o2, center);
                if (Double.compare(first, second) == 0) {
                    return -1;
                } else {
                    return first.compareTo(second);
                }
            }
        });
        for(int x = startPointX; x <= endPointX; x++){
            for (int y = startPointY; y <=endPointY;y++ ){
                I2DPoint tempPoint = new D2Point(x,y);
                if(distance(tempPoint,center) < radius){
                    result.put(tempPoint,distance(tempPoint,center));
                }
            }
        }
        StringBuilder lineBuffer = new StringBuilder();
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(output))) {

            for (Map.Entry<I2DPoint, Double> entry : result.entrySet()) {
                I2DPoint key = entry.getKey();
                Double value = entry.getValue();
                lineBuffer.append(key.getX());
                lineBuffer.append(" ");
                lineBuffer.append(key.getY());
                lineBuffer.append(" ");
                lineBuffer.append(value);
                writer.write(lineBuffer.toString());
                writer.newLine();
                lineBuffer.setLength(0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return new FileWithPoints(center, output);
    }
    private double distance(I2DPoint first, I2DPoint second){
        return  Math.sqrt(Math.pow(first.getX() - second.getX(), 2) +  Math.pow(first.getY() - second.getY(), 2));
    }
    public class FileWithPoints implements IFileWithPoints {
        I2DPoint center;
        File file;

        public FileWithPoints(I2DPoint center, File file) {
            this.center = center;
            this.file = file;
        }

        @Override
        public File getFile() {
            return file;
        }

        @Override
        public SortedMap<I2DPoint, Double> getPoints() {
            SortedMap<I2DPoint, Double> result = new TreeMap<>(new Comparator<I2DPoint>() {
                @Override
                public int compare(I2DPoint o1, I2DPoint o2) {
                    Double first = distance(o1, center);
                    Double second = distance(o2, center);
                    if (Double.compare(first, second) == 0) {
                        return -1;
                    } else {
                        return first.compareTo(second);
                    }
                }
            });
            try(BufferedReader br = new BufferedReader(new FileReader(file))) {
                String lineString;
                String[] temp;
                while ((lineString = br.readLine()) != null) {
                    temp = lineString.split(" ");
                    I2DPoint tempPoint = new D2Point(Double.parseDouble(temp[0]), Double.parseDouble(temp[1]));
                    result.put(tempPoint,Double.parseDouble(temp[2]));
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }
    }
}
