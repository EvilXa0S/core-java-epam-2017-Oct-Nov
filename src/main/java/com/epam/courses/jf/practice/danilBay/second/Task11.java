package com.epam.courses.jf.practice.danilBay.second;

import com.epam.courses.jf.practice.common.second.ITestableTask11;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Task11 implements ITestableTask11 {
    @Override
    public String emulate(ArrayList<String> peoples) {
        for(int i=0; peoples.size()>1; i=(i+1)%peoples.size()){

            peoples.remove(i);
        }
        return peoples.get(0);
    }

    @Override
    public String emulate(LinkedList<String> peoples) {
        int counter = 0;
        while(peoples.size() != 1) {
            Iterator<String> it = peoples.iterator();
            while (it.hasNext()) {
                String s = it.next();
                if (counter % 2 == 0)
                    it.remove();
                counter++;
            }
        }
        return peoples.get(0);
    }
//    public String emulate(LinkedList<String> peoples) {
//        for(int i=0; peoples.size()>1; i=(i+1)%peoples.size()){
//            peoples.remove(i);
//        }
//        return peoples.get(0);
//    }

}
