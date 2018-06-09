package com.epam.courses.jf.practice.klimenko.second;

import com.epam.courses.jf.practice.common.second.ITestableTask19;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TestableTask19Impl implements ITestableTask19 {
    @Override
    public int getNumberOvertaking(Set<ICar> cars, long lengthLap, int numberLaps) {
        int ret = 0;

        List<CarExt> listCars = new ArrayList<>();
        for (ICar car : cars) {
            listCars.add(new CarExt(car, lengthLap));
        }

        OUTER:
        while (true) {
            double nextEvent = Double.POSITIVE_INFINITY;
            for (CarExt car : listCars) {
                nextEvent = Math.min(nextEvent, car.timeToFinish());
            }
            for (CarExt car : listCars) {
                for (CarExt other : listCars) {
                    if (car != other && car.overtakes(other, nextEvent)) {
                        ++ret;
                    }
                }
            }
            for (CarExt car : listCars) {
                car.move(nextEvent);
                if (car.lapsPassed == numberLaps) {
                    break OUTER;
                }
            }
        }

        return ret;
    }

    private class CarExt implements ICar {
        private int speed, lapsPassed;
        private long lapLength;
        private double pos;

        CarExt(ICar c, long lap) {
            pos = c.getStartPosition();
            speed = c.getSpeed();
            lapLength = lap;
            lapsPassed = 0;
        }

        void move(double t) {
            pos = futurePos(t);
            if (pos == lapLength) {
                pos = 0;
                lapsPassed++;
            }
        }

        double futurePos(double t) {
            return pos + speed * t;
        }

        boolean overtakes(CarExt other, double t) {
            return other.pos > pos && other.futurePos(t) < futurePos(t);
        }

        double timeToFinish() {
            if (speed == 0) {
                return Double.POSITIVE_INFINITY;
            }
            return (lapLength - pos) / (double) speed;
        }

        @Override
        public int getStartPosition() {
            return (int) Math.round(pos);
        }

        @Override
        public int getSpeed() {
            return speed;
        }
    }
}
