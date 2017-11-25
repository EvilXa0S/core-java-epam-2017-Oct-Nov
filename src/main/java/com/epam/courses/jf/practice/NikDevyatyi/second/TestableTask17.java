package com.epam.courses.jf.practice.NikDevyatyi.second;

import com.epam.courses.jf.practice.common.second.I2DPoint;
import com.epam.courses.jf.practice.common.second.ITestableTask17;

import java.util.*;

public class TestableTask17 implements ITestableTask17 {
    @Override
    public Set<I2DPoint> analyze(Set<ISegment> segments) {
        List<ISegment> allSegments = new ArrayList<ISegment>(segments);//ArrayList of points to iterate
        Set<I2DPoint> resultLines = new HashSet<>();//createResult;
        int j =0;
        boolean flag = false;
        SortedMap<I2DPoint, Double> result = new TreeMap<>(new Comparator<I2DPoint>() {
            @Override
            public int compare(I2DPoint o1, I2DPoint o2) {
                Double first = o1.getX();
                Double second = o2.getX();
                if (Double.compare(first, second) == 0) {
                    return -1;
                } else {
                    return first.compareTo(second);
                }
            }
        });
        for(int i = 0; i < allSegments.size(); i++){
            j =i+1;
            for(;j < allSegments.size(); j++) {
                double A1 = coefs(allSegments.get(i))[0];double B1 = coefs(allSegments.get(i))[1];double C1 = coefs(allSegments.get(i))[2];
                double A2 = coefs(allSegments.get(j))[0];double B2 = coefs(allSegments.get(j))[1];double C2 = coefs(allSegments.get(j))[2];
                if(A1!=0 && B1!= 0){
                    if(A2/A1==B2/A1){
                        continue;
                    }
                }
                if((A1+A2)==0 ){
                    continue;
                }
                if((B1+B2)==0 ){
                    continue;
                }

                if(allSegments.get(i).equals(allSegments.get(j))){
                    continue;

                }
                if(maxX(allSegments.get(i)) <
                        minX(allSegments.get(j))){
                    continue;
                }
                if(maxY(allSegments.get(i)) <
                        minY(allSegments.get(j))){
                    continue;
                }
                I2DPoint intersectionPoint = findIntersection(allSegments.get(i),allSegments.get(j));
                if(intersectionPoint.getX()> Math.min(maxX(allSegments.get(i)),maxX(allSegments.get(j)))){
                    continue;
                }
                if(intersectionPoint.getX()< Math.max(minX(allSegments.get(i)),minX(allSegments.get(j)))){
                    continue;
                }
                if(intersectionPoint.getY()> Math.min(maxY(allSegments.get(i)),maxY(allSegments.get(j)))){
                    continue;
                }
                if(intersectionPoint.getY()< Math.max(minY(allSegments.get(i)),minY(allSegments.get(j)))){
                    continue;
                }

                result.put(findIntersection(allSegments.get(i),allSegments.get(j)),
                        findIntersection(allSegments.get(i),allSegments.get(j)).getX());

            }
        }
        double min = result.values().iterator().next();
        for (Map.Entry<I2DPoint, Double> entry : result.entrySet()) {
            I2DPoint key = entry.getKey();
            Double value = entry.getValue();
            if (min==value){
                resultLines.add(key);
                min = value;
            }else break;
        }
        return resultLines;
    }
    public double maxX(ISegment segment){
        return  Math.max(segment.first().getX(),segment.second().getX());
    }
    public double maxY(ISegment segment){
        return  Math.max(segment.first().getY(),segment.second().getY());
    }
    public double minX(ISegment segment){
        return  Math.min(segment.first().getX(),segment.second().getX());
    }
    public double minY(ISegment segment){
        return  Math.min(segment.first().getY(),segment.second().getY());
    }
    public double[] coefs(ITestableTask17.ISegment segment){
        double A = segment.first().getY() - segment.second().getY();
        double B = segment.second().getX() - segment.first().getX();
        double C = segment.first().getX() * segment.second().getY() -  segment.second().getX() * segment.first().getY();
        return new double[]{A,B,C};
    }
    I2DPoint findIntersection(ISegment segment1, ISegment segment2){
        double A1 = coefs(segment1)[0];double B1 = coefs(segment1)[1];double C1 = coefs(segment1)[2];
        double A2 = coefs(segment2)[0];double B2 = coefs(segment2)[1];double C2 = coefs(segment2)[2];
        double y = 0;
        double x = 0;

        if(B2 == 0. && A1 == 0.){
            y = -C1/B1;
            x = -C2/A2;
            return new D2Point(x,y);
        }
        else if(B1 == 0. && A2 == 0.){
            x = -C1/A1;
            y = -C2/B2;
            return new D2Point(x,y);
        }
        else  if(A1 == 0.){
            y = -C1/B1;
            x = (B2*C1/B1 -C2)/A2;
            return new D2Point(x,y);
        }
        else if(A2 == 0.){
            y = - C2/B2;
            x = (B1*C2/B2 - C1)/A1;
            return new D2Point(x,y);
        }
        else if(B1 == 0.){
            x = -C1/A1;
            y = (A2*C1/A1 - C2)/B2;
            return new D2Point(x,y);
        }
        else if (B2 == 0.){
            x= -C2/A2;
            y = (C2*A1/A2 - C1)/B1;
            return new D2Point(x,y);
        }
        y = ((A1/A2) * C2 - C1)/((B1 - (A1/A2) * B2));
        x = -(B1 * y + C1)/A1;
        return new D2Point(x,y);


    }

}
