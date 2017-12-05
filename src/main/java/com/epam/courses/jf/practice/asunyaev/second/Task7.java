package com.epam.courses.jf.practice.asunyaev.second;

import com.epam.courses.jf.practice.common.second.ITestableTask7;

import java.util.ArrayList;
import java.util.List;

public class Task7 implements ITestableTask7 {
    public List<Integer> multiplyPolynomials(List<Integer> first, List<Integer> second) {
        int n = first.size();
        int m = second.size();
        List<Integer> result = new ArrayList<>(n + m);
        for (int i = 0; i < n + m - 1; i++) {
            int s = 0;
            for (int j = 0; j <= i; j++) {
                if (j >= n || i-j >= m) {
                    continue;
                }
                s += first.get(j) * second.get(i-j);
            }
            result.add(i, s);
        }
        return result;
    }
}
