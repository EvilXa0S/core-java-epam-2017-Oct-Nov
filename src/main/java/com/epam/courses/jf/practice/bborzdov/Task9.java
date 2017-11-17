package com.epam.courses.jf.practice.bborzdov;

import com.epam.courses.jf.practice.common.second.ITestableTask9;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Created by bogdan on 17.11.17.
 */
public class Task9 implements ITestableTask9 {

    @Override
    public HashSet<String> getUniqueWords(File input) {
        List<String> strings = new ArrayList<>();
        HashSet<String> uniqWords = new HashSet<>();
        HashSet<String> caseSensitive = new HashSet<>();
        try{
            strings = Files.readAllLines(input.toPath());
        }catch (IOException e){
            System.out.println("Problems with reading of file!" + e.getMessage());
        }
        for (int i = 0; i < strings.size(); i++) {
            String[] split = strings.get(i).split(" ");
            if(split.length > 1){
                strings.remove(strings.get(i));
                strings.addAll(Arrays.asList(split));
            }
            if(!caseSensitive.contains(strings.get(i).toLowerCase())){
                uniqWords.add(strings.get(i));
                caseSensitive.add(strings.get(i).toLowerCase());
            }
        }
        return uniqWords;
    }
}
