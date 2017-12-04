package com.epam.courses.jf.practice.desided.second;

import com.epam.courses.jf.practice.common.second.ITestableTask11;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;


/**
 * В кругу стоят N человек.
 * На каждой итерации цикла выбывает человек (через одного, начиная с первого), пока не останется единственный.
 * Выбывание из круга означает удаление элемента из исходного списка.
 * Разработать два решения, моделирующие процесс.
 * Первое должно использовать класс ArrayList, а второе – LinkedList.
 */
public class Task11 implements ITestableTask11{


    /**
     * Выполняет эмуляцию поставленной задачи.
     *
     * @param peoples Список с именами участников.
     * @return Имя последнего оставшегося.
     */
    @Override
    public String emulate(ArrayList<String> peoples) {

        if (peoples == null || peoples.isEmpty()) {
            throw new IllegalArgumentException();
        }

        int del = 0;
        while (peoples.size() != 1) {
            peoples.remove(del);
            del++;
            if (del >= peoples.size()) {
                del %= peoples.size();
            }
        }

        return peoples.get(0);
    }


    /**
     * Выполняет эмуляцию поставленной задачи.
     *
     * @param peoples Список с именами участников.
     * @return Имя последнего оставшегося.
     */
    @Override
    public String emulate(LinkedList<String> peoples) {

        if (peoples == null || peoples.isEmpty()) {
            throw new IllegalArgumentException();
        }

        boolean check = true;
        while (peoples.size() != 1){
            Iterator<String> iterator = peoples.iterator();
            while (iterator.hasNext()) {
                iterator.next();

                if (check) {
                    iterator.remove();
                }
                check = !check;
            }
        }

        return peoples.getFirst();
    }
}
