package com.epam.courses.jf.practice.vkostin.second;

import com.epam.courses.jf.practice.common.second.ITestableTask14;

import java.util.*;

public class Task14 implements ITestableTask14 {

    @Override
    public <T extends Number> INumberCollection<T> createCollection(Class<T> required) {
        return new NumberCollection<T>();
    }


    private static class NumberCollection<T extends Number> implements INumberCollection<T> {

        List<T> numberCollection = new ArrayList<>();

        @Override
        public T nearest(T value) {
            T mostSimilarNumber = numberCollection.get(0);
            for (T num : numberCollection) {
                if (Math.abs(num.doubleValue() - value.doubleValue())
                        < Math.abs(mostSimilarNumber.doubleValue() - value.doubleValue())) {
                    mostSimilarNumber = num;
                }
            }
            return mostSimilarNumber;
        }

        @Override
        public int size() {
            return numberCollection.size();
        }

        @Override
        public boolean isEmpty() {
            return numberCollection.isEmpty();
        }

        @Override
        public boolean contains(Object o) {
            return numberCollection.contains(o);
        }

        @Override
        public boolean add(T t) {
            return numberCollection.add(t);
        }

        @Override
        public boolean remove(Object o) {
            return numberCollection.remove(o);
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            return numberCollection.containsAll(c);
        }

        @Override
        public boolean addAll(Collection<? extends T> c) {
            return numberCollection.addAll(c);
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            return numberCollection.removeAll(c);
        }
        
        @Override
        public String toString() {
            return numberCollection.toString();
        }

        @Override
        public Object[] toArray() {
            return numberCollection.toArray();
        }

        @Override
        public <T1> T1[] toArray(T1[] a) {
            return numberCollection.toArray(a);
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            return numberCollection.retainAll(c);
        }

        @Override
        public Iterator<T> iterator() {
            return numberCollection.iterator();
        }

        @Override
        public void clear() {
            numberCollection.clear();
        }
    }
}
