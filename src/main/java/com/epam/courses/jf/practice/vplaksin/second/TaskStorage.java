package com.epam.courses.jf.practice.vplaksin.second;

import com.epam.courses.jf.practice.common.second.*;

public class TaskStorage implements ITaskStorage {

    @Override
    public <T extends ITestableTask> T getSolver(Class<T> taskInterface) {

        if (taskInterface == ITestableTask1.class) {
            return (T) new Task1();
        }
        if (taskInterface == ITestableTask2.class) {
            return (T) new Task2();
        }
        if (taskInterface == ITestableTask3.class) {
            return (T) new Task3();
        }

        return null;
    }
}
