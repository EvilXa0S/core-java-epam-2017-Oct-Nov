package com.epam.courses.jf.practice.igulyaev.util;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class MatrixUtils{
    public static int[][] expandMatrix(final int[][] matrix, int border){
        int[][] expandedMatrix = new int[matrix.length + 2][matrix.length + 2];
        for (int row = 0; row < expandedMatrix.length; ++row) {
            expandedMatrix[row][0] = border;
            expandedMatrix[row][expandedMatrix.length - 1] = border;
        }
        for (int col = 0; col < expandedMatrix.length; ++col) {
            expandedMatrix[0][col] = border;
            expandedMatrix[expandedMatrix.length - 1][col] = border;
        }
        for (int row = 0; row < matrix.length; ++row) {
            System.arraycopy(matrix[row], 0, expandedMatrix[row + 1], 1, matrix.length);
        }
        return expandedMatrix;
    }

    public static int[][] shiftRows(final int[][] matrix, int shift){
        int[][] newMatrix = new int[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; ++i){
            newMatrix[(i + (shift % matrix.length) + matrix.length) % matrix.length] = matrix[i];
        }
        return newMatrix;
    }

    public static String matrixToString(final int[][] matrix){
        StringBuilder builder = new StringBuilder();
        AtomicInteger index = new AtomicInteger(1);
        Arrays.stream(matrix).flatMapToInt(Arrays::stream).forEach(i ->{
            builder.append(i);
            builder.append(index.getAndIncrement() % matrix[0].length == 0 ? "\n" :" ");
        });
        return builder.toString();
    }

    public static int[][] readMatrix(Scanner scanner) {
        final int DIMENSION = scanner.nextInt();
        int[][] matrix = new int[DIMENSION][DIMENSION];
        for (int row = 0; row < DIMENSION; ++row) {
            for (int col = 0; col < DIMENSION; ++col) {
                matrix[row][col] = scanner.nextInt();
            }
        }
        return matrix;
    }

    public static double[][] readDoubleMatrix(Scanner scanner) {
        final int DIMENSION = scanner.nextInt();
        double[][] matrix = new double[DIMENSION][DIMENSION];
        for (int row = 0; row < DIMENSION; ++row) {
            for (int col = 0; col < DIMENSION; ++col) {
                matrix[row][col] = scanner.nextDouble();
            }
        }
        return matrix;
    }

    public static void rotateMatrixRight(int[][] matrix) {
        final int length = matrix.length - 1;
        for (int i = 0; i <= (length)/2; ++i) {
            for (int j = i; j < length - i; ++j) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[length - j][i];
                matrix[length - j][i] = matrix[length - i][length - j];
                matrix[length - i][length - j] = matrix[j][length - i];
                matrix[j][length - i] = temp;
            }
        }
    }

    public static void rotateMatrixLeft(int[][] matrix) {
        final int length = matrix.length - 1;
        for (int i = 0; i <= (length)/2; ++i) {
            for (int j = i; j < length - i; ++j) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][length - i];
                matrix[j][length - i] = matrix[length - i][length - j];
                matrix[length - i][length - j] = matrix[length - j][i];
                matrix[length - j][i] = temp;
            }
        }
    }

    public static int[][] deleteRowsAndColumns(Set<Integer> invalidRows, Set<Integer> invalidColumns, int[][] matrix){
        Set<Integer> validRows = new HashSet<>();
        Set<Integer> validColumns = new HashSet<>();
        for (int i = 0; i < matrix.length; ++i){
            validRows.add(i);
            validColumns.add(i);
        }
        validColumns.removeAll(invalidColumns);
        validRows.removeAll(invalidRows);
        int[][] shiftRowsMatrix = new int[validRows.size()][matrix[0].length];
        int[][] resultMatrix = new int[validRows.size()][validColumns.size()];
        final AtomicInteger destIndex = new AtomicInteger(0);
        validRows.iterator().forEachRemaining(srcIndex -> System.arraycopy(matrix[srcIndex], 0,
                shiftRowsMatrix[destIndex.getAndIncrement()], 0, matrix[0].length));
        destIndex.set(0);
        for (int srcCol : validColumns) {
            int destCol = destIndex.getAndIncrement();
            for (int row = 0; row < validRows.size(); row++) {
                resultMatrix[row][destCol] = shiftRowsMatrix[row][srcCol];
            }
        }
        return resultMatrix;
    }

    public static void replaceRows(int[][] matrix, int r1, int r2){
        int[] temp = new int[matrix[0].length];
        System.arraycopy(matrix[r1], 0, temp, 0, matrix[0].length);
        System.arraycopy(matrix[r2], 0, matrix[r1], 0, matrix[0].length);
        System.arraycopy(temp, 0, matrix[r2], 0, matrix[0].length);
    }

    public static void replaceColumns(int[][] matrix, int c1, int c2){
        int temp;
        for(int i = 0;i< matrix.length;++i){
            temp = matrix[i][c1];
            matrix[i][c1] = matrix[i][c2];
            matrix[i][c2] = temp;
        }
    }

    public static long determinant(int matrix[][], Map<String, Long> determinantMap) {
        String key = matrixToString(matrix);
        Long determinant = determinantMap.get(key);
        if(determinant != null){
            return determinant;
        }
        long det = 0;
        if(matrix.length == 1) {
            det = matrix[0][0];
        } else if (matrix.length == 2) {
            det = matrix[0][0]*matrix[1][1] - matrix[1][0]*matrix[0][1];
        } else {
            for(int skippedCol = 0; skippedCol < matrix.length; ++skippedCol) {
                int[][] m = new int[matrix.length - 1][matrix.length - 1];
                for(int row = 1; row < matrix.length ; ++row) {
                    int newCol = 0;
                    for(int col = 0; col < matrix.length; ++col) {
                        if(col == skippedCol) {
                            continue;
                        }
                        m[row - 1][newCol] = matrix[row][col];
                        newCol++;
                    }
                }
                det += Math.pow(-1, skippedCol) * matrix[0][skippedCol] * determinant(m, determinantMap);
            }
        }
        determinantMap.put(key, det);
        return det;
    }
}
