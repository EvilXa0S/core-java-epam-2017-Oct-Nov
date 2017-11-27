package com.epam.courses.jf.practice.nbikbaev.second;

import com.epam.courses.jf.practice.common.second.ITestableTask18;

import java.util.*;

public class Task18 implements ITestableTask18 {
    @Override
    public IRectangularIntegerMatrix getMaxSubMatrix(IRectangularIntegerMatrix matrix) {
        int[][] a = new int[matrix.getHeight()][matrix.getWidth()];
        for (int i = 0; i < matrix.getHeight(); i++) {
            for (int j = 0; j < matrix.getWidth(); j++) {
                a[i][j] = matrix.getValue(j, i);
            }
        }
        int[][] b = getMatrix(a);
        return new RectangularIntegerMatrix(b);
    }

    private static int[][] getMatrix(int[][] matrix) {
        int width = matrix.length;
        int length = matrix[0].length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int[] row : matrix) {
            for (int j = 0; j < length; j++) {
                if (!(map.containsKey(row[j]))) {
                    map.put(row[j], 1);
                } else {
                    map.put(row[j], map.get(row[j]) + 1);
                }
            }
        }

        Map.Entry<Integer, Integer> maxEntry =
                Collections.max(map.entrySet(), Comparator.comparingInt(Map.Entry::getValue));

        int element = maxEntry.getKey();
        int maxUp = width - 1;
        int maxDown = 0;
        int maxLeft = length - 1;
        int maxRight = 0;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < length; j++) {
                if (matrix[i][j] == element) {
                    if (maxDown < i) {
                        maxDown = i;
                    }
                    if (maxRight < j) {
                        maxRight = j;
                    }
                    if (maxUp > i) {
                        maxUp = i;
                    }
                    if (maxLeft > j) {
                        maxLeft = j;
                    }
                }
            }
        }

        int[][] newMatrix = new int[maxDown - maxUp + 1][maxRight - maxLeft + 1];
        for (int i = 0; i < maxDown - maxUp + 1; i++) {
            System.arraycopy(matrix[i + maxUp], maxLeft, newMatrix[i], 0, maxRight - maxLeft + 1);
        }
        return newMatrix;
    }


}
