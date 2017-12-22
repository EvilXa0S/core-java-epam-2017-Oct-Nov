package com.epam.courses.jf.practice.vkostin.second;

import com.epam.courses.jf.practice.common.second.ITestableTask10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Task10 implements ITestableTask10 {

    @Override
    public HashMap<String, Integer> countNumberWords(File input) {
        // use interface
        //  [ i can't, because it will violate the method contract. ]
        HashMap<String, Integer> dictionary = new HashMap<>();

        String currentWord;

        try (Scanner scanner = new Scanner(input)) {

            while (scanner.hasNext()) {
                currentWord = scanner.next().trim();
                // + try to avoid if
                //      [ .compute() is a very good method, thank you very much ]
                dictionary.compute(currentWord, (k, v) -> v == null ? 1 : v + 1);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return dictionary;
    }
}
