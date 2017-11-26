package com.epam.courses.jf.practice.nzenkova.first;


import java.util.Scanner;

public class Matrix {


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


    public static void printMatrix(int[][] matrix){
        final int numberRows = matrix.length;
        final int numberColumns = matrix[0].length;
        for(int i = 0; i < numberRows; ++i){
            for(int j = 0; j < numberColumns; ++j){
                if(j == numberColumns - 1) System.out.printf("%d", matrix[i][j]);
                else System.out.printf("%d\t", matrix[i][j]);
            }
            System.out.printf("%n");
        }
    }


    public static void swapRows(int[] row1, int[] row2){
        final int dimension = row1.length;
        for(int i = 0; i < dimension; ++i){
            int temp;
            temp = row1[i];
            row1[i] = row2[i];
            row2[i] = temp;
        }
    }

    /**
     * @return Upper triangular matrix.
     */
    public static double[][] upperTriangularMatrix(int[][] temp){
        int dimension = temp.length;
        double[][] matrix = new double[dimension][dimension];

        for(int i = 0; i < dimension; ++i){
            for(int j = 0; j < dimension; ++j){
                matrix[i][j] = temp[i][j];
            }
        }


        for (int i = 0; i < dimension; ++i) {
            if(matrix[i][i] == 0) break;
            for (int j = i + 1; j < dimension; ++j) {
                double coef = matrix[j][i]/matrix[i][i];
                for (int k = 0; k < dimension; ++k) {
                    matrix[j][k] -= coef * matrix[i][k];
                }
            }
        }
        return matrix;
    }

    /**
     * @return Determinant of the matrix.
     */
    public static int getDet(double[][] matrix){
        int dimension = matrix.length;
        double det = 1.0;
        for(int i = 0; i < dimension; ++i){
            if(matrix[i][i] == 0) return 0;
            else{
                det *= matrix[i][i];
            }
        }
        return (int)Math.round(det);
    }

    public static Double[][] readDoubleMatrix(Scanner scanner) {
        final int DIMENSION = scanner.nextInt();
        Double[][] matrix = new Double[DIMENSION][DIMENSION];
        for (int row = 0; row < DIMENSION; ++row) {
            for (int col = 0; col < DIMENSION; ++col) {
                matrix[row][col] = scanner.nextDouble();
            }
        }
        return matrix;
    }

    /**
     * @return Check the local minimum.
     */
    public static boolean testLocalMin(int[][] matrix, int i, int j){
        if(i == 0){
            if(j == 0){
                if((matrix[i][j] < matrix[i][j + 1]) && (matrix[i][j] < matrix[i + 1][j])
                        && (matrix[i][j] < matrix[i + 1][j + 1]))
                    return true;
                else return false;
            }
            else if(j == matrix.length - 1){
                if((matrix[i][j] < matrix[i][j - 1]) && (matrix[i][j] < matrix[i + 1][j])
                        && (matrix[i][j] < matrix[i + 1][j - 1]))
                    return true;
                else return false;
            }
            else if((matrix[i][j] < matrix[i][j - 1]) && (matrix[i][j] < matrix[i + 1][j])
                    && (matrix[i][j] < matrix[i + 1][j - 1]) && (matrix[i][j] < matrix[i][j + 1])
                    && (matrix[i][j] < matrix[i + 1][j + 1]))
                return true;
            else return false;
        }
        else if(i == matrix.length - 1){
            if(j == 0){
                if((matrix[i][j] < matrix[i - 1][j]) && (matrix[i][j] < matrix[i - 1][j  + 1])
                        && (matrix[i][j] < matrix[i][j + 1]))
                    return true;
                else return false;
            }
            else if(j == matrix.length - 1){
                if((matrix[i][j] < matrix[i - 1][j]) && (matrix[i][j] < matrix[i - 1][j  - 1])
                        && (matrix[i][j] < matrix[i][j - 1]))
                    return true;
                else return false;
            }
            else if((matrix[i][j] < matrix[i][j - 1]) && (matrix[i][j] < matrix[i - 1][j])
                    && (matrix[i][j] < matrix[i - 1][j - 1]) && (matrix[i][j] < matrix[i][j + 1])
                    && (matrix[i][j] < matrix[i - 1][j + 1]))
                return true;
            else return false;
        }
        else if(j == 0){
            if((matrix[i][j] < matrix[i - 1][j]) && (matrix[i][j] < matrix[i - 1][j + 1])
                    && (matrix[i][j] < matrix[i][j + 1]) && (matrix[i][j] < matrix[i + 1][j + 1])
                    && (matrix[i][j] < matrix[i + 1][j]))
                return true;
            else return false;
        }
        else if(j == matrix.length){
            if((matrix[i][j] < matrix[i - 1][j]) && (matrix[i][j] < matrix[i - 1][j - 1])
                    && (matrix[i][j] < matrix[i][j - 1]) && (matrix[i][j] < matrix[i + 1][j - 1])
                    && (matrix[i][j] < matrix[i + 1][j]))
                return true;
            else return false;
        }
        else if((matrix[i][j] < matrix[i][j - 1]) && (matrix[i][j] < matrix[i - 1][j - 1])
                && (matrix[i][j] < matrix[i - 1][j]) && (matrix[i][j] < matrix[i - 1][j + 1])
                && (matrix[i][j] < matrix[i][j + 1]) && (matrix[i][j] < matrix[i + 1][j + 1])
                && (matrix[i][j] < matrix[i + 1][j]) && (matrix[i][j] < matrix[i + 1][j - 1]))
            return true;
        else return false;
    }

