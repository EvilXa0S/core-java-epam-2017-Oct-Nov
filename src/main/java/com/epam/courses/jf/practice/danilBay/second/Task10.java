package com.epam.courses.jf.practice.danilBay.second;

import com.epam.courses.jf.practice.common.second.ITestableTask10;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Task10 implements ITestableTask10 {
    @Override
    public HashMap<String, Integer> countNumberWords(File input) {
        HashMap<String,Integer> res=new HashMap<>();
        String temp;
        Integer integer;
        try (Scanner fileScanner = new Scanner((new FileReader(input)))) {
            fileScanner.useDelimiter(" ");

            while (fileScanner.hasNext()){
                temp=fileScanner.next();

                if(res.containsKey(temp)){
                    integer=res.get(temp)+1;
                }
                else {
                    integer=1;
                }
                res.put(temp,integer);
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
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
