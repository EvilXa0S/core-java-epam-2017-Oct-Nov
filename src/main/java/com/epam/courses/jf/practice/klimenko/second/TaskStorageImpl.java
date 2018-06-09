package com.epam.courses.jf.practice.klimenko.second;

import com.epam.courses.jf.practice.common.second.*;

public class TaskStorageImpl implements ITaskStorage {
    @SuppressWarnings("unchecked")
    @Override
    public <T extends ITestableTask> T getSolver(Class<T> taskInterface) {
        if( taskInterface == ITestableTask1.class) {
            return (T) new TestableTask1Impl();
        }
        if( taskInterface == ITestableTask2.class) {
            return (T) new TestableTask2Impl();
        }
        if( taskInterface == ITestableTask3.class) {
            return (T) new TestableTask3Impl();
        }
        if( taskInterface == ITestableTask4.class) {
            return (T) new TestableTask4Impl();
        }
        if( taskInterface == ITestableTask5.class) {
            return (T) new TestableTask5Impl();
        }
        if( taskInterface == ITestableTask6.class) {
            return (T) new TestableTask6Impl();
        }
        if( taskInterface == ITestableTask7.class) {
            return (T) new TestableTask7Impl();
        }
        if( taskInterface == ITestableTask8.class) {
            return (T) new TestableTask8Impl();
        }
        if( taskInterface == ITestableTask9.class) {
            return (T) new TestableTask9Impl();
        }
        if( taskInterface == ITestableTask10.class) {
            return (T) new TestableTask10Impl();
        }
        if( taskInterface == ITestableTask11.class) {
            return (T) new TestableTask11Impl();
        }
        if( taskInterface == ITestableTask12.class) {
            return (T) new TestableTask12Impl();
        }
        if( taskInterface == ITestableTask13.class) {
            return (T) new TestableTask13Impl();
        }
        if( taskInterface == ITestableTask14.class) {
            return (T) new TestableTask14Impl();
        }
        if( taskInterface == ITestableTask15.class) {
            return (T) new TestableTask15Impl();
        }
        if( taskInterface == ITestableTask16.class) {
            return (T) new TestableTask16Impl();
        }
        if( taskInterface == ITestableTask17.class) {
            return (T) new TestableTask17Impl();
        }
        if( taskInterface == ITestableTask18.class) {
            return (T) new TestableTask18Impl();
        }
        if( taskInterface == ITestableTask19.class) {
            return (T) new TestableTask19Impl();
        }
        return  null;
    }
}
