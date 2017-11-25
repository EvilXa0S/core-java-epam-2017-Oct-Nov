package com.epam.courses.jf.practice.nzenkova.second;

import com.epam.courses.jf.practice.common.second.ITestableTask11;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;


public class TestableTask11 implements ITestableTask11 {
    @Override
    public String emulate(ArrayList<String> peoples) {
        int out = 0;
        while (peoples.size() > 1) {
            peoples.remove(out);
            out = (out + 1) % peoples.size();
        }
        return peoples.get(0);
    }

    @Override
    public String emulate(LinkedList<String> peoples) {
        Iterator iter = peoples.iterator();

        int out = 1;
        while (peoples.size() > 1) {
            if (iter.hasNext()) {
                out++;
                iter.next();
                if (out % 2 == 0) {
                    iter.remove();
                    out = 0;
                }

            } else {
                iter = peoples.iterator();
            }
        }
        return peoples.get(0);
    }

}
