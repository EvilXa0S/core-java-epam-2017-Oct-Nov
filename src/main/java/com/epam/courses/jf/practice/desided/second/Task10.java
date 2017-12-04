package com.epam.courses.jf.practice.desided.second;

import com.epam.courses.jf.practice.common.second.ITestableTask10;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
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
     *
     * @param input Файл с исходными данными.
     * @return Множество пар <слово, количество вхождений в файле>.
     */
    @Override
    public HashMap<String, Integer> countNumberWords(File input) {

        HashMap<String, Integer> hashMap = new HashMap<>();
        try {
            Scanner scanner = new Scanner(new FileReader(input));
            while (scanner.hasNext()){

                String string = scanner.next();
                if (hashMap.containsKey(string)){
                    hashMap.compute(string, (key, oldVal) -> oldVal + 1);
                }
                else {
                    hashMap.put(string, 1);
                }
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return hashMap;
    }
}
