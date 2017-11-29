package com.epam.courses.jf.practice.hkryzhik.second;

import com.epam.courses.jf.practice.common.second.ITestableTask1;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Task1 implements ITestableTask1{
    @Override
    public List<String> reverseFile(File input, File output) {

        List<String> inputStringList = new ArrayList<>();

        if (input.canRead() && input.exists()) {

            try {
                inputStringList = Files.lines(Paths.get(input.getPath())).collect(Collectors.toList());

                Collections.reverse(inputStringList);

                Files.write(Paths.get(output.getPath()), inputStringList);

            } catch (IOException e) {
                System.out.println(e);
            }
            Collections.reverse(inputStringList);
        }
        return inputStringList;
    }
}
