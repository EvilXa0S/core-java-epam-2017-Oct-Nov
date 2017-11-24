package com.epam.courses.jf.practice.danilBay.second;

import com.epam.courses.jf.practice.common.second.ITestableTask7;

import java.util.ArrayList;
import java.util.List;

public class Task7 implements ITestableTask7 {
    @Override
    public List<Integer> multiplyPolynomials(List<Integer> first, List<Integer> second) {
        List<Integer> result=new ArrayList<>(first.size()*second.size());
        int buf;
        for(int i=0; i< first.size();i++)
            for(int j=0; j< second.size();j++){
                buf = result.get(i*j);
                result.set(i*j,buf + first.get(i)*second.get(j));
            }
        return result;
    }
}
