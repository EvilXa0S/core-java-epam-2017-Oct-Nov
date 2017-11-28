package com.epam.courses.jf.practice.SigarevVL.second;

import com.epam.courses.jf.practice.common.second.ITestableTask18;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Дана матрица из целых чисел.
 * Найти в ней прямоугольную подматрицу, состоящую из максимального количества одинаковых элементов.
 * Использовать стек.
 */
public class Task18 implements ITestableTask18 {

    /**
     * @param matrix Анализируемая матрица.
     * @return Подматрица, состоящая из максимального количества одинаковых элементов.
     */
    @Override
    public IRectangularIntegerMatrix getMaxSubMatrix(IRectangularIntegerMatrix matrix) {
        int minColumn = matrix.getHeight() + 1;
        int row  = 1;
        Deque<IRectangularIntegerMatrixImpl> deque = new ArrayDeque<>();

        for (int i = 0; i < matrix.getHeight(); i++) {
            for (int j = 0; j < matrix.getWidth(); j++) {
                int k = i + 1;
                int column = 1;
                while (k < matrix.getHeight() && matrix.getValue(j,k) == matrix.getValue(j,i)) {
                    column++;
                    k++;
                }
                k = i - 1;
                while (k >= 0 && matrix.getValue(j,k) == matrix.getValue(j,i)) {
                    column++;
                    k--;
                }
                if (minColumn > column) {
                    minColumn = column;
                }
                if (column > row * minColumn || row == 1) {
                    deque.push(new IRectangularIntegerMatrixImpl(row,column ,matrix.getValue(j,i)));
                }
                if (j == matrix.getWidth() - 1 || matrix.getValue(j,i) != matrix.getValue(j + 1,i)) {
                    if (row > 1) {
                        deque.push(new IRectangularIntegerMatrixImpl(row, column, matrix.getValue(j,i)));
                    }
                    row = 1;
                    minColumn = matrix.getHeight() + 1;
                } else {
                    row++;
                }
            }
        }
        IRectangularIntegerMatrixImpl maxMatrix = deque.pop();
        for (int i = 0; i < deque.size(); i++) {
            if (maxMatrix.getSquare() < deque.peek().getSquare()) {
                maxMatrix = deque.pop();
            } else {
                deque.pop();
            }
        }
        return maxMatrix;
    }

    public class IRectangularIntegerMatrixImpl implements IRectangularIntegerMatrix {

        private int wigth;
        private int height;
        private int matrix[][];

        public IRectangularIntegerMatrixImpl(int wigth, int height, int[][] matrix) {
            this.wigth = wigth;
            this.height = height;
            this.matrix = matrix;
        }

        public IRectangularIntegerMatrixImpl(int wigth, int height, int element) {
            this.wigth = wigth;
            this.height = height;
            matrix = new int[this.height][this.wigth];
            for (int i = 0; i < this.height; i++) {
                for (int j = 0; j < this.wigth; j++) {
                    matrix[i][j] = element;
                }
            }
        }

        @Override
        public int getWidth() {
            return wigth;
        }

        @Override
        public int getHeight() {
            return height;
        }

        public int getSquare() {
            return wigth * height;
        }

        @Override
        public int getValue(int indexWidth, int indexHeight) {
            return matrix[indexHeight][indexWidth];
        }
    }
}
