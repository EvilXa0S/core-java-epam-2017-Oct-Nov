package com.epam.courses.jf.practice.sskovalevskiy.second;

import com.epam.courses.jf.practice.common.second.*;

/**
 * Created by asus on 01.11.2017.
 */
public class TaskStorage implements ITaskStorage {
    @Override
    public <T extends ITestableTask> T getSolver(Class<T> taskInterface) {
        if (taskInterface == ITestableTask1.class){
            return (T) new ITestableTask1Impl();
        }
        if (taskInterface == ITestableTask2.class){
            return (T) new ITestableTask2Impl();
        }
        if (taskInterface == ITestableTask3.class){
            return (T) new ITestableTask3Impl();
        }
        if (taskInterface == ITestableTask4.class){
            return (T) new ITestableTask4Impl();
        }
        if (taskInterface == ITestableTask5.class){
            return (T) new ITestableTask5Impl();
        }
        if (taskInterface == ITestableTask6.class){
            return (T) new ITestableTask6Impl();
        }
        if (taskInterface == ITestableTask7.class){
            return (T) new ITestableTask7Impl();
        }
        if (taskInterface == ITestableTask8.class){
            return (T) new ITestableTask8Impl();
        }
        if (taskInterface == ITestableTask9.class){
            return (T) new ITestableTask9Impl();
        }
        if (taskInterface == ITestableTask10.class){
            return (T) new ITestableTask10Impl();
        }
        if (taskInterface == ITestableTask11.class){
            return (T) new ITestableTask11Impl();
        }
        if (taskInterface == ITestableTask12.class){
            return (T) new ITestableTask12Impl();
        }
        if (taskInterface == ITestableTask13.class){
            return (T) new ITestableTask13Impl();
        }
        if (taskInterface == ITestableTask14.class){
            return (T) new ITestableTask14Impl();
        }
        if (taskInterface == ITestableTask15.class){
            return (T) new ITestableTask15Impl();
        }
        if (taskInterface == ITestableTask16.class){
            return (T) new ITestableTask16Impl();
        }
        if (taskInterface == ITestableTask17.class){
            return (T) new ITestableTask17Impl();
        }
        if (taskInterface == ITestableTask18.class){
            return (T) new ITestableTask18Impl();
        }
        if (taskInterface == ITestableTask19.class){
            return (T) new ITestableTask19Impl();
        }
        return null;
    }
}
