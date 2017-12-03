package com.epam.courses.jf.practice.vplaksin.second;

import com.epam.courses.jf.practice.common.second.ITestableTask19;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Set;

import static java.lang.Math.floor;

/**
 * На кольцевой гоночной трассе стоит N автомобилей.
 * Для каждого из них известны начальное положение и скорость.
 * Определить, сколько произойдет обгонов за указанное количество кругов.
 */
public class Task19 implements ITestableTask19 {

    /**
     * @param cars       Расположенные на трассе машины.
     * @param lengthLap  Длина одного круга.
     * @param numberLaps Количество кругов.
     * @return Количество осуществленных обгонов.
     */
    @Override
    public int getNumberOvertaking(Set<ICar> cars, long lengthLap, int numberLaps) {
        ArrayList<ICar> list = new ArrayList<>(cars);
        list.sort(Comparator.comparingInt(ICar::getSpeed).reversed());
        int result = 0;

        for (int i = 0; i < list.size(); i++) {
            ICar car1 = list.get(i);
            int speed1 = car1.getSpeed();
            int startPosition1 = car1.getStartPosition();

            for (int j = i + 1; j < list.size(); j++) {
                ICar car2 = list.get(j);
                int speed2 = car2.getSpeed();
                int startPosition2 = car2.getStartPosition();
                if (speed1 == speed2) {
                    continue;
                }

                double difference = startPosition2 - startPosition1;
                if (startPosition2 < startPosition1) {
                    difference = lengthLap + difference;
                }

                double timeForFirst = (double) (lengthLap * numberLaps + startPosition1) / speed1;
                int numberOfThatPairOvertaking = (int) floor(1 + (timeForFirst * (speed1 - speed2) - difference) / lengthLap);
                result += numberOfThatPairOvertaking;
            }
        }

        return result;
    }

    //Машина
    private class Car implements ICar {

        int startPosition;
        int speed;

        private Car(int startPosition, int speed) {
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
