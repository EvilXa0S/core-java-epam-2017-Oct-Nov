package com.epam.courses.jf.practice.nzenkova.second;

import com.epam.courses.jf.practice.common.second.ITestableTask9;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;


public class TestableTask9 implements ITestableTask9 {
    @Override
    public HashSet<String> getUniqueWords(File input) {
        HashSet<String> strings = new HashSet<>();
        try (BufferedReader in = new BufferedReader(new FileReader(input.getAbsoluteFile()))) {
            String line;
            while ((line = in.readLine()) != null) {
                line = line.toLowerCase();
                String[] lines = line.split(" ");
                strings.addAll(Arrays.asList(lines));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return strings;
    }
}
