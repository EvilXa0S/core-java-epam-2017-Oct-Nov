package com.epam.courses.jf.practice.NikDevyatyi.second;

import com.epam.courses.jf.practice.common.second.ITestableTask1;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class TestableTask1 implements ITestableTask1 {
    @Override
    public List<String> reverseFile(File input, File output) {
        List<String> result = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(input))){
            String line;
            while ((line = br.readLine()) != null) {
                //System.out.println(line);
                result.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();

        }
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(output))){
            Collections.reverse(result);
            for(String lines: result){
                writer.write(lines);
                writer.newLine();
                //System.out.println(lines);
            }

        }catch (IOException e) {
            e.printStackTrace();
        }
        Collections.reverse(result);
        return result;
    }
}
