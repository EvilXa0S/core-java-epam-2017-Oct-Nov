package com.epam.courses.jf.practice.asgalantsev.second;

import com.epam.courses.jf.practice.common.second.ITestableTask11;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * В кругу стоят N человек.
 * На каждой итерации цикла выбывает человек (через одного, начиная с первого), пока не останется единственный.
 * Разработать два решения, моделирующие процесс.
 * Первое должно использовать класс ArrayList, а второе – LinkedList.
 */

public class Task11 implements ITestableTask11 {

    /**
     * Выполняет эмуляцию поставленной задачи.
     * @param peoples Список с именами участников.
     * @return Имя последнего оставшегося.
     */

    @Override
    public String emulate(ArrayList<String> peoples) {
        int counter = 0;
        while(peoples.size() != 1) {
            Iterator<String> it = peoples.iterator();
            while (it.hasNext()) {
                String s = it.next();
                if (counter % 2 == 0)
                    it.remove();
                counter++;
            }
        }
        return peoples.get(0);
    }

    /**
     * Выполняет эмуляцию поставленной задачи.
     * @param peoples Список с именами участников.
     * @return Имя последнего оставшегося.
     */

    @Override
    public String emulate(LinkedList<String> peoples) {
        int counter = 0;
        while(peoples.size() != 1) {
            Iterator<String> it = peoples.iterator();
            while (it.hasNext()) {
                String s = it.next();
                if (counter % 2 == 0)
                    it.remove();
                counter++;
            }
        }
        return peoples.get(0);
    }
}
