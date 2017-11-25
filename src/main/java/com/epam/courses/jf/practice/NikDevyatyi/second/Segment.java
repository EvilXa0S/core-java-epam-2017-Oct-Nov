package com.epam.courses.jf.practice.NikDevyatyi.second;

import com.epam.courses.jf.practice.common.second.I2DPoint;
import com.epam.courses.jf.practice.common.second.ITestableTask17;

public class Segment implements ITestableTask17.ISegment {
    I2DPoint first;
    I2DPoint second;
    public Segment(I2DPoint first, I2DPoint second) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Segment segment = (Segment) o;

        if (first != null ? !first.equals(segment.first) : segment.first != null) return false;
        return second != null ? second.equals(segment.second) : segment.second == null;
    }

    @Override
    public int hashCode() {
        int result = first != null ? first.hashCode() : 0;
        result = 31 * result + (second != null ? second.hashCode() : 0);
        return result;
    }
}
