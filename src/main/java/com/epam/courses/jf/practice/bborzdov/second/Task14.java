package com.epam.courses.jf.practice.bborzdov.second;

import com.epam.courses.jf.practice.common.second.ITestableTask14;

/**
 * Created by bogdan on 20.11.17.
 */
public class Task14 implements ITestableTask14{
    @Override
    public <T extends Number> INumberCollection<T> createCollection(Class<T> required) {
        return new NumberCollection();
    }

}
