package com.epam.courses.jf.practice.bborzdov;

import com.epam.courses.jf.practice.common.second.ITestableTask1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.List;
/**
 * Created by bogdan on 14.11.17.
 */
public class Task1 implements ITestableTask1 {
    @Override
    public List<String> reverseFile(File input, File output) {
        List<String> string = Collections.emptyList();
        try {
            string = Files.readAllLines(input.toPath());
            Collections.reverse(string);
            Files.write(output.toPath(),string, StandardOpenOption.WRITE);
            Collections.reverse(string);
        }catch (IOException e ){
            System.out.println("Problems with i/o file" + e.getMessage());
        }
        return string;
    }

}
