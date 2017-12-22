package com.epam.courses.jf.practice.vkostin.second;

import com.epam.courses.jf.practice.common.second.ITestableTask1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task1 implements ITestableTask1 {

    // + remove commented code
    // + remove currentString
    // - remove try/catch [ i can't ]
    // + investigate flush
    @Override
    public List<String> reverseFile(File input, File output) {
        List<String> strings = new ArrayList<>();

        try (Scanner scanner = new Scanner(input)){
            while (scanner.hasNext()) {
                strings.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(output))) {
            for (int i = strings.size() - 1; i >= 0; --i) {
                writer.append(strings.get(i)).append("\n");
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return strings;
    }
}