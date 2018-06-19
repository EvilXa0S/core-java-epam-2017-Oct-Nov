package com.epam.courses.jf.practice.mallayarov.second;

import com.epam.courses.jf.practice.common.second.ITestableTask18;

import java.util.ArrayList;

/**
 * Дана матрица из целых чисел.
 * Найти в ней прямоугольную подматрицу, состоящую из максимального количества одинаковых элементов.
 * Использовать стек.
 */
public class Task18Impl implements ITestableTask18 {

    /**
     * @param matrix Анализируемая матрица.
     * @return Подматрица, состоящая из максимального количества одинаковых элементов.
     */
    @Override
    public IRectangularIntegerMatrix getMaxSubMatrix(IRectangularIntegerMatrix matrix) {
        int height = matrix.getHeight();
        int width = matrix.getWidth();

        if (height == 1 && width == 1) {
            return matrix;
        }

       ArrayList<ArrayList<Pair>> coefMatrix = new ArrayList<>();

        // initialize init matrix with zeroes
        for (int i = 0; i < height; ++i) {
            ArrayList<Pair> row = new ArrayList<>();
            for (int j = 0; j < width; ++j) {
                row.add(j, new Pair(0,0));
            }
            coefMatrix.add(i, row);
        }

        coefMatrix.get(height - 1).set(width - 1, new Pair(1, 1)); // initialize bottom right pair

        // initialize bottom row
        for (int j = width - 2; j >= 0; --j) {
            if (matrix.getValue(j, height - 1) == matrix.getValue(j + 1, height - 1)) {
                Pair temp = new Pair(1, coefMatrix.get(height - 1).get(j + 1).getWidth() + 1);
                coefMatrix.get(height - 1).set(j, temp);
            }
            else {
                coefMatrix.get(height - 1).set(j, new Pair(1, 1));
            }
        }

        // initialize right column
        for (int i = height - 2; i >= 0; --i) {
            if (matrix.getValue(width - 1, i) == matrix.getValue(width - 1, i + 1)) {
                Pair temp = new Pair(coefMatrix.get(i + 1).get(width - 1).getHeight() + 1, 1);
                coefMatrix.get(i).set(width - 1, temp);
            }
            else {
                coefMatrix.get(i).set(width - 1, new Pair(1, 1));
            }
        }

        int maxSquare = 1;
        int maxI = height - 1;
        int maxJ = width - 1;

        // analise remain cells
        for (int i = height - 2; i >= 0; --i) {
            for (int j = width - 2; j >= 0; --j) {

                Pair temp = new Pair(1, 1);

                // three cases
                // first - right neighbor is equals and bottom is not
                if (matrix.getValue(j, i) == matrix.getValue(j + 1, i)
                        && matrix.getValue(j, i) != matrix.getValue(j, i + 1)) {
                     temp = new Pair(1, coefMatrix.get(i).get(j + 1).getWidth() + 1);
                }

                // second - right is not equals and bottom is
                if (matrix.getValue(j, i) == matrix.getValue(j, i + 1)
                        && matrix.getValue(j, i) != matrix.getValue(j + 1, i)) {
                     temp = new Pair(coefMatrix.get(i + 1).get(j).getHeight() + 1, 1);
                }

                // third - bottom and right is equals
                if (matrix.getValue(j, i) == matrix.getValue(j, i + 1)
                        && matrix.getValue(j, i) == matrix.getValue(j + 1, i)) {
                    // check line, row and rectangular
                    Pair tempRow = new Pair(1, coefMatrix.get(i).get(j + 1).getWidth() + 1);
                    Pair tempCol = new Pair(coefMatrix.get(i + 1).get(j).getHeight() + 1, 1);
                    Pair tempRectangular = new Pair(
                            Math.min(coefMatrix.get(i).get(j + 1).getHeight(),
                                    coefMatrix.get(i + 1).get(j).getHeight() + 1),
                            Math.min(coefMatrix.get(i).get(j + 1).getWidth() + 1,
                                    coefMatrix.get(i + 1).get(j).getWidth())
                            );

                     temp = maxPair(tempRectangular, maxPair(tempRow, tempCol));
                }

                coefMatrix.get(i).set(j, temp);

                int tempSquare = temp.getHeight() * temp.getWidth();
                if (tempSquare >= maxSquare) {
                    maxSquare = tempSquare;
                    maxI = i;
                    maxJ = j;
                }
            }
        }

        int resultHeight = coefMatrix.get(maxI).get(maxJ).getHeight();
        int resultWidth = coefMatrix.get(maxI).get(maxJ).getWidth();
        int[][] resultMatrix = new int[resultHeight][resultWidth];

        for (int i = maxI; i < maxI + resultHeight; ++i) {
            for (int j = maxJ; j < maxJ + resultWidth; ++j) {
                resultMatrix[i - maxI][j - maxJ] = matrix.getValue(j, i);
            }
        }

        return new RectangularIntegerMatrix(resultMatrix);
    }

    /**
     * Compare two pairs by square
     * @param f first pair
     * @param s second pair
     * @return the largest pair
     */
    private Pair maxPair(Pair f, Pair s) {
        if (f.height * f.width >= s.height * s.width) {
            return f;
        }

        return s;
    }

    /**
     * Class represents a pair of height and width
     */
    public class Pair {
        private int height; // is height
        private int width; // is width

        public Pair(int height, int width) {
            this.height = height;
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public int getWidth() {
            return width;
        }
    }

    /**
     * Прямоугольная матрица целых чисел.
     */
    public class RectangularIntegerMatrix implements IRectangularIntegerMatrix {

        private int[][] matrix;

        public RectangularIntegerMatrix(int[][] matrix) {
            this.matrix = matrix;
        }

        /** @return Ширина матрицы. */
        @Override
        public int getWidth() {
            return matrix[0].length;
        }

        /** @return Высота матрицы. */
        @Override
        public int getHeight() {
            return matrix.length;
        }

        /**
         * @param indexWidth Индекс по ширине.
         * @param indexHeight Индекс по высоте.
         * @return Значение, располагающееся в указанной ячейке.
         */
        @Override
        public int getValue(int indexWidth, int indexHeight) {
            return matrix[indexHeight][indexWidth];
        }
    }
}
