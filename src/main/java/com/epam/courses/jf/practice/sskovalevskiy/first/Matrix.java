package com.epam.courses.jf.practice.sskovalevskiy.first;

import java.util.Scanner;
import java.util.Set;

/**
 * Created by asus on 19.11.2017.
 */
public class Matrix {

    /**
     * @param scanner - принимает на вход Scanner
     * @return Integer[][] matrix - возвращает прочитанную с консоли матрицу
     */
    public static Integer[][] readMatrix(Scanner scanner) {
        final int DIMENSION = scanner.nextInt();
        Integer[][] matrix = new Integer[DIMENSION][DIMENSION];
        for (int row = 0; row < DIMENSION; ++row) {
            for (int col = 0; col < DIMENSION; ++col) {
                matrix[row][col] = scanner.nextInt();
            }
        }
        return matrix;
    }

    /**
     * @param matrix метод принимает на вход матрицу Integer[][] matrix
     * выводит в консоль размерность матрицы и её саму
     */
    public static void printMatrix(Integer[][] matrix) {

        System.out.println(matrix.length);

        for (int row = 0; row < matrix.length; ++row) {
            for (int col = 0; col < matrix.length; ++col) {
                System.out.print(matrix[row][col] + "\t");
            }
        }
        System.out.println();
    }

    /**
     * @param matrix метод принимает на вход матрицу int[][] matrix
     * выводит в консоль размерность матрицы и её саму
     */
    public static void printMatrix(int[][] matrix) {

        System.out.println(matrix.length);

        for (int row = 0; row < matrix.length; ++row) {
            for (int col = 0; col < matrix.length; ++col) {
                System.out.print(matrix[row][col] + "\t");
            }
        }
        System.out.println();
    }

    /**
     * @param matrix метод принимает на вход матрицу int[][] matrix
     * @param linesToDelete - множество строк, которые не нужно печатать
     * @param columnToDelete - множество столбцов, которые не нужно печатать
     * выводит в консоль размерность матрицы и её саму
     */
    public static void printReducedMatrix(int[][] matrix,Set<Integer> linesToDelete,Set<Integer> columnToDelete) {

        System.out.println(matrix.length - linesToDelete.size());
        System.out.println(matrix.length - columnToDelete.size());

        for (Integer i = 0; i < matrix.length; i++) {
            if (linesToDelete.contains(i)) continue;
            for (Integer j = 0; j < matrix.length; j++) {
                if (columnToDelete.contains(j)) continue;
                System.out.print(matrix[i][j] + (j.equals(matrix.length - 1) ? "\n" : "\t"));
            }
        }
    }

}
