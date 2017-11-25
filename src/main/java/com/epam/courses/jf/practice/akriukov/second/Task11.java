package com.epam.courses.jf.practice.akriukov.second;

import com.epam.courses.jf.practice.common.second.ITestableTask11;

import java.util.*;

public class Task11 implements ITestableTask11 {

    @Override
    public String emulate(ArrayList<String> peoples) {
        return removeOneOverOne(peoples);
    }

    @Override
    public String emulate(LinkedList<String> peoples) {
        return removeOneOverOne(peoples);
    }

    public static String removeOneOverOne(List<String> list) {

        while (list.size() > 1) {
            Iterator iterator = list.iterator();
            while (iterator.hasNext()) {
                iterator.next();
                if (iterator.hasNext()) {
                    iterator.next();
                    iterator.remove();
                }
            }
        }

        return list.get(0);
    }
}
