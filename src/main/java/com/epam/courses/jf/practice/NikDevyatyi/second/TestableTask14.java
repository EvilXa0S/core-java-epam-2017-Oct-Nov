package com.epam.courses.jf.practice.NikDevyatyi.second;

import com.epam.courses.jf.practice.common.second.ITestableTask14;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class TestableTask14 implements ITestableTask14 {
    @Override
    public <T extends Number> INumberCollection<T> createCollection(Class<T> required) {

        return new NumberCollection<>();
    }

    class NumberCollection<T extends Number> implements ITestableTask14.INumberCollection<T> {
        ArrayList<T> newCollection;

        public NumberCollection() {
            this.newCollection = new ArrayList<>();
        }

        @Override
        public T nearest(T value) {
            T nearestVal = this.newCollection.get(0);
            double temp = Math.abs(this.newCollection.get(0).doubleValue() - value.doubleValue());
            for(T item : this.newCollection){
                if( Math.abs(item.doubleValue() - value.doubleValue())<temp){
                    temp = Math.abs(item.doubleValue() - value.doubleValue());
                    nearestVal = item;
                }
            }
            return nearestVal;
        }

        @Override
        public int size() {
            return newCollection.size();
        }

        @Override
        public boolean isEmpty() {
            return newCollection.isEmpty();
        }

        @Override
        public boolean contains(Object o) {
            return newCollection.contains(o);
        }


        @Override
        public Iterator<T> iterator() {
            return newCollection.iterator();
        }


        @Override
        public Object[] toArray() {
            return newCollection.toArray();
        }


        @Override
        public <T> T[] toArray( T[] a) {
            return newCollection.toArray(a);
        }

        @Override
        public boolean add(T t) {
            return newCollection.add(t);
        }

        @Override
        public boolean remove(Object o) {
            return newCollection.remove(o);
        }

        @Override
        public boolean containsAll( Collection<?> c) {
            return newCollection.containsAll(c);
        }

        @Override
        public boolean addAll( Collection<? extends T> c) {
            return newCollection.addAll(c);
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            return newCollection.removeAll(c);
        }

        @Override
        public boolean retainAll( Collection<?> c) {
            return newCollection.retainAll(c);
        }

        @Override
        public void clear() {
            newCollection.clear();

        }
    }
}
