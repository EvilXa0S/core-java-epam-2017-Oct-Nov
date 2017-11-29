package com.epam.courses.jf.practice.common.second;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Интерфейс для юнит-тестирования задания №11.
 *
 * В кругу стоят N человек.
 * На каждой итерации цикла выбывает человек (через одного, начиная с первого), пока не останется единственный.
 * Разработать два решения, моделирующие процесс.
 * Первое должно использовать класс ArrayList, а второе – LinkedList.
 */
public interface ITestableTask11 extends ITaskStorage.ITestableTask {

    /**
     * Выполняет эмуляцию поставленной задачи.
     * @param peoples Список с именами участников.
     * @return Имя последнего оставшегося.
     */
    String emulate(ArrayList<String> peoples);

    /**
     * Выполняет эмуляцию поставленной задачи.
     * @param peoples Список с именами участников.
     * @return Имя последнего оставшегося.
     */
    String emulate(LinkedList<String> peoples) throws InterruptedException;
}