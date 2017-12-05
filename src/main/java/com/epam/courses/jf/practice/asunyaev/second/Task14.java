package com.epam.courses.jf.practice.asunyaev.second;

import com.epam.courses.jf.practice.common.second.ITestableTask14;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Task14 implements ITestableTask14 {

    @Override
    public <T extends Number> INumberCollection<T> createCollection(Class<T> required) {
        return new NumberCollection<>();
    }

    class NumberCollection<T extends Number> implements INumberCollection<T> {

        ArrayList<T> numbers;

        public NumberCollection() {
            numbers = new ArrayList<>();
        }

        @Override
        public void clear() {
            numbers.clear();
        }

        @Override
        public Object[] toArray() {
            return numbers.toArray();
        }

        @Override
        public <T> T[] toArray(T[] a) {
            return numbers.toArray(a);
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            return numbers.removeAll(c);
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            return numbers.retainAll(c);
        }

        @Override
        public Iterator<T> iterator() {
            return numbers.iterator();
        }

        @Override
        public boolean add(T t) {
            return numbers.add(t);
        }

        @Override
        public boolean addAll(Collection<? extends T> c) {
            return numbers.containsAll(c);
        }

        @Override
        public boolean contains(Object o) {
            return numbers.contains(o);
        }

        @Override
        public int size() {
            return numbers.size();
        }

        @Override
        public boolean isEmpty() {
            return numbers.isEmpty();
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            return numbers.containsAll(c);
        }

        @Override
        public boolean remove(Object o) {
            return numbers.remove(o);
        }

        public T nearest(T value) {
            if (size() == 0) {
                return null;
            }
            T result = numbers.get(0);
            double minDist = Math.abs(result.doubleValue() - value.doubleValue());
            for (T number : numbers) {
                if (Math.abs(number.doubleValue() - value.doubleValue()) < minDist) {
                    minDist = Math.abs(number.doubleValue() - value.doubleValue());
                    result = number;
                }
            }
            return result;
        }
    }
}
