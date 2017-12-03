package com.epam.courses.jf.practice.nzenkova.second;


import com.epam.courses.jf.practice.common.second.ITestableTask14;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Realize the structure of the numbers storage, which is based on Java Framework Collections
 * and supports the next operations:
 *                                  1. Add/Delete a number (the numbers could be not unique)
 *                                  2. Find a number, which is the nearest to the given number (|the difference| = min).
 */
public class TestableTask14 implements ITestableTask14{
    /**
     *
     * @param required The type for the collection being created.
     * @return The resultant collection.
     */
    @Override
    public <T extends Number> ITestableTask14.INumberCollection<T> createCollection(Class<T> required) {
        return new NumberCollection<T>();
    }

    class NumberCollection<T extends Number> implements ITestableTask14.INumberCollection {

        private List<T> list = new ArrayList<>();

        /**
         * @param value The reference value.
         * @return The number, which is the nearest to the given number, or null, if the collection is empty.
         */
        @Override
        public Number nearest(Number value) {
            Number nearest = null;
            if (value.getClass() != Float.class) {
                Double min = Double.MAX_VALUE;
                for (T t : list) {
                    if (Double.compare(Math.abs(t.doubleValue() - value.doubleValue()), min) <= 0) {
                        min = Math.abs(t.doubleValue() - value.doubleValue());
                        nearest = t;
                    }
                }
            } else {
                for (T t : list) {
                    Float min = Float.MAX_VALUE;
                    if (Float.compare(Math.abs(t.floatValue() - value.floatValue()), min) <= 0) {
                        min = Math.abs(t.floatValue() - value.floatValue());
                        nearest = t;
                    }
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
        public Iterator iterator() {
            return list.iterator();
        }
        @Override
        public Object[] toArray() {
            return list.toArray();
        }


        @Override
        public boolean add(Object o) {
            try {
                return list.add((T) o);
            } catch (ClassCastException ex) {
                return false;
            }
        }


        @Override
        public boolean remove(Object o) {
            return list.remove(o);
        }


        @Override
        public boolean addAll(Collection c) {
            return list.addAll(c);
        }


        @Override
        public void clear() {
            list.clear();
        }


        @Override
        public boolean retainAll(Collection c) {
            return list.retainAll(c);
        }

        @Override
        public boolean removeAll(Collection c) {
            return list.removeAll(c);
        }


        @Override
        public boolean containsAll( Collection c) {
            return list.containsAll(c);
        }


        @Override
        public Object[] toArray(Object[] a) {
            return list.toArray(a);
        }
    }
}
