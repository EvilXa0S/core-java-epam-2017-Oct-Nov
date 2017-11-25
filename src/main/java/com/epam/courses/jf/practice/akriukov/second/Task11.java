package com.epam.courses.jf.practice.akriukov.second;

import com.epam.courses.jf.practice.common.second.ITestableTask11;

import java.util.*;

public class Task11 implements ITestableTask11 {

    public static void main(String [] args) {

        ArrayList<String> list = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5"));
        Task11 task = new Task11();
        task.emulate(list);
    }

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