    /**
     * @return Check the local maximum.
     */
    public static boolean testLocalMax(int[][] matrix, int i , int j){
        if(i == 0){
            if(j == 0){
                if((matrix[i][j] > matrix[i][j + 1]) && (matrix[i][j] > matrix[i + 1][j])
                        && (matrix[i][j] > matrix[i + 1][j + 1]))
                    return true;
                else return false;
            }
            else if(j == matrix.length - 1){
                if((matrix[i][j] > matrix[i][j - 1]) && (matrix[i][j] > matrix[i + 1][j])
                        && (matrix[i][j] > matrix[i + 1][j - 1]))
                    return true;
                else return false;
            }
            else if((matrix[i][j] > matrix[i][j - 1]) && (matrix[i][j] > matrix[i + 1][j])
                    && (matrix[i][j] > matrix[i + 1][j - 1]) && (matrix[i][j] > matrix[i][j + 1])
                    && (matrix[i][j] > matrix[i + 1][j + 1]))
                return true;
            else return false;
        }
        else if(i == matrix.length - 1){
            if(j == 0){
                if((matrix[i][j] > matrix[i - 1][j]) && (matrix[i][j] > matrix[i - 1][j  + 1])
                        && (matrix[i][j] > matrix[i][j + 1]))
                    return true;
                else return false;
            }
            else if(j == matrix.length - 1){
                if((matrix[i][j] > matrix[i - 1][j]) && (matrix[i][j] > matrix[i - 1][j  - 1])
                        && (matrix[i][j] > matrix[i][j - 1]))
                    return true;
                else return false;
            }
            else if((matrix[i][j] > matrix[i][j - 1]) && (matrix[i][j] > matrix[i - 1][j])
                    && (matrix[i][j] > matrix[i - 1][j - 1]) && (matrix[i][j] > matrix[i][j + 1])
                    && (matrix[i][j] > matrix[i - 1][j + 1]))
                return true;
            else return false;
        }
        else if(j == 0){
            if((matrix[i][j] > matrix[i - 1][j]) && (matrix[i][j] > matrix[i - 1][j + 1])
                    && (matrix[i][j] > matrix[i][j + 1]) && (matrix[i][j] > matrix[i + 1][j + 1])
                    && (matrix[i][j] > matrix[i + 1][j]))
                return true;
            else return false;
        }
        else if(j == matrix.length){
            if((matrix[i][j] > matrix[i - 1][j]) && (matrix[i][j] > matrix[i - 1][j - 1])
                    && (matrix[i][j] > matrix[i][j - 1]) && (matrix[i][j] > matrix[i + 1][j - 1])
                    && (matrix[i][j] > matrix[i + 1][j]))
                return true;
            else return false;
        }
        else if((matrix[i][j] > matrix[i][j - 1]) && (matrix[i][j] > matrix[i - 1][j - 1])
                && (matrix[i][j] > matrix[i - 1][j]) && (matrix[i][j] > matrix[i - 1][j + 1])
                && (matrix[i][j] > matrix[i][j + 1]) && (matrix[i][j] > matrix[i + 1][j + 1])
                && (matrix[i][j] > matrix[i + 1][j]) && (matrix[i][j] > matrix[i + 1][j - 1]))
            return true;
        else return false;
    }

    /**
     * @return Transposed matrix.
     */
    public static int[][] transposedMatrix(int[][] matrix){
        int dimension = matrix.length;
        int[][] transposedMatrix = new int[dimension][dimension];
        for(int i = 0; i < dimension; ++i){
            for(int j = 0; j < dimension; ++j){
                transposedMatrix[i][j] = matrix[j][i];
            }
        }
        return transposedMatrix;
    }
}


