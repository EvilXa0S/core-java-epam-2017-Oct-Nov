package com.epam.courses.jf.practice.hkryzhik.second;

import com.epam.courses.jf.practice.common.second.ITestableTask10;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Task10 implements ITestableTask10{
    @Override
    public HashMap<String, Integer> countNumberWords(File input) {

        List<String> checkingWords = new ArrayList<>();
        HashMap<String, Integer> result = new HashMap<>();

        List<String> finalList = new ArrayList<>();
        
        try {
            checkingWords = Files.lines(Paths.get(input.getPath())).collect(Collectors.toList());

        }catch (IOException e){
            e.printStackTrace();
        }

        for (String checkingWord : checkingWords) {
            finalList.addAll(Arrays.asList(checkingWord.split(" ")));
        }

        for (String checkingWord : finalList) {

            if(result.containsKey(checkingWord)){

                result.put(checkingWord, result.get(checkingWord) + 1);

            }else {

                result.put(checkingWord, 1);
            }
        }
        
        return result;
    }
}
