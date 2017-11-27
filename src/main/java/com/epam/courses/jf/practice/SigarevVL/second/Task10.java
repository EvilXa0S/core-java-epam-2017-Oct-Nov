package com.epam.courses.jf.practice.SigarevVL.second;

import com.epam.courses.jf.practice.common.second.ITestableTask10;

import java.io.*;
import java.util.HashMap;

/**
 * A file contains English words (without punctuation marks).
 * It is required to select all the different words and
 * to calculate the frequency of their occurrence.
 * Words that differ in the register of letters are different.
 * Use class HashMap.
 */
public class Task10 implements ITestableTask10 {

    /**
     * Counts the number of occurrences of each word in the file.
     *
     * @param input The file with the initial data.
     * @return Multiple pairs <word, number of occurrences in file>.
     */
    @Override
    public HashMap<String, Integer> countNumberWords(File input) {
        HashMap<String, Integer> wordsMap = new HashMap<>();
        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader bufferedReader =
                     new BufferedReader(new FileReader(input))) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append(" ");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String allWords[] = stringBuilder.toString().trim().split(" ");

        if (stringBuilder.length() == 0) {
            return wordsMap;
        }
        for (String string : allWords) {
            if (wordsMap.containsKey(string)) {
                wordsMap.put(string, wordsMap.get(string) + 1);
            } else {
                wordsMap.put(string, 1);
            }
        }

        return wordsMap;
    }
}
