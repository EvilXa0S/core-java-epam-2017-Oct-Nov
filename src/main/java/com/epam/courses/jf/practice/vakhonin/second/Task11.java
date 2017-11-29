package com.epam.courses.jf.practice.vakhonin.second;

import com.epam.courses.jf.practice.common.second.ITestableTask11;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

///**
// * В кругу стоят N человек.
// * На каждой итерации цикла выбывает человек (через одного, начиная с первого), пока не останется единственный.
// * Разработать два решения, моделирующие процесс.
// * Первое должно использовать класс ArrayList, а второе – LinkedList.
// */

public class Task11 implements ITestableTask11 {

//    /**
//     * Выполняет эмуляцию поставленной задачи.
//     * @param peoples Список с именами участников.
//     * @return Имя последнего оставшегося.
//     */

    @Override
    public String emulate(ArrayList<String> peoples){
        Iterator<String> it;
        int shift = 0;

        while(peoples.size() != 1){
            it = peoples.iterator();

            if(shift == 1){
                it.next();
                shift--;
            }

            while(it.hasNext()){
                if(shift == 0){
                    it.remove();
                    shift++;
                }
                else{
                    it.next();
                    shift--;
                }
            }
        }

        return peoples.get(0);
    }

//    /**
//     * Выполняет эмуляцию поставленной задачи.
//     * @param peoples Список с именами участников.
//     * @return Имя последнего оставшегося.
//     */

    @Override
    public String emulate(LinkedList<String> peoples){
        Iterator<String> it;
        int shift = 0;

        while(peoples.size() != 1){
            it = peoples.iterator();

            if(shift == 1){
                it.next();
                shift--;
            }

            while(it.hasNext()){
                if(shift == 0){
                    it.remove();
                    shift++;
                }
                else{
                    it.next();
                    shift--;
                }
            }
        }

        return peoples.get(0);
    }
}
