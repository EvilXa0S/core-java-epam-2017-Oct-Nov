package com.epam.courses.jf.practice.klimenko.second;

import com.epam.courses.jf.practice.common.second.ITestableTask1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TestableTask1Impl implements ITestableTask1 {
    @Override
    public List<String> reverseFile(File input, File output) {
        List<String> list;

        try {
            list = Files.lines(input.toPath()).collect(Collectors.toList());
            Collections.reverse(list);
            Files.write(output.toPath(), list);
            Collections.reverse(list);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return list;
    }
}
