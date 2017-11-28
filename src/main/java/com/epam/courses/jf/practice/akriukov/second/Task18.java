package com.epam.courses.jf.practice.akriukov.second;

import com.epam.courses.jf.practice.common.second.ITestableTask18;

import static java.lang.Math.min;

/**
 * Дана матрица из целых чисел.
 * Найти в ней прямоугольную подматрицу, состоящую из максимального количества одинаковых элементов.
 * Использовать стек.
 */
public class Task18 implements ITestableTask18{

    /**
     * @param matrix Анализируемая матрица.
     * @return Подматрица, состоящая из максимального количества одинаковых элементов.
     */
    @Override
    public IRectangularIntegerMatrix getMaxSubMatrix(IRectangularIntegerMatrix matrix) {
        int matrixHeight = matrix.getHeight();
        int matrixWidth = matrix.getWidth();
        int resultSize = 0;
        int resultSample = 0;
        int resultHeight = 0;
        int resultWidth = 0;

        for (int i = 0; i < matrixHeight; i++) {
            for (int j = 0; j < matrixWidth; j++) {
                int sample = matrix.getValue(j, i);
                int currentHeight = 0;
                int currentWidth = matrixWidth - j;

                for (int n = i; n < matrixHeight; n++) {
                    int width = 0;

                    for (int m = j; m < j + currentWidth; m++) {
                        if (matrix.getValue(m, n) == sample) {
                            width++;
                            if (m != j + currentWidth - 1) {
                                continue;
                            }
                        }
                        currentWidth = min(currentWidth, width);
                        currentHeight++;
                        if (resultSize < currentHeight * currentWidth) {
                            resultHeight = currentHeight;
                            resultWidth = currentWidth;
                            resultSize = currentHeight * currentWidth;
                            resultSample = sample;
                        }
                        break;
                    }
                }
            }
        }

        return new RectangularIntegerMatrix(resultHeight, resultWidth, resultSample);
    }

    private class RectangularIntegerMatrix implements IRectangularIntegerMatrix {
        private int[][] matrix = new int[0][0];

        private RectangularIntegerMatrix(int height, int width) {
            matrix = new int[height][width];
        }

        private RectangularIntegerMatrix(int height, int width, int value) {
            this(height, width);
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    matrix[i][j] = value;
                }
            }
        }

        @Override
        public int getWidth() {
            if (matrix.length == 0) {
                return 0;
            } else {
                return matrix[0].length;
            }
        }

        @Override
        public int getHeight() {
            return matrix.length;
        }

        @Override
        public int getValue(int indexWidth, int indexHeight) {
            return matrix[indexHeight][indexWidth];
        }
    }
}
