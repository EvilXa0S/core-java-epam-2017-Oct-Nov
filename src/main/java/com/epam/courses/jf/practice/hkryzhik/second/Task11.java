package com.epam.courses.jf.practice.hkryzhik.second;

import com.epam.courses.jf.practice.common.second.ITestableTask11;

import java.util.ArrayList;
import java.util.LinkedList;

public class Task11 implements ITestableTask11 {
    @Override
    public String emulate(ArrayList<String> peoples) {

        int index = 0;

        while (peoples.size() != 1) {

            peoples.remove(index);

            index++;

            index = index%peoples.size();

        }
        return peoples.get(0);
    }

    @Override
    public String emulate(LinkedList<String> peoples) {

        int index = 0;

        while(peoples.size() != 1){

            peoples.remove(index);

            index++;

            index = index%peoples.size();
        }
        return peoples.get(0);

    }
}
