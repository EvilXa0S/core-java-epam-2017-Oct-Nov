package com.epam.courses.jf.practice.NikDevyatyi.second;

import com.epam.courses.jf.practice.common.second.ITestableTask7;

import java.util.ArrayList;
import java.util.List;


public class TestableTask7 implements ITestableTask7 {
    @Override
    public List<Integer> multiplyPolynomials(List<Integer> first, List<Integer> second) {
        List<Integer> res = new ArrayList<>();

        int[] result = new int[first.size()+second.size()-1];
        for (int i=0;i<first.size();i++){
            for (int j=0;j<second.size();j++){
                result[i+j]+= second.get(j)*first.get(i);
            }
        }
        for (int item:result) {
            res.add(item);
        }
        return res;
    }
}
