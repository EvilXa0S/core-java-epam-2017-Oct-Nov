package com.epam.courses.jf.practice.NikDevyatyi.second;

import com.epam.courses.jf.practice.common.second.ITestableTask6;

import java.util.HashMap;

public class TestableTask6 implements ITestableTask6 {

    @Override
    public HashMap<Integer, Integer> addPolynomials(HashMap<Integer, Integer> first, HashMap<Integer, Integer> second) {
        HashMap<Integer,Integer> result = new HashMap<>();
        for(int key : first.keySet()){
            //System.out.println(key);
            if(second.get(key)!=null){
                result.put(key,first.get(key) + second.get(key));
            }
            else{
                result.put(key,first.get(key));
            }
        }
        for(int key: second.keySet()){
            if(result.get(key)==null){
                result.put(key,second.get(key));
            }
        }
        return result;
    }
}
