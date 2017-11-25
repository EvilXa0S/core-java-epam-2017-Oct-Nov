package com.epam.courses.jf.practice.nzenkova.second;

import com.epam.courses.jf.practice.common.second.ITestableTask11;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * There are N persons in he circle.
 * On each iteration of the cycle a person is excluded (through one, starting from the first), until the only one remains.
 * Dropping out of a circle means removing an item from the source list.
 * Develop two solutions, which are modeling the process.
 * The first solution uses ArrayList, the second -- LinkedList.
 */

public class TestableTask11 implements ITestableTask11 {
    /**
     * Execute an execute of the posed task.
     * @param peoples The list of the names of the members.
     * @return The name of the last remaining.
     */
    @Override
    public String emulate(ArrayList<String> peoples) {
        int out = 0;
        while (peoples.size() > 1) {
            peoples.remove(out);
            out = (out + 1) % peoples.size();
        }
        return peoples.get(0);
    }

    /**
     * Execute an execute of the posed task.
     * @param peoples The list of the names of the members.
     * @return The name of the last remaining.
     */
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
