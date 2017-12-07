package com.epam.courses.jf.practice.akunats.second;

import com.epam.courses.jf.practice.common.second.ITestableTask10;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Task10 implements ITestableTask10 {
    public HashMap<String, Integer> countNumberWords(File input) {
        Map<String, Integer> result = new HashMap<>();
        try {
            Files.lines(Paths.get(input.getPath()), Charset.forName("ISO-8859-1"))
                    .forEach(i -> {
                        for (String s : i.split(" ")) {
                            if (result.containsKey(s)) {
                                result.put(s, result.get(s) + 1);
                            } else {
                                result.put(s, 1);
                            }
                        }
                    });
        } catch (IOException e) {
            System.out.println();
        }
        return new HashMap<>(result);
    }
}
