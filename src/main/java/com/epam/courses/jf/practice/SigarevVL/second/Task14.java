package com.epam.courses.jf.practice.SigarevVL.second;

import com.epam.courses.jf.practice.common.second.ITestableTask14;

import java.util.*;

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
        return new INumberCollectionImpl<>();
    }

    /**
     * Коллекция для хранения чисел.
     */
    public class INumberCollectionImpl <T extends Number> extends ArrayList<T> implements  INumberCollection<T> {

        List<T> list = new ArrayList<>();
        @Override
        public T nearest(T value) {
            double subMod = Math.abs(list.get(0).doubleValue() - value.doubleValue());
            T minimal = list.get(0);
            Iterator<T> iterator = list.iterator();
            while (iterator.hasNext()) {
                if (Math.abs(iterator.next().doubleValue() - value.doubleValue()) < subMod) {
                    minimal = iterator.next();
                }
            }
            return minimal ;

        }
    }
}
