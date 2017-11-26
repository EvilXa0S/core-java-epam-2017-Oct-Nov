package com.epam.courses.jf.practice.bborzdov.second;

import com.epam.courses.jf.practice.common.second.ITestableTask2;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by bogdan on 15.11.17.
 */
public class Task2 implements ITestableTask2 {
    @Override
    public Set<File> getFiles(File directory) {
        File[] files = directory.listFiles();
        if(files==null){
            return null;
        }
        Set<File> set = new HashSet(Arrays.asList(files));
        for (File file : files) {
            if(file.listFiles()!=null) {
                List<File> list = Arrays.asList(file.listFiles());
                if (!list.isEmpty()) {
                    set.addAll(list);
                }
                for (File file1 : list) {
                    Set<File> set1 = getFiles(file1);
                    if(set1!=null) {
                        set.addAll(set1);
                    }
                }
            }
        }
        return set;

    }
}
