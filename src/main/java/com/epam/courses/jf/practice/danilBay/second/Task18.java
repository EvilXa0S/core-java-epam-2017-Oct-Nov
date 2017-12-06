package com.epam.courses.jf.practice.danilBay.second;

import com.epam.courses.jf.practice.common.second.ITestableTask18;

import java.util.ArrayDeque;
import java.util.Deque;

public class Task18 implements ITestableTask18 {
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
      return null;
    }
}
