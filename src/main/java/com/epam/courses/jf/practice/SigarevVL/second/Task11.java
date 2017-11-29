package com.epam.courses.jf.practice.SigarevVL.second;

import com.epam.courses.jf.practice.common.second.ITestableTask11;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * There are N people in the circle.
 * At each iteration of the cycle, a person drops out (through one,
 * starting from the first person), until the only one remains.
 * Develop two solutions modeling the process.
 * The first solution is to use the ArrayList class,
 * and the second solution is to use the LinkedList.
 */
public class Task11 implements ITestableTask11 {

    /**
     * Performs an emulation of the task.
     *
     * @param peoples List with the names of the participants.
     * @return The name of the last person.
     */
    @Override
    public String emulate(ArrayList<String> peoples) {
        int i = 1;
        while (peoples.size() != 1) {
            Iterator<String> iterator = peoples.iterator();
            while (iterator.hasNext()) {
                i++;
                iterator.next();
                if (i == 2) {
                    iterator.remove();
                    i = 0;
                }
            }
        }
        return peoples.get(0);
    }

    /**
     * Performs an emulation of the task.
     *
     * @param peoples List with the names of the participants.
     * @return The name of the last person.
     */
    @Override
    public String emulate(LinkedList<String> peoples) {
        int i = 1;
        while (peoples.size() != 1) {
            Iterator<String> iterator = peoples.iterator();
            while (iterator.hasNext()) {
                i++;
                iterator.next();
                if (i == 2) {
                    iterator.remove();
                    i = 0;
                }
            }
        }
        return peoples.get(0);
    }
}
