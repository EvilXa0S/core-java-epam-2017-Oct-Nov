package com.epam.courses.jf.practice.nbikbaev.second;

import com.epam.courses.jf.practice.common.second.ITestableTask1;
import com.epam.courses.jf.practice.nbikbaev.first.Utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Task1 implements ITestableTask1 {
    @Override
    public List<String> reverseFile(File input, File output) {
        List<String> strings = new ArrayList<>();
        try (Scanner scanner = new Scanner(input)) {
            while (scanner.hasNext()) {
                strings.add(scanner.next());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collections.reverse(strings);
        Utils.writeToFile(output, strings);
        Collections.reverse(strings);
        return strings;
    }
}
