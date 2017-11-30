package com.epam.courses.jf.practice.desided.second;

import com.epam.courses.jf.practice.common.second.ITestableTask9;

import java.io.*;
import java.util.HashSet;
import java.util.Scanner;

public class Task9 implements ITestableTask9{
    @Override
    public HashSet<String> getUniqueWords(File input){

        HashSet<String> hashSet = new HashSet<>();
        try {
            Scanner scanner = new Scanner(new FileReader(input));
            while (scanner.hasNext()){
                hashSet.add(scanner.next().toLowerCase());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return hashSet;
    }
}
