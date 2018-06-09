package com.epam.courses.jf.practice.klimenko.second;

import com.epam.courses.jf.practice.common.second.ITestableTask11;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

public class TestableTask11Impl implements ITestableTask11 {
    @Override
    public String emulate(ArrayList<String> peoples) {
        int first = 0;
        while (peoples.size() > 1) {
            int next = (peoples.size() - first) % 2;
            for (int i = first; i < peoples.size(); ++i) {
                peoples.remove(i);
            }
            first = next;
        }
        return peoples.get(0);
    }

    @Override
    public String emulate(LinkedList<String> peoples) {
        boolean doRemove = true;
        while (peoples.size() > 1) {
            for (ListIterator<String> it = peoples.listIterator(); it.hasNext(); ) {
                it.next();
                if (doRemove) {
                    it.remove();
                }
                doRemove = !doRemove;
            }
        }
        return peoples.get(0);
    }
}
