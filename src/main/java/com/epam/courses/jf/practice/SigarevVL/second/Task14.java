package com.epam.courses.jf.practice.SigarevVL.second;

import com.epam.courses.jf.practice.common.second.ITestableTask14;

import java.util.NavigableSet;
import java.util.TreeSet;

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
    public class INumberCollectionImpl <T extends Number> extends TreeSet<T> implements  INumberCollection<T> {

        NavigableSet<T> set = new TreeSet<>();
        @Override
        public T nearest(T value) {
            double bigger;
            double less;
            if (set.ceiling(value) != null && set.floor(value) != null) {
                bigger = set.ceiling(value).doubleValue() - value.doubleValue();
                less = value.doubleValue() - set.floor(value).doubleValue();
                if ( bigger >= less ) {
                    return set.floor(value);
                } else {
                    return set.ceiling(value);
                }
            } else if (set.floor(value) == null && set.ceiling(value) != null) {
                return set.ceiling(value);
            } else if (set.floor(value) != null) {
                return set.floor(value);
            } else {
                return null;
            }

        }
    }
}
