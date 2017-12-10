package com.epam.courses.jf.practice.hkryzhik.second;

import com.epam.courses.jf.practice.common.second.ITestableTask19;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Set;

public class Task19 implements ITestableTask19 {

    @Override
    public int getNumberOvertaking(Set<ICar> cars, long lengthLap, int numberLaps) {

        int overtakes = 0;

        HashMap<ICar, Integer> carsDistances = new HashMap<>();

        ICar slowestCar = cars.stream().peek((c) -> carsDistances.put(c, c.getStartPosition()))
                .min(Comparator.comparingInt(ICar::getSpeed)).get();

        while (carsDistances.get(slowestCar) < lengthLap * numberLaps){

            for (ICar car1: carsDistances.keySet()) {

                if (carsDistances.get(car1) < lengthLap * numberLaps) {

                    for (ICar car2 : carsDistances.keySet()) {

                        if (carsDistances.get(car2) < lengthLap * numberLaps) {

                            if (car1.getSpeed() > car2.getSpeed() && Math.abs(carsDistances.get(car1) % lengthLap
                                    - carsDistances.get(car2) % lengthLap) <= car1.getSpeed() - car2.getSpeed()
                                    && carsDistances.get(car1) % lengthLap < carsDistances.get(car2) % lengthLap) {

                                overtakes++;
                            }
                        }
                    }
                }
            }
            carsDistances.keySet().stream().forEach((c) -> {

                carsDistances.put(c, carsDistances.get(c) + c.getSpeed());

            });

        }
        return overtakes;
    }
}
