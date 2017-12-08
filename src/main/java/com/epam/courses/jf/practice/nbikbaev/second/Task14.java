package com.epam.courses.jf.practice.nbikbaev.second;

import com.epam.courses.jf.practice.common.second.ITestableTask14;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Task14 implements ITestableTask14 {
    @Override
    public <T extends Number> INumberCollection<T> createCollection(Class<T> required) {
        return new NumberCollection<>(required);
    }

    class NumberCollection<T extends Number> implements INumberCollection<T> {

        private List<T> data = new ArrayList<>();
        private Class<T> clazz;

        public NumberCollection(Class<T> clazz) {
            this.clazz = clazz;
        }

        @Override
        public T nearest(T value) {
            T nearest = data.get(0);
            for (T number : data) {
                if (clazz == Double.class) {
                    if (Math.abs(number.doubleValue() - value.doubleValue())
                            < Math.abs(nearest.doubleValue() - value.doubleValue())) {
                        nearest = number;
                    }
                }
                if (clazz == Integer.class) {
                    if (Math.abs(number.intValue() - value.intValue())
                            < Math.abs(nearest.intValue() - value.intValue())) {
                        nearest = number;
                    }
                }
                if (clazz == Long.class) {
                    if (Math.abs(number.longValue() - value.longValue())
                            < Math.abs(nearest.longValue() - value.longValue())) {
                        nearest = number;
                    }
                }
                if (clazz == Short.class) {
                    if (Math.abs(number.shortValue() - value.shortValue())
                            < Math.abs(nearest.shortValue() - value.shortValue())) {
                        nearest = number;
                    }
                }
                if (clazz == Float.class) {
                    if (Math.abs(number.floatValue() - value.floatValue())
                            < Math.abs(nearest.floatValue() - value.floatValue())) {
                        nearest = number;
                    }
                }
            }
            return nearest;
        }

        @Override
        public int size() {
            return data.size();
        }

        @Override
        public boolean isEmpty() {
            return data.isEmpty();
        }

        @Override
        public boolean contains(Object o) {
            return data.contains(o);
        }

        @Override
        public Iterator<T> iterator() {
            return data.iterator();
        }

        @Override
        public Object[] toArray() {
            return data.toArray();
        }

        @Override
        public <T1> T1[] toArray(T1[] a) {
            return data.toArray(a);
        }

        @Override
        public boolean add(T t) {
            return data.add(t);
        }

        @Override
        public boolean remove(Object o) {
            return data.remove(o);
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            return data.containsAll(c);
        }

        @Override
        public boolean addAll(Collection<? extends T> c) {
            return data.addAll(c);
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            return data.removeAll(c);
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            return data.retainAll(c);
        }

        @Override
        public void clear() {
            data.clear();
        }
    }

}
