package com.epam.courses.jf.practice.nzenkova.second;

import com.epam.courses.jf.practice.common.second.ITestableTask18;


public class TestableTask18 implements ITestableTask18 {
    @Override
    public IRectangularIntegerMatrix getMaxSubMatrix(IRectangularIntegerMatrix matrix) {
        if (matrix == null) return null;
        IRectangularIntegerMatrix res = null;
        int max = 0;
        int m = matrix.getWidth();
        int n = matrix.getHeight();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                RectangularIntegerMatrix matrix1 = getMaxSubMatrix(i, j, n, m, matrix);
                if(matrix1.getWidth() * matrix1.getHeight() > max) {
                    max = matrix1.getHeight() * matrix1.getWidth();
                    res = matrix1;
                }
            }
        }
        return res;
    }

    /**
     * @return The maximum submatrix ( to the left down from the element (i,j))
     */
    private RectangularIntegerMatrix getMaxSubMatrix(int i, int j, int n,  int m, IRectangularIntegerMatrix matrix) {
        int[][] resArray;
        int max; //the maximum submatrix
        int x = i, y = i; //the coordinates of the lower right corner
        int up = i + 1;
        int down;
        while (up < n && matrix.getValue(up, j) == matrix.getValue(up - 1, j)) up++;
        max = up - i;
        x = up - 1;
        y = j;
        for (int l = j + 1; l < m; l++) {
            if(matrix.getValue(i, l) != matrix.getValue(i, l - 1))
                break;
            down = i + 1;
            while(down < n && matrix.getValue(down, l) == matrix.getValue(down - 1, l))
            {
                down++;
            }
            if(max < (Math.min(up, down) - i)  * (l - j + 1)) {
                max = (Math.min(up, down) - i) * (l - j + 1);
                x = Math.min(up, down) - 1;
                y = l;
            }
            up = Math.min(up, down);
        }
        resArray = new int[y - j + 1][x - i + 1];
        setMatrix(i, j, x, y, resArray, matrix);
        return new RectangularIntegerMatrix(x - i + 1, y - j + 1, resArray);
    }

    /**
     * Specify the array from submatrix
     */
    private void setMatrix(int i, int j, int x, int y, int[][] arrMatrix, IRectangularIntegerMatrix matrix) {
        for (int k = 0; k <= x - i; k++) {
            for (int l = 0; l <= y - j; l++) {
                arrMatrix[l][k] = matrix.getValue(k + i, l + j);
            }
        }
    }

    class RectangularIntegerMatrix implements ITestableTask18.IRectangularIntegerMatrix {

        private int width;
        private int height;
        private int[][] matrix;



        public RectangularIntegerMatrix(int width, int height, int[][] matrix) {
            this.width = width;
            this.height = height;
            this.matrix = matrix;
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
            return matrix[indexHeight][indexWidth];
        }

    }
}