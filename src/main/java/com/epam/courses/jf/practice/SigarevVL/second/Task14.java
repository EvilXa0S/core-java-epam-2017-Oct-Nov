package com.epam.courses.jf.practice.SigarevVL.second;

import com.epam.courses.jf.practice.common.second.ITestableTask14;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static java.lang.Math.abs;

/**
 * On the basis of collections the structure of storage of numbers
 * with support of following operations is realized:
 * 1) Add and delete operation.
 * 2)Search for the number closest to the given.
 */
public class Task14 implements ITestableTask14 {

    /**
     * @param required The type by which the collection is typed.
     * @return A collection for storing numbers with support for
     * adding, removing operations and searching for numbers.
     */

    @Override
    public <T extends Number> INumberCollection<T> createCollection(Class<T> required) {
        return new INumberCollectionImpl<T>();
    }

    /**
     * Collection for storing numbers.
     */
    private class INumberCollectionImpl<T extends Number>
            extends ArrayList<T> implements INumberCollection<T> {

        List<T> list = new ArrayList<>();

        /**
         * @param value Reference value.
         * @return The number closest to the given.
         */
        @Override
        public T nearest(Number value) {
            double subMod = Math.abs(list.get(0).doubleValue()
                    - value.doubleValue());

            T minimal = list.get(0);
            for (T element : list) {

                if (abs(element.doubleValue() - value.doubleValue()) < subMod) {
                    subMod = abs(element.doubleValue() - value.doubleValue());
                    minimal = element;
                }
            }
            return minimal;

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
//
//        @Override
//        public boolean containsAll(Collection<?> c) {
//            return list.containsAll(c);
//        }

        @Override
        public boolean addAll(Collection<? extends T> c) {
            return list.addAll(c);
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            return list.removeAll(c);
        }
//
//        @Override
//        public boolean retainAll(Collection<?> c) {
//            return retainAll(c);
//        }

        @Override
        public void clear() {
            list.clear();
        }
    }
}
