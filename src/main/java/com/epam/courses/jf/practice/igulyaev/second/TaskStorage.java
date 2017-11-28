package com.epam.courses.jf.practice.igulyaev.second;

import com.epam.courses.jf.practice.common.second.ITaskStorage;
import com.epam.courses.jf.practice.common.second.*;

import java.util.HashMap;
import java.util.Map;

public class TaskStorage implements ITaskStorage {
    private Map<String, ITestableTask> taskMap;

    public TaskStorage() {
        taskMap = new HashMap<>();
        taskMap.put(ITestableTask1.class.getSimpleName(), new TestableTask1());
        taskMap.put(ITestableTask2.class.getSimpleName(), new TestableTask2());
        taskMap.put(ITestableTask3.class.getSimpleName(), new TestableTask3());
        taskMap.put(ITestableTask4.class.getSimpleName(), new TestableTask4());
        taskMap.put(ITestableTask5.class.getSimpleName(), new TestableTask5());
        taskMap.put(ITestableTask6.class.getSimpleName(), new TestableTask6());
        taskMap.put(ITestableTask7.class.getSimpleName(), new TestableTask7());
        taskMap.put(ITestableTask8.class.getSimpleName(), new TestableTask8());
        taskMap.put(ITestableTask9.class.getSimpleName(), new TestableTask9());
        taskMap.put(ITestableTask10.class.getSimpleName(), new TestableTask10());
        taskMap.put(ITestableTask11.class.getSimpleName(), new TestableTask11());
        taskMap.put(ITestableTask12.class.getSimpleName(), new TestableTask12());
        taskMap.put(ITestableTask13.class.getSimpleName(), new TestableTask13());
        taskMap.put(ITestableTask14.class.getSimpleName(), new TestableTask14());
        taskMap.put(ITestableTask15.class.getSimpleName(), new TestableTask15());
        taskMap.put(ITestableTask16.class.getSimpleName(), new TestableTask16());
        taskMap.put(ITestableTask17.class.getSimpleName(), new TestableTask17());
    }

    @Override
    public <T extends ITestableTask> T getSolver(Class<T> taskInterface) {
        return (T) taskMap.get(taskInterface.getSimpleName());
    }
}
