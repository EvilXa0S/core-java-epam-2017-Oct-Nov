package com.epam.courses.jf.practice.nbikbaev.first;


import java.io.*;
import java.util.*;

public class Utils {

    /**
     * Reads a matrix from input stream using {@link Scanner} interface.
     *
     * @param scanner   Scanner
     * @param dimension Matrix dimension
     * @param matrix    Target matrix
     */
    public static void readMatrix(Scanner scanner, int dimension, int[][] matrix) {
        for (int row = 0; row < dimension; ++row) {
            for (int col = 0; col < dimension; ++col) {
                matrix[row][col] = scanner.nextInt();
            }
        }
    }

    /**
     * Reads a matrix from input stream using {@link Scanner} interface.
     *
     * @param scanner   Scanner
     * @param dimension Matrix dimension
     * @param matrix    Target matrix
     */
    public static void readMatrix(Scanner scanner, int dimension, float[][] matrix) {
        for (int row = 0; row < dimension; ++row) {
            for (int col = 0; col < dimension; ++col) {
                matrix[row][col] = scanner.nextFloat();
            }
        }
    }

    /**
     * Prints a matrix to the output stream
     *
     * @param matrix      Matrix to be printed
     * @param printStream The output stream to which matrix will be printed
     */
    public static void printMatrix(int[][] matrix, PrintStream printStream) {
        int dimension = matrix[0].length;
        for (int[] row : matrix) {
            for (int j = 0; j < dimension; j++) {
                printStream.print(row[j]);
                if (j == dimension - 1) {
                    printStream.print("\n");
                } else {
                    printStream.print(" ");
                }
            }
        }
    }

    /**
     * Prints a matrix to the output stream
     *
     * @param matrix      Matrix to be printed
     * @param printStream The output stream to which matrix will be printed
     */
    public static void printMatrix(float[][] matrix, PrintStream printStream) {
        int dimension = matrix.length;
        for (float[] row : matrix) {
            for (int j = 0; j < dimension; j++) {
                printStream.print(row[j]);
                if (j == dimension - 1) {
                    printStream.print("\n");
                } else {
                    printStream.print(" ");
                }
            }
        }
    }

    /**
     * Swaps the rows at the specified positions in the specified matrix.
     *
     * @param matrix    Target matrix
     * @param rowIndex1 The index of one row to be swapped.
     * @param rowIndex2 The index of the other row to be swapped.
     */
    public static void swapMatrixRows(int[][] matrix, int rowIndex1, int rowIndex2) {
        int[] tempRow = matrix[rowIndex1];
        matrix[rowIndex1] = matrix[rowIndex2];
        matrix[rowIndex2] = tempRow;
    }

    /**
     * Swaps the columns at the specified positions in the specified matrix.
     *
     * @param matrix    Target matrix
     * @param colIndex1 The index of one column to be swapped.
     * @param colIndex2 The index of the other column to be swapped.
     */
    public static void swapMatrixColumns(int[][] matrix, int colIndex1, int colIndex2) {
        int dimension = matrix.length;
        int tmp = 0;
        for (int i = 0; i < dimension; i++) {
            tmp = matrix[i][colIndex1];
            matrix[i][colIndex1] = matrix[i][colIndex2];
            matrix[i][colIndex2] = tmp;
        }
    }

    /**
     * Cyclically shifts the specified matrix by specified number of rows up
     *
     * @param matrix    The matrix to be shifted.
     * @param shift     Number of rows per which the matrix to be shifted
     * @param dimension Matrix dimension
     */
    public static void matrixDownShift(int[][] matrix, int shift, int dimension) {
        for (int i = 0; i < shift; i++) {
            for (int j = dimension - 1; j > 0; j--) {
                Utils.swapMatrixRows(matrix, j, j - 1);
            }
        }
    }

    /**
     * Cyclically shifts the specified matrix by specified number of rows down
     *
     * @param matrix    The matrix to be shifted.
     * @param shift     Number of rows per which the matrix to be shifted
     * @param dimension Matrix dimension
     */
    public static void matrixUpShift(int[][] matrix, int shift, int dimension) {
        for (int i = 0; i < shift; i++) {
            for (int j = 0; j < dimension - 1; j++) {
                Utils.swapMatrixRows(matrix, j, j + 1);
            }
        }
    }

