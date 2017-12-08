package com.epam.courses.jf.practice.nbikbaev.second;

import com.epam.courses.jf.practice.common.second.ITestableTask2;

import java.io.File;
import java.util.*;

public class Task2 implements ITestableTask2 {
    @Override
    public Set<File> getFiles(File directory) {
        Set<File> files = new HashSet<>();
        Collection<File> fileCollection = Arrays.asList(directory.listFiles());
        files.addAll(fileCollection);
        for (File file : fileCollection) {
            if (file.isDirectory()) {
                files.addAll(getFiles(file));
            }
        }
        return files;
    }
}
