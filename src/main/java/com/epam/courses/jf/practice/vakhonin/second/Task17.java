package com.epam.courses.jf.practice.vakhonin.second;

import com.epam.courses.jf.practice.common.second.I2DPoint;
import com.epam.courses.jf.practice.common.second.ITestableTask17;

import java.util.*;
import java.util.logging.Logger;

/**
 * Created by igorvahonin on 13.11.17.
 */

public class Task17 implements ITestableTask17{
    static Logger log = Logger.getLogger(Task1.class.getName());


    class Line {
//        private double k, b;
//        Ax + By + C = 0
        private double a, b, c;

        double getA(){
            return a;
        }

        double getB(){
            return b;
        }

        double getC(){
            return c;
        }

        Line(ISegment segment){
            double x1,x2,y1,y2;
            x1 = segment.first().getX();
            x2 = segment.second().getX();
            y1 = segment.first().getY();
            y2 = segment.second().getY();

            if((x2-x1) == 0){
                this.b = 0;
                this.a = 1;
                this.c = -x1;
            }
            else {
                this.b = -1;
                this.a = (y2 - y1) / (x2 - x1);
                this.c = y1 - a * x1;
            }
        }

        Point pointOfIntersection(Line line2){
            double a1,a2,b1,b2, c1, c2, x0,y0;

            a1 = this.getA();
            a2 = line2.getA();

            b1 = this.getB();
            b2 = line2.getB();

            c1 = this.getC();
            c2 = line2.getC();

            y0 = (c2*a1 - a2*c1)/(b1*a2-b2*a1);
            x0 = (-c1-b1*y0)/a1;

            Point result = new Point(x0,y0);

            return result;
        }


    }

    class Point implements I2DPoint{

        private double x,y;

        Point(double x, double y){
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

//    TODO: doing one segment and use second segment!!!
    static boolean isPointOfSegment(Point point, ISegment segment){
        log.info("point: x = " + point.getX() + "   y = " + point.getY());
        log.info("segment: x1 = " + segment.first().getX() + "   y1 = " + segment.first().getY() + "   x2 = " + segment.second().getX() + "   y2 = " + segment.second().getY());

        if(     (point.getX() >= Math.min(segment.first().getX(), segment.second().getX())) &&
                (point.getX() <= Math.max(segment.first().getX(), segment.second().getX())) &&
                (point.getY() >= Math.min(segment.first().getY(), segment.second().getY())) &&
                (point.getY() <= Math.max(segment.first().getY(), segment.second().getY()))){
            log.info("" + true);
            return true;
        }
        else{
            log.info("" + false);
            return false;
        }
    }

    @Override
    public Set<I2DPoint> analyze(Set<ISegment> segments) {

        List<ISegment> segmentsList = new ArrayList<>(segments);
        List<Line> linesList = new ArrayList<>();
        Map<Double, Set<I2DPoint>> pointsMap= new TreeMap<>();

//        Logger log = Logger.getLogger(Task1.class.getName());

        for(ISegment segment: segmentsList){
            linesList.add(new Line(segment));
        }

        int size = segmentsList.size();
        Point point;
        Line lineJ, lineK;
        ISegment segmentJ, segmentK;

        for(int j = 0; j < size-1; j++){
            lineJ = linesList.get(j);
            segmentJ = segmentsList.get(j);
            for(int k = j+1; k<size; k++){
                lineK = linesList.get(k);
                segmentK = segmentsList.get(k);
                point = lineJ.pointOfIntersection(lineK);
                log.info("x = " + point.getX() + "   y = " + point.getY());
//                log.info("" + isPointOfSegment(point, segmentJ) + "" + isPointOfSegment(point, segmentK));
                if(isPointOfSegment(point, segmentJ) && isPointOfSegment(point, segmentK)){
                    log.info("INTO");
                    if(pointsMap.containsKey(point.getX())){
                        pointsMap.get(point.getX()).add(point);
                    }
                    else{
                        pointsMap.put(point.getX(), new HashSet<>());
                        pointsMap.get(point.getX()).add(point);
                    }
                }
            }
        }

        Iterator<Map.Entry<Double, Set<I2DPoint>>> it = pointsMap.entrySet().iterator();
        Set<I2DPoint> result = it.next().getValue();

        return result;
    }
}