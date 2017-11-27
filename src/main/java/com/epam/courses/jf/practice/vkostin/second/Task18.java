package com.epam.courses.jf.practice.vkostin.second;

import com.epam.courses.jf.practice.common.second.ITestableTask18;

import java.util.*;

public class Task18 implements ITestableTask18 {

    @Override
    public IRectangularIntegerMatrix getMaxSubMatrix(IRectangularIntegerMatrix matrix) {

        if ((1 == matrix.getHeight()) && (1 == matrix.getWidth())) {
            return matrix;
        }

        Stack<Integer> stack = new Stack<>();
        List<IRectangularIntegerMatrix> list = new ArrayList<>();

        int countWidth = 0;
        int countHeight = 0;
        int beginWidth = 0;
        int endWidth = 0;

        for (int i = 0; i < matrix.getHeight(); ++i) {
            for (int j = 0; j < matrix.getWidth(); ++j) {
                if (stack.isEmpty()) {
                    stack.push(matrix.getValue(j, i));
                    countWidth++;
                    beginWidth = j;
                    continue;
                }
                if (matrix.getValue(j, i) == stack.peek()) {
                    stack.push(matrix.getValue(j, i));
                    if (countHeight < 1) {
                        countWidth++;
                        endWidth++;
                    }
                    continue;
                } else if ((i + 1 != matrix.getHeight())
                        && matrix.getValue(i + 1, beginWidth) == stack.peek()) {
                    countHeight++;

                    anchor:
                    for (int z = i + 1; z < matrix.getHeight(); ++z) {
                        for (int x = beginWidth; x <= endWidth; ++x) {
                            if (matrix.getValue(x, z) == stack.peek()) {
                                stack.push(matrix.getValue(x, z));
                            } else {
                                break anchor;
                            }
                        }
                        countHeight++;
                    }
                }

                if (0 == countHeight) {
                    countHeight++;
                }

                int[][] newMatrix = new int[countHeight][countWidth];

                for (int j1 = 0; j1 < countHeight; ++j1) {
                    for (int k = 0; k < countWidth; ++k) {
                        newMatrix[j1][k] = stack.pop();
                    }
                }

                list.add(new RectangularIntegerMatrix(newMatrix));
                countWidth = 0;
                countHeight = 0;
                beginWidth = 0;
                endWidth = 0;
            }

            if (!stack.isEmpty()) {
                countHeight++;
            }
            if ((i == matrix.getHeight() - 1) && (!stack.isEmpty())) {
                if (0 == countHeight) {
                    countHeight++;
                }

                int[][] newMatrix = new int[countHeight][countWidth];

                for (int j = 0; j < countHeight; ++j) {
                    for (int k = 0; k < countWidth; ++k) {
                        newMatrix[j][k] = stack.pop();
                    }
                }

                list.add(new RectangularIntegerMatrix(newMatrix));
            }
        }

        Collections.sort(list, (o1, o2)
                -> o1.getHeight() * o1.getWidth() > 1 + o2.getHeight() * o2.getWidth() ? -1 : 1);

        return list.get(0);
    }


    static class RectangularIntegerMatrix implements IRectangularIntegerMatrix {

        private int[][] matrix;

        RectangularIntegerMatrix(int[][] srcMatrix) {
            matrix = srcMatrix;
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
