package com.epam.courses.jf.practice.NikDevyatyi.second;

import com.epam.courses.jf.practice.common.second.ITestableTask9;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class TestableTask9 implements ITestableTask9 {
    @Override
    public HashSet<String> getUniqueWords(File input) {
        List<String> result = new ArrayList<>();
        HashSet<String> out = new HashSet<>();
        try(BufferedReader br = new BufferedReader(new FileReader(input))){
            String line;
            while ((line = br.readLine()) != null) {
                //System.out.println(line);
                result.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();

        }
        for(String item:result){
            for(String word: item.split(" ")){
                //System.out.println(word);
                out.add(word.toLowerCase());

            }
        }

        return out;
    }
}
