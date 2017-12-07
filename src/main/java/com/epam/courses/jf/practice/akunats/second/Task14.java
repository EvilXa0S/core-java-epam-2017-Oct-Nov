package com.epam.courses.jf.practice.akunats.second;

import com.epam.courses.jf.practice.common.second.ITestableTask14;

import java.util.*;

public class Task14 implements ITestableTask14 {

    public <T extends Number> INumberCollection<T> createCollection(Class<T> required) {
        return new MyNumberCollection<T>();
    }

    private class MyNumberCollection<T extends Number> extends AbstractCollection<T> implements INumberCollection<T> {
        List<T> repository = new ArrayList<>();

        @Override
        public T nearest(Number value) {
            T resultNumber = repository.get(0);
            for (T t : repository) {
                if (t.equals(value)) {
                    break;
                }
                if (resultNumber.equals(0)
                        || Math.abs(value.doubleValue() - t.doubleValue())
                        < Math.abs(value.doubleValue() - resultNumber.doubleValue())) {
                    resultNumber = t;
                }
            }
            return resultNumber;
        }

        @Override
        public int size() {
            int size = 0;
            for (T ignored : repository) {
                size++;
            }
            return size;
        }

        @Override
        public Iterator<T> iterator() {
            return repository.listIterator();
        }

        @Override
        public boolean add(T elem) {
            return repository.add(elem);
        }

    }
}
