package com.epam.courses.jf.practice.danilBay.second;

import com.epam.courses.jf.practice.common.second.ITestableTask1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Task1 implements ITestableTask1 {
    @Override
    public List<String> reverseFile(File input, File output) {
        List<String> inStr=new ArrayList<>();

        try (Scanner fileScanner = new Scanner(input)) {
            while (fileScanner.hasNextLine())
                inStr.add(fileScanner.nextLine());
            fileScanner.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try (FileWriter outWriter = new FileWriter(output)) {
            for(int i=inStr.size()-1; i>-1; i--)
                outWriter.write(inStr.get(i)+System.lineSeparator());
            outWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return inStr;
    }

    public static void main(String[] args) {
        File input=new File("C:\\Users\\dana\\Downloads\\Новая папка\\input.txt");
        File output=new File("C:\\Users\\dana\\Downloads\\Новая папка\\output.txt");
        Task1 d=new Task1();
        d.reverseFile(input,output);
    }
}
