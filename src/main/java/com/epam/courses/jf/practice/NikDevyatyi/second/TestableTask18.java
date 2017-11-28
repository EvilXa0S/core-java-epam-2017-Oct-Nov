package com.epam.courses.jf.practice.NikDevyatyi.second;

import com.epam.courses.jf.practice.common.second.ITestableTask18;

import java.util.*;

public class TestableTask18 implements ITestableTask18 {
    @Override
    public IRectangularIntegerMatrix getMaxSubMatrix(IRectangularIntegerMatrix matrix) {
        Set<Integer> res = new HashSet<>();
        int ans = 0;
        int[] d = new int [matrix.getWidth()];
        int[] d1 = new int [matrix.getWidth()];
        int[] d2 = new int [matrix.getWidth()];
        int firstStart = 0;
        int firstFinish = 0;
        int secondStart = 0;
        int secondFinish = 0;
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
                resArr[i][j] =1;
            }
        }
        return new RectangularIntegerMatrix(resArr);
    }

    public class RectangularIntegerMatrix implements IRectangularIntegerMatrix {
        public int[][] matrix;

        public RectangularIntegerMatrix(int[][] matrix) {
            this.matrix = Arrays.copyOf(matrix, matrix.length);
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
    }
}
