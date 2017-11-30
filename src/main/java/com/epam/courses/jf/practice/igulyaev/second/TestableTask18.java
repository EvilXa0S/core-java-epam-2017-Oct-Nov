package com.epam.courses.jf.practice.igulyaev.second;

import com.epam.courses.jf.practice.common.second.ITestableTask18;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.stream.Stream;

public class TestableTask18 implements ITestableTask18 {
    /**
     * @param matrix Анализируемая матрица.
     * @return Подматрица, состоящая из максимального количества одинаковых элементов.
     */
    @Override
    public IRectangularIntegerMatrix getMaxSubMatrix(IRectangularIntegerMatrix matrix) {
        int[][] intMatrix = new int[0][0];
        for(int col = 0; col < matrix.getWidth(); ++col){
            for(int row = 0; row < matrix.getHeight(); ++row){
                int[][] newMatrix = getMaxSubMatrix(matrix, row, col);
                if(matrixSize(intMatrix) < matrixSize(newMatrix)){
                    intMatrix = newMatrix;
                }
            }
        }
        return new RectangularIntegerMatrix(intMatrix);
    }

    private int matrixSize(int [][] matrix){
        if(matrix.length == 0 && matrix[0].length == 0){
            return 0;
        }
        return matrix.length * matrix[0].length;
    }
    private int[][] getMaxSubMatrix(IRectangularIntegerMatrix matrix, int row, int col){
        Deque<Integer> rowStack = new ArrayDeque<>();
        Deque<Integer> colStack = new ArrayDeque<>();
        int newCol = col;
        int newRow = row;
        int value = matrix.getValue(col, row);
        rowStack.push(row);
        colStack.push(col);
        while (++newRow < matrix.getHeight() && matrix.getValue(col, newRow) == value){
            rowStack.push(newRow);
        }
        while (++newCol < matrix.getWidth() && matrix.getValue(newCol, row) == value){
            colStack.push(newCol);
        }
        if(countSameValues(matrix, value, row, rowStack.peek(), col, colStack.peek()) == rowStack.size() * colStack.size()){
            return getMatrix(colStack.size(), rowStack.size(), value);
        }
        //brute force
        int resRow = 1;
        int resCol = 1;
        int maxSize = 1;
        for(int i = rowStack.peek(); i > row; --i){
            for (int j = colStack.peek(); j > col; --j) {
                int newSize = countSameValues(matrix, value, row, i, col, j);
                newCol = j - col + 1;
                newRow = i - row + 1;
                if(newSize == (i - row + 1)*(j - col + 1) && newSize > maxSize){
                    maxSize = newSize;
                    resRow = newRow;
                    resCol = newCol;
                }
            }
        }
        return getMatrix(resRow, resCol, value);
    }

    private int countSameValues(IRectangularIntegerMatrix matrix, int value, int rowStart, int RowEnd, int colStart, int colEnd){
        int count = 0;
        for(int i = colStart; i <= colEnd; ++i){
            for(int j = rowStart; j <= RowEnd; ++j){
                if(matrix.getValue(i, j) == value){
                    count++;
                }
            }
        }
        return count;
    }
    private int[][] getMatrix(int width, int height, int value){
        int[][] matrix = new int[height][width];
        Stream.of(matrix).forEach(row -> Arrays.fill(row, value));
        return matrix;
    }

    private static class RectangularIntegerMatrix implements IRectangularIntegerMatrix {
        int[][] matrix;

        public RectangularIntegerMatrix(int[][] matrix) {
            this.matrix = matrix;
        }

        public int getWidth(){
            return matrix[0].length;
        }

        public int getHeight(){
            return matrix.length;
        }

        public int getValue(int indexWidth, int indexHeight){
            return matrix[indexHeight][indexWidth];
        }
    }
}
