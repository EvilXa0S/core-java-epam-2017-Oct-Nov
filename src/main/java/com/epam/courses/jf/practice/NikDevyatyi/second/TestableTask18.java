package com.epam.courses.jf.practice.NikDevyatyi.second;

import com.epam.courses.jf.practice.common.second.ITestableTask18;

import java.util.*;

public class TestableTask18 implements ITestableTask18 {
    @Override
    public IRectangularIntegerMatrix getMaxSubMatrix(IRectangularIntegerMatrix matrix) {
        Set<Integer> res = new HashSet<>();
        int ansMax = 0;
        int[] rowsCols = new int[4];
        int[] resRowsCols = new int[4];
        int ans = 0;
        int[] d = new int [matrix.getHeight()];
        int[] d1 = new int [matrix.getHeight()];
        int[] d2 = new int [matrix.getHeight()];
        IRectangularIntegerMatrix result;
        Deque<Integer> stack  =  new ArrayDeque<>();
        for(int i = 0; i < matrix.getHeight(); i++){
            for(int j = 0; j < matrix.getWidth(); j++){
                res.add(matrix.getValue(i,j));
            }
        }
        Arrays.fill(d,-1);
        for(Integer item:res){
            for (int i = 0; i < matrix.getHeight(); i++) {
                for (int j = 0; j < matrix.getWidth(); j++) {
                    if (matrix.getValue(i,j) != item.intValue()) {
                        d[j] = i;
                    }
                }
                stack.clear();
                for (int j = 0; j < matrix.getHeight(); j++) {
                    while (!stack.isEmpty() && d[stack.peek()] <= d[j]) {
                        stack.pop();
                    }
                    d1[j] = stack.isEmpty() ? -1 : stack.peek();
                    stack.push(j);
                }
                stack.clear();
                for (int j = matrix.getHeight() - 1; j >= 0; j--) {
                    while (!stack.isEmpty() && d[stack.peek()] <= d[j])  stack.pop();
                    d2[j] = stack.isEmpty() ? matrix.getHeight() : stack.peek();
                    stack.push(j);
                }
                //int max =  Math.max (ans, (i - d[0]) * (d2[0] - d1[0] - 1));
                for (int j=0; j<matrix.getWidth(); ++j){
                    int max = ans;
                    ans = Math.max (ans, (i - d[j]) * (d2[j] - d1[j] - 1));
                    if(ans-max!=0){

                        rowsCols[0] =d[j]+1;
                        rowsCols[1] = i;
                        rowsCols[2] =d1[j]+1;
                        rowsCols[3] =d2[j]-1;
                        max = ans;
                    }
                 }
            }
            if (ans >ansMax){
                resRowsCols = Arrays.copyOf(rowsCols,rowsCols.length);
                ansMax = ans;
            }
        }
        int [][]resArr = new int[resRowsCols[1]-resRowsCols[0]+1][resRowsCols[3]-resRowsCols[2]+1];
        for(int i = 0;i<resArr.length;i++){
            for (int j = 0;j<resArr[0].length;j++){
                resArr[i][j] = matrix.getValue(resRowsCols[0],resRowsCols[2]);
            }
        }
        return new RectangularIntegerMatrix(resArr);
    }

}
