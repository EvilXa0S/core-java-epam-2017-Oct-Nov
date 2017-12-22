package com.epam.courses.jf.practice.danilBay.second;

import com.epam.courses.jf.practice.common.second.ITestableTask10;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Task10 implements ITestableTask10 {
    @Override
    public HashMap<String, Integer> countNumberWords(File input) {
        HashMap<String,Integer> res=new HashMap<>();
        String temp,line;
        Integer integer;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(input))) {
            while (( line = bufferedReader.readLine()) != null) {
                for (String buf : line.split(" ")) {

                    if(res.containsKey(buf)){
                        integer=res.get(buf)+1;
                    }
                    else {
                        integer=1;
                    }
                    res.put(buf,integer);
                }
            }
        }   catch (IOException e) {
            e.printStackTrace();
        }

        return res;

    }

    public static void main(String[] args) {
        Task10 d=new Task10();
         Map<String,Integer> hui=d.countNumberWords(new File("C:\\Users\\dana\\IdeaProjects\\d.txt"));
        int b=0;
    }
}
