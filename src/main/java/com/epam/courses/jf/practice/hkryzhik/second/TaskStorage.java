package com.epam.courses.jf.practice.hkryzhik.second;

import com.epam.courses.jf.practice.common.second.ITaskStorage;

public class TaskStorage implements ITaskStorage{
    @Override
    public <T extends ITestableTask> T getSolver(Class<T> taskInterface) {

        switch(Integer.parseInt(taskInterface.getSimpleName().replaceAll("([a-zA-Z])", ""))){
            case 1: return (T) new Task1();
            case 2: return (T) new Task2();
            case 3: return (T) new Task3();
            case 4: return (T) new Task4();
            case 5: return (T) new Task5();
            case 6: return (T) new Task6();
            case 7: return (T) new Task7();
            case 8: return (T) new Task8();
            case 9: return (T) new Task9();
            case 10: return (T) new Task10();
            case 11: return (T) new Task11();
            case 12: return (T) new Task12();
            case 13: return (T) new Task13();
            case 14: return (T) new Task14();
            case 15: return (T) new Task15();
            case 16: return (T) new Task16();
            case 17: return (T) new Task17();
            case 18: return (T) new Task18();
            case 19: return (T) new Task19();
            default: return null;
        }
    }
}
