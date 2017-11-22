package com.epam.courses.jf.practice.vakhonin.second;

import com.epam.courses.jf.practice.common.second.ITestableTask9;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by igorvahonin on 13.11.17.
 */
public class Task9 implements ITestableTask9{



    @Override
    public HashSet<String> getUniqueWords(File input) {
        HashSet<String> set = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(input))){
            String str;
            String[] strArr;
            while((str = reader.readLine()) != null){
                strArr = str.split("\\s");
                for(String s: strArr){
                    set.add(s.toLowerCase());
                }
            }

            return set;

        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return null;
    }
}
