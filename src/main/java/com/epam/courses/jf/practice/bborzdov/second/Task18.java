package com.epam.courses.jf.practice.bborzdov.second;

import com.epam.courses.jf.practice.common.second.ITestableTask18;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
/**
 * Created by bogdan on 24.11.17.
 */
public class Task18 implements ITestableTask18 {
    @Override
    public IRectangularIntegerMatrix getMaxSubMatrix(IRectangularIntegerMatrix matrix) {
        Stack<int[]> cordMaxSubMatrix = new Stack<>();
        for (int i = 0; i < matrix.getHeight(); i++) {
            for (int j = 0; j < matrix.getWidth(); j++){
                Stack<int[]> cordOfSubMatrix = new Stack<>();
                int [] arr ={i,j};
                cordOfSubMatrix.push(arr);
                int elem = matrix.getValue(j,i);
                for (int l = i; l < matrix.getHeight(); l++) {
                    for (int k = j; k < matrix.getWidth(); k++) {
                        int [] tmp = {l,k};
                        if(Arrays.equals(tmp,arr)){
                            continue;
                        }
                        if(elem == matrix.getValue(k,l)){
                            cordOfSubMatrix.push(tmp);
                        }else{
                            break;
                        }
                    }
                }
                if(cordMaxSubMatrix.size() < cordOfSubMatrix.size()){
                    cordMaxSubMatrix = cordOfSubMatrix;
                }
            }
        }
        boolean result[][] = new boolean[matrix.getHeight()][matrix.getWidth()];
        int elem = matrix.getValue(cordMaxSubMatrix.peek()[1],cordMaxSubMatrix.peek()[0]);
        while (cordMaxSubMatrix.size()>0){
            result[cordMaxSubMatrix.peek()[0]][cordMaxSubMatrix.pop()[1]] = true;
        }
        ArrayList<ArrayList<Integer>> mass = new ArrayList<>();
        for (int i = 0; i < result.length; i++) {
            ArrayList<Integer> arrayList = new ArrayList<>();
            boolean flag = false;
            for (int j = 0; j < result[i].length; j++) {
                if(result[i][j]){
                    arrayList.add(elem);
                    flag = true;
                }
            }
            if(flag) {
                mass.add(arrayList);
            }
        }
        int subMatrix [][] = new int[mass.size()][mass.get(0).size()];
        for (int i = 0; i < subMatrix.length; i++) {
            for (int j = 0; j < subMatrix[i].length; j++) {
                subMatrix[i][j] = mass.get(i).get(j);
            }
        }
        return new RectangularIntegerMatrix(subMatrix);
    }
    private class RectangularIntegerMatrix implements IRectangularIntegerMatrix{
        private int[][] matrix;
        private RectangularIntegerMatrix(int [][] matrix){
            this.matrix = matrix;
        }
        @Override
        public int getWidth() {
            return matrix[0].length;
        }

        @Override
        public int getHeight() {
            return matrix.length;
        }

        @Override
        public int getValue(int indexWidth, int indexHeight) {
            return matrix[indexHeight][indexWidth];
        }
    }
}
