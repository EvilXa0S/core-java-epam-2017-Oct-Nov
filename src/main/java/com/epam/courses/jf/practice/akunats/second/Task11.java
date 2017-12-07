package com.epam.courses.jf.practice.akunats.second;

import com.epam.courses.jf.practice.common.second.ITestableTask11;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class Task11 implements ITestableTask11 {
    public String emulate(ArrayList<String> peoples) {
        if (peoples == null || peoples.isEmpty()) {
            throw new IllegalArgumentException("Linked list not provided!");
        }
        if (peoples.size() == 1) {
            return peoples.get(0);
        }
        int i = 0;
        while (true) {
            peoples.remove(i);
            i++;
            if (i >= peoples.size()) {
                i %= peoples.size();
            }
            if (peoples.size() == 1) {
                return peoples.get(0);
            }
        }
    }

    public String emulate(LinkedList<String> peoples) {
        int i = 0;
        while (peoples.size() > 1) {
            Iterator<String> iterator = peoples.iterator();
            while (iterator.hasNext()) {
                iterator.next();
                if (i == 0) iterator.remove();
                if (iterator.hasNext()) {
                    iterator.next();
                    if (i == 1) iterator.remove();
                } else i = (i == 0 ? 1 : 0);
            }
        }
        return peoples.get(0);
    }
}
