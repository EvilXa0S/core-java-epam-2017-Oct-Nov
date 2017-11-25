package com.epam.courses.jf.practice.NikDevyatyi.second;

public class Car implements  TestableTask19.ICar {
    private int position;
    private int speed;

    public Car(int position, int speed) {
        this.position = position;
        this.speed = speed;
    }

    @Override
    public int getStartPosition() {
        return position;
    }

    @Override
    public int getSpeed() {
        return speed;
    }
}
