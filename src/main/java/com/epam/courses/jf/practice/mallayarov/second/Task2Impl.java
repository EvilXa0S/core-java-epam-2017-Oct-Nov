package com.epam.courses.jf.practice.mallayarov.second;

import com.epam.courses.jf.practice.common.second.ITestableTask2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

/**
 * Сформировать множество элементов, входящих в каталог и его подкаталоги.
 */
public class Task2Impl implements ITestableTask2 {

    /**
     * Формирует множество всех элементов, входящих в текущий каталог и во все вложенные.
     * @param directory Корневой каталог.
     * @return Множество элементов корневого каталога и подкаталогов.
     */
    @Override
    public Set<File> getFiles(File directory) {
        Set<File> result = new HashSet<>();
        Path path = Paths.get(directory.getAbsolutePath());

        try {
            Files.walkFileTree(path, new MyFileVisitor(result, path));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }
}
