package com.epam.courses.jf.practice.klimenko.second;

import com.epam.courses.jf.practice.common.second.ITestableTask9;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

public class TestableTask9Impl implements ITestableTask9 {
    @Override
    public HashSet<String> getUniqueWords(File input) {
        try (FileReader reader = new FileReader(input)) {
            HashSet<String> ret = new HashSet<>();
            Scanner scanner = new Scanner(reader);
            while (scanner.hasNext()) {
                ret.add(scanner.next().toLowerCase());
            }
            return ret;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