    /**
     * Returns sum of the array elements between first and second positive element
     *
     * @param array Input array
     * @return Sum of the found elements
     */
    public static int findSumBetweenPositives(int[] array) {
        int sum = 0;
        boolean flag = false;
        for (int anArray : array) {
            if (!flag) {
                if (anArray > 0) {
                    flag = true;
                }
            } else {
                if (anArray <= 0) {
                    sum += anArray;
                } else {
                    break;
                }
            }
        }
        return sum;
    }

    /**
     * Returns matrix rotated by 90 degrees
     *
     * @param matrix Matrix to be rotated
     * @return Rotated matrix
     */
    public static int[][] rotateMatrix(int[][] matrix) {
        int[][] rotatedMatrix = new int[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[0].length; j++)
                rotatedMatrix[j][i] = matrix[i][matrix[0].length - 1 - j];
        return rotatedMatrix;
    }


    /**
     * Returns the determinant of the specified matrix
     *
     * @param matrix    Matrix, the determinant of which to be calculated
     * @param dimension Matrix dimension
     * @return Determinant value
     */
    public static int determinant(int[][] matrix, int dimension) {
        int result;
        if (dimension == 1) {
            result = matrix[0][0];
        } else if (dimension == 2) {
            result = matrix[0][0] * matrix[1][1] - matrix[1][0] * matrix[0][1];
        } else {
            result = 0;
            for (int column = 0; column < dimension; column++) {
                int[][] minor = getMinor(matrix, dimension, 0, column);
                result += Math.pow(-1.0, 1.0 + column + 1.0) * matrix[0][column] * determinant(minor, dimension - 1);
            }
        }
        return result;
    }

