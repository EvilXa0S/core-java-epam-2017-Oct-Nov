package com.epam.courses.jf.practice.NikDevyatyi.second;

import com.epam.courses.jf.practice.common.second.ITestableTask11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

public class TestableTask11 implements ITestableTask11 {
    @Override
    public String emulate(ArrayList<String> peoples) {
        while (peoples.size()>1){
            Iterator<String> iterator = peoples.iterator();
            while (iterator.hasNext()){
                if(iterator.hasNext()){
                    iterator.next();
                    iterator.remove();
                    if(iterator.hasNext()){
                        iterator.next();
                    }
                }
            }
        }
        return Arrays.toString(peoples.toArray());
    }

    @Override
    public String emulate(LinkedList<String> peoples) {
        while (peoples.size()>1){
            Iterator<String> iterator = peoples.iterator();
            while (iterator.hasNext()){
                if(iterator.hasNext()){
                    iterator.next();
                    iterator.remove();
                    if(iterator.hasNext()){
                        iterator.next();
                    }
                }
            }
        }


        return Arrays.toString(peoples.toArray());

    }
}
