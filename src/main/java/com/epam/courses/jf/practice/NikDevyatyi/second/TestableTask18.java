package com.epam.courses.jf.practice.NikDevyatyi.second;

import com.epam.courses.jf.practice.common.second.ITestableTask18;

import java.util.*;

public class TestableTask18 implements ITestableTask18 {
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
        int ferstStart = 0;
        int ferstFinish = 0;
        int secondStart = 0;
        int secondFinish = 0;
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
                    ferstStart = d[j] + 1;
                    ferstFinish = i;
                    secondStart = d1[j] + 1;
                    secondFinish = d2[j] - 1;
                }
            }

        }
        int k = ferstFinish - ferstStart + 1;
        int l = secondFinish - secondStart + 1;
        int[][] subMatrix = new int[k][l];
        for (int i = ferstStart; i <= ferstFinish; i++) {
            for (int j = secondStart; j <= secondFinish; j++) {
                subMatrix[j - secondStart][i - secondFinish] = matrix.getValue(i, j);
            }
        }
        return new RectangularIntegerMatrix(subMatrix);//generateSubMatrix(matrix, ferstStart, ferstFinish, secondStart, secondFinish);
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



    }

}
