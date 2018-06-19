package com.epam.courses.jf.practice.sskovalevskiy.second;

import com.epam.courses.jf.practice.common.second.ITestableTask1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 24.10.2017.
 *
 * Ввести строки из файла, записать в список.
 * Вывести строки в файл в обратном порядке.
 */
public class ITestableTask1Impl implements ITestableTask1 {
    /**
     * Читает строки из исходного файла и сохраняет в выходной в обратном порядке.
     * @param input Файл с входными данными.
     * @param output Файл с выходными данными.
     * @return Список строк, прочитанных из входного файла в прямом порядке.
     */
    @Override
    public List<String> reverseFile(File input, File output) {

        ArrayList<String> strings = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(input))) {
            while (reader.ready()) {
                strings.add(reader.readLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try (BufferedWriter writer = new BufferedWriter(new FileWriter(output))) {
            for (int i = strings.size() - 1; i >= 0; i--) {
                writer.write(strings.get(i) + System.getProperty("line.separator"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strings;
    }
}
