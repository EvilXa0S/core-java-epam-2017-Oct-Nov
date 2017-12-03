package com.epam.courses.jf.practice.nzenkova.second;

import com.epam.courses.jf.practice.common.second.ITaskStorage;

/**
 * Task2.
 */
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
            case "ITestableTask8": return (T) new TestableTask8();
            case "ITestableTask9": return (T) new TestableTask9();
            case "ITestableTask10": return (T) new TestableTask10();
            case "ITestableTask11": return (T) new TestableTask11();
            case "ITestableTask12": return (T) new TestableTask12();
            case "ITestableTask13": return (T) new TestableTask13();
            case "ITestableTask14": return (T) new TestableTask14();
            case "ITestableTask15": return (T) new TestableTask15();
            case "ITestableTask16": return (T) new TestableTask16();
            case "ITestableTask17": return (T) new TestableTask17();
            case "ITestableTask18": return (T) new TestableTask18();
            case "ITestableTask19": return (T) new TestableTask19();
            default: return null;
        }
    }
}

