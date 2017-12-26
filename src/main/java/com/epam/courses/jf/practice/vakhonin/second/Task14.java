package com.epam.courses.jf.practice.vakhonin.second;

import com.epam.courses.jf.practice.common.second.ITestableTask14;

import java.util.*;

/**
 * На базе коллекций реализовать структуру хранения чисел с поддержкой следующих операций:
 *       1) Добавление/удаление числа.
 *       2) Поиск числа, наиболее близкого к заданному (т.е. модуль разницы минимален).
 */

public class Task14 implements ITestableTask14{


    /**
     * Коллекция для хранения чисел.
     */

    class NumberCollection<T extends Number> implements INumberCollection<T> {
        List<T> numbersList;

        NumberCollection(){
            numbersList = new ArrayList<>();
        }

        /**
         * @param value Эталонное значение.
         * @return Число, наиболее близкое к заданному.
         */

        // but this solving not for all cases...
        @Override
        public T nearest(T value) {
            Double valueDouble = value.doubleValue();
            Double numberDouble;
            Double minDiff = Double.POSITIVE_INFINITY;
            Double diff;
            T result = null;

            for(T number: numbersList){
                numberDouble = number.doubleValue();
                diff = Math.abs(numberDouble - valueDouble);

                if(diff < minDiff){
                    minDiff = diff;
                    result = number;
                }
            }

            return result;
        }

        @Override
        public int size() {
            return numbersList.size();
        }

        @Override
        public boolean isEmpty() {
            return numbersList.isEmpty();
        }

        @Override
        public boolean contains(Object obj) {
            return numbersList.contains(obj);
        }

        @Override
        public Iterator<T> iterator() {
            return numbersList.iterator();
        }

        @Override
        public Object[] toArray() {
            return numbersList.toArray();
        }

        @Override
        public <T> T[] toArray(T[] a) {
            return numbersList.toArray(a);
        }

        @Override
        public boolean add(T t) {
            return numbersList.add(t);
        }

        @Override
        public boolean remove(Object obj) {
            return numbersList.remove(obj);
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            return numbersList.containsAll(c);
        }

        @Override
        public boolean addAll(Collection<? extends T> c) {
            return numbersList.addAll(c);
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            return numbersList.removeAll(c);
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            return numbersList.retainAll(c);
        }

        @Override
        public void clear() {
            numbersList.clear();
        }
    }

    /**
     * @param required Тип, которым типизируется создаваемая коллекция.
     * @return Коллекция для хранения чисел с поддержкой операций добавления/удаления и поиска числа.
     */

    @Override
    public <T extends Number> INumberCollection<T> createCollection(Class<T> required) {
        return new NumberCollection<>();
    }
}
