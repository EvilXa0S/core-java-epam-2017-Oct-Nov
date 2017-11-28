package com.epam.courses.jf.practice.akriukov.second;

import com.epam.courses.jf.practice.common.second.ITestableTask14;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Реализовать структуру хранения чисел на базе Java Framework Collections с поддержкой следующих операций:
 *       1) Добавление/удаление числа (уникальность хранимых элементов не обязательна).
 *       2) Поиск числа, наиболее близкого к заданному (т.е. модуль разницы
 */
public class Task14 implements ITestableTask14 {
    @Override
    public <T extends Number> INumberCollection<T> createCollection(Class<T> required) {
        return new INumberCollectionImpl<T>();
    }

    /**
     * Implementation of structure for storing numbers with using Java Framework Collections
     * @param <T>
     */
    private class INumberCollectionImpl <T extends Number> extends ArrayList<T> implements  INumberCollection<T> {
        List<T> list = new ArrayList<>();

        /**
         * Method searches number with the least abs difference to input number
         * @param specifiedNum
         * @return
         */
        @Override
        public T nearest(Number specifiedNum) {
            double subMod = Math.abs(list.get(0).doubleValue() - specifiedNum.doubleValue());
            T minElement = list.get(0);
            for (T element : list) {

                if (Math.abs(element.doubleValue() - specifiedNum.doubleValue()) < subMod) {
                    subMod = Math.abs(element.doubleValue() - specifiedNum.doubleValue());
                    minElement = element;
                }
            }
            return minElement;
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
