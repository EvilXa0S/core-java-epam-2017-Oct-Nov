package com.epam.courses.jf.practice.bborzdov.second;

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
