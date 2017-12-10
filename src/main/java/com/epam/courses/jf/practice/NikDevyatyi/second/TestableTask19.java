package com.epam.courses.jf.practice.NikDevyatyi.second;

import com.epam.courses.jf.practice.common.second.ITestableTask19;

import java.util.*;

public class TestableTask19 implements ITestableTask19 {
    @Override
    public int getNumberOvertaking(Set<ICar> cars, long lengthLap, int numberLaps) {
        List<ICar> allCars = new ArrayList<ICar>(cars);
        Collections.sort(allCars, Comparator.comparing((ICar car) -> car.getSpeed()));
        Collections.reverse(allCars);
        int j = 0;
        int overtaking =0;
        for(int i = 0; i < allCars.size(); i++){
            j =i+1;
            for(;j < allCars.size(); j++) {

                int v1 = allCars.get(j).getSpeed();
                int v2 = allCars.get(i).getSpeed();
                int n = numberLaps;
                int strt1 =allCars.get(j).getStartPosition();
                int strt2 =allCars.get(i).getStartPosition();
                double startLenght1 =((double)(lengthLap-strt1)/(double)lengthLap);
                double startLenght2 = ((double)(lengthLap-strt2)/(double)lengthLap);
                double lmda1 = 1/(double)v1;
                double lmda2 = 1/(double)v2;
                double summ;
                if(strt1>strt2){
                    summ = 1-(startLenght1/startLenght2)*(lmda1/lmda2-1)+(lmda1/lmda2-1)*n;
                }else if (strt1<strt2){
                    summ = (lmda1/lmda2-1)*(n)+startLenght2/startLenght1*(lmda1/lmda2-1)-1;
                }
                else {summ = (lmda1/lmda2-1)*n;}

                if(summ >=n){
                    summ=n;
                }
                if(v1 == v2){
                    summ = 0;
                }
                overtaking+=(int)summ;
            }
        }

        return overtaking;
    }
}
