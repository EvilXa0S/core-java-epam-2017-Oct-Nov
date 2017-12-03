package com.epam.courses.jf.practice.nzenkova.second;


import com.epam.courses.jf.practice.common.second.ITestableTask19;

import java.util.Set;

public class TestableTask19 implements ITestableTask19 {
    @Override
    public int getNumberOvertaking(Set<ICar> cars, long lengthLap, int numberLaps) {
        int numberOvertaking = 0;
        for (ICar car : cars) {
            for (ICar iCar : cars) {
                //for all pairs: car1.speed > car2.speed
                numberOvertaking += getNumber(car, iCar, numberLaps, lengthLap);
            }
        }
        return numberOvertaking;
    }

    /**
     * @param car1 The first car.
     * @param car2 The second car.
     * @return Number of overtaking.
     */
    private int getNumber(ICar car1, ICar car2, int laps, long lengthLap) {
        int number = 0;
        //the approach velocity
        double speed = car1.getSpeed() - car2.getSpeed();
        if (Double.compare(speed,0) > 0) {
            //the distance from 1 to 2 cars
            double pos = car2.getStartPosition() - car1.getStartPosition();
            while(Double.compare(pos, 0d) < 0) pos += lengthLap;
            //the time to meet with the starting positions
            double time1 = pos / speed;
            //if(Double.compare(time1, 0) == 0) number--;
            //the first meeting place
            double position = car1.getStartPosition() + time1 * car1.getSpeed();
            double time2 = (double)lengthLap / speed;
            while (Double.compare(position, lengthLap * laps) < 0) {
                ++number;
                position += time2 * car1.getSpeed();
            }
        }
        return number;
    }
}