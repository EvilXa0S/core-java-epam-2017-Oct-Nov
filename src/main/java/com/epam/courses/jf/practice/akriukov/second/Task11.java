package com.epam.courses.jf.practice.akriukov.second;

import com.epam.courses.jf.practice.common.second.ITestableTask11;

import java.util.*;

/**
 * В кругу стоят N человек.
 * На каждой итерации цикла выбывает человек (через одного, начиная с первого), пока не останется единственный.
 * Выбывание из круга означает удаление элемента из исходного списка.
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
        return removeOneOverOne(peoples);
    }

    /**
     * Выполняет эмуляцию поставленной задачи.
     * @param peoples Список с именами участников.
     * @return Имя последнего оставшегося.
     */
    @Override
    public String emulate(LinkedList<String> peoples) {
        return removeOneOverOne(peoples);
    }

    public static String removeOneOverOne(List<String> list) {

        while (list.size() > 1) {
            Iterator iterator = list.iterator();
            while (iterator.hasNext()) {
                iterator.next();
                if (iterator.hasNext()) {
                    iterator.next();
                    iterator.remove();
                }
            }
        }

        return list.get(0);
    }
}
