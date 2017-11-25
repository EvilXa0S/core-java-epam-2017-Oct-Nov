package com.epam.courses.jf.practice.NikDevyatyi.second;

import com.epam.courses.jf.practice.common.second.ITestableTask11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

public class TestableTask11 implements ITestableTask11 {
    @Override
    public String emulate(ArrayList<String> peoples) {
        boolean flag = true;
        while (peoples.size() > 1) {
            Iterator<String> iterator = peoples.iterator();
            while (iterator.hasNext()) {
                iterator.next();
                if (flag) {
                    iterator.remove();
                }
                flag = !flag;
            }
        }
        return peoples.get(0);
    }

    @Override
    public String emulate(LinkedList<String> peoples) {
        boolean flag = true;
        while (peoples.size() > 1) {
            Iterator<String> iterator = peoples.iterator();
            while (iterator.hasNext()) {
                iterator.next();
                if (flag) {
                    iterator.remove();
                }
                flag = !flag;
            }
        }
        return peoples.get(0);
    }

}
