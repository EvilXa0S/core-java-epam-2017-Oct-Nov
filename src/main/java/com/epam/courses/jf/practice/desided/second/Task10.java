package com.epam.courses.jf.practice.desided.second;

import com.epam.courses.jf.practice.common.second.ITestableTask10;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;

public class Task10 implements ITestableTask10{
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
