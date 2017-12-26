package com.epam.courses.jf.practice.vakhonin.second;

import com.epam.courses.jf.practice.common.second.ITestableTask9;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Задан файл, содержащий английские слова (без знаков препинания).
 * Требуется выделить все различные слова.
 * Слова, отличающиеся только регистром букв, считать одинаковыми.
 * Использовать класс HashSet.
 */

public class Task9 implements ITestableTask9 {

    /**
     * Формирует множество уникальных слов, извлекая их из файла.
     * @param input Анализируемый файл.
     * @return Множество полученных слов.
     */

    @Override
    public HashSet<String> getUniqueWords(File input) {
        HashSet<String> set = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(input))) {
            String str;
            String[] strArr;
            while ((str = reader.readLine()) != null) {
                strArr = str.split("\\s");
                for (String s: strArr) {
                    set.add(s.toLowerCase());
                }
            }

            return set;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
