package com.epam.courses.jf.practice.akriukov.second;

import com.epam.courses.jf.practice.common.second.ITestableTask9;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Задан файл, содержащий английские слова (без знаков препинания).
 * Требуется выделить все различные слова.
 * Слова, отличающиеся только регистром букв, считать одинаковыми.
 * Использовать класс HashSet.
 */
public class Task9 implements ITestableTask9{

    /**
     * Формирует множество уникальных слов, извлекая их из файла.
     * @param input Анализируемый файл.
     * @return Множество полученных слов.
     */
    @Override
    public HashSet<String> getUniqueWords(File input) {

        HashSet<String> result = new HashSet<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(input));

            while (reader.ready()) {
                for(String string : reader.readLine().split(" ")){
                    result.add(string.toLowerCase());
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
