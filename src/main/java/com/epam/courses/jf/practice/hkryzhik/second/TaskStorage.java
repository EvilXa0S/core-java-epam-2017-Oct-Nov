package com.epam.courses.jf.practice.hkryzhik.second;

import com.epam.courses.jf.practice.common.second.ITaskStorage;

public class TaskStorage implements ITaskStorage{
    @Override
    public <T extends ITestableTask> T getSolver(Class<T> taskInterface) {

        switch(Integer.parseInt(taskInterface.getSimpleName().replaceAll("([a-zA-Z])", ""))){
            case 1: return (T) new Task1();
            case 2: return (T) new Task2();
            case 3: return (T) new Task3();
//            case "Task4": return (T) new Task4();
//            case "Task5": return (T) new Task5();
//            case "Task6": return (T) new Task6();
//            case "Task7": return (T) new Task7();
//            case "Task8": return (T) new Task8();
//            case "Task9": return (T) new Task9();
//            case "Task10": return (T) new Task10();
//            case "Task11": return (T) new Task11();
//            case "Task12": return (T) new Task12();
//            case "Task13": return (T) new Task13();
//            case "Task14": return (T) new Task14();
//            case "Task15": return (T) new Task15();
//            case "Task16": return (T) new Task16();
//            case "Task17": return (T) new Task17();
//            case "Task18": return (T) new Task18();
//            case "Task19": return (T) new Task19();
            default: return null;
        }
    }
}
