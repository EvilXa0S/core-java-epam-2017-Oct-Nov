package com.epam.courses.jf.practice.akriukov.second;

import com.epam.courses.jf.practice.common.second.ITestableTask9;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class Task9 implements ITestableTask9{
    @Override
    public HashSet<String> getUniqueWords(File input) {

        HashSet<String> result = new HashSet<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(input));

            while (reader.ready()) {
                for(String string : reader.readLine().split(" ")){
                    result.add(string.toLowerCase());
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
