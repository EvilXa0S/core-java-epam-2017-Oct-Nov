package com.epam.courses.jf.practice.klimenko.second;

import com.epam.courses.jf.practice.common.second.ITestableTask2;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class TestableTask2Impl implements ITestableTask2 {
    @Override
    public Set<File> getFiles(File directory) {
        Set<File> ret = new HashSet<>();

        try {
            for (File file : directory.listFiles()) {
                ret.add(file);
                if (file.isDirectory()) {
                    ret.addAll(getFiles(file));
                }
            }
        } catch (NullPointerException e) {
            return null;
        }

        return ret;
    }
}
