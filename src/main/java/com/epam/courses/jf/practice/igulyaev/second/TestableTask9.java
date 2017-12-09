package com.epam.courses.jf.practice.igulyaev.second;

import com.epam.courses.jf.practice.common.second.ITestableTask9;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class TestableTask9 implements ITestableTask9 {
    @Override
    public HashSet<String> getUniqueWords(File input) {
        Set<String> treeSet = new TreeSet<>(String::compareToIgnoreCase);
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(input))) {
            for(String str = bufferedReader.readLine(); str != null; str = bufferedReader.readLine()){
                Arrays.stream(str.split(" ")).forEach(treeSet::add);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return new HashSet<>(treeSet);
    }
}
