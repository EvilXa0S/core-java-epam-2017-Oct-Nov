package com.epam.courses.jf.practice.common.second;

import java.io.File;
import java.util.Set;

/**
 * Интерфейс для юнит-тестирования задания №2.
 * Сформировать множество файлов и каталогов, входящих в указанный каталог и его подкаталоги.
 */
public interface ITestableTask2 extends ITaskStorage.ITestableTask {

    /**
     * Формирует множество всех файлов и каталогов, входящих в указанный каталог и во все вложенные.
     * @param directory Корневой каталог.
     * @return Полученное множество каталогов и файлов.
     */
    Set<File> getFiles(File directory);
}
