package com.epam.courses.jf.practice.akunats.second;

import com.epam.courses.jf.practice.common.second.I2DPoint;
import com.epam.courses.jf.practice.common.second.ITestableTask17;

public class Segment implements ITestableTask17.ISegment {
    private I2DPoint first;
    private I2DPoint second;

    Segment(I2DPoint first, I2DPoint second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public I2DPoint first() {
        return first;
    }

    @Override
    public I2DPoint second() {
        return second;
    }
}
