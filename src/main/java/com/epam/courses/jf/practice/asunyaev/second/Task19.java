package com.epam.courses.jf.practice.asunyaev.second;

import com.epam.courses.jf.practice.common.second.ITestableTask19;

import java.util.*;

/**
 * Реализация интерфейса для юнит-тестирования задания №19.
 *
 * На кольцевой гоночной трассе стоит N автомобилей.
 * Для каждого из них известны начальное положение и скорость.
 * Определить, сколько произойдет обгонов за указанное количество кругов.
 */
public class Task19 implements ITestableTask19 {

    /**
     * @param cars Расположенные на трассе машины.
     * @param lengthLap Длина трассы.
     * @param numberLaps Количество кругов.
     * @return Количество осуществленных обгонов.
     */
    @Override
    public int getNumberOvertaking(Set<ICar> cars, long lengthLap, int numberLaps) {
        int numberOvertaking = 0;
        List<ICar> carsList = new ArrayList<>(cars);
        carsList.sort(Comparator.comparingInt(ICar::getSpeed).reversed());
        for (int i = 0; i < carsList.size(); i++) {
            ICar firstCar = carsList.get(i);

            for (int j = i + 1; j < carsList.size(); j++) {
                ICar secondCar = carsList.get(j);
                double p1 = firstCar.getStartPosition();
                double p2 = secondCar.getStartPosition();
                int v1 = firstCar.getSpeed();
                int v2 = secondCar.getSpeed();
                for (int k = 0; k < numberLaps; k++) {
                    double t = (p2 - p1)/(v1 - v2);
                    if ((t > 0) && (t < (double) lengthLap/v1)) {
                        numberOvertaking++;
                    }
                    p2 += (v2 * (double) lengthLap/v1) % lengthLap;
                }
            }
        }
        return numberOvertaking;
    }

}
