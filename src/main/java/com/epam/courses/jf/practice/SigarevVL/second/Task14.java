package com.epam.courses.jf.practice.SigarevVL.second;

import com.epam.courses.jf.practice.common.second.ITestableTask14;

import java.util.*;

import static java.lang.Math.abs;

/**
 * Интерфейс для юнит-тестирования задания №14.
 *
 * На базе коллекций реализовать структуру хранения чисел с поддержкой следующих операций:
 *       1) Добавление/удаление числа.
 *       2) Поиск числа, наиболее близкого к заданному (т.е. модуль разницы минимален).
 */
public class Task14 implements ITestableTask14 {

    /**
     * @param required Тип, которым типизируется создаваемая коллекция.
     * @return Коллекция для хранения чисел с поддержкой операций добавления/удаления и поиска числа.
     */

    @Override
    public <T extends Number> INumberCollection<T> createCollection(Class<T> required) {
        return new INumberCollectionImpl<T>();
    }

    /**
     * Коллекция для хранения чисел.
     */
    private class INumberCollectionImpl <T extends Number> extends ArrayList<T> implements  INumberCollection<T> {

        List<T> list = new ArrayList<>();
//        @Override
//        public T nearest(Number value) {
//            double subMod = Math.abs(list.get(0).doubleValue() - value.doubleValue());
//            T minimal = list.get(0);
//            Iterator<T> iterator = list.iterator();
//            while (iterator.hasNext()) {
//                if (Math.abs(iterator.next().doubleValue() - value.doubleValue()) < subMod) {
//                    minimal = iterator.next();
//                }
//            }
//            return minimal ;
//
//        }

        @Override
        public T nearest(T value) {
            if (list.isEmpty()) {
                return null;
            }

            T nearest = list.get(0);
            double doubleValue = value.doubleValue();
            double minDifference = abs(nearest.doubleValue());

            for (T t : list) {
                if (abs(doubleValue - t.doubleValue()) < minDifference) {
                    minDifference = abs(doubleValue - t.doubleValue());
                    nearest = t;
                }
            }

            return nearest;
        }

        @Override
        public int size() {
            return list.size();
        }

        @Override
        public Iterator<T> iterator() {
            return list.iterator();
        }

        @Override
        public boolean add(T t) {
            return list.add(t);
        }
    }
}
