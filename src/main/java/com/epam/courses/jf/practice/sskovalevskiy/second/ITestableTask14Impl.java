package com.epam.courses.jf.practice.sskovalevskiy.second;

import com.epam.courses.jf.practice.common.second.ITestableTask14;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by asus on 01.11.2017.
 *
 * На базе коллекций реализовать структуру хранения чисел с поддержкой следующих операций:
 * 1) Добавление/удаление числа.
 * 2) Поиск числа, наиболее близкого к заданному (т.е. модуль разницы минимален).
 */
public class ITestableTask14Impl implements ITestableTask14 {
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

        /**
         * @param value Эталонное значение.
         * @return Число, наиболее близкое к заданному.
         */
        @Override
        public T nearest(T value) {

            return stream()
                    .min(Comparator.comparingDouble(o -> Math.abs(o.doubleValue() - value.doubleValue())))
                    .get();
        }
    }
}
