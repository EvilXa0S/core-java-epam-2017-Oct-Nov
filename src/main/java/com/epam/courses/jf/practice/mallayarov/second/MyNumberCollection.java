package com.epam.courses.jf.practice.mallayarov.second;

import com.epam.courses.jf.practice.common.second.ITestableTask14;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Коллекция для хранения чисел.
 */
public class MyNumberCollection<T extends Number> implements ITestableTask14.INumberCollection<T> {

    /**
     * Delegate all default methods to ArrayList
     */
    private ArrayList<T> data;

    public MyNumberCollection() {
        data = new ArrayList<>();
    }

    @Override
    public Iterator<T> iterator() {
        return data.iterator();
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return (T1[]) data.toArray();
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

    /**
     * @param value Эталонное значение.
     * @return Число, наиболее близкое к заданному.
     */
    @Override
    public T nearest(T value) {
        T nearest = (T) ((Double) Double.MAX_VALUE);
        double lowestDelta = Double.MAX_VALUE;

        /*
        for each number find delta between number and value
        if the lowest delta is less than current delta then assign new lowest delta
        and remember this number
         */
        for (T number : data) {
            double delta = Math.abs((number.doubleValue() - value.doubleValue()));
            if (lowestDelta > delta) {
                lowestDelta = delta;
                nearest = number;
            }
        }

        return nearest;
    }
}