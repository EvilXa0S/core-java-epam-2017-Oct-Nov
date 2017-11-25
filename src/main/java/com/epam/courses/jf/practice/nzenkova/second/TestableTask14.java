package com.epam.courses.jf.practice.nzenkova.second;

import com.epam.courses.jf.practice.common.second.ITestableTask14;
import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;


public class TestableTask14 implements ITestableTask14 {
    @Override
    public <T extends Number> ITestableTask14.INumberCollection<T> createCollection(Class<T> required) {
        return new NumberCollection<T>();
    }

    class NumberCollection<T extends Number> implements ITestableTask14.INumberCollection {

        private List<T> list = new ArrayList<>();

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

        @NotNull
        @Override
        public Iterator iterator() {
            return list.iterator();
        }

        @NotNull
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
        public boolean addAll(@NotNull Collection c) {
            return list.addAll(c);
        }

        @Override
        public void clear() {
            list.clear();
        }

        @Override
        public boolean retainAll(@NotNull Collection c) {
            return list.retainAll(c);
        }

        @Override
        public boolean removeAll(@NotNull Collection c) {
            return list.removeAll(c);
        }

        @Override
        public boolean containsAll(@NotNull Collection c) {
            return list.containsAll(c);
        }

        @NotNull
        @Override
        public Object[] toArray(@NotNull Object[] a) {
            return list.toArray(a);
        }
    }
}
