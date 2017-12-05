package com.epam.courses.jf.practice.asunyaev.second;

import com.epam.courses.jf.practice.common.second.ITestableTask11;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class Task11 implements ITestableTask11 {

    public String emulate(ArrayList<String> peoples) {
        int i = 0;
        while(peoples.size() > 1) {
            peoples.remove(i);
            i++;
            if (i == peoples.size()) {
                i = 0;
            } else if (i > peoples.size()) {
                i = 1;
            }
        }
        return peoples.get(0);
    }

    public String emulate(LinkedList<String> peoples) {
        boolean removeModeON = true;
        while (peoples.size() > 1) {
            Iterator<String> iterator = peoples.iterator();
            while (iterator.hasNext()) {
                iterator.next();
                if (removeModeON) {
                    iterator.remove();
                }
                removeModeON = !removeModeON;
            }
        }
        return peoples.get(0);
    }
}
