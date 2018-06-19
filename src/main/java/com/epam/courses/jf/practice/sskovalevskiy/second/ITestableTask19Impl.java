package com.epam.courses.jf.practice.sskovalevskiy.second;

import com.epam.courses.jf.practice.common.second.ITestableTask19;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by asus on 28.10.2017.
 *
 * На кольцевой гоночной трассе стоит N автомобилей.
 * Для каждого из них известны начальное положение и скорость.
 * Определить, сколько произойдет обгонов за указанное количество кругов.
 */
public class ITestableTask19Impl implements ITestableTask19 {

    private int countOvertaking = 0;

    /**
     * @param cars Расположенные на трассе машины.
     * @param lengthLap Длина трассы.
     * @param numberLaps Количество кругов.
     * @return Количество осуществленных обгонов.
     */
    @Override
    public int getNumberOvertaking(Set<ICar> cars, long lengthLap, int numberLaps) {
        long[] positionsCars = new long[cars.size()];
        Set<RaceCar> raceCars = new HashSet<>();

        cars.forEach(car -> raceCars.add(new RaceCar(car)));
        RaceCar[] masRaceCars = raceCars.toArray(new RaceCar[cars.size()]);

        for (int i = 0; i < positionsCars.length; i++) {
            positionsCars[i] = masRaceCars[i].getPosition();
        }
        bubbleSort(positionsCars, masRaceCars, false);

        //main race
        while (true) {
            //one step
            for (int i = 0; i < positionsCars.length; i++) {
                if (masRaceCars[i].getPosition() != Long.MAX_VALUE) {
                    masRaceCars[i].moveCar();

                    if (masRaceCars[i].position >= lengthLap) {
                        masRaceCars[i].setPosition(masRaceCars[i].position - lengthLap);
                        masRaceCars[i].nextLap();
                        if (masRaceCars[i].lap > numberLaps) {
                            //set MAX_VALUE as a finish
                            masRaceCars[i].setPosition(Long.MAX_VALUE);
                        }
                    }
                }
                positionsCars[i] = masRaceCars[i].getPosition();
            }
            bubbleSort(positionsCars,masRaceCars,true);
            if(masRaceCars[0].getPosition() == Long.MAX_VALUE) break;
        }

        return countOvertaking;
    }

    private class RaceCar {
        private int lap;
        private long position;
        private int speed;

        public RaceCar(ICar car) {
            this.lap = 1;
            this.position = car.getStartPosition();
            this.speed = car.getSpeed();
        }

        public void moveCar() {
            position += speed;
        }

        public void nextLap() {
            lap++;
        }

        public long getPosition() {
            return position;
        }

        public int getLap() {
            return lap;
        }

        public void setPosition(long position) {
            this.position = position;
        }
    }

    private void bubbleSort(long[] massive, RaceCar[] masCars, boolean isRace) {
        boolean isAlreadySorted = false;
        for (int i = 0; i < massive.length - 1 && !isAlreadySorted; i++) {
            isAlreadySorted = true;
            for (int j = 0; j < massive.length - i - 1; j++) {
                if(masCars[j].getLap() <= masCars[j+1].getLap()) {
                    if (massive[j] > massive[j + 1]) {
                        swap(massive, masCars, j, j + 1);
                        isAlreadySorted = false;
                        if (isRace) {
                            countOvertaking++;
                        }
                    }
                }
            }
        }
    }

    private void swap(long[] massive, RaceCar[] masCars, int leftIndex, int rightIndex) {

        long temp = massive[rightIndex];
        massive[rightIndex] = massive[leftIndex];
        massive[leftIndex] = temp;

        RaceCar tempCar = masCars[rightIndex];
        masCars[rightIndex] = masCars[leftIndex];
        masCars[leftIndex] = tempCar;
    }
}
