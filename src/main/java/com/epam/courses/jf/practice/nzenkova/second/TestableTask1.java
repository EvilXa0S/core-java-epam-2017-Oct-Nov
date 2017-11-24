package com.epam.courses.jf.practice.nzenkova.second;

import com.epam.courses.jf.practice.common.second.ITestableTask1;


import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Input strings from a file, write strings in a list
 * Output strings to a file in reverse order
 */
public class TestableTask1 implements ITestableTask1{
    @Override
    /**
     * @param input A input file
     * @param output A output file
     * @return A list of strings in direct order
     */
    public List<String> reverseFile(File input, File output) {
        List<String> list = new ArrayList<>();

        try (BufferedReader in = new BufferedReader(new FileReader(input))) {
            String line;
            while ((line = in.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Collections.reverse(list);

        try (BufferedWriter out = new BufferedWriter(new FileWriter(output))) {
            for (String string : list) {
                out.write(string + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Collections.reverse(list);

        return list;
    }
}
