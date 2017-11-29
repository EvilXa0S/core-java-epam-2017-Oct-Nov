package com.epam.courses.jf.practice.vakhonin.second;

import com.epam.courses.jf.practice.common.second.ITestableTask10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * Задан файл, содержащий английские слова (без знаков препинания).
 * Требуется выделить все различные слова и посчитать частоту их встречаемости.
 * Слова, отличающиеся регистром букв, считать различными.
 * Использовать класс HashMap.
 */

public class Task10 implements ITestableTask10{

    /**
     * Подсчитывает количество вхождений каждого слова в файле.
     * @param input Файл с исходными данными.
     * @return Множество пар <слово, количество вхождений в файле>.
     */

    @Override
    public HashMap<String, Integer> countNumberWords(File input) {
        HashMap<String, Integer> wordsMap = new HashMap<>();
        List<String> stringList = new ArrayList<>();
        String string;


        try (Scanner reader = new Scanner(input)) {
            while (reader.hasNext()) {
                string = reader.next();
                stringList.add(string);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (String s: stringList) {
            if (wordsMap.containsKey(s)) {
                wordsMap.put(s, wordsMap.get(s) + 1);
            }
            else {
                wordsMap.put(s, 1);
            }
        }

        return wordsMap;
    }
}
