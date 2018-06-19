package com.epam.courses.jf.practice.sskovalevskiy.second;

import com.epam.courses.jf.practice.common.second.ITestableTask10;

import java.io.*;
import java.util.*;

/**
 * Created by asus on 24.10.2017.
 *
 * Задан файл, содержащий английские слова (без знаков препинания).
 * Требуется выделить все различные слова и посчитать частоту их встречаемости.
 * Слова, отличающиеся регистром букв, считать различными.
 * Использовать класс HashMap.
 */
public class ITestableTask10Impl implements ITestableTask10 {

    /**
     * Подсчитывает количество вхождений каждого слова в файле.
     * @param input Файл с исходными данными.
     * @return Множество пар <слово, количество вхождений в файле>.
     */
    @Override
    public HashMap<String, Integer> countNumberWords(File input) {
        HashMap<String, Integer> result = new HashMap<>();

        List<String> words = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(input))) {
            while (reader.ready()) {
                words.addAll(Arrays.asList(reader.readLine().split(" ")));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String word : words) {
            if (result.containsKey(word)){
                result.put(word, result.get(word) + 1);
            } else {
                result.put(word, 1);
            }
        }
        return result;
    }
}
