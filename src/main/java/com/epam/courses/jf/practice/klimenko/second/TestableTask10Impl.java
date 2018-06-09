package com.epam.courses.jf.practice.klimenko.second;

import com.epam.courses.jf.practice.common.second.ITestableTask10;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class TestableTask10Impl implements ITestableTask10 {
    @Override
    public HashMap<String, Integer> countNumberWords(File input) {
        try (FileReader reader = new FileReader(input)) {
            HashMap<String, Integer> ret = new HashMap<>();
            Scanner scanner = new Scanner(reader);
            while (scanner.hasNext()) {
                String word = scanner.next();
                if (ret.containsKey(word)) {
                    ret.put(word, ret.get(word) + 1);
                } else {
                    ret.put(word, 1);
                }
            }
            return ret;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
