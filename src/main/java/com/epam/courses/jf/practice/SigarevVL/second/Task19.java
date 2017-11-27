package com.epam.courses.jf.practice.SigarevVL.second;

import com.epam.courses.jf.practice.common.second.ITestableTask19;

import java.util.*;

public class Task19 implements ITestableTask19 {

    @Override
    public int getNumberOvertaking(Set<ICar> cars, long lengthLap, int numberLaps) {
        int countOfAllOvertake = 0;
        List<ICar> carsList = new ArrayList<>(cars);
        Collections.sort(carsList, (o1, o2) -> o1.getSpeed() > o2.getSpeed() ? 1 : -1 );
        for (int i = carsList.size()- 1; i >= 0; i--) {
            for (int j = carsList.size() - 1; j >=0; j--) {
                countOfAllOvertake += overtakeCountBetweenTwoCars(carsList.get(i),
                        carsList.get(j), lengthLap, numberLaps);
            }
        }

        return countOfAllOvertake;
    }

    public int overtakeCountBetweenTwoCars (ICar car1, ICar car2, long lengthLap, int numberLaps) {
        int overtakeCount = 0;

        if(car1.getSpeed() <= car2.getSpeed()) {
            return overtakeCount;
        }
        double car1LapTime = lengthLap / car1.getSpeed();
        double car2LapTime = lengthLap / car2.getSpeed();
        double car1RaceTime = car1LapTime * numberLaps;


        double meetingTime = ((double)(car2.getStartPosition() - car1.getStartPosition())
                / (double)(car1.getSpeed() - car2.getSpeed()));

        if (meetingTime > 0) {
            if (car1RaceTime > meetingTime) {
                car1RaceTime -= meetingTime;
                overtakeCount++;
            }
        } else {
            if (car1RaceTime > Math.abs(meetingTime) + car1LapTime) {
                car1RaceTime -= Math.abs(meetingTime) + car1LapTime;
                overtakeCount++;
            }
        }

         do {
            meetingTime = ((double)Math.abs(car2.getSpeed() * car1LapTime)
                    / (car1.getSpeed() - car2.getSpeed())) + car1LapTime;

            if (car1RaceTime > meetingTime) {
                car1RaceTime -= meetingTime;
                overtakeCount++;
            } else {
                break;
            }
         } while (car1RaceTime > car1LapTime);

        return overtakeCount;
    }


    public class ICarImpl implements ICar {
        private int startPosition;
        private int speed;

        public ICarImpl(int startPosition, int speed) {
            this.startPosition = startPosition;
            this.speed = speed;
        }

        @Override
        public int getStartPosition() {
            return startPosition;
        }

        @Override
        public int getSpeed() {
            return speed;
        }
    }
}
