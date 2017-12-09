package com.epam.courses.jf.practice.igulyaev.second;

import com.epam.courses.jf.practice.common.second.ITestableTask2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.Set;


public class TestableTask2 implements ITestableTask2 {
    @Override
    public Set<File> getFiles(File directory) {
        Set<File> files = new HashSet<>();
        try {
            Files.walk(directory.toPath(), Integer.MAX_VALUE)
                    .forEach(p -> files.add(p.toFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        files.remove(directory);
        return files;
    }
}
