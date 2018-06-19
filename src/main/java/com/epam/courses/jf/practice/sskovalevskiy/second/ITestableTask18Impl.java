package com.epam.courses.jf.practice.sskovalevskiy.second;

import com.epam.courses.jf.practice.common.second.ITestableTask18;

import java.util.LinkedList;

/**
 * Created by asus on 01.11.2017.
 *
 * Дана матрица из целых чисел.
 * Найти в ней прямоугольную подматрицу, состоящую из максимального количества одинаковых элементов.
 * Использовать стек.
 */
public class ITestableTask18Impl implements ITestableTask18 {

    private LinkedList<IRectangularIntegerMatrix> list = new LinkedList<>();
    private IRectangularIntegerMatrix matrix;

    /**
     * @param matrix Анализируемая матрица.
     * @return Подматрица, состоящая из максимального количества одинаковых элементов.
     */
    @Override
    public IRectangularIntegerMatrix getMaxSubMatrix(IRectangularIntegerMatrix matrix) {
        this.matrix = matrix;
        if (getElementsCount(matrix) == 1) return matrix;
        for (int i = 0; i < matrix.getHeight(); ++i) {
            for (int j = 0; j < matrix.getWidth(); ++j) {
                generateSubmatrixes(j, i);
            }
        }
        return list.pop();
    }

    // generated all submatrix
    private void generateSubmatrixes(int startWidth, int startHeight) {
        int width;
        int height = matrix.getHeight() - startHeight;
        int element = matrix.getValue(startWidth, startHeight);
        RectangularIntegerMatrix integerMatrix = null;

        while (height >= 1) {
            width = matrix.getWidth() - startWidth;
            while (width >= 1) {
                integerMatrix = new ITestableTask18Impl.RectangularIntegerMatrix(width, height);
                for (int i = 0; i < height; ++i) {
                    for (int j = 0; j < width; ++j) {
                        integerMatrix.setValue(j, i, matrix.getValue(startWidth + j, startHeight + i));
                    }
                }
                if (integerMatrix.isSingleElement(element)) {
                    addMatrixInList(integerMatrix);
                }
                width--;
            }
            height--;
        }
        if (integerMatrix != null && integerMatrix.isSingleElement(element)) {
            addMatrixInList(integerMatrix);
        }
    }

    private void addMatrixInList(IRectangularIntegerMatrix matrix) {
        if (list.isEmpty()) {
            list.push(matrix);
        }
        if (getElementsCount(matrix) > getElementsCount(list.peek())) {
            list.push(matrix);
        }
    }

    private int getElementsCount(IRectangularIntegerMatrix matrix) {
        return (matrix.getWidth() == 0) ?
                matrix.getHeight() : ((matrix.getHeight() == 0) ?
                matrix.getWidth() : matrix.getWidth() * matrix.getHeight());
    }

    /**
     * Прямоугольная матрица целых чисел.
     */
    private class RectangularIntegerMatrix implements IRectangularIntegerMatrix {
        private int[][] matrix;
        private int width;
        private int height;

        RectangularIntegerMatrix(int width, int height) {
            this.width = width;
            this.height = height;
            this.matrix = new int[height][width];
        }

        /** @return Ширина матрицы. */
        @Override
        public int getWidth() {
            return width;
        }

        /** @return Высота матрицы. */
        @Override
        public int getHeight() {
            return height;
        }

        /**
         * @param indexWidth Индекс по ширине.
         * @param indexHeight Индекс по высоте.
         * @return Значение, располагающееся в указанной ячейке.
         */
        @Override
        public int getValue(int indexWidth, int indexHeight) {
            return matrix[indexHeight][indexWidth];
        }

        void setValue(int indexWidth, int indexHeight, int value) {
            matrix[indexHeight][indexWidth] = value;
        }

        boolean isSingleElement(int element) {
            for (int i = 0; i < getHeight(); i++) {
                for (int j = 0; j < getWidth(); j++) {
                    if (getValue(j, i) != element)
                        return false;
                }
            }
            return true;
        }
    }
}
