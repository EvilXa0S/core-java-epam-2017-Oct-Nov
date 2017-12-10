package com.epam.courses.jf.practice.hkryzhik.second;

import com.epam.courses.jf.practice.common.second.ITestableTask2;

import java.io.File;
import java.util.Set;
import java.util.TreeSet;

public class Task2 implements ITestableTask2 {

    private void recursionFileArrayGetter(File directory, Set<File> resultFileSet){
        if(directory.exists()) {
            File[] filesAndDirectories = directory.listFiles();
            for (File file : filesAndDirectories) {
                if (file.isDirectory()) {

                    resultFileSet.add(file);

                    recursionFileArrayGetter(file, resultFileSet);

                } else {
                    resultFileSet.add(file);
                }
            }
        }
    }

    @Override
    public Set<File> getFiles(File directory) {

        Set<File> resultFileSet = new TreeSet<>();

        recursionFileArrayGetter(directory, resultFileSet);

        return resultFileSet;
    }
}
