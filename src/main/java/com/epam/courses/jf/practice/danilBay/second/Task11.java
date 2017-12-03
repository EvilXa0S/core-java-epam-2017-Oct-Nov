package com.epam.courses.jf.practice.danilBay.second;

import com.epam.courses.jf.practice.common.second.ITestableTask11;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class Task11 implements ITestableTask11 {
    @Override
    public String emulate(ArrayList<String> peoples) {
        for(int i=0; peoples.size()>1; i=(i++)%peoples.size()){

            peoples.remove(i);
        }
        return peoples.get(0);
    }

    @Override
    public String emulate(LinkedList<String> peoples) {
        Iterator iter = peoples.iterator();

        int out = 1;
        while (peoples.size() > 1) {
            if (iter.hasNext()) {
                ++out;
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
    public static void main(String[] args) {
        ArrayList <Integer>d =new ArrayList(15);
        d.add(2);
        d.add(6);
        d.add(5);
        System.out.println(d.size());
        d.remove(1);
        System.out.println(d.size());
    }
}
