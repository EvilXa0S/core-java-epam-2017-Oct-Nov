package com.epam.courses.jf.practice.akunats.second;

import com.epam.courses.jf.practice.common.second.ITestableTask18;

public class RectangularIntegerMatrix implements ITestableTask18.IRectangularIntegerMatrix {
    private int width;
    private int height;
    private int[][] matrix;

    RectangularIntegerMatrix(int width, int height) {
        this.width = width;
        this.height = height;
        this.matrix = new int[height][width];
    }
    RectangularIntegerMatrix(int[][] matrix) {
        this.matrix = matrix;
        this.height = matrix.length;
        this.width = matrix[0].length;
    }

    void setMatrix(int str, int column, int value) {
        this.matrix[str][column] = value;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public int getValue(int indexWidth, int indexHeight) {
        return matrix[indexHeight][indexWidth];
    }
}
