package com.epam.courses.jf.practice.danilBay.second;

import com.epam.courses.jf.practice.common.second.ITestableTask18;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.ArrayList;
//public class Task18 implements ITestableTask18 {
//    @Override
//    public IRectangularIntegerMatrix getMaxSubMatrix(IRectangularIntegerMatrix matrix) {
//        Deque<int[]> maxSubMatrix = new ArrayDeque<>();
//        for (int i = 0; i < matrix.getHeight(); i++) {
//            for (int j = 0; j < matrix.getWidth(); j++){
//                Deque<int[]> subMatrix = new ArrayDeque<>();
//                int [] arr ={i,j};
//                subMatrix.push(arr);
//                int elem = matrix.getValue(j,i);
//                for (int l = i; l < matrix.getHeight(); l++) {
//                    for (int k = j; k < matrix.getWidth(); k++) {
//                        int [] tmp = {l,k};
//                        if(elem == matrix.getValue(k,l))
//                            subMatrix.push(tmp);
//                        else
//                            break;
//                    }
//                }
//                if(maxSubMatrix.size() < subMatrix.size()){
//                    maxSubMatrix = subMatrix;
//                }
//            }
//        }
//    }
//}


public class Task18 implements ITestableTask18 {
    /**
     * @param matrix Анализируемая матрица.
     * @return Подматрица, состоящая из максимального количества одинаковых элементов.
     */
    @Override
    public IRectangularIntegerMatrix getMaxSubMatrix(IRectangularIntegerMatrix matrix) {
        Deque<int[]> maxSubMatrix = new ArrayDeque<>();
        for (int i = 0; i < matrix.getHeight(); i++) {
            for (int j = 0; j < matrix.getWidth(); j++){
                Deque<int[]> subMatrix = new ArrayDeque<>();
                int [] arr ={i,j};
                subMatrix.push(arr);
                int elem = matrix.getValue(j,i);
                for (int l = i; l < matrix.getHeight(); l++) {
                    for (int k = j; k < matrix.getWidth(); k++) {
                        int [] tmp = {l,k};
                        if(elem == matrix.getValue(k,l))
                            subMatrix.push(tmp);
                        else
                            break;
                    }
                }
                if(maxSubMatrix.size() < subMatrix.size()){
                    maxSubMatrix = subMatrix;
                }
            }
        }
        boolean result[][] = new boolean[matrix.getHeight()][matrix.getWidth()];
        int elem = matrix.getValue(maxSubMatrix.peek()[1],maxSubMatrix.peek()[0]);
        while (maxSubMatrix.size()>0){
            result[maxSubMatrix.peek()[0]][maxSubMatrix.pop()[1]] = true;
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
        private int[][] M;
        private RectangularIntegerMatrix(int [][] M){
            this.M = M;
        }
        @Override
        public int getWidth() {
            return M[0].length;
        }

        @Override
        public int getHeight() {
            return M.length;
        }

        @Override
        public int getValue(int indexWidth, int indexHeight) {
            return M[indexHeight][indexWidth];
        }
    }
}
