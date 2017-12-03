package com.epam.courses.jf.practice.vplaksin.second;

import com.epam.courses.jf.practice.common.second.ITestableTask2;

import java.io.File;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.HashSet;

/**
 * Сформировать множество файлов и каталогов, входящих в указанный каталог и его подкаталоги.
 */
public class Task2 implements ITestableTask2 {

    /**
     * Формирует множество всех файлов и каталогов, входящих в указанный каталог и во все вложенные.
     *
     * @param directory Корневой каталог.
     * @return Полученное множество каталогов и файлов.
     */
    @Override
    public Set<File> getFiles(File directory) {
        Set<File> result = new HashSet<>();
        Queue<File> queue = new LinkedList<>();
        Collections.addAll(queue, directory.listFiles());

        while (!queue.isEmpty()) {
            File file = queue.remove();
            if (file.isDirectory()) {
                Collections.addAll(queue, file.listFiles());
            }
            result.add(file);
        }

        return result;
    }

}
