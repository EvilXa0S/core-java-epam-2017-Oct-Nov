package com.epam.courses.jf.practice.akunats.second;

import com.epam.courses.jf.practice.common.second.ITestableTask19;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class Task19Test {
    @Test
    public void getNumberOvertaking() {
        Car c1 = new Car(0, 30);
        Car c2 = new Car(5, 25);
        Car c3 = new Car(10, 5);
        Set<ITestableTask19.ICar> cars = new HashSet<>();
        cars.add(c1);
        cars.add(c2);
        cars.add(c3);
        System.out.println(new Task19().getNumberOvertaking(cars, 100, 2));
    }
}
