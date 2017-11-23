package com.epam.courses.jf.practice.vakhonin.second;

import com.epam.courses.jf.practice.common.second.ITestableTask19;

import java.util.*;

/**
 * Created by igorvahonin on 13.11.17.
 */
public class Task19 implements ITestableTask19{
    @Override
    public int getNumberOvertaking(Set<ICar> cars, long lengthLap, int numberLaps) {
        Comparator<ICar> comparator = (car1, car2) -> {
            int speed1 = car1.getSpeed();
            int speed2 = car2.getSpeed();
            if(speed1 == speed2){
                return -1;
            }
            else{
                //MORE AND LESS AND LESS...
                return Integer.compare(speed2, speed1);
            }
        };

//        Set<ICar> carTreeSet = new TreeSet<>(cars);

        List<ICar> carList = new ArrayList<>(cars);

        carList.sort(comparator);

        int size = cars.size();

        ICar carJ, carK;
        int speedJ, speedK;

        int numberOfOvertakings = 0;

        for(int j = 0; j < (size-1); j++){
            carJ = carList.get(j);
            speedJ = carJ.getSpeed();
            if(speedJ != 0){
                for(int k = j+1; k < size; k++){
                    carK = carList.get(k);
                    speedK = carK.getSpeed();
                    numberOfOvertakings +=(int) (((double)(numberLaps * (speedJ - speedK) * lengthLap - speedJ * (carK.getStartPosition() - carJ.getStartPosition()))) / (carJ.getSpeed() * numberLaps));
                }
            }
        }

        return numberOfOvertakings;
    }
}
