package com.epam.courses.jf.practice.vakhonin.second;

import com.epam.courses.jf.practice.common.second.ITestableTask18;

import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class Task18 implements ITestableTask18{

    //TODO: ИСПРАВИТЬ СЛОВО ВЫСОТА НА АНГЛИЙСКОМ!!!

    class RectangularIntegerMatrix implements IRectangularIntegerMatrix{

        private int[][] matrix;

        RectangularIntegerMatrix(int height, int width){
            matrix = new int[height][width];
        }

        @Override
        public int getWidth() {
            int width = matrix[0].length;
            return width;
        }

        @Override
        public int getHeight() {
            int hieght = matrix.length;
            return hieght;
        }

        //автор интерфейса явно линейную алгебру не изучал... СТРОКА-СТОЛБЕЦ, а не наоборот!!!!
        @Override
        public int getValue(int indexWidth, int indexHeight) {
            int value = matrix[indexHeight][indexWidth];
            return value;
        }

        RectangularIntegerMatrix(int[][] matrix){
            this.matrix = matrix;
        }

    }

    Map<String, Integer> getInfoAboutMaxSubMatrixFromIndexes(IRectangularIntegerMatrix matrix, int j, int k){
        int value = matrix.getValue(k, j);
        int colValue = value;
        int rowValue = value;
        int indexJ = j;
        int indexK = k;
        int subWidth = 1;
        int hieght = matrix.getHeight();
        int subHieght = 0;
        int width = matrix.getWidth();
        Map<String, Integer> map = new HashMap<>();
        int numberOfCol = 1;
        int number = 1;


        while ((rowValue == value) && (indexJ != (hieght - 1))) {
            subHieght++;
            indexJ++;
            rowValue = matrix.getValue(indexK, indexJ);
        }

        number = subHieght;
        int maxNumber = number;
        map.put("maxNumber", maxNumber);
        map.put("subWidth", subWidth);
        map.put("subHieght", subHieght);
        map.put("value", matrix.getValue(indexK, indexJ));


        subWidth++;
        indexK++;
        indexJ = j;





        while (( matrix.getValue(indexK, indexJ) == value) && (indexK != (width - 1))) {
            while ((matrix.getValue(indexK, indexJ) == value) && (indexJ != (j + subHieght - 1))) {
                indexJ++;
            }
            subHieght = indexJ - j + 1;
            number = subHieght * subWidth;
            if(number > maxNumber){
                map.put("maxNumber", maxNumber);
                map.put("subWidth", subWidth);
                map.put("subHieght", subHieght);
                map.put("value", matrix.getValue(indexK, indexJ));
            }

            subWidth++;
            indexK++;
            indexJ = j;
        }
        return map;
    }




    @Override
    public IRectangularIntegerMatrix getMaxSubMatrix(IRectangularIntegerMatrix matrix) {

        int wigth = matrix.getWidth();
        int height = matrix.getHeight();
        Map<String, Integer> map = new HashMap<>();
        Map<String, Integer> result = new HashMap<>();
        int maxNumber = 1;
        for (int j = 0; j < height; j++) {
            for (int k = 0; k < wigth; k++) {
                map = getInfoAboutMaxSubMatrixFromIndexes(matrix, j, k);
                if(map.get("maxNumber") >= maxNumber){
                    result = map;
                }
            }
        }

        int[][] resMatrix = new int[result.get("subHieght")][result.get("subWidth")];

        for (int j = 0; j < result.get("subHieght"); j++) {
            for (int k = 0; k < result.get("subWidth"); k++) {
                resMatrix[j][k] = result.get("value");
            }
        }



        return new RectangularIntegerMatrix(resMatrix);
    }
}
