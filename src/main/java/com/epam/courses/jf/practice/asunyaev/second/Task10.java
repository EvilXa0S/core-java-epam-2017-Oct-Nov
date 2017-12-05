package com.epam.courses.jf.practice.asunyaev.second;

import com.epam.courses.jf.practice.common.second.ITestableTask10;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class Task10 implements ITestableTask10 {
    public HashMap<String, Integer> countNumberWords(File input) {
        HashMap<String, Integer> words = new HashMap<>();

        try (Scanner reader = new Scanner(input)) {
            while (reader.hasNext()) {
                String string = reader.next();
                if (words.containsKey(string)) {
                    words.put(string, words.get(string) + 1);
                } else {
                    words.put(string, 1);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return words;
    }
}
