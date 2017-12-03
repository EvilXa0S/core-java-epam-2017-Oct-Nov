package com.epam.courses.jf.practice.desided.second;

import com.epam.courses.jf.practice.common.second.ITestableTask14;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Task14 implements ITestableTask14{

    @Override
    public <T extends Number> INumberCollection<T> createCollection(Class<T> required) {
        return new NumberCollection<>();
    }

    class NumberCollection<T extends Number> extends ArrayList<T> implements ITestableTask14.INumberCollection<T> {

        @Override
        public T nearest(T value) {

            return stream()
                    .min(Comparator.comparingDouble(o -> Math.abs(o.doubleValue() - value.doubleValue())))
                    .get();
        }
    }
}
