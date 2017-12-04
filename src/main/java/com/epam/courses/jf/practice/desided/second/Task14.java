package com.epam.courses.jf.practice.desided.second;

import com.epam.courses.jf.practice.common.second.ITestableTask14;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;


/**
 * Реализовать структуру хранения чисел на базе Java Framework Collections с поддержкой следующих операций:
 * 1) Добавление/удаление числа (уникальность хранимых элементов не обязательна).
 * 2) Поиск числа, наиболее близкого к заданному (т.е. модуль разницы
 */
public class Task14 implements ITestableTask14{


    /**
     * @param required Тип, которым типизируется создаваемая коллекция.
     * @return Коллекция для хранения чисел с поддержкой операций добавления/удаления и поиска числа.
     */
    @Override
    public <T extends Number> INumberCollection<T> createCollection(Class<T> required) {
        return new NumberCollection<>();
    }


    /**
     * Коллекция для хранения чисел.
     */
    class NumberCollection<T extends Number> extends ArrayList<T> implements ITestableTask14.INumberCollection<T> {

        @Override
        public T nearest(T value) {

            return stream()
                    .min(Comparator.comparingDouble(o -> Math.abs(o.doubleValue() - value.doubleValue())))
                    .get();
        }
    }
}
