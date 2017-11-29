package com.epam.courses.jf.practice.asgalantsev.second;

import com.epam.courses.jf.practice.common.second.ITestableTask2;

import java.io.File;
import java.util.Set;
import java.util.TreeSet;

/**
 * Сформировать множество элементов, входящих в каталог и его подкаталоги.
 */

public class Task2 implements ITestableTask2 {

    /**
     * Формирует множество всех элементов, входящих в текущий каталог и во все вложенные.
     * @param directory Корневой каталог.
     * @return Множество элементов корневого каталога и подкаталогов.
     */

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
