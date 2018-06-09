package com.epam.courses.jf.practice.klimenko.second;

import com.epam.courses.jf.practice.common.second.ITestableTask18;

import java.util.Arrays;
import java.util.Stack;

public class TestableTask18Impl implements ITestableTask18 {
    private class SolutionInfo {
        int area = -1;
        int value;
        int w, h;
    }

    @Override
    public IRectangularIntegerMatrix getMaxSubMatrix(IRectangularIntegerMatrix matrix) {
        int[] upperBound = new int[matrix.getWidth()];
        int[] leftBound = new int[matrix.getWidth()];
        int[] rightBound = new int[matrix.getWidth()];
        Stack<Integer> stack = new Stack<>();
        SolutionInfo best = new SolutionInfo();

        Arrays.fill(upperBound, -1);

        /* For each row select best possible answer */
        for (int lastRow = 0; lastRow < matrix.getHeight(); ++lastRow) {

            /* Update upper bound */
            if (lastRow > 0) {
                for (int j = 0; j < matrix.getWidth(); ++j) {
                    if (matrix.getValue(j, lastRow) != matrix.getValue(j, lastRow - 1)) {
                        upperBound[j] = lastRow - 1;
                    }
                }
            }

            /* Update left bound */
            stack.clear();
            for (int j = 0; j < matrix.getWidth(); ++j) {
                while (!stack.empty()
                        && upperBound[stack.peek()] <= upperBound[j]
                        && matrix.getValue(stack.peek(), lastRow) == matrix.getValue(j, lastRow)) {
                    stack.pop();
                }
                leftBound[j] = stack.isEmpty() ? -1 : stack.peek();
                stack.push(j);
            }

            /* Update right bound */
            stack.clear();
            for (int j = matrix.getWidth() - 1; j >= 0; --j) {
                while (!stack.empty()
                        && upperBound[stack.peek()] <= upperBound[j]
                        && matrix.getValue(stack.peek(), lastRow) == matrix.getValue(j, lastRow)) {
                    stack.pop();
                }
                rightBound[j] = stack.isEmpty() ? matrix.getWidth() : stack.peek();
                stack.push(j);
            }

            /* Update answer */
            for (int j = 0; j < matrix.getWidth(); ++j) {
                int w = rightBound[j] - leftBound[j] - 1;
                int h = lastRow - upperBound[j];
                int area = w * h;

                if (area > best.area) {
                    best.area = area;
                    best.value = matrix.getValue(j, lastRow);
                    best.w = w;
                    best.h = h;
                }
            }
        }

        return new RectMatrix(best.w, best.h, best.value);
    }

    private class RectMatrix implements IRectangularIntegerMatrix {
        private Integer[][] data;
        private int w, h;

        RectMatrix(int w, int h, int value) {
            this.w = w;
            this.h = h;
            this.data = new Integer[h][w];
            for (int i = 0; i < h; ++i) {
                for (int j = 0; j < w; ++j) {
                    data[i][j] = value;
                }
            }
        }

        @Override
        public int getWidth() {
            return w;
        }

        @Override
        public int getHeight() {
            return h;
        }

        @Override
        public int getValue(int indexWidth, int indexHeight) {
            return data[indexHeight][indexWidth];
        }
    }
}
