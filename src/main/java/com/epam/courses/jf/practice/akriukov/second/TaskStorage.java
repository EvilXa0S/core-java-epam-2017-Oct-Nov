package com.epam.courses.jf.practice.akriukov.second;

import com.epam.courses.jf.practice.common.second.ITaskStorage;

import com.epam.courses.jf.practice.common.second.*;

public class TaskStorage implements ITaskStorage {
    @Override
    public <T extends ITestableTask> T getSolver(Class<T> taskInterface) {
        if (taskInterface == ITestableTask1.class){
            return (T) new Task1();
        }
        if (taskInterface == ITestableTask2.class){
            return (T) new Task2();
        }
        if (taskInterface == ITestableTask3.class){
            return (T) new Task3();
        }
        if (taskInterface == ITestableTask4.class){
            return (T) new Task4();
        }
        if (taskInterface == ITestableTask5.class){
            return (T) new Task5();
        }
        if (taskInterface == ITestableTask6.class){
            return (T) new Task6();
        }
        if (taskInterface == ITestableTask7.class){
            return (T) new Task7();
        }
        if (taskInterface == ITestableTask8.class){
            return (T) new Task8();
        }
        if (taskInterface == ITestableTask9.class){
            return (T) new Task9();
        }
        if (taskInterface == ITestableTask10.class){
            return (T) new Task10();
        }
        if (taskInterface == ITestableTask11.class){
            return (T) new Task11();
        }
        if (taskInterface == ITestableTask12.class){
            return (T) new Task12();
        }
        if (taskInterface == ITestableTask13.class){
            return (T) new Task13();
        }
        if (taskInterface == ITestableTask14.class){
            return (T) new Task14();
        }
        /*if (taskInterface == ITestableTask15.class){
            return (T) new Task15();
        }
        if (taskInterface == ITestableTask16.class){
            return (T) new Task16();
        }
        if (taskInterface == ITestableTask17.class){
            return (T) new Task17();
        }
        if (taskInterface == ITestableTask18.class){
            return (T) new Task18();
        }
        if (taskInterface == ITestableTask19.class){
            return (T) new Task19();
        }*/
        return null;
    }
}