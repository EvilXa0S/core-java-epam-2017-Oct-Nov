package com.epam.courses.jf.practice.nzenkova.second;

import com.epam.courses.jf.practice.common.second.ITestableTask10;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;


/**
 * Specify a file, which contains english words (without punctuation marks).
 * Find all different words and calculate their frequency.
 * Words that differ only in the case of letters aren't assumed to be the same.
 * Use HashMap.
 */
public class TestableTask10 implements ITestableTask10 {
    /**
     * Calculate the amount of entries of each word into the file.
     * @param input A file with the initial data.
     * @return A set of the pairs: <the word, the amount of entries into the file>..
     */
    @Override
    public HashMap<String, Integer> countNumberWords(File input) {
        HashMap<String, Integer> words = new HashMap<>();

        try (BufferedReader in = new BufferedReader(new FileReader(input.getAbsoluteFile()))) {
            String line;
            while ((line = in.readLine()) != null) {
                String[] lines = line.split(" ");
                for (String line1 : lines) {
                    if (words.get(line1) == null)
                        words.put(line1, 1);
                    else
                        words.replace(line1, words.get(line1) + 1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return words;
    }
}

