package com.epam.courses.jf.practice.akunats.second;

import com.epam.courses.jf.practice.common.second.ITestableTask2;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Task2 implements ITestableTask2 {
    private Set<File> result = new HashSet<>(); // Записываем результат
    public Set<File> getFiles(File directory) {
        // Список файлов текущей директории
        String[] currentFiles = directory.list();
        if (currentFiles != null) {
            Arrays.stream(currentFiles).
                    map(i -> directory + "\\" + i)
                    .forEach(i -> {
                        File file = new File(i);
                        // Если файл то добавляем в результат
                        if (file.isFile()) {
                            result.add(new File(i));
                        }
                        // Если каталог записываем рекурсионно вызываем этот метод и идем пока не встретим файл
                        else {
                            result.add(new File(i));
                            getFiles(new File(i));
                        }
                    });
        }
        return result;
    }
}
