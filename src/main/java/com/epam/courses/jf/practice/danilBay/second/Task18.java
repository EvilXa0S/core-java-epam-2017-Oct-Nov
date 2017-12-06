package com.epam.courses.jf.practice.danilBay.second;

import com.epam.courses.jf.practice.common.second.ITestableTask18;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class Task18 implements ITestableTask18 {

    private int getSubMatrixSize(int[]leftIndex, int[]rightIndex){
       return  (rightIndex[1]-leftIndex[1]+1) * (rightIndex[0]-leftIndex[0]+1);
    }
    @Override
    public IRectangularIntegerMatrix getMaxSubMatrix(IRectangularIntegerMatrix matrix) {

        RectangularMatrix maxSubMatrix=new RectangularMatrix(new int[][]{{matrix.getValue(0,0)}});

        for (int i = 0; i < matrix.getHeight(); i++) {
            for (int j = 0; j < matrix.getWidth(); j++){

                int elem = matrix.getValue(j,i);
                int sizeHorizontal=1;
                int leftIndex[] = {i, j};
                int rightIndex[] = {i, j};
                for (int l = i; l < matrix.getHeight(); l++) {
                    for (int k = j; k < matrix.getWidth(); k++) {
                        if (elem == matrix.getValue(k, l)) {
                            rightIndex[1]++;
                        }
                        else break;
                    }
                    int size = getSubMatrixSize(leftIndex, rightIndex);
                    if (size >= sizeHorizontal) {
                        sizeHorizontal = size;
                        rightIndex[0]++;
                    }
                    else break;
                }

                int sizeVertical=1;
                int topIndex[] = {i, j};
                int bottomIndex[] = {i, j};
                for (int k = j; k < matrix.getWidth(); k++) {
                    for (int l = i; l < matrix.getHeight(); l++) {
                        if(elem == matrix.getValue(k,l)){
                            bottomIndex[0]++;
                        }
                        else break;
                    }
                    int size = getSubMatrixSize(topIndex, bottomIndex);
                    if (size >= sizeVertical) {
                        sizeVertical = size;
                        bottomIndex[1]++;
                    }
                    else break;
                }


                int biggestSub[][];
                if(sizeHorizontal>=sizeVertical ){
                    biggestSub = new int[rightIndex[0]+1][rightIndex[1]+1];
                    for (int l = 0; l <= rightIndex[0]; l++)
                        for (int k = 0; k <= rightIndex[1]; k++)
                            biggestSub[l][k]=elem;

                }
                else {
                    biggestSub = new int[bottomIndex[0]+1][bottomIndex[1]+1];
                    for (int k = 0; k <= bottomIndex[1]; k++) {
                        for (int l = 0; l <=bottomIndex[0]; l++) {
                            biggestSub[l][k]=elem;
                        }

                    }

                }
                if(maxSubMatrix.size()<sizeHorizontal || maxSubMatrix.size()<sizeVertical){
                    maxSubMatrix=new RectangularMatrix(biggestSub);
                }
            }
        }
      return maxSubMatrix;
    }
    class RectangularMatrix implements IRectangularIntegerMatrix{
        int width;
        int height;
        int[][]ar;

        RectangularMatrix(int[][] ar){
            width=ar[0].length;
            height=ar.length;
            this.ar=ar;
        }
        public int size(){
            return getHeight()*getWidth();
        }
        @Override
        public int getWidth() {
            return width;
        }

        @Override
        public int getHeight() {
            return height;
        }

        @Override
        public int getValue(int indexWidth, int indexHeight) {
            return ar[indexHeight][indexWidth];
        }
    }
}
