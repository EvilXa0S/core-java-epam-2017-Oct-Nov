package com.epam.courses.jf.practice.akunats.second;

import com.epam.courses.jf.practice.common.second.ITestableTask19;

public class Car implements ITestableTask19.ICar {
    private int startPosition;
    private int speed;

    Car(int startPosition, int speed) {
        this.speed = speed;
        this.startPosition = startPosition;
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
