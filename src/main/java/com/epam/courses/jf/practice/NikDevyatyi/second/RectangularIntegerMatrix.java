package com.epam.courses.jf.practice.NikDevyatyi.second;

import com.epam.courses.jf.practice.common.second.ITestableTask18;

import java.util.Arrays;

public class RectangularIntegerMatrix implements ITestableTask18.IRectangularIntegerMatrix {
    public int[][] matrix;

    public RectangularIntegerMatrix(int[][] matrix) {
        this.matrix = Arrays.copyOf(matrix, matrix.length);
    }

    @Override
    public int getWidth() {
        return matrix[0].length;
    }

    @Override
    public int getHeight() {
        return matrix.length;
    }

    @Override
    public int getValue(int indexWidth, int indexHeight) {
        return matrix[indexWidth][indexHeight];
    }
}

