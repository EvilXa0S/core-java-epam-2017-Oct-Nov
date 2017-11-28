package com.epam.courses.jf.practice.NikDevyatyi.second;

import com.epam.courses.jf.practice.common.second.ITestableTask18;

import java.util.*;

public class TestableTask18 implements ITestableTask18 {
    @Override
    public IRectangularIntegerMatrix getMaxSubMatrix(IRectangularIntegerMatrix matrix) {
        IRectangularIntegerMatrix result = matrix;
        Set<Integer> res = new HashSet<>();
        int ansMax = 0;
        int[] rowsCols = new int[4];
        int[] resRowsCols = new int[4];
        int ans = 0;
        int[] d = new int [matrix.getWidth()];
        int[] d1 = new int [matrix.getWidth()];
        int[] d2 = new int [matrix.getWidth()];
        int firstStart = 0;
        int firstFinish = 0;
        int secondStart = 0;
        int secondFinish = 0;
        //IRectangularIntegerMatrix result;
        Deque<Integer> stack  =  new ArrayDeque<>();
        for(int i = 0; i < matrix.getHeight(); i++){
            for(int j = 0; j < matrix.getWidth(); j++){
                res.add(matrix.getValue(i,j));
            }
        }
        Arrays.fill(d,-1);
        for(Integer item:res){
            for (int i = 0; i < matrix.getHeight(); i++) {
                for (int j = 0; j < matrix.getWidth(); j++) {
                    if (matrix.getValue(i,j) != item.intValue()) {
                        d[j] = i;
                    }
                }
                stack.clear();
                for (int j = 0; j < matrix.getWidth(); j++) {
                    while (!stack.isEmpty() && d[stack.peek()] <= d[j]) {
                        stack.pop();
                    }
                    d1[j] = stack.isEmpty() ? -1 : stack.peek();
                    stack.push(j);
                }
                stack.clear();
                for (int j = matrix.getWidth() - 1; j >= 0; j--) {
                    while (!stack.isEmpty() && d[stack.peek()] <= d[j])  stack.pop();
                    d2[j] = stack.isEmpty() ? matrix.getWidth() : stack.peek();
                    stack.push(j);
                }

                for (int j = 0; j < matrix.getWidth(); j++) {
                    int current = ans;
                    ans = Math.max(ans, (i - d[j]) * (d2[j] - d1[j] - 1));
                    if ((current - ans) != 0) {
                        firstStart = d[j] + 1;
                        firstFinish = i;
                        secondStart = d1[j] + 1;
                        secondFinish = d2[j] - 1;
                    }

                }
            }
        }

        int [][]resArr = new int[firstFinish-firstStart+1][secondFinish-secondStart+1];
        for (int i = 0; i < firstFinish-firstStart+1; i++) {
            for (int j = 0; j < secondFinish-secondStart+1; j++) {
                resArr[i][j] =matrix.getValue(firstStart, secondStart);// matrix.getValue(firstStart, secondStart);
            }
        }
        result = getMaxSubMatrixOfValue(new RectangularIntegerMatrix(resArr));
        return result;
    }

    IRectangularIntegerMatrix getMaxSubMatrixOfValue(IRectangularIntegerMatrix matrix) {
        Set<Integer> res = new HashSet<>();
        int ansMax = 0;
        int[] rowsCols = new int[4];
        int[] resRowsCols = new int[4];
        int ans = 0;
        int[] d = new int [matrix.getWidth()];
        int[] d1 = new int [matrix.getWidth()];
        int[] d2 = new int [matrix.getWidth()];
        int firstStart = 0;
        int firstFinish = 0;
        int secondStart = 0;
        int secondFinish = 0;
        IRectangularIntegerMatrix result;
        Deque<Integer> stack  =  new ArrayDeque<>();
        for(int i = 0; i < matrix.getHeight(); i++){
            for(int j = 0; j < matrix.getWidth(); j++){
                res.add(matrix.getValue(i,j));
            }
        }
        //System.out.println(Arrays.toString(res.toArray()));
        Arrays.fill(d,-1);
        for(Integer item:res){
            for (int i = 0; i < matrix.getHeight(); i++) {
                for (int j = 0; j < matrix.getWidth(); j++) {
                    if (matrix.getValue(i,j) != item.intValue()) {
                        d[j] = i;
                    }
                }
                stack.clear();
                for (int j = 0; j < matrix.getWidth(); j++) {
                    while (!stack.isEmpty() && d[stack.peek()] <= d[j]) {
                        stack.pop();
                    }
                    d1[j] = stack.isEmpty() ? -1 : stack.peek();
                    stack.push(j);
                }
                stack.clear();
                for (int j = matrix.getWidth() - 1; j >= 0; j--) {
                    while (!stack.isEmpty() && d[stack.peek()] <= d[j])  stack.pop();
                    d2[j] = stack.isEmpty() ? matrix.getWidth() : stack.peek();
                    stack.push(j);
                }

                for (int j = 0; j < matrix.getWidth(); j++) {
                    int current = ans;
                    ans = Math.max(ans, (i - d[j]) * (d2[j] - d1[j] - 1));
                    if ((current - ans) != 0) {
                        firstStart = d[j] + 1;
                        firstFinish = i;
                        secondStart = d1[j] + 1;
                        secondFinish = d2[j] - 1;
                    }

                }
            }
        }
        //System.out.println(Arrays.toString(rowsCols));

        //int n = firstFinish - firstStart + 1;
        //int m = secondFinish - secondStart + 1;
        //int[][] subMatrix = new int[n][m];
        int [][]resArr = new int[firstFinish-firstStart+1][secondFinish-secondStart+1];
        for (int i = 0; i < firstFinish-firstStart+1; i++) {
            for (int j = 0; j < secondFinish-secondStart+1; j++) {
                resArr[i][j] =matrix.getValue(firstStart, secondStart);// matrix.getValue(firstStart, secondStart);
            }
        }
        return new RectangularIntegerMatrix(resArr);
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
        public int getValue(int indexHeight, int indexWidth) {
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
