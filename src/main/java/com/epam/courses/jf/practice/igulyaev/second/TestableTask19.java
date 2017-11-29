package com.epam.courses.jf.practice.igulyaev.second;

import com.epam.courses.jf.practice.common.second.ITestableTask19;

import java.util.*;

public class TestableTask19 implements ITestableTask19 {
    /**
     * @param cars       Расположенные на трассе машины.
     * @param lengthLap  Длина трассы.
     * @param numberLaps Количество кругов.
     * @return Количество осуществленных обгонов.
     */
    @Override
    public int getNumberOvertaking(Set<ICar> cars, long lengthLap, int numberLaps) {
        List<ICar> carsList = new ArrayList<>(cars);
        carsList.sort(Comparator.comparingInt(ICar::getStartPosition));
        int count = 0;
        for (int i = 0; i < carsList.size(); ++i) {
            ICar first = carsList.get(i);
            for (int j = i + 1; j < carsList.size(); ++j) {
                ICar second = carsList.get(j);
                int closingSpeed = first.getSpeed() - second.getSpeed();
                if(closingSpeed != 0){
                    long distance = second.getStartPosition() - first.getStartPosition();
                    ICar car = first;
                    if(closingSpeed < 0){
                        distance = lengthLap - distance;
                        closingSpeed = -closingSpeed;
                        car = second;
                    }
                    long overtakenPosition1 = overtakenPosition(car, car.getStartPosition(), distance, closingSpeed);
                    if(overtakenPosition1 > lengthLap * numberLaps){
                        continue;
                    }
                    count++;
                    long overtakenPosition2 = overtakenPosition(car, overtakenPosition1, lengthLap, closingSpeed);
                    long distanceForOneLoopOvertake = overtakenPosition2 - overtakenPosition1;
                    count += (lengthLap * numberLaps - overtakenPosition1) / distanceForOneLoopOvertake;
                }
            }
        }
        return count;
    }

    private long overtakenPosition(ICar car, long startPosition, long distance, int closingSpeed) {
        double time = ((double)distance)/closingSpeed;
        return (long)(startPosition + time * car.getSpeed());
    }
}
