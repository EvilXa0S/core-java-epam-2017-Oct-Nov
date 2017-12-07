package com.epam.courses.jf.practice.akunats.second;

import com.epam.courses.jf.practice.common.second.ITestableTask9;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public class Task9 implements ITestableTask9 {
    public HashSet<String> getUniqueWords(File input) {
        Set<String> result = new HashSet<>();
        try {
            Files.lines(Paths.get(input.getPath()), Charset.forName("ISO-8859-1"))
                    .forEach(i -> {
                        for (String s : i.split(" ")) {
                            if (result.contains(s)) {
                                result.remove(s);
                            } else {
                                result.add(s);
                            }
                        }
                    });
        } catch (IOException e) {
            System.out.println();
        }
        return new HashSet<>(result);
    }
}
