package com.epam.courses.jf.practice.asunyaev.second;

import com.epam.courses.jf.practice.common.second.*;


public class TaskStorage implements ITaskStorage {

    public <T extends ITestableTask> T getSolver(Class<T> taskInterface) {

        if (taskInterface.equals(ITestableTask1.class)) {
            return (T) new Task1();
        }
        if (taskInterface.equals(ITestableTask2.class)) {
            return (T) new Task2();
        }
        if (taskInterface.equals(ITestableTask3.class)) {
            return (T) new Task3();
        }
        if (taskInterface.equals(ITestableTask4.class)) {
            return (T) new Task4();
        }
        if (taskInterface.equals(ITestableTask5.class)) {
            return (T) new Task5();
        }
        if (taskInterface.equals(ITestableTask6.class)) {
            return (T) new Task6();
        }
        if (taskInterface.equals(ITestableTask7.class)) {
            return (T) new Task7();
        }
        if (taskInterface.equals(ITestableTask8.class)) {
            return (T) new Task8();
        }
        if (taskInterface.equals(ITestableTask9.class)) {
            return (T) new Task9();
        }
        if (taskInterface.equals(ITestableTask10.class)) {
            return (T) new Task10();
        }
        if (taskInterface.equals(ITestableTask11.class)) {
            return (T) new Task11();
        }
        if (taskInterface.equals(ITestableTask12.class)) {
            return (T) new Task12();
        }
        if (taskInterface.equals(ITestableTask13.class)) {
            return (T) new Task13();
        }
        if (taskInterface.equals(ITestableTask14.class)) {
            return (T) new Task14();
        }
        if (taskInterface.equals(ITestableTask15.class)) {
            return (T) new Task15();
        }
        if (taskInterface.equals(ITestableTask16.class)) {
            return (T) new Task16();
        }
        if (taskInterface.equals(ITestableTask17.class)) {
            return (T) new Task17();
        }
        if (taskInterface.equals(ITestableTask18.class)) {
            return (T) new Task18();
        }
        if (taskInterface.equals(ITestableTask19.class)) {
            return (T) new Task19();
        }
        return null;
    }
}
