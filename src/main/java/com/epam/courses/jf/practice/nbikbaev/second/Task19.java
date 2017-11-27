package com.epam.courses.jf.practice.nbikbaev.second;

import com.epam.courses.jf.practice.common.second.ITestableTask19;

import java.util.Set;

public class Task19 implements ITestableTask19 {
    @Override
    public int getNumberOvertaking(Set<ICar> cars, long lengthLap, int numberLaps) {
        int overtakingCounter = 0;
        for (ICar firstCar : cars) {
            for (ICar secondCar : cars) {
                if (firstCar.getSpeed() <= secondCar.getSpeed()) {
                    continue;
                }
                long positionsDifference;
                positionsDifference = secondCar.getStartPosition() - firstCar.getStartPosition();
                if (positionsDifference <= 0) {
                    positionsDifference += lengthLap;
                }
                double timeToFinish;
                timeToFinish = lengthLap / firstCar.getSpeed() * numberLaps;
                double timeToFirstOvertaking;
                timeToFirstOvertaking = (double) positionsDifference / (firstCar.getSpeed() - secondCar.getSpeed());
                if (timeToFinish > timeToFirstOvertaking) {
                    timeToFinish -= timeToFirstOvertaking;
                    overtakingCounter++;
                }
                double timeToOvertaking = (double) lengthLap / (firstCar.getSpeed() - secondCar.getSpeed());
                overtakingCounter += (int) timeToFinish / timeToOvertaking;
            }

        }

        return overtakingCounter;
    }
}
