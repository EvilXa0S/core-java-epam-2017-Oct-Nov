package com.epam.courses.jf.practice.desided.second;

import com.epam.courses.jf.practice.common.second.ITaskStorage;
import com.epam.courses.jf.practice.common.second.ITestableTask1;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Task1 implements ITestableTask1 {
    @Override
    public List<String> reverseFile(File input, File output) throws IOException {

        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(
                    new FileReader(input)
            );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String s;
        List<String> arr = new ArrayList<>();

        while ((s = bufferedReader.readLine()) != null){
            System.out.println(s);
            arr.add(s);
        }

        bufferedReader.close();
        arr.sort(Collections.reverseOrder());
        BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter(output)
        );
        Iterator iterator = arr.iterator();
        while (iterator.hasNext()){
            bufferedWriter.write((String) iterator.next());
            bufferedWriter.newLine();
        }
        bufferedWriter.close();

        return arr;
    }
}
