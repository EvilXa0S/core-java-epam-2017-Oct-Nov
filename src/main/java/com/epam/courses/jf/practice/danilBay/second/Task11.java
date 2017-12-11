package com.epam.courses.jf.practice.danilBay.second;

import com.epam.courses.jf.practice.common.second.ITestableTask11;

import java.util.ArrayList;
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
        for(int i=0; peoples.size()>1; i=(i+1)%peoples.size()){
            peoples.remove(i);
        }
        return peoples.get(0);
    }

    public static void main(String[] args) {


        Task11 tsk11=new Task11();
        LinkedList<String> d =new LinkedList<String>();
        d.add(2+"");
        d.add(6+"");
        d.add(5+"");
        d.add(8+"");
        d.add(9+"");

        System.out.println(tsk11.emulate(d));
    }
}
