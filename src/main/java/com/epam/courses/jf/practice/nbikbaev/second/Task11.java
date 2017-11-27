package com.epam.courses.jf.practice.nbikbaev.second;

import com.epam.courses.jf.practice.common.second.ITestableTask11;

import java.util.*;

public class Task11 implements ITestableTask11 {
    @Override
    public String emulate(ArrayList<String> peoples) {
        while (peoples.size() != 1) {
            if (peoples.size() % 2 == 0) {
                removeEachSecondElement(peoples);
            } else {
                removeEachSecondElement(peoples);
                Collections.rotate(peoples, -1);
            }
        }
        return peoples.get(0);
    }

    /**
     * Removes every second element from specified list starting from zero index
     *
     * @param peoples Target list
     */
    private void removeEachSecondElement(List<String> peoples) {
        for (int i = 0; i < peoples.size(); ++i) {
            peoples.remove(i);
        }
    }

    @Override
    public String emulate(LinkedList<String> peoples) {
        int k = 0;
        while (peoples.size() > 1) {
            ListIterator iterator = peoples.listIterator();
            while (iterator.hasNext()) {
                iterator.next();
                if (k % 2 == 0) {
                    iterator.remove();
                }
                k++;
            }
        }
        return peoples.get(0);
    }
}
