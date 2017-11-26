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
        @Override
        public T nearest(Number value) {

            double subMod = Math.abs(list.get(0).doubleValue() - value.doubleValue());
            T minimal = list.get(0);
            for (T element : list) {

                if (abs(element.doubleValue() - value.doubleValue()) < subMod) {
                    subMod = abs(element.doubleValue() - value.doubleValue());
                    minimal = element;
                }
            }
            return minimal ;

        }

        @Override
        public int size() {
            return list.size();
        }

        @Override
        public boolean isEmpty() {
            return list.isEmpty();
        }

        @Override
        public boolean contains(Object o) {
            return list.contains(o);
        }

        @Override
        public Iterator<T> iterator() {
            return list.iterator();
        }

        @Override
        public Object[] toArray() {
            return list.toArray();
        }

        @Override
        public <T1> T1[] toArray(T1[] a) {
            return list.toArray(a);
        }

        @Override
        public boolean add(T t) {
            return list.add(t);
        }

        @Override
        public boolean remove(Object o) {
            return list.remove(o);
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            return list.containsAll(c);
        }

        @Override
        public boolean addAll(Collection<? extends T> c) {
            return list.addAll(c);
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            return list.removeAll(c);
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            return retainAll(c);
        }

        @Override
        public void clear() {
            list.clear();
        }
    }
}
