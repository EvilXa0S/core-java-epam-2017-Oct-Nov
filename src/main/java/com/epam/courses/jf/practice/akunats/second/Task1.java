package com.epam.courses.jf.practice.akunats.second;

import com.epam.courses.jf.practice.common.second.ITestableTask1;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Task1 implements ITestableTask1 {
    public List<String> reverseFile(File input, File output) {
        List<String> result = new ArrayList<>();
        try {
            result = Files.lines(Paths.get(input.getPath()), Charset.forName("ISO-8859-1"))
                    .collect(Collectors.toList());
            Collections.reverse(result);
            Files.write(Paths.get(output.getPath()), result, StandardCharsets.ISO_8859_1);
        } catch (IOException e) {
            System.out.println("Error");
        }
        Collections.reverse(result);
        return result;
    }
}
