package com.epam.courses.jf.practice.bborzdov.second;

import com.epam.courses.jf.practice.common.second.ITestableTask10;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by bogdan on 17.11.17.
 */

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
        List<String> strings = new ArrayList<>();
        HashMap<String,Integer> words = new HashMap<>();
        try {
            strings = Files.readAllLines(input.toPath());
        }catch (IOException e){
            System.out.println("Error with reading of file! " + e.getMessage());
        }
        for (int i = 0; i < strings.size(); i++) {
            String [] stringArray = strings.get(i).split(" ");
            if(stringArray.length > 1){
                strings.remove(i);
                strings.addAll(Arrays.asList(stringArray));
                i = -1;
            }
        }
        for (String string : strings) {
            if(words.containsKey(string)){
                words.put(string, words.get(string)+1);
            }else{
                words.put(string,1);
            }
        }
        return words;
    }
}
