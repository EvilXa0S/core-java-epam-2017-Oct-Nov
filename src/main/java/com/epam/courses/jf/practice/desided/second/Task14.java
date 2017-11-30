package com.epam.courses.jf.practice.desided.second;

import com.epam.courses.jf.practice.common.second.ITestableTask14;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Task14 implements ITestableTask14{
    @Override
    public <T extends Number> INumberCollection<T> createCollection(Class<T> required) {
        return new NumberCollection<>();
    }

    private class NumberCollection<T extends Number> implements INumberCollection<T>{

        private ArrayList<T> list = new ArrayList<>();
        @Override
        public T nearest(T value) {
            if (list.isEmpty()){
                return null;
            }

            T nearest = list.get(0);
            double dValue = value.doubleValue();
            double diff = Math.abs(nearest.doubleValue());

            for (T t : list) {
                if (Math.abs(dValue - t.doubleValue()) < diff) {
                    diff = Math.abs(dValue - t.doubleValue());
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
