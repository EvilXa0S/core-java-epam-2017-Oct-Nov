package com.epam.courses.jf.practice.bborzdov;

import com.epam.courses.jf.practice.common.second.ITestableTask11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by bogdan on 17.11.17.
 */
public class Task11 implements ITestableTask11 {
    @Override
    public String emulate(ArrayList<String> peoples) {
        int step=0;
        int peoplesSize = peoples.size()-1;
        peoples.set(0,"");
        for (int i = 0; peoplesSize > 1 ; i++) {
            if(!peoples.get(i % peoples.size()).equals("")){
                step++;
                if(step==2){
                    peoples.set(i % peoples.size(), "");
                    peoplesSize--;
                    step=0;
                }
            }
        }
        peoples.removeAll(Collections.singleton(""));
        return peoples.get(0);
    }

    @Override
    public String emulate(LinkedList<String> peoples) {
        peoples.removeFirst();
        Iterator<String> it1 = peoples.listIterator();
        int step = 1;
        it1.next();
        while (peoples.size() > 1){
            if(!it1.hasNext()){
                if(peoples.size() == 1){
                    return peoples.getFirst();
                }
                it1 = peoples.listIterator();
            }else {
                it1.next();
                step++;
                if(step == 2) {
                    it1.remove();
                    step = 0;
                }
            }
        }
        return peoples.getFirst();
    }
}
