package com.epam.courses.jf.practice.danilBay.second;

import com.epam.courses.jf.practice.common.second.I2DPoint;
import com.epam.courses.jf.practice.common.second.ITestableTask15;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Task15 implements ITestableTask15 {
    @Override
    public IFileWithLines analyze(Set<I2DPoint> points, File output) {

        List<I2DPoint> allPoints=new ArrayList<>(points);
        Set<ILine> resultSet=new HashSet<>();
        Set<I2DPoint> set=new HashSet<>();
        //для начала найдём все прямые, образуемые точками
        Set<I2DPoint> lineSet=new HashSet<>();
        I2DPoint firstPoint;
        I2DPoint secondPoint;
        for( int i=0; i<allPoints.size(); i++){
             firstPoint=allPoints.get(i);

            for(int j=i+1;j<allPoints.size(); j++){
                secondPoint=allPoints.get(j);
                //выбираем 2 неравные точки
                if (firstPoint.equals(secondPoint)) {
                    continue;
                }
                double K =(secondPoint.getY()-firstPoint.getY())/(firstPoint.getX() - secondPoint.getX());
                double B= firstPoint.getY()/(firstPoint.getX()*K);
                lineSet.add(new Point(K,B));
                //проходим по всем последующим точкам в наборе и выясняем, лежат ли они на прямой
                set.clear();
                for(I2DPoint thirdPoint : allPoints){


                    set.add(firstPoint);
                    set.add(secondPoint);

                    double deltaY=secondPoint.getY()-firstPoint.getY();
                    double deltaX=secondPoint.getX()-firstPoint.getX();

                    if(!thirdPoint.equals(firstPoint) && !thirdPoint.equals(secondPoint) &&
                        (thirdPoint.getX()-firstPoint.getX()*deltaY)==(thirdPoint.getY()-firstPoint.getY())*deltaX ){

                        set.add(thirdPoint);

                    }

                }
                if(set.size()>2){
                    resultSet.add(new Line(set));
                }

            }

        }

        for(I2DPoint line : lineSet){
            double K = line.getX();
            double B = line.getY();

            for(I2DPoint isBelongToLine : points){
                if( (isBelongToLine.getX()*K + B) == isBelongToLine.getY() ){
                    set.add(isBelongToLine);
                }
            }
            if(set.size()>2){
                resultSet.add(new Line(set));
            }
            set.clear();

        }

        try (PrintWriter writer = new PrintWriter(output)) {
            for (ILine line : resultSet) {
                for (I2DPoint point : line.getPoints()) {
                    writer.write(point.getX() + " " + point.getY() + "\t");
                }
                writer.write("\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new FileWithLines(output);
    }

    class FileWithLines implements IFileWithLines{
        File output;

        FileWithLines(File out){
            output=out;
        }
        @Override
        public File getFile() {
            return output;
        }

        @Override
        public Set<ILine> getLines() {
            Set<ILine> result = new HashSet<>();
            try {
                List<String> interList = Files
                        .lines(Paths.get(output.getPath()), Charset.forName("ISO-8859-1"))
                        .collect(Collectors.toList());
                for (String s : interList) {
                    Set<I2DPoint> interSet = new HashSet<>();
                    String[] interMas = s.split("\t");
                    for (String inter : interMas) {
                        String[] nextMas = inter.split(" ");
                        interSet.add(new Point(Double.parseDouble(nextMas[0]), Double.parseDouble(nextMas[1])));
                    }
                    result.add(new Line(interSet));
                }
            } catch (Exception e) {
                System.out.println("Error read file");
                return null;
            }
            return result;
        }
//        public Set<ILine> getLines() {
//
//
//            Set<ILine> res = new HashSet();
//
//            try{
//                FileInputStream fstream = new FileInputStream(output);
//                BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
//                String strLine;
//                Set<I2DPoint> set ;
//                String[] strLineSplitted;
//                while ((strLine = br.readLine()) != null){
//                    strLineSplitted = strLine.split("\t");
//                    set = new HashSet<>();
//                    for(String line : strLineSplitted){
//                       String[] tmp=line.split(" ");
//                        set.add(new Point(Double.parseDouble(tmp[0]),Double.parseDouble(tmp[0])));
//
//                    }
//                    res.add(new Line(set));
//                }
//            }catch (IOException e){
//                e.printStackTrace();
//            }
//            return res;
//        }

    }

    class Line implements ILine{


        Set<I2DPoint> points=new HashSet<>();
        Line(Set<I2DPoint> points){

            this.points = points;
        }
        @Override
        public Set<I2DPoint> getPoints() {

            return points;
        }
    }

    public static void main(String[] args) {
        Task15 m=new Task15();

    }
}
