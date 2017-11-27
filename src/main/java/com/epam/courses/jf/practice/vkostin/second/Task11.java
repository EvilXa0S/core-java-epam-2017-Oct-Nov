package com.epam.courses.jf.practice.vkostin.second;

import com.epam.courses.jf.practice.common.second.ITestableTask11;

import java.util.*;

public class Task11 implements ITestableTask11 {

    @Override
    public String emulate(ArrayList<String> peoples) {
        int counter = 0;

        while (peoples.size() > 1) {
            ListIterator iter = peoples.listIterator();
            while (iter.hasNext()) {
                iter.next();
                if (counter % 2 == 0) {
                    iter.remove();
                }
                ++counter;
            }
        }
        return peoples.get(0);
    }

    @Override
    public String emulate(LinkedList<String> peoples) {
        int counter = 0;

        while (peoples.size() > 1) {
            ListIterator iter = peoples.listIterator();
            while (iter.hasNext()) {
                iter.next();
                if (counter % 2 == 0) {
                    iter.remove();
                }
                ++counter;
            }
        }
        return peoples.get(0);
    }
}
