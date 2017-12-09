package com.epam.courses.jf.practice.igulyaev.second;

import com.epam.courses.jf.practice.common.second.ITestableTask11;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Iterator;

public class TestableTask11 implements ITestableTask11 {
    @Override
    public String emulate(ArrayList<String> peoples) {
        int n = peoples.size();
        int current_index = -1;
        for(int i = 0; i < n - 1; ++i) {
            current_index += 1;
            current_index %= peoples.size();
            peoples.remove(current_index);
        }
        return peoples.get(0);
    }

    @Override
    public String emulate(LinkedList<String> peoples) {
        int start = 0;
        int size;
        while((size = peoples.size()) > 1){
            Iterator<String> i = peoples.iterator();
            if(start != 0){
                i.next();
            }
            do {
                i.next();
                i.remove();
            } while(i.hasNext() && i.next() != null && i.hasNext());
            start = (size + start) % 2;
        }
        return peoples.get(0);
    }
}
