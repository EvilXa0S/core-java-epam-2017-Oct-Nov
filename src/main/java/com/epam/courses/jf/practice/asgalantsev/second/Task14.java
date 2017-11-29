package com.epam.courses.jf.practice.asgalantsev.second;

import com.epam.courses.jf.practice.common.second.ITestableTask14;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Task14 implements ITestableTask14{
    @Override
    public <T extends Number> INumberCollection<T> createCollection(Class<T> required) {
        return new MyCollection<T>(required);
    }
    
    private class MyCollection<T extends Number> implements INumberCollection<T>{

        private List<T> list;
        public MyCollection(Class<T> required) {
            list = new ArrayList<>();
        }

        @Override
        public T nearest(T value) {
            double diff = Math.abs(list.get(0).doubleValue() - value.doubleValue());
            int idx = 0;
            if(list.size() == 1)
                return list.get(0);

            for(int i=1; i < list.size(); i++) {
                double difference = Math.abs(list.get(i).doubleValue() - value.doubleValue());
                if (difference < diff) {
                    diff = difference;
                    idx = i;
                }
            }

            return list.get(idx);
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
            return list.contains((T)o);
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
            return list.retainAll(c);
        }

        @Override
        public void clear() {

        }
    }
}
