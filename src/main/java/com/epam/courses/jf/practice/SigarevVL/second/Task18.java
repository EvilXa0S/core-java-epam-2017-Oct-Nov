package com.epam.courses.jf.practice.SigarevVL.second;

import com.epam.courses.jf.practice.common.second.ITestableTask18;

public class Task18 implements ITestableTask18 {

    @Override
    public IRectangularIntegerMatrix getMaxSubMatrix(IRectangularIntegerMatrix matrix) {
        return null;
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

        @Override
        public int getWidth() {
            return wigth;
        }

        @Override
        public int getHeight() {
            return height;
        }

        @Override
        public int getValue(int indexWidth, int indexHeight) {
            return matrix[indexHeight][indexWidth];
        }
    }
}
