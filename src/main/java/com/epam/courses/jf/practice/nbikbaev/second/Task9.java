package com.epam.courses.jf.practice.nbikbaev.second;

import com.epam.courses.jf.practice.common.second.ITestableTask9;

import java.io.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Task9 implements ITestableTask9 {
    @Override
    public HashSet<String> getUniqueWords(File input) {
        HashSet<String> uniqueWords = new HashSet<>();
        try (Scanner scanner = new Scanner(input)) {
            while (scanner.hasNext()) {
                String word = scanner.next();
                uniqueWords.add(word);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return uniqueWords;
    }
}
