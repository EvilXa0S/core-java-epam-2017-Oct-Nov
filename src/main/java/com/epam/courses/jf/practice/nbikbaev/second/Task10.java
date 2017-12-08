package com.epam.courses.jf.practice.nbikbaev.second;

import com.epam.courses.jf.practice.common.second.ITestableTask10;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Task10 implements ITestableTask10 {
    @Override
    public HashMap<String, Integer> countNumberWords(File input) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        try (Scanner scanner = new Scanner(input)) {
            while (scanner.hasNext()) {
                String word = scanner.next();
                if (hashMap.containsKey(word)) {
                    int count = hashMap.get(word);
                    count++;
                    hashMap.put(word, count);
                } else {
                    hashMap.put(word, 1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return hashMap;
    }
}
