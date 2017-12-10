package com.epam.courses.jf.practice.hkryzhik.second;

import com.epam.courses.jf.practice.common.second.ITestableTask14;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Task14 implements ITestableTask14 {

    private class NumberCollection<T extends Number> implements INumberCollection{

        private List<T> collection;

        NumberCollection(){
            this.collection = new ArrayList<>();
        }

        @Override
        public T nearest(Number value) {

            T minNumberInCollection = this.collection.get(0);

            for (T element : this.collection){

                if(Math.abs(value.doubleValue() - element.doubleValue())
                        < Math.abs(value.doubleValue() - minNumberInCollection.doubleValue())){

                    minNumberInCollection = element;

                }
            }

            return minNumberInCollection;

        }

        @Override
        public int size() {
            return this.collection.size();
        }

        @Override
        public boolean isEmpty() {
            return this.collection.isEmpty();
        }

        @Override
        public boolean contains(Object o) {
            return this.collection.contains(o);
        }

        @Override
        public Iterator iterator() {
            return this.collection.iterator();
        }

        @Override
        public Object[] toArray() {
            return this.collection.toArray();
        }

        @Override
        public boolean add(Object o) {
            return this.collection.add((T)o);
        }

        @Override
        public boolean remove(Object o) {
            return this.collection.remove(o);
        }

        @Override
        public boolean addAll(Collection c) {

            return this.collection.addAll(c);
        }

        @Override
        public void clear() {
            this.collection.clear();
        }

        @Override
        public boolean retainAll(Collection c) {
            return this.collection.retainAll(c);
        }

        @Override
        public boolean removeAll(Collection c) {
            return this.collection.retainAll(c);
        }

        @Override
        public boolean containsAll(Collection c) {
            return this.collection.containsAll(c);
        }

        @Override
        public Object[] toArray(Object[] a) {
            return this.collection.toArray(a);
        }
    }
    @Override
    public <T extends Number> INumberCollection<T> createCollection(Class<T> required) {
        return new NumberCollection<T>();
    }
}
