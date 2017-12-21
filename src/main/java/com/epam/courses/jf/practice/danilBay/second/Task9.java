package com.epam.courses.jf.practice.danilBay.second;

import com.epam.courses.jf.practice.common.second.ITestableTask9;

import java.io.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Task9 implements ITestableTask9{
    @Override
    public HashSet<String> getUniqueWords(File input) {
        HashSet<String> res=new HashSet<>();
        String line;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(input))) {
            while ((line = bufferedReader.readLine()) != null) {
                for (String buf : line.split(" ")) {
                    res.add(buf.toLowerCase());
                }
            }
        }   catch (IOException e) {
            e.printStackTrace();
        }

        return res;
    }

    public static void main(String[] args) {
        File file=new File("C:\\Users\\dana\\Desktop\\Новая папка (3)\\3D модель для МК\\Новый текстовый документ.txt");
        Task9 d=new Task9();
        for(String i: d.getUniqueWords(file)){
            System.out.println(i);
        }
    }
}
