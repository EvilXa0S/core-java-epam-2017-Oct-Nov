package com.epam.courses.jf.practice.vakhonin.second;

import com.epam.courses.jf.practice.common.second.ITestableTask18;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Дана матрица из целых чисел.
 * Найти в ней прямоугольную подматрицу, состоящую из максимального количества одинаковых элементов.
 * Использовать стек.
 */

public class Task18 implements ITestableTask18{
    
    /**
     * Прямоугольная матрица целых чисел.
     */

    class RectangularIntegerMatrix implements IRectangularIntegerMatrix{

        private int[][] matrix;

        RectangularIntegerMatrix(int[][] matrix){
            this.matrix = matrix;
        }

        @Override
        public int getWidth() {
            int width = matrix[0].length;
            return width;
        }

        @Override
        public int getHeight() {
            int height = matrix.length;
            return height;
        }

        /**
         * @param indexWidth Индекс по ширине.
         * @param indexHeight Индекс по высоте.
         * @return Значение, располагающееся в указанной ячейке.
         */

        @Override
        public int getValue(int indexWidth, int indexHeight) {
            int value = matrix[indexHeight][indexWidth];
            return value;
        }
    }

    /**
     * Получение map с информацией о текущем элементе в матрице.
     * От заданного в аргументах элемента двигаемся в право и вниз.
     * Находим максимальное количество одинаоквых элементов, параметры подматрицы.
     * @param matrix
     * @param j Индекс по высоте.
     * @param k Индекс по ширине.
     * @return map с информацией
     */

    Map<String, Integer> getInfoAboutMaxSubMatrixFromIndexes(IRectangularIntegerMatrix matrix, int j, int k){
        int value = matrix.getValue(k, j);
        int indexJ = j;
        int indexK = k;
        int subWidth = 1;
        int height = matrix.getHeight();
        int subHeight = 0;
        int width = matrix.getWidth();
        Map<String, Integer> map = new HashMap<>();

        while ((indexJ != (height)) && (matrix.getValue(indexK, indexJ) == value)) {
            subHeight++;
            indexJ++;
        }

        int number = subHeight;
        int maxNumber = number;
        map.put("maxNumber", maxNumber);
        map.put("subWidth", subWidth);
        map.put("subHeight", subHeight);
        map.put("value", value);
        subWidth++;
        indexK++;
        indexJ = j;

        while ((indexK != width) && ( matrix.getValue(indexK, indexJ) == value)) {
            while ((indexJ != (j + subHeight)) && (matrix.getValue(indexK, indexJ) == value)) {
                indexJ++;
            }

            indexJ--;
            subHeight = indexJ - j + 1;
            number = subHeight * subWidth;

            if (number > maxNumber) {
                maxNumber = number;
                map.put("maxNumber", maxNumber);
                map.put("subWidth", subWidth);
                map.put("subHeight", subHeight);
                map.put("value", matrix.getValue(indexK, indexJ));
            }

            subWidth++;
            indexK++;
            indexJ = j;
        }

        return map;
    }

    /**
     * @param matrix Анализируемая матрица.
     * @return Подматрица, состоящая из максимального количества одинаковых элементов.
     */

    @Override
    public IRectangularIntegerMatrix getMaxSubMatrix(IRectangularIntegerMatrix matrix) {

        int width = matrix.getWidth();
        int height = matrix.getHeight();
        Map<String, Integer> map;
        Stack<int[][]> stack = new Stack<>();
        int maxNumber = 0;
        for (int j = 0; j < height; j++) {
            for (int k = 0; k < width; k++) {
                map = getInfoAboutMaxSubMatrixFromIndexes(matrix, j, k);

                if(!stack.isEmpty()){
                    maxNumber = stack.peek().length * stack.peek()[0].length;
                }

                if(map.get("maxNumber") > maxNumber){
                    stack.add(generateMatrix(map.get("subHeight"), map.get("subWidth"), map.get("value")));
                }
            }
        }

        return new RectangularIntegerMatrix(stack.peek());
    }

    /**
     * Генерация матрицы заданых размеров с элементами значения.
     * @param h высота матрица.
     * @param w ширина матрица.
     * @param value значение для элементов.
     * @return Матрица из одинаковых элементов.
     */

    int[][] generateMatrix(int h, int w, int value){
        int[][] resMatrix = new int[h][w];

        for (int j = 0; j < h; j++) {
            for (int k = 0; k < w; k++) {
                resMatrix[j][k] = value;
            }
        }
        return resMatrix;
    }
}
