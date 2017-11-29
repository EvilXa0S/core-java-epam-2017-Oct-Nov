package com.epam.courses.jf.practice.SigarevVL.second;

import com.epam.courses.jf.practice.common.second.ITestableTask9;

import java.io.*;
import java.util.HashSet;

/**
 * A file contains English words (without punctuation marks).
 * It is required to select all the different words.
 * Words that differ only in the case of letters are the same.
 * Use class HashSet.
 */
public class Task9 implements ITestableTask9 {

    /**
     * Forms a set of unique words, extracting them from a file.
     *
     * @param input The analyzed file.
     * @return Set of received words.
     */
    @Override
    public HashSet<String> getUniqueWords(File input) {
        HashSet<String> resultSet = new HashSet<>();

        try (BufferedReader bufferedReader =
                     new BufferedReader(new FileReader(input))) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                for (String string : line.split(" ")) {
                    resultSet.add(string.toLowerCase());
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultSet;
    }
}
