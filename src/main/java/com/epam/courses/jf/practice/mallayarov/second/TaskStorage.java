package com.epam.courses.jf.practice.mallayarov.second;

import com.epam.courses.jf.practice.common.second.*;

public class TaskStorage implements ITaskStorage {
    /**
     * @param taskInterface Интерфейс требуемой задачи.
     * @return Сущность, решающую указанную задачу.
     */

    @Override
    public <T extends ITestableTask> T getSolver(Class<T> taskInterface) {
        if (taskInterface == ITestableTask1.class){
            return (T) new Task1Impl();
        }
        if (taskInterface == ITestableTask2.class){
            return (T) new Task2Impl();
        }
        if (taskInterface == ITestableTask3.class){
            return (T) new Task3Impl();
        }
        if (taskInterface == ITestableTask4.class){
            return (T) new Task4Impl();
        }
        if (taskInterface == ITestableTask5.class){
            return (T) new Task5Impl();
        }
        if (taskInterface == ITestableTask6.class){
            return (T) new Task6Impl();
        }
        if (taskInterface == ITestableTask7.class){
            return (T) new Task7Impl();
        }
        if (taskInterface == ITestableTask8.class){
            return (T) new Task8Impl();
        }
        if (taskInterface == ITestableTask9.class){
            return (T) new Task9Impl();
        }
        if (taskInterface == ITestableTask10.class){
            return (T) new Task10Impl();
        }
        if (taskInterface == ITestableTask11.class){
            return (T) new Task11Impl();
        }
        if (taskInterface == ITestableTask12.class){
            return (T) new Task12Impl();
        }
        if (taskInterface == ITestableTask13.class){
            return (T) new Task13Impl();
        }
        if (taskInterface == ITestableTask14.class) {
            return (T) new Task14Impl();
        }

        if (taskInterface == ITestableTask15.class) {
            return (T) new Task15Impl();
        }

        if (taskInterface == ITestableTask16.class) {
            return (T) new Task16Impl();
        }

        if (taskInterface == ITestableTask17.class) {
            return (T) new Task17Impl();
        }

        if (taskInterface == ITestableTask18.class) {
            return (T) new Task18Impl();
        }

        if (taskInterface == ITestableTask19.class) {
            return (T) new Task19Impl();
        }

        return null;
    }
}
