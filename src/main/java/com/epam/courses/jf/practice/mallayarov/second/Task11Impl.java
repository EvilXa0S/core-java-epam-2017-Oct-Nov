package com.epam.courses.jf.practice.mallayarov.second;

import com.epam.courses.jf.practice.common.second.ITestableTask11;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * В кругу стоят N человек.
 * На каждой итерации цикла выбывает человек (через одного, начиная с первого), пока не останется единственный.
 * Разработать два решения, моделирующие процесс.
 * Первое должно использовать класс ArrayList, а второе – LinkedList.
 */

public class Task11Impl implements ITestableTask11 {

    // https://en.wikipedia.org/wiki/Josephus_problem

    /**
     *  Count for each step
     */
    public static final int K = 2;

    /**
     * Выполняет эмуляцию поставленной задачи.
     * @param peoples Список с именами участников.
     * @return Имя последнего оставшегося.
     */
    @Override
    public String emulate(ArrayList<String> peoples) {
        int pointer = 0;

        while (peoples.size() > 1) {
            peoples.remove(pointer);
            pointer = (pointer + K - 1) % peoples.size();

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
        int pointer = 0;

        while (peoples.size() > 1) {
            Iterator<String> iterator = peoples.iterator();
            while (iterator.hasNext()) {
                iterator.next();
                if (pointer % K == 0) {
                    iterator.remove();
                }
                ++pointer;
            }
        }

        return peoples.getFirst();
    }
}
