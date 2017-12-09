package com.epam.courses.jf.practice.igulyaev.second;

import com.epam.courses.jf.practice.common.second.ITestableTask14;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class TestableTask14 implements ITestableTask14 {
    @Override
    public <T extends Number> INumberCollection<T> createCollection(Class<T> required) {
        return new NumberCollection<>();
    }

    public static class NumberCollection<T extends Number> implements TestableTask14.INumberCollection<T>{
        private final List<T> list;
        NumberCollection(){
            list = new ArrayList<>();
        }
        @Override
        public T nearest(T value) {
            final BigDecimal bigValue = new BigDecimal(value.doubleValue());
            final String stringNaN = Double.toString(Double.NaN);
            final String stringPositiveInf = Double.toString(Double.POSITIVE_INFINITY);
            final String stringNegativeInf = Double.toString(Double.NEGATIVE_INFINITY);
            return list.stream().reduce((i1, i2) -> {
                if((i1 instanceof Double || i1 instanceof Float)){
                    String d1 = i1.toString();
                    if(d1.equals(stringNaN) || d1.equals(stringPositiveInf) || d1.equals(stringNegativeInf)){
                        return i2;
                    }
                }
                if(new BigDecimal(i1.doubleValue()).subtract(bigValue).abs()
                        .subtract(new BigDecimal(i2.doubleValue()).subtract(bigValue).abs()).signum() < 0){
                    return i1;
                }
                return i2;
            }).orElse(null);
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
        public <T> T[] toArray(T[] a) {
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
            return list.retainAll(c);
        }

        @Override
        public void clear() {
            list.clear();
        }
    }
}
