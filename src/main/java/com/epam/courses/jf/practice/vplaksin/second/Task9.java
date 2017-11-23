package com.epam.courses.jf.practice.vplaksin.second;

import com.epam.courses.jf.practice.common.second.ITestableTask9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Задан файл, содержащий английские слова (без знаков препинания).
 * Требуется выделить все различные слова.
 * Слова, отличающиеся только регистром букв, считать одинаковыми.
 * Использовать класс HashSet.
 */
public class Task9 implements ITestableTask9 {

    /**
     * Формирует множество уникальных слов, извлекая их из файла.
     *
     * @param input Анализируемый файл.
     * @return Множество полученных слов.
     */
    @Override
    public HashSet<String> getUniqueWords(File input) {
        HashSet<String> result = new HashSet<>();

        try (Scanner scanner = new Scanner(input)) {
            while (scanner.hasNext()) {
                result.add(scanner.next().toLowerCase());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return result;
    }

}
