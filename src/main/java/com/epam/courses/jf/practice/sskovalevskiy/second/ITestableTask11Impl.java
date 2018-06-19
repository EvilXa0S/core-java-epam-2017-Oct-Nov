package com.epam.courses.jf.practice.sskovalevskiy.second;

import com.epam.courses.jf.practice.common.second.ITestableTask11;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by asus on 01.11.2017.
 *
 * В кругу стоят N человек.
 * На каждой итерации цикла выбывает человек (через одного, начиная с первого), пока не останется единственный.
 * Разработать два решения, моделирующие процесс.
 * Первое должно использовать класс ArrayList, а второе – LinkedList.

 */
public class ITestableTask11Impl implements ITestableTask11 {

    /**
     * Выполняет эмуляцию поставленной задачи.
     * @param peoples Список с именами участников.
     * @return Имя последнего оставшегося.
     */
    @Override
    public String emulate(ArrayList<String> peoples) {
        return removeEvery2ndElement(peoples);
    }

    /**
     * Выполняет эмуляцию поставленной задачи.
     * @param peoples Список с именами участников.
     * @return Имя последнего оставшегося.
     */
    @Override
    public String emulate(LinkedList<String> peoples) {
        return removeEvery2ndElement(peoples);
    }

    private String removeEvery2ndElement(List<String> peoples){
        boolean previousElementWasRemoved = false;
        while (peoples.size() > 1){
            Iterator iterator = peoples.iterator();
            while (iterator.hasNext()){
                iterator.next();
                if (!previousElementWasRemoved){
                    iterator.remove();
                    previousElementWasRemoved = true;
                } else {
                    previousElementWasRemoved = false;
                }
            }
        }
        return peoples.get(0);
    }
}
