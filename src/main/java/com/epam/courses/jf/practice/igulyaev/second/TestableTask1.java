package com.epam.courses.jf.practice.igulyaev.second;

import com.epam.courses.jf.practice.common.second.ITestableTask1;

import java.io.*;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestableTask1 implements ITestableTask1 {
    @Override
    public List<String> reverseFile(File input, File output) {
        List<String> stringList = new LinkedList<>();
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(input))){
            for(String str = bufferedReader.readLine(); str != null; str = bufferedReader.readLine()){
                stringList.add(str);
            }
        }catch(IOException e){
            e.printStackTrace();//TODO: Add logger
        }
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(output))){
            for(Iterator<String> i = ((LinkedList<String>)stringList).descendingIterator(); i.hasNext();){
                bufferedWriter.write(i.next());
                bufferedWriter.write("\n");
            }
        }catch(IOException e){
            e.printStackTrace();//TODO: Add logger
        }
        return stringList;
    }
}
