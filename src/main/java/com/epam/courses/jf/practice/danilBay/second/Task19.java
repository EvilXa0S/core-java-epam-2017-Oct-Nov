package com.epam.courses.jf.practice.danilBay.second;

import com.epam.courses.jf.practice.common.second.ITestableTask19;

import java.util.Set;

public class Task19 implements ITestableTask19 {
    @Override
    public int getNumberOvertaking(Set<ICar> cars, long lengthLap, int numberLaps) {
        long S= lengthLap*numberLaps;
        int numberOvertaking = 0;
        for (ICar fastCar : cars) {
            for (ICar slowCar : cars) {

                double velocity= fastCar.getSpeed()-slowCar.getSpeed();
                if(velocity<0)continue;

                double tSlowToFinish=(S-slowCar.getStartPosition())/slowCar.getSpeed();
                double tFastToFinish=(S-fastCar.getStartPosition())/fastCar.getSpeed();
                double curTime=0;
                int startPosDif= Math.abs(slowCar.getStartPosition()-fastCar.getStartPosition());
                double tFirstOvertake= startPosDif/velocity;
                if(tFirstOvertake<tFastToFinish && tFirstOvertake<tSlowToFinish) {
                    curTime += tFirstOvertake;
                    numberOvertaking++;
                }
                double tFullOvertake=lengthLap/velocity;
                while (curTime+tFullOvertake<tFastToFinish){
                    numberOvertaking++;
                    curTime+= lengthLap/velocity;
                }


            }
        }
        return numberOvertaking;
    }
}
