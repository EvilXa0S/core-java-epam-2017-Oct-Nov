package com.epam.courses.jf.practice.asunyaev.second;

import com.epam.courses.jf.practice.common.second.ITestableTask1;

import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Task1 implements ITestableTask1 {
    @Override
    public List<String> reverseFile(File input, File output) {
        List<String> readStrings = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(input))) {
            String string;
            while ((string = reader.readLine()) != null) {
                readStrings.add(string);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(output))) {
            for (int i = readStrings.size() - 1; i >= 0; i--) {
                writer.write(readStrings.get(i));
                writer.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return readStrings;
    }
}
