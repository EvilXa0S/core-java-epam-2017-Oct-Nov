package com.epam.courses.jf.practice.danilBay.second;

import com.epam.courses.jf.practice.common.second.ITestableTask6;
import com.sun.tools.corba.se.idl.InterfaceGen;

import java.util.HashMap;

import static sun.tools.jstat.Alignment.keySet;

public class Task6 implements ITestableTask6{
    @Override
    public HashMap<Integer, Integer> addPolynomials(HashMap<Integer, Integer> first, HashMap<Integer, Integer> second) {
        HashMap<Integer,Integer> hashMap=new HashMap<>();

        for(Integer integer :first.keySet()){
            hashMap.put(integer,first.get(integer));

        }
        Integer value;
        for(Integer integer : second.keySet()){
            value = hashMap.get(integer);
            if(value==null){
               value=0;
            }
            hashMap.put(integer,second.get(integer)+value);

        }
        return hashMap;
    }
}
