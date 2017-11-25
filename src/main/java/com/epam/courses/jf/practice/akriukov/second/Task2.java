package com.epam.courses.jf.practice.akriukov.second;

import com.epam.courses.jf.practice.common.second.ITestableTask2;

import java.io.File;
import java.util.Set;
import java.util.TreeSet;

public class Task2 implements ITestableTask2 {

    @Override
    public Set<File> getFiles(File directory) {
        Set<File> set = new TreeSet<>();
        return getFiles(directory, set);
    }

    private Set<File> getFiles(File directory, Set<File> set) {
        for(File file: directory.listFiles()) {
            if(file.isDirectory()) {
                set.add(file);
                getFiles(file, set);
            } else
                set.add(file);
        }
        return set;
    }
}
