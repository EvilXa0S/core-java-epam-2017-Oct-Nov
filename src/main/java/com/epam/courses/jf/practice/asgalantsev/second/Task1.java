package com.epam.courses.jf.practice.asgalantsev.second;

import com.epam.courses.jf.practice.common.second.ITestableTask1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class Task1 implements ITestableTask1 {

    @Override
    public List<String> reverseFile(File input, File output) {
        List<String> lines = new ArrayList<>();
        Stack<String> reverseList = new Stack<>();
        try {
            lines = Files.readAllLines(Paths.get(input.toURI()), Charset.defaultCharset());
            for (String s : lines) {
                reverseList.add(s);
            }
        } catch (IOException e) {}

        try {
            FileWriter writer = new FileWriter(output, true);
            while(!reverseList.isEmpty()) {
                writer.append(reverseList.pop());
                writer.append("\n");
            }
            writer.flush();
            writer.close();
        } catch (IOException e) { }

        return lines;
    }
}
