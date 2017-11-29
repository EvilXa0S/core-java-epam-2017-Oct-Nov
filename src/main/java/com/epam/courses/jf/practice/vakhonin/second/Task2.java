package com.epam.courses.jf.practice.vakhonin.second;

import com.epam.courses.jf.practice.common.second.ITestableTask2;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

/**
 * Сформировать множество элементов, входящих в каталог и его подкаталоги.
 */

public class Task2 implements ITestableTask2 {

    Set<File> allFiles = new HashSet<>();

    /**
     * Формирует множество всех элементов, входящих в текущий каталог и во все вложенные.
     * @param directory Корневой каталог.
     * @return Множество элементов корневого каталога и подкаталогов.
     */

    @Override
    public Set<File> getFiles(File directory) {

        for (File f: directory.listFiles()) {
            if (f.isDirectory()) {
                allFiles.add(f);
                allFiles.addAll(getFiles(f));
            }
            else {
                allFiles.add(f);
            }
        }

        return allFiles;
    }
}
