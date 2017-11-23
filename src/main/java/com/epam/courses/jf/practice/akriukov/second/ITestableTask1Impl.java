package com.epam.courses.jf.practice.akriukov.second;

import com.epam.courses.jf.practice.common.second.ITestableTask1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ITestableTask1Impl implements ITestableTask1 {
    @Override
    public List<String> reverseFile(File input, File output) {
        List<String> strings = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(input))) {
            while (reader.ready()) {
                strings.add(reader.readLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(output))) {
            for (int i = strings.size() - 1; i >= 0; i--) {
                writer.write(strings.get(i) + "\n\r");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strings;
    }
}
