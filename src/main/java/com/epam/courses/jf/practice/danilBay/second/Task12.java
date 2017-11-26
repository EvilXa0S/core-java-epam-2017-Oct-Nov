package com.epam.courses.jf.practice.danilBay.second;

import com.epam.courses.jf.practice.common.second.ITestableTask12;
import java.util.ArrayList;
import java.util.List;

public class Task12 implements ITestableTask12 {
    @Override
    public List<Integer> transform(List<Integer> integers, int value) {

        for(int i=-1, g=integers.size();  ;){
            do i++; while (integers.get(i)<=value);

            do g--;
               while (integers.get(g)> value);


            if(i >= g) return integers;
            {
                int temp=integers.get(i);
                integers.set(i,integers.get(g));
                integers.set(g,temp);

            }

        }
    }

    public static void main(String[] args) {
        Task12 d=new Task12();
        ArrayList <Integer> g=new ArrayList<>();
        g.add(5);
        g.add(1);
        g.add(7);
        g.add(8);
        g.add(0);
        g.add(-1);
        g.add(5);
        g.add(-4);
        g.add(55);
        g.add(335);


        for(Integer x : d.transform(g,5)){
          System.out.println(x);
      };
    }
}
