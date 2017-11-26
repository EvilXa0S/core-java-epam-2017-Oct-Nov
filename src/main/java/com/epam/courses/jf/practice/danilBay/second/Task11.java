package com.epam.courses.jf.practice.danilBay.second;

import com.epam.courses.jf.practice.common.second.ITestableTask11;

import java.util.ArrayList;
import java.util.LinkedList;

public class Task11 implements ITestableTask11 {
    @Override
    public String emulate(ArrayList<String> peoples) {
        for(int i=0; peoples.size()>1; i+=2){
            peoples.remove(i%peoples.size());
        }
        return peoples.get(0);
    }

    @Override
    public String emulate(LinkedList<String> peoples) {
        for(int i=0; peoples.size()>1; i+=2){
            peoples.remove(i%peoples.size());
        }
        return peoples.get(0);
    }
}
