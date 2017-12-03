package com.epam.courses.jf.practice.danilBay.second;

import com.epam.courses.jf.practice.common.second.I2DPoint;
import com.epam.courses.jf.practice.common.second.ITestableTask16;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Task16 implements ITestableTask16 {


    private double distance(I2DPoint p1, I2DPoint p2){
        return Math.sqrt(Math.pow(p1.getX()-p2.getX(),2)+ Math.pow(p1.getY()-p2.getY(),2));
    }
    @Override
    public IFileWithPoints analyze(I2DPoint center, int radius, File output) {

        SortedMap<I2DPoint,Double> res= new TreeMap<>(new Comparator<I2DPoint>() {
            @Override
            public int compare(I2DPoint o1, I2DPoint o2) {
                if(distance(o1,center)<distance(o2,center)){
                    return -1;
                }
                else return 1;
            }
        });

        Double startX=center.getX()-radius;
        Double startY=center.getY()-radius;
        Double curX,curY;
        Double distance;
        int diam=2*radius;
        for(int x = 0; x<=diam; x++ ){
            for (int y = 0; y<=diam; y++){
                curX=(startX)+x;
                curY=startY+y;
                distance=distance(new Point(curX,curY),center);
                if(distance<=radius){
                    res.put(new Point(curX,curY),distance);
                }

            }
        }

        try (PrintWriter writer = new PrintWriter(output)) {

                for (I2DPoint point : res.keySet()) {
                    writer.write(point.getX() + " " + point.getY());
                }
                writer.write(System.lineSeparator());

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new FileWithPoints(output,res);

    }

    class FileWithPoints implements IFileWithPoints{
        File output;
        SortedMap map;
        FileWithPoints(File out, SortedMap<I2DPoint,Double> map){
            this.output=out;
            this.map=map;

        }
        @Override
        public File getFile() {
            return output;
        }

        @Override

        public SortedMap<I2DPoint,Double> getPoints() {

            return map;

        }

    }

    public static void main(String[] args) {
        Task16 task16=new Task16();
        for(I2DPoint p :task16.analyze(new Point(3,3),6,new File("ds")).getPoints().keySet()){
            System.out.println(p.getX() + " " +p.getY());
        };

    }


}
