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
                int rightIndex[] = {i, j-1};
                int rightIndexFinal[]=  rightIndex;

                for (int l = i; l < matrix.getHeight(); l++) {
                    for (int k = j; k < matrix.getWidth(); k++) {
                        if (elem == matrix.getValue(k, l)) {
                            rightIndex[1]++;
                        }
                        else break;
                    }
                    int size = getSubMatrixSize(leftIndex, rightIndex);
                    if (size >= sizeHorizontal ) {
                        sizeHorizontal = size;
                        rightIndexFinal=new int[]{rightIndex[0],rightIndex[1]};
                        rightIndex[0]++;
                        if((l+1)==matrix.getHeight() || rightIndex[0]==matrix.getWidth()|| matrix.getValue( j,l+1)!=elem){
                            break;
                        }
                        else {
                            rightIndex[1] = j-1;
                        }
                    }
                    else break;
                }

                int sizeVertical=1;
                int topIndex[] = {i, j};
                int bottomIndex[] = {i-1, j};
                int bottomIndexFinal[]=  bottomIndex;
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
                        bottomIndexFinal=new int[]{bottomIndex[0],bottomIndex[1]};;
                        bottomIndex[1]++;
                        if((k+1) == matrix.getWidth() || bottomIndex[1]==matrix.getHeight()|| matrix.getValue(k+1,i)!=elem){
                            break;
                        }
                        else {
                            bottomIndex[0] = i - 1;
                        }

                    }
                    else break;
                }


                int biggestSub[][];
                if(sizeHorizontal>=sizeVertical ){
                    int n1=rightIndexFinal[0]-leftIndex[0]+1;
                    int n2=rightIndexFinal[1]-leftIndex[1]+1;
                    biggestSub = new int[n1][n2];
                    for (int l = 0; l <n1; l++)
                        for (int k = 0; k <n2; k++)
                            biggestSub[l][k]=elem;

                }
                else {
                    int n1=bottomIndexFinal[0]-topIndex[0]+1;
                    int n2=bottomIndexFinal[1]-topIndex[1]+1;
                    biggestSub = new int[n1][n2];
                    for (int k = 0; k < n2; k++) {
                        for (int l = 0; l <n1; l++) {
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

    public static void main(String[] args) {
        Task18 d=new Task18();


        int m[][]=new int[][]{
                {1,2,3,4,5,6,7,8,9,10},
                {5,5,5,6,7,9,9,9,9,0},
                {5,5,4,1,2,9,9,9,9,0},
                {5,5,1,1,2,9,7,6,5,3},
                {5,2,2,3,4,4,3,2,1,2}
        };
        IRectangularIntegerMatrix x =d.new RectangularMatrix(m);
        IRectangularIntegerMatrix result=d.getMaxSubMatrix(x);
        for(int i=0;i<result.getHeight();i++) {
            for (int j = 0; j < result.getWidth(); j++) {
                System.out.printf(result.getValue(j, i) + " ");
            }
            System.out.println();
        }
    }
}
