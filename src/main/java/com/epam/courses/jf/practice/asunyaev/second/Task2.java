package com.epam.courses.jf.practice.asunyaev.second;

import com.epam.courses.jf.practice.common.second.ITestableTask2;

import java.io.File;
import java.util.*;

public class Task2 implements ITestableTask2 {
    @Override
    public Set<File> getFiles(File directory) {
        Set<File> files = new HashSet<>();
        Queue<File> queue = new LinkedList<>(Arrays.asList(directory.listFiles()));

        while (!queue.isEmpty()) {
            File currentFile = queue.remove();
            files.add(currentFile);
            if (currentFile.isDirectory()) {
                Collections.addAll(queue, currentFile.listFiles());
            }
        }

        return files;
    }
}
