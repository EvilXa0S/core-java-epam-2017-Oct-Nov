package com.epam.courses.jf.practice.nzenkova.second;

import com.epam.courses.jf.practice.common.second.ITestableTask2;

import java.io.File;
import java.util.Set;
import java.util.TreeSet;

/**
 * Create a bunch of files and directories, which include in a specified directory
 * and its subdirectory
 */

public class TestableTask2 implements ITestableTask2 {
    private Set<File> set = new TreeSet<>();

    /**
     * @param directory The root directory.
     * @return The bunch of files and directories.
     */
    @Override
    public Set<File> getFiles(File directory) {
        getAll(directory.listFiles());
        return set;
    }

    /**
     * The bypass of all embedded files and directories.
     * @param files The array of files and directories.
     */
    private void getAll(File[] files) {
        for (File file : files) {
            if (file.isDirectory()) {
                getAll(file.listFiles());
            }
        }
        for (File file : files) {
            set.add(new File(String.valueOf(file.getAbsoluteFile())));
        }
    }
}
