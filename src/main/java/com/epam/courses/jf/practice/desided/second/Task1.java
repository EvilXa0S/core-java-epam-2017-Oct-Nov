package com.epam.courses.jf.practice.desided.second;


import com.epam.courses.jf.practice.common.second.ITestableTask1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Ввести строки из файла, записать в список.
 * Вывести строки в файл в обратном порядке.
 */
public class Task1 implements ITestableTask1 {


    /**
     * Читает строки из исходного файла и сохраняет в выходной в обратном порядке.
     *
     * @param input  Файл с входными данными.
     * @param output Файл с выходными данными.
     * @return Список строк, прочитанных из входного файла в прямом порядке.
     */
    @Override
    public List<String> reverseFile(File input, File output) throws IOException {

        List<String> arr = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(input))) {
            String s;
            while ((s = reader.readLine()) != null) {
                arr.add(s);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(output))) {
            for (int i = arr.size() - 1; i >= 0; i--) {
                writer.write(arr.get(i));
                writer.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arr;
    }
}
