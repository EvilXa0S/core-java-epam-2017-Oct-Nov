package com.epam.courses.jf.practice.SigarevVL.second;

import com.epam.courses.jf.practice.common.second.ITestableTask18;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Searches in a matrix a square matrix
 * which is consist of the maximum number of identical elements.
 * Uses stack.
 */
public class Task18 implements ITestableTask18 {

    /**
     * @param matrix Analyzed matrix.
     * @return Submatrix consisting of the maximum number of identical elements.
     */
    @Override
    public IRectangularIntegerMatrix getMaxSubMatrix(IRectangularIntegerMatrix matrix) {

        Deque<IRectangularIntegerMatrixImpl> deque = new ArrayDeque<>(getAllSubmatrix(matrix));
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

    /**
     * Performs the division of the matrix into submatrix of identical elements
     *
     * @param matrix Analyzed matrix.
     * @return returns a queue containing submatrix
     * of identical elements of the input matrix
     */
    private Deque getAllSubmatrix(IRectangularIntegerMatrix matrix) {
        Deque<IRectangularIntegerMatrixImpl> deque = new ArrayDeque<>();

        int minColumn = matrix.getHeight() + 1;
        int countRowElements = 1;
        for (int i = 0; i < matrix.getHeight(); i++) {
            for (int j = 0; j < matrix.getWidth(); j++) {
                int k = i + 1;
                int column = 1;
                while (k < matrix.getHeight() && matrix.getValue(j, k)
                        == matrix.getValue(j, i)) {
                    column++;
                    k++;
                }
                k = i - 1;
                while (k >= 0 && matrix.getValue(j, k) == matrix.getValue(j, i)) {
                    column++;
                    k--;
                }
                if (minColumn > column) {
                    minColumn = column;
                }
                if (column > countRowElements * minColumn
                        || countRowElements == 1) {

                    deque.push(new IRectangularIntegerMatrixImpl(countRowElements,
                            column, matrix.getValue(j, i)));
                }
                if (j == matrix.getWidth() - 1 || matrix.getValue(j, i)
                        != matrix.getValue(j + 1, i)) {
                    if (countRowElements > 1) {
                        deque.push(new IRectangularIntegerMatrixImpl(countRowElements,
                                column, matrix.getValue(j, i)));
                    }
                    countRowElements = 1;
                    minColumn = matrix.getHeight() + 1;
                } else {
                    countRowElements++;
                }
            }
        }
        return deque;
    }


    /**
     * Rectangular matrix of integers.
     */
    public class IRectangularIntegerMatrixImpl
            implements IRectangularIntegerMatrix {

        private int wigth;
        private int height;
        private int matrix[][];

        public IRectangularIntegerMatrixImpl(int wigth, int height,
                                             int[][] matrix) {
            this.wigth = wigth;
            this.height = height;
            this.matrix = matrix;
        }

        public IRectangularIntegerMatrixImpl(int wigth, int height,
                                             int element) {
            this.wigth = wigth;
            this.height = height;
            matrix = new int[this.height][this.wigth];
            for (int i = 0; i < this.height; i++) {
                for (int j = 0; j < this.wigth; j++) {
                    matrix[i][j] = element;
                }
            }
        }

        /**
         * @return Width of the matrix.
         */
        @Override
        public int getWidth() {
            return wigth;
        }

        /**
         * @return The height of the matrix.
         */
        @Override
        public int getHeight() {
            return height;
        }

        /**
         * @return The square of the matrix.
         */
        public int getSquare() {
            return wigth * height;
        }

        /**
         * @param indexWidth  Index by width.
         * @param indexHeight Index for height.
         * @return A value is located in the specified cell.
         */
        @Override
        public int getValue(int indexWidth, int indexHeight) {
            return matrix[indexHeight][indexWidth];
        }
    }
}
