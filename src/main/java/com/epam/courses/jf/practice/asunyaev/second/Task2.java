package com.epam.courses.jf.practice.asunyaev.second;

import com.epam.courses.jf.practice.common.second.ITestableTask2;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Task2 implements ITestableTask2 {
    @Override
    public Set<File> getFiles(File directory) {
        Set<File> files = new HashSet<>(Arrays.asList(directory.listFiles()));
        return files;
    }
}
