package com.epam.courses.jf.practice.NikDevyatyi.second;

import com.epam.courses.jf.practice.common.second.I2DPoint;
import com.epam.courses.jf.practice.common.second.ITestableTask15;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestableTask15 implements ITestableTask15 {
    @Override
    public IFileWithLines analyze(Set<I2DPoint> points, File output) {
        Set<ILine> lineFound = new HashSet<>();//set of founded lines
        List<I2DPoint> allPoints = new ArrayList<I2DPoint>(points);//ArrayList of points to iterate
        Set<ILine> resultLines = new HashSet<>();//createResult;
        int j =0;
        boolean flag = false;
        for(int i = 0; i < allPoints.size(); i++){
            j =i+1;
            if(lineFound.isEmpty()){
                lineFound.add(new Line(allPoints.get(i),allPoints.get(i+1)));
            }
            for(;j < allPoints.size(); j++){

                if(!((allPoints.get(i).getY() == allPoints.get(j).getY())&&(allPoints.get(i).getX() == allPoints.get(j).getX()))){
                    ILine tempLine = new Line(allPoints.get(i),allPoints.get(j));
                    if (!lineFound.contains(tempLine)){
                        flag = true;
                        for(ILine item: lineFound){
                            if(!checkLine(item,tempLine.getPoints())){
                                flag = false;
                            }
                        }
                        if(flag){
                            lineFound.add(tempLine);
                        }
                    }
                }
            }
        }

        for(ILine line: lineFound){
            if(check(line,points)){
                resultLines.add(line);
            }
        }

        IFileWithLines result = new FileWithLines(resultLines,output);
        return result;
    }
    public double[] coefs(ILine line){
        List<I2DPoint> points = new ArrayList<>(line.getPoints());
        double A = points.get(0).getY() - points.get(1).getY();
        double B = points.get(1).getX() - points.get(0).getX();
        double C = points.get(0).getX()*points.get(1).getY() -  points.get(1).getX()*points.get(0).getY();
        return new double[]{A,B,C};
    }
    public boolean check(ILine line, Set<I2DPoint> points){
        double[] coef = coefs(line);
        for(I2DPoint point: points){
            if(!(line.getPoints().contains(point))){
                if((coef[0]*point.getX() + coef[1]*point.getY()+coef[2])==0){
                    return true;
                }
            }
        }
        return false;
    }
    public boolean checkLine(ILine line, Set<I2DPoint> points){
        double[] coef = coefs(line);
        int counter=0;
        for(I2DPoint point: points){
             if((coef[0]*point.getX() + coef[1]*point.getY()+coef[2])==0){
                 counter++;
             }
        }
        if (counter ==2){
            return false;
        }
        return true;
    }
    public class Line implements ITestableTask15.ILine{
        I2DPoint firstPoint;
        I2DPoint secondPoint;
        public Line(I2DPoint firstPoint, I2DPoint secondPoint){
            this.firstPoint = firstPoint;
            this.secondPoint = secondPoint;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Line line = (Line) o;

            if (firstPoint != null ? !firstPoint.equals(line.firstPoint) : line.firstPoint != null) return false;
            return secondPoint != null ? secondPoint.equals(line.secondPoint) : line.secondPoint == null;
        }

        @Override
        public int hashCode() {
            int result = firstPoint != null ? firstPoint.hashCode() : 0;
            result = 31 * result + (secondPoint != null ? secondPoint.hashCode() : 0);
            return result;
        }

        @Override
        public Set<I2DPoint> getPoints() {
            Set<I2DPoint> result = new HashSet<>();
            result.add(firstPoint);
            result.add(secondPoint);
            return result;

        }
    }
    public class FileWithLines implements IFileWithLines {
        private File file;
        public FileWithLines(Set<ILine>result, File file){
            this.file = file;
            StringBuilder lineBuffer = new StringBuilder();
            try(BufferedWriter writer = new BufferedWriter(new FileWriter(this.file))) {
                for( ILine line: result){
                    List<I2DPoint> list = new ArrayList<I2DPoint>(line.getPoints());
                    lineBuffer.append(list.get(0).getX());
                    lineBuffer.append(" ");
                    lineBuffer.append(list.get(0).getY());
                    lineBuffer.append(" ");
                    lineBuffer.append(list.get(1).getX());
                    lineBuffer.append(" ");
                    lineBuffer.append(list.get(1).getY());
                    writer.write(lineBuffer.toString());
                    writer.newLine();
                    lineBuffer.setLength(0);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        @Override
        public File getFile() {
            return file;
        }

        @Override
        public Set<ILine> getLines() {
            Set<ILine> result = new HashSet<>();
            try(BufferedReader br = new BufferedReader(new FileReader(file))) {
                String lineString;
                String[] temp;
                while ((lineString = br.readLine()) != null) {
                    temp = lineString.split(" ");
                    result.add(new Line(new D2Point( Double.parseDouble(temp[0]), Double.parseDouble(temp[1])),
                            new D2Point( Double.parseDouble(temp[2]), Double.parseDouble(temp[3]))));
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
