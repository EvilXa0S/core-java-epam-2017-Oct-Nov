package com.epam.courses.jf.practice.mallayarov.second;

import com.epam.courses.jf.practice.common.second.ITestableTask19;

import java.util.Set;

/**
 * На кольцевой гоночной трассе стоит N автомобилей.
 * Для каждого из них известны начальное положение и скорость.
 * Определить, сколько произойдет обгонов за указанное количество кругов.
 */
public class Task19Impl implements ITestableTask19 {

    /**
     * @param cars Расположенные на трассе машины.
     * @param lengthLap Длина трассы.
     * @param numberLaps Количество кругов.
     * @return Количество осуществленных обгонов.
     */
    @Override
    public int getNumberOvertaking(Set<ICar> cars, long lengthLap, int numberLaps) {
        int result = 0;

        for (ICar firstCar : cars) {
            for (ICar secondCar : cars) {
                result += countNumberOvertaking(firstCar, secondCar, lengthLap, numberLaps);
            }
        }

        return result;
    }

    private int countNumberOvertaking(ICar first, ICar second, long lengthLap, int numberLaps) {
        if (first.getSpeed() <= second.getSpeed()) {
            return 0;
        }

        double firstFinishTime = (lengthLap * numberLaps - first.getStartPosition()) / first.getSpeed();
        double secondDistanceTraveled = second.getSpeed() * firstFinishTime; // distance traveled by second car per first car finish time
        double distanceBetweenCars = firstFinishTime * first.getSpeed() - secondDistanceTraveled - second.getStartPosition();
        double overtake = (distanceBetweenCars / lengthLap);

        if (overtake <= 0) {
            overtake = 0;
        } else {
            if (first.getStartPosition() < second.getStartPosition()) {
                ++overtake;
            }
        }

        return (int) overtake;
    }
}
