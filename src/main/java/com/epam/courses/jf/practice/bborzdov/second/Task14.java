package com.epam.courses.jf.practice.bborzdov.second;

import com.epam.courses.jf.practice.common.second.ITestableTask14;

/**
 * Created by bogdan on 20.11.17.
 */

/**
 * На базе коллекций реализовать структуру хранения чисел с поддержкой следующих операций:
 *       1) Добавление/удаление числа.
 *       2) Поиск числа, наиболее близкого к заданному (т.е. модуль разницы минимален).
 */
public class Task14 implements ITestableTask14{
    /** Создаёт объект коллекции для хранения чисел с поддержкой операций добавления/удаления и поиска числа.
     * @param required Тип, которым типизируется создаваемая коллекция.
     * @return Коллекция для хранения чисел с поддержкой операций добавления/удаления и поиска числа.
     */
    @Override
    public <T extends Number> INumberCollection<T> createCollection(Class<T> required) {
        return new NumberCollection();
    }

}