    /**
     * Returns minimal element in the specified row of the specified matrix
     *
     * @param matrix Target matrix
     * @param row    The index of row
     * @return minimal element
     */
    public static int getRowMinElement(int[][] matrix, int row) {
        int min = matrix[row][0];
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[row][i] < min) {
                min = matrix[row][i];
            }
        }
        return min;
    }

    /**
     * Returns maximal element in the specified column of the specified matrix
     *
     * @param matrix Target matrix
     * @param column The index of row
     * @return maximal element
     */
    public static int getColumnMaxElement(int[][] matrix, int column) {
        int max = matrix[0][column];
        for (int[] rows : matrix) {
            if (rows[column] > max) {
                max = rows[column];
            }
        }
        return max;
    }

    /**
     * Returns sum of all elements of the given array
     *
     * @param array Array which elements to be summed
     * @return Sum of all elements
     */
    public static int sumOfElement(int[] array) {
        return Arrays.stream(array).sum();
    }

    /**
     * Removes columns and rows specified in rowSet and columnSet from specified square matrix
     *
     * @param matrix    Target matrix
     * @param dimension Matrix dimension
     * @param rowSet    The set of rows to be deleted
     * @param columnSet The set of columns to be deleted
     * @return Matrix which not containing specified rows and columns
     */
    public static int[][] removeColumnsAndRows(int[][] matrix, int dimension, Collection<Integer> rowSet, Collection<Integer> columnSet) {
        return removeColumnsAndRows(matrix, dimension, dimension, rowSet, columnSet);
    }

    /**
     * Removes columns and rows specified in rowSet and columnSet from specified rectangular matrix
     *
     * @param matrix    Target matrix
     * @param width     Matrix width
     * @param height    Matrix height
     * @param rowSet    The set of rows to be deleted
     * @param columnSet The set of columns to be deleted
     * @return Matrix which not containing specified rows and columns
     */
    public static int[][] removeColumnsAndRows(int[][] matrix, int width, int height, Collection<Integer> rowSet, Collection<Integer> columnSet) {
        int[][] result = new int[height - rowSet.size()][width - columnSet.size()];
        int k = 0;
        for (int i = 0; i < height; i++) {
            int n = 0;
            if (!(rowSet.contains(i))) {
                for (int j = 0; j < width; j++) {
                    if (!(columnSet.contains(j))) {
                        result[k][n] = matrix[i][j];
                        n++;
                    }
                }
                k++;
            }
        }
        return result;
    }


    /**
     * Swaps the elements at the specified positions in the specified array.
     *
     * @param array Target array
     * @param i     The index of one column to be swapped.
     * @param j     The index of the other column to be swapped.
     */
    public static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    /**
     * Returns minor of the specified square matrix
     *
     * @param matrix    Target matrix
     * @param dimension Matrix dimension
     * @param row       Index of row to be removed
     * @param column    Index of column to be removed
     * @return Minor of the specified matrix
     */
    private static int[][] getMinor(int[][] matrix, int dimension, int row, int column) {
        return removeColumnsAndRows(matrix, dimension, Collections.singleton(row), Collections.singleton(column));
    }

    /**
     * Returns true if the specified element is local minimum
     *
     * @param matrix Matrix that contains element with row index i and column index j
     * @param i      Elements row index
     * @param j      Elements column index
     * @return true, if element specified element is local minimum, otherwise returns false
     */
    public static boolean isLocalMinimum(int[][] matrix, int i, int j) {
        boolean lowerRowNeighbors;
        boolean lowerColumnsNeighbors;
        boolean lowerThanDiagonalNeighbors;
        int dimension = matrix.length;
        if (dimension == 1) {
            return true;
        }
        lowerRowNeighbors = isLowerThanRowNeighbors(matrix, i, j);
        lowerColumnsNeighbors = isLowerThanColumnNeighbors(matrix, i, j);
        lowerThanDiagonalNeighbors = isLowerThanDiagonalNeighbors(matrix, i, j);

        return lowerColumnsNeighbors && lowerRowNeighbors && lowerThanDiagonalNeighbors;
    }

    /**
     * Returns true if matrix element e[i][j] lower than e[i+1][j+1], e[i-1][j+1], e[i-1][j-1] and e[i+1][j-1] otherwise returns false.
     *
     * @param matrix Matrix that contains element with row index i and column index j
     * @param i      Element row index
     * @param j      Element column index
     * @return True, if matrix element is lower than his diagonal neighbors, false if not
     */
    private static boolean isLowerThanDiagonalNeighbors(int[][] matrix, int i, int j) {
        int dimension = matrix.length;
        boolean result = true;
        if (i > 0 && i <= dimension - 1 && j < dimension - 1 && j >= 0) {
            result = result && matrix[i][j] < matrix[i - 1][j + 1];
        }
        if (i > 0 && i <= dimension - 1 && j > 0 && j <= dimension - 1) {
            result = result && matrix[i][j] < matrix[i - 1][j - 1];
        }
        if (i >= 0 && i < dimension - 1 && j > 0 && j <= dimension - 1) {
            result = result && matrix[i][j] < matrix[i + 1][j - 1];
        }
        if (i >= 0 && i < dimension - 1 && j < dimension - 1 && j >= 0) {
            result = result && matrix[i][j] < matrix[i + 1][j + 1];
        }
        return result;
    }

    /**
     * Returns true if matrix element e[i][j] lower than e[i+1][j] and e[i-1][j], otherwise returns false.
     * If one of the neighbors does not exist compares with e[i+1][j] or e[i-1][j]
     *
     * @param matrix Matrix that contains element with row index i and column index j
     * @param i      Element row index
     * @param j      Element column index
     * @return true, if matrix element is lower column neighbors, false if not
     */
    private static boolean isLowerThanColumnNeighbors(int[][] matrix, int i, int j) {
        boolean result;
        if (j == 0) {
            result = matrix[i][j] < matrix[i][j + 1];
        } else if (j < matrix.length - 1) {
            result = matrix[i][j] < matrix[i][j + 1] && matrix[i][j] < matrix[i][j - 1];
        } else {
            result = matrix[i][j] < matrix[i][j - 1];
        }
        return result;
    }

    /**
     * Returns true if matrix element e[i][j] lower than e[i+1][j] and e[i-1][j], otherwise returns false.
     * If one of the neighbors does not exist compares with e[i+1][j] or e[i-1][j]
     *
     * @param matrix Matrix that contains element with row index i and column index j
     * @param i      Element row index
     * @param j      Element column index
     * @return true, if matrix element is lower row neighbors, false if not
     */
    private static boolean isLowerThanRowNeighbors(int[][] matrix, int i, int j) {
        boolean result;
        if (i == 0) {
            result = matrix[i][j] < matrix[i + 1][j];
        } else if (i < matrix.length - 1) {
            result = matrix[i][j] < matrix[i + 1][j] && matrix[i][j] < matrix[i - 1][j];
        } else {
            result = matrix[i][j] < matrix[i - 1][j];
        }
        return result;
    }


    /**
     * Returns true if the specified element is local maximum
     *
     * @param matrix Matrix that contains element with row index i and column index j
     * @param i      Elements row index
     * @param j      Elements column index
     * @return true, if element specified element is local maximum, otherwise returns false
     */
    public static boolean isLocalMaximum(int[][] matrix, int i, int j) {
        boolean greaterColumnNeighbors;
        boolean greaterRowNeighbors;
        boolean greaterThanDiagonalNeighbors;
        int dimension = matrix.length;
        if (dimension == 1) {
            return true;
        }
        greaterColumnNeighbors = isGreaterThanColumnNeighbors(matrix, i, j);
        greaterRowNeighbors = isGreaterThanRowNeighbors(matrix, i, j);
        greaterThanDiagonalNeighbors = isGreaterThanDiagonalNeighbors(matrix, i, j);

        return greaterRowNeighbors && greaterColumnNeighbors && greaterThanDiagonalNeighbors;
    }

    /**
     * Returns true if matrix element e[i][j] greater than e[i+1][j+1], e[i-1][j+1], e[i-1][j-1] and e[i+1][j-1] otherwise returns false.
     *
     * @param matrix Matrix that contains element with row index i and column index j
     * @param i      Element row index
     * @param j      Element column index
     * @return True, if matrix element is greater than his diagonal neighbors, false if not
     */
    private static boolean isGreaterThanDiagonalNeighbors(int[][] matrix, int i, int j) {
        int dimension = matrix.length;
        boolean result = true;
        if (i > 0 && i <= dimension - 1 && j < dimension - 1 && j >= 0) {
            result = matrix[i][j] > matrix[i - 1][j + 1];
        }
        if (i > 0 && i <= dimension - 1 && j > 0 && j <= dimension - 1) {
            result = result && matrix[i][j] > matrix[i - 1][j - 1];
        }
        if (i >= 0 && i < dimension - 1 && j > 0 && j <= dimension - 1) {
            result = result && matrix[i][j] > matrix[i + 1][j - 1];
        }
        if (i >= 0 && i < dimension - 1 && j < dimension - 1 && j >= 0) {
            result = result && matrix[i][j] > matrix[i + 1][j + 1];
        }
        return result;
    }

    /**
     * Returns true if matrix element e[i][j] greater than e[i+1][j] and e[i-1][j], otherwise returns false.
     * If one of the neighbors does not exist compares with e[i+1][j] or e[i-1][j]
     *
     * @param matrix Matrix that contains element with row index i and column index j
     * @param i      Element row index
     * @param j      Element column index
     * @return true, if matrix element is greater row neighbors, false if not
     */
    private static boolean isGreaterThanRowNeighbors(int[][] matrix, int i, int j) {
        boolean result;
        if (j == 0) {
            result = matrix[i][j] > matrix[i][j + 1];
        } else if (j < matrix.length - 1) {
            result = matrix[i][j] > matrix[i][j + 1] && matrix[i][j] < matrix[i][j - 1];
        } else {
            result = matrix[i][j] > matrix[i][j - 1];
        }
        return result;
    }


    /**
     * Returns true if matrix element e[i][j] greater than e[i+1][j] and e[i-1][j], otherwise returns false.
     * If one of the neighbors does not exist compares with e[i+1][j] or e[i-1][j]
     *
     * @param matrix Matrix that contains element with row index i and column index j
     * @param i      Element row index
     * @param j      Element column index
     * @return true, if matrix element is greater column neighbors, false if not
     */
    private static boolean isGreaterThanColumnNeighbors(int[][] matrix, int i, int j) {
        boolean result;
        if (i == 0) {
            result = matrix[i][j] > matrix[i + 1][j];
        } else if (i < matrix.length - 1) {
            result = matrix[i][j] > matrix[i + 1][j] && matrix[i][j] < matrix[i - 1][j];
        } else {
            result = matrix[i][j] > matrix[i - 1][j];
        }
        return result;
    }

    /**
     * Returns transposed matrix of given matrix
     *
     * @param m Matrix to be transposed
     * @return Transposed matrix
     */
    public static int[][] transposeMatrix(int[][] m) {
        int[][] temp = new int[m[0].length][m.length];
        for (int i = 0; i < m.length; i++)
            for (int j = 0; j < m[0].length; j++)
                temp[j][i] = m[i][j];
        return temp;
    }

    /**
     * Prints specified collection to {@link File}
     *
     * @param output     Target file
     * @param collection Collection to be printed
     * @param <E>        The class of the objects in the collection
     */
    public static <E> void writeCollectionToFile(File output, Collection<E> collection) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(output))) {
            for (E element : collection) {
                writer.write(element.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}