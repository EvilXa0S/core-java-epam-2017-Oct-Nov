package com.epam.courses.jf.practice.igulyaev.second;

import com.epam.courses.jf.practice.common.second.ITestableTask19;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class TestableTask19 implements ITestableTask19 {
    /**
     * @param cars       Расположенные на трассе машины.
     * @param lengthLap  Длина трассы.
     * @param numberLaps Количество кругов.
     * @return Количество осуществленных обгонов.
     */
    @Override
    public int getNumberOvertaking(Set<ICar> cars, long lengthLap, int numberLaps) {
        List<ICar> carsList = new ArrayList<>(cars);
        carsList.sort(Comparator.comparingInt(ICar::getStartPosition));
        int count = 0;
        for (int i = 0; i < carsList.size(); ++i) {
            ICar first = carsList.get(i);
            for (int j = i + 1; j < carsList.size(); ++j) {
                ICar second = carsList.get(j);
                int closingSpeed = first.getSpeed() - second.getSpeed();
                if(closingSpeed == 0){
                    continue;
                } else if(closingSpeed > 0){
                    int distance = first.getStartPosition() - second.getStartPosition();
                }
            }
        }
        return 0;
    }
}
