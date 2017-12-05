package com.epam.courses.jf.practice.asunyaev.second;

import com.epam.courses.jf.practice.common.second.ITestableTask18;

import java.util.*;

public class Task18 implements ITestableTask18 {

    //За основу взят алгоритм с http://e-maxx.ru/algo/maximum_zero_submatrix
    @Override
    public IRectangularIntegerMatrix getMaxSubMatrix(IRectangularIntegerMatrix matrix) {
        IRectangularIntegerMatrix result = matrix;
        int n = matrix.getHeight();
        int m = matrix.getWidth();
        int maxSize = 0;
        Set<Integer> differentValues = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                differentValues.add(matrix.getValue(i, j));
            }
        }
        for (Integer element : differentValues) {
            IRectangularIntegerMatrix currentSubMatrix = getMaxSubMatrixOfValue(matrix, element);
            int size = currentSubMatrix.getHeight() * currentSubMatrix.getWidth();
            if (size > maxSize) {
                maxSize = size;
                result = currentSubMatrix;
            }
        }
        return result;
    }

    IRectangularIntegerMatrix getMaxSubMatrixOfValue(IRectangularIntegerMatrix matrix, int value) {
        int n = matrix.getHeight();
        int m = matrix.getWidth();
        Deque<Integer> stack = new ArrayDeque<>();
        int[] d = new int[m];
        Arrays.fill(d, -1);
        int[] d1 = new int[m];
        int[] d2 = new int[m];
        int size = 0;
        int iStart = 0;
        int iFinish = 0;
        int jStart = 0;
        int jFinish = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix.getValue(i, j) != value) {
                    d[j] = i;
                }
            }
            stack.clear();
            for (int j = 0; j < m; j++) {
                while (!stack.isEmpty() && d[stack.peek()] <= d[j]) {
                    stack.pop();
                }
                d1[j] = stack.isEmpty() ? -1 : stack.peek();
                stack.push(j);
            }
            stack.clear();
            for (int j = m - 1; j >= 0; j--) {
                while (!stack.isEmpty() && d[stack.peek()] <= d[j])  stack.pop();
                d2[j] = stack.isEmpty() ? m : stack.peek();
                stack.push(j);
            }
            for (int j = 0; j < m; j++) {
                int oldSize = size;
                size = Math.max(size, (i - d[j]) * (d2[j] - d1[j] - 1));
                if ((oldSize - size) != 0) {
                    iStart = d[j] + 1;
                    iFinish = i;
                    jStart = d1[j] + 1;
                    jFinish = d2[j] - 1;
                }
            }
        }
        return generateSubMatrix(matrix, iStart, iFinish, jStart, jFinish);
    }

    IRectangularIntegerMatrix generateSubMatrix(IRectangularIntegerMatrix matrix, int iStart, int iFinish, int jStart, int jFinish) {
        int n = iFinish - iStart + 1;
        int m = jFinish - jStart + 1;
        int[][] subMatrix = new int[m][n];
        for (int i = iStart; i <= iFinish; i++) {
            for (int j = jStart; j <= jFinish; j++) {
                subMatrix[j - jStart][i - iStart] = matrix.getValue(i, j);
            }
        }
        return new RectangularIntegerMatrix(subMatrix);
    }





    private class RectangularIntegerMatrix implements IRectangularIntegerMatrix {

        private int[][] matrix;

        RectangularIntegerMatrix(int[][] array) {
            matrix = Arrays.copyOf(array, array.length);
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
            return matrix[indexHeight][indexWidth];
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            if (other == null || !(other instanceof IRectangularIntegerMatrix)) return false;
            IRectangularIntegerMatrix matrix = (IRectangularIntegerMatrix) other;
            if (getHeight() != matrix.getHeight() || getWidth() != matrix.getWidth()) {
                return false;
            }
            for (int row = 0; row < getHeight(); row++) {
                for (int col = 0; col < getWidth(); col++) {
                    if (getValue(col, row) != matrix.getValue(col, row)) {
                        return false;
                    }
                }
            }
            return true;
        }

    }
}
