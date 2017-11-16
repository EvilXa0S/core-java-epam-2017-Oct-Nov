package com.epam.courses.jf.practice.nzenkova.second;

import com.epam.courses.jf.practice.common.second.ITaskStorage;

public class TaskStorage implements ITaskStorage{
    @Override
    public <T extends ITestableTask> T getSolver(Class<T> taskInterface) {
        String taskInterfaceName = taskInterface.getSimpleName();
        switch(taskInterfaceName){
            case "ITestableTask1": return (T) new TestableTask1();
            case "ITestableTask2": return (T) new TestableTask2();
            case "ITestableTask3": return (T) new TestableTask3();
            case "ITestableTask4": return (T) new TestableTask4();
            case "ITestableTask5": return (T) new TestableTask5();
            case "ITestableTask6": return (T) new TestableTask6();
            case "ITestableTask7": return (T) new TestableTask7();
            default: return null;
        }
    }
}
