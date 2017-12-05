package com.epam.courses.jf.practice.asunyaev.second;

import com.epam.courses.jf.practice.common.second.ITestableTask9;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class Task9 implements ITestableTask9 {
    public HashSet<String> getUniqueWords(File input) {
        HashSet<String> readStrings = new HashSet<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(input))) {
            String string;
            while ((string = reader.readLine()) != null) {
                readStrings.add(string.toLowerCase());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return readStrings;
    }
}
