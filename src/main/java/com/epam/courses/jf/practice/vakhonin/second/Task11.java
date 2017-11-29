package com.epam.courses.jf.practice.vakhonin.second;

import com.epam.courses.jf.practice.common.second.ITestableTask11;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class Task11 implements ITestableTask11{

    @Override
    public String emulate(ArrayList<String> peoples){


        Iterator<String> it;
        int shift = 0;
        while(peoples.size() != 1){
            it = peoples.iterator();
            if(shift == 1){
                it.next();
                shift--;
            }
            while(it.hasNext()){
                if(shift == 0){
                    System.out.println(it.next());

                    it.remove();
                    shift++;
                }
                else{
                    it.next();
                    shift--;
                }
            }
        }
        return peoples.get(0);
    }

    @Override
    public String emulate(LinkedList<String> peoples){
        Iterator<String> it;
        int shift = 0;
        while(peoples.size() != 1){
            it = peoples.iterator();
            if(shift == 1){
                it.next();
                shift--;
            }
            while(it.hasNext()){
                if(shift == 0){
                    System.out.println(it.next());

                    it.remove();
                    shift++;
                }
                else{
                    it.next();
                    shift--;
                }
            }
        }
        return peoples.get(0);
    }

}