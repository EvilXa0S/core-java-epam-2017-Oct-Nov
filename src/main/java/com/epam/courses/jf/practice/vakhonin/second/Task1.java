package com.epam.courses.jf.practice.vakhonin.second;

import com.epam.courses.jf.practice.common.second.ITestableTask1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Ввести строки из файла, записать в список.
 * Вывести строки в файл в обратном порядке.
 */

public class Task1 implements ITestableTask1 {

    /**
     * Читает строки из исходного файла и сохраняет в выходной в обратном порядке.
     * @param input Файл с входными данными.
     * @param output Файл с выходными данными.
     * @return Список строк, прочитанных из входного файла в прямом порядке.
     */

    @Override
    public List<String> reverseFile(File input, File output) {

        List<String> strings = new ArrayList<>();
        String string;

        try (BufferedReader reader = new BufferedReader(new FileReader(input))) {
            while ((string = reader.readLine()) != null) {
                strings.add(string);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        ListIterator<String> iterator = strings.listIterator(strings.size());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(output))) {
            while(iterator.hasPrevious()){
                writer.write(iterator.previous());
                writer.write("\n");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return strings;
    }
}
