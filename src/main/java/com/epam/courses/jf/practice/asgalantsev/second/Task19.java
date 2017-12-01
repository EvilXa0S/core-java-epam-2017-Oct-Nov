package com.epam.courses.jf.practice.asgalantsev.second;

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
        int counter = 0;
        long distance = lengthLap * numberLaps;
        List<ICar> list = new ArrayList<>(cars);
        Collections.sort(list, new Comparator<ICar>() {
            @Override
            public int compare(ICar o1, ICar o2) {
                if((lengthLap*numberLaps - o1.getStartPosition()) / o1.getSpeed() < (lengthLap*numberLaps - o2.getStartPosition()) / o2.getSpeed())
                    return -1;
                else if((lengthLap*numberLaps - o1.getStartPosition()) / o1.getSpeed() == (lengthLap*numberLaps - o2.getStartPosition()) / o2.getSpeed())
                    return 0;
                else
                    return 1;
            }
        });

        for(int i=0; i < list.size()-1; i++)
            for(int j=i+1; j < list.size(); j++) {
                int start1 = list.get(i).getStartPosition();
                int start2 = list.get(j).getStartPosition();
                int v1 = list.get(i).getSpeed();
                int v2 = list.get(j).getSpeed();

                if(v1 > v2 && start1 <= start2)
                    counter++;
                double t1 = (double)(distance - start1) / (double)v1;
                double circles2 = (double)(t1 * v2 - start2) / (double)lengthLap;
                counter += (int)(numberLaps - circles2);
            }
        return counter;
    }
}
