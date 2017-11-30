package com.epam.courses.jf.practice.desided.second;

import com.epam.courses.jf.practice.common.second.ITestableTask12;

import java.util.List;

public class Task12 implements ITestableTask12 {
    @Override
    public List<Integer> transform(List<Integer> integers, int value) {
        for (int i = 0; i < integers.size(); i++){

            if (integers.get(i) > value){
                int temp = 0;
                for (int x = integers.size() - 1; x > i; x--){
                    if (integers.get(x) < value){
                        temp = integers.get(i);
                        integers.set(i, integers.get(x));
                        integers.set(x, temp);
                        break;
                    }
                }
            }
        }
        return integers;
    }
}
