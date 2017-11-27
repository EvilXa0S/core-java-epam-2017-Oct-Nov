package com.epam.courses.jf.practice.vkostin.second;

import com.epam.courses.jf.practice.common.second.ITestableTask9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;


public class Task9 implements ITestableTask9 {

    @Override
    public HashSet<String> getUniqueWords(File input) {
        // use interface
        //  [ i can't, because it will violate the method contract. ]
        HashSet<String> set = new HashSet<>();

        // remove try/catch
        //   [ on the last review we decided that we would leave it. ]
        try (Scanner scanner = new Scanner(input)) {
            while (scanner.hasNext()) {
                set.add(scanner.next()
                        .trim()             // remove white spaces
                        .toLowerCase());    // lower case
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return set;
    }
}
