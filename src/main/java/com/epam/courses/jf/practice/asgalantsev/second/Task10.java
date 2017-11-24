package com.epam.courses.jf.practice.asgalantsev.second;

import com.epam.courses.jf.practice.common.second.ITestableTask10;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task10 implements ITestableTask10{
    @Override
    public HashMap<String, Integer> countNumberWords(File input) {
        List<String> lines = new ArrayList<>();
        List<String> words = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();
        try {
            lines = Files.readAllLines(Paths.get(input.toURI()), Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(String line: lines) {
            String[] wordsInLine = line.split(" ");
            for(String w: wordsInLine)
                words.add(w);
        }

        for(String word: words) {
            if(map.containsKey(word))
                map.replace(word, map.get(word) + 1);
            else
                map.put(word, 1);
        }

        return map;
    }
}
