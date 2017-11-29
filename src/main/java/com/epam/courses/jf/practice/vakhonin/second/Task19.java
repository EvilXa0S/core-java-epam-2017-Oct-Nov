package com.epam.courses.jf.practice.vakhonin.second;

import com.epam.courses.jf.practice.common.second.ITestableTask19;

import java.util.*;

/**
 * На кольцевой гоночной трассе стоит N автомобилей.
 * Для каждого из них известны начальное положение и скорость.
 * Определить, сколько произойдет обгонов за указанное количество кругов.
 */

public class Task19 implements ITestableTask19{

    /**
     * @param cars Расположенные на трассе машины.
     * @param lengthLap Длина трассы.
     * @param numberLaps Количество кругов.
     * @return Количество осуществленных обгонов.
     */

    @Override
    public int getNumberOvertaking(Set<ICar> cars, long lengthLap, int numberLaps) {
        Comparator<ICar> comparator = (car1, car2) -> {
            int speed1 = car1.getSpeed();
            int speed2 = car2.getSpeed();
            if (speed1 == speed2) {
                return -1;
            }
            else {
                return Integer.compare(speed2, speed1);
            }
        };

        List<ICar> carList = new ArrayList<>(cars);
        carList.sort(comparator);
        int size = cars.size();
        int speedJ, speedK;
        ICar carJ, carK;
        int numberOfOvertakings = 0;
        int nOfOvertakings;
        long ds, startPositionJ, startPositionK;

        for (int j = 0; j < (size-1); j++) {
            carJ = carList.get(j);
            speedJ = carJ.getSpeed();

            if (speedJ != 0) {
                for (int k = j+1; k < size; k++) {
                    carK = carList.get(k);
                    speedK = carK.getSpeed();
                    startPositionJ = carJ.getStartPosition();
                    startPositionK = carK.getStartPosition();

                    if (startPositionK > startPositionJ) {
                        ds = startPositionK - startPositionJ;
                    }
                    else {
                        ds = lengthLap - (startPositionJ - startPositionK);
                    }

                    nOfOvertakings = ((speedJ-speedK) * numberLaps / speedJ);
                    numberOfOvertakings += nOfOvertakings;

                    if ((((speedJ-speedK) * numberLaps * lengthLap) / (speedJ) - nOfOvertakings*lengthLap) > ds) {
                        numberOfOvertakings ++;
                    }
                }
            }
        }

        return numberOfOvertakings;
    }
}
