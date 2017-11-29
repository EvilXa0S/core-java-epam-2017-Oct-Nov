package com.epam.courses.jf.practice.SigarevVL.second;

import com.epam.courses.jf.practice.common.second.ITestableTask1;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Enter the lines from the file, write to the list.
 * Output the lines in the file in reverse order.
 */
public class Task1 implements ITestableTask1 {

    /**
     * Reads the lines from the source file
     * and stores them in the output in reverse order.
     *
     * @param input  File with input data.
     * @param output A file with output data.
     * @return List of strings read from the input file
     * in the direct order.
     */
    @Override
    public List<String> reverseFile(File input, File output) {

        List<String> list = new ArrayList<>();
        String string;

        try (BufferedReader bufferedReader =
                     new BufferedReader(new FileReader(input))) {

            while ((string = bufferedReader.readLine()) != null) {
                list.add(string);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter bufferedWriter =
                     new BufferedWriter(new FileWriter(output))) {

            for (int i = list.size() - 1; i >= 0; i--) {
                bufferedWriter.append(list.get(i)).append("\n");
                bufferedWriter.flush();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }
}
