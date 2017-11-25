package com.epam.courses.jf.practice.vakhonin.second;

import com.epam.courses.jf.practice.common.second.ITestableTask2;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by igorvahonin on 13.11.17.
 */
public class Task2 implements ITestableTask2 {

    Set<File> allFiles = new HashSet<>();

    @Override
    public Set<File> getFiles(File directory) {

        for(File f: directory.listFiles()){
            if(f.isDirectory()){
                allFiles.addAll(getFiles(f));
            }
            else{
                allFiles.add(f);
            }
        }
        return allFiles;
    }
}
