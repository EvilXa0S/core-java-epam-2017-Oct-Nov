package com.epam.courses.jf.practice.igulyaev.second;

import com.epam.courses.jf.practice.common.second.ITestableTask10;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TestableTask10 implements ITestableTask10 {
    @Override
    public HashMap<String, Integer> countNumberWords(File input) {
        Map<String, Integer> map = new HashMap<>();
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(input))) {
            for(String str = bufferedReader.readLine(); str != null; str = bufferedReader.readLine()){
                Arrays.stream(str.split(" ")).forEach(s -> {
                    Integer count = map.get(s);
                    if(count == null){
                        count = 0;
                    }
                    map.put(s, ++count);
                }
                );
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return (HashMap<String, Integer>)map;
    }
}
