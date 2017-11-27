package com.epam.courses.jf.practice.SigarevVL.second;

import com.epam.courses.jf.practice.common.second.ITestableTask2;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

/**
 * Create a lot of files and directories that are included
 * in the specified directory and its subdirectories.
 */
public class Task2 implements ITestableTask2 {

    /**
     * Forms a set of all files and directories
     * included in the specified directory and all nested.
     *
     * @param directory Root directory.
     * @return The resulting set of directories and files.
     */
    @Override
    public Set<File> getFiles(File directory) {

        Set<File> set = new HashSet<>();

        for (File unit : directory.listFiles()) {
            if (unit.isDirectory()) {
                set.add(unit);
                set.addAll(getFiles(unit));
            } else {
                set.add(unit);
            }
        }
        return set;
    }
}
