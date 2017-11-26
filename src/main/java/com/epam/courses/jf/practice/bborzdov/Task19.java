package com.epam.courses.jf.practice.bborzdov;

import com.epam.courses.jf.practice.common.second.ITestableTask19;

import java.util.Set;

/**
 * Created by bogdan on 24.11.17.
 */
public class Task19 implements ITestableTask19 {
    @Override
    public int getNumberOvertaking(Set<ICar> cars, long lengthLap, int numberLaps) {
        int numberOvertaking = 0;
        for (ICar car1 : cars) {
            for (ICar car2 : cars) {
                if(car1.equals(car2)){
                    continue;
                }
                if(car1.getSpeed() < car2.getSpeed()){
                    continue;
                }
                int speed = car1.getSpeed() - car2.getSpeed();
                int distance;
                if(car1.getStartPosition() > car2.getStartPosition()){
                    distance = (((int)lengthLap - car1.getStartPosition()) / speed)*car1.getSpeed();
                    if(distance > lengthLap*numberLaps){
                        return 0;
                    }
                }else{
                    distance = car2.getStartPosition() - car1.getStartPosition();
                }
                float time = (float) distance / (float) speed;
                float position = ((float) car2.getSpeed() * time) + car2.getStartPosition();
                if(position < lengthLap){
                    numberOvertaking++;
                }
                for (int i = 1; i < numberLaps; i++) {
                    position += ((float) lengthLap / (float) speed) * (float) car2.getSpeed();
                    if((position + (i*numberLaps)) <= numberLaps*lengthLap){
                        numberOvertaking++;
                    }
                }

            }
        }
        return numberOvertaking;
    }
}
