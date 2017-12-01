package com.epam.courses.jf.practice.akriukov.second;

import com.epam.courses.jf.practice.common.second.ITestableTask10;

import java.io.*;
import java.util.HashMap;

/**
 * Задан файл, содержащий английские слова (без знаков препинания).
 * Требуется выделить все различные слова и посчитать частоту их встречаемости.
 * Слова, отличающиеся регистром букв, считать различными.
 * Использовать класс HashMap.
 */
public class Task10 implements ITestableTask10 {

    /**
     * Подсчитывает количество вхождений каждого слова в файле.
     * @param input Файл с исходными данными.
     * @return Множество пар <слово, количество вхождений в файле>.
     */
    @Override
    public HashMap<String, Integer> countNumberWords(File input) {

        HashMap<String, Integer> result = new HashMap<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(input));

            while (reader.ready()) {
                for(String string : reader.readLine().split(" ")){
                    if (!result.containsKey(string)) {
                        result.put(string, 1);
                    } else {
                        int count = result.get(string);
                        result.put(string, count + 1);
                    }
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
