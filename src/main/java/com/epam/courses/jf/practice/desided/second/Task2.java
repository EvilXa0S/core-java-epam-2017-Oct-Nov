package com.epam.courses.jf.practice.desided.second;

import com.epam.courses.jf.practice.common.second.ITaskStorage;
import com.epam.courses.jf.practice.common.second.ITestableTask1;
import com.epam.courses.jf.practice.common.second.ITestableTask2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Task2 implements ITestableTask2 {
    @Override
    public Set<File> getFiles(File directory) {
        Set<File> fileSet = new HashSet<>();

        try {
            fileSet = Files.walk(directory.toPath())
                    .map(Path::toFile)
                    .collect(Collectors.toSet());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileSet;
    }
}
