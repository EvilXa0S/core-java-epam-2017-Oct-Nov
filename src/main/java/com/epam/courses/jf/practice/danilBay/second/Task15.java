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
        I2DPoint thirdPoint;
        for( int i=0; i<allPoints.size(); i++){
             firstPoint=allPoints.get(i);

            for(int j=i+1;j<allPoints.size(); j++){
                secondPoint=allPoints.get(j);

                set=new HashSet<>();
                for(int k=j+1;k<allPoints.size(); k++){

                    thirdPoint = allPoints.get(k);
                    set.add(firstPoint);
                    set.add(secondPoint);

                    double deltaY=thirdPoint.getY()-firstPoint.getY()/secondPoint.getY()-firstPoint.getY();
                    double deltaX=thirdPoint.getX()-firstPoint.getX()/secondPoint.getX()-firstPoint.getX();

                    if(deltaY==deltaX || thirdPoint.getX()==firstPoint.getX() && firstPoint.getX()==secondPoint.getX()){

                        set.add(thirdPoint);

                    }

                }
                if(set.size()>2){
                    resultSet.add(new Line(set));
                }

            }

        }




        try (PrintWriter writer = new PrintWriter(output)) {
            for (ILine line : resultSet) {
                for (I2DPoint point : line.getPoints()) {
                    writer.write(point.getX() + " " + point.getY() + "\t");
                }
                writer.write(System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new FileWithLines(output,resultSet);
    }

    class FileWithLines implements IFileWithLines{
        File output;
        Set<ILine> lineSet;
        FileWithLines(File out, Set<ILine>l){
            output=out;
            lineSet=l;
        }
        @Override
        public File getFile() {
            return output;
        }

        @Override

        public Set<ILine> getLines() {

            return lineSet;
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
        }

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
        Set<I2DPoint> d=new HashSet<>();
        d.add(new Point(1,1));
        d.add(new Point(2,2));
        d.add(new Point(3,3));
        d.add(new Point(3,0));
        d.add(new Point(3,-3));
        d.add(new Point(6,1));
        File file = new File("C:\\Users\\dana\\Desktop\\СТАТ_функц\\s.txt");
        FileWithLines f= (FileWithLines) m.analyze(d,file);
        for(ILine l :f.getLines()){
            for(I2DPoint p : l.getPoints()) {
                System.out.print("x=" + p.getX()+" y=" + p.getY()+" ");
            }
            System.out.println();

        }

    }
}
