package com.epam.courses.jf.practice.nzenkova.second;

import com.epam.courses.jf.practice.common.second.ITestableTask1;


import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Input strings from the file, write strings in the list.
 * Output strings to the file in reverse order.
 */
public class TestableTask1 implements ITestableTask1{

    /**
     * @param input The input file.
     * @param output The output file.
     * @return The list of the strings in direct order.
     */
    @Override
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
