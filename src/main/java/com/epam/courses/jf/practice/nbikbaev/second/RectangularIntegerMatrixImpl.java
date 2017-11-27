package com.epam.courses.jf.practice.nbikbaev.second;

import com.epam.courses.jf.practice.common.second.ITestableTask18.IRectangularIntegerMatrix;

import java.util.Arrays;

public class RectangularIntegerMatrixImpl implements IRectangularIntegerMatrix {
    private int[][] a;
    private int width;
    private int height;


    public RectangularIntegerMatrixImpl(int[][] a) {
        this.a = a;
        width = a[0].length;
        height = a.length;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getValue(int indexWidth, int indexHeight) {
        return a[indexHeight][indexWidth];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RectangularIntegerMatrixImpl that = (RectangularIntegerMatrixImpl) o;

        if (width != that.width) return false;
        if (height != that.height) return false;
        return Arrays.deepEquals(a, that.a);
    }

    @Override
    public int hashCode() {
        int result = Arrays.deepHashCode(a);
        result = 31 * result + width;
        result = 31 * result + height;
        return result;
    }
}
