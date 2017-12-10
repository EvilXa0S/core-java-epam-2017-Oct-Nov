package com.epam.courses.jf.practice.hkryzhik.second;

import com.epam.courses.jf.practice.common.second.ITestableTask7;

import java.util.ArrayList;
import java.util.List;

public class Task7 implements ITestableTask7 {
    @Override
    public List<Integer> multiplyPolynomials(List<Integer> first, List<Integer> second) {

        ArrayList<Integer> result = new ArrayList<>();

        result.addAll(second);

        for (int i = 0; i < result.size(); ++i){
            result.set(i, (result.get(i) * first.get(0)));
        }
        for (int i = 1; i < first.size(); ++i) {

            ArrayList<Integer> subResult = new ArrayList<>();

            for (int j = 0; j < i; ++j) {

                subResult.add(0);

            }
            for (int j = 0; j < second.size(); ++j) {

                subResult.add(first.get(i) * second.get(j));

            }
            for (int j = 0; j < result.size(); ++j) {

                result.set(j, (result.get(j) + subResult.get(j)));

            }
            result.add(subResult.get(subResult.size() - 1));
        }

        return result;
    }
}
