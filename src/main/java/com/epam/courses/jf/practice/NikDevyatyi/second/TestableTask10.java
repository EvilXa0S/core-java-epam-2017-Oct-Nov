package com.epam.courses.jf.practice.NikDevyatyi.second;

import com.epam.courses.jf.practice.common.second.ITestableTask10;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestableTask10 implements ITestableTask10 {
    @Override
    public HashMap<String, Integer> countNumberWords(File input) {
        int counter = 1;
        HashMap<String,Integer> temp = new HashMap<>();
        List<String> result = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(input))){
            String line;
            while ((line = br.readLine()) != null) {
                for(String word: line.split(" ")){
                    result.add(word);
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        result.sort(String::compareTo);
        String tmp = "";
        if(result.size()>0){
            tmp = result.get(result.size() - 1);
        }
        for (String item: result){
            if(tmp.equals(item)){
                counter++;
            }
            else{
                temp.put(tmp, counter);
                counter = 1;
                tmp = item;
            }

        }
        return temp;
    }
}
