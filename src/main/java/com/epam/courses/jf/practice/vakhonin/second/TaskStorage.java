package com.epam.courses.jf.practice.vakhonin.second;

/**
 * Created by igorvahonin on 13.11.17.
 */

import com.epam.courses.jf.practice.common.second.ITaskStorage;
import com.epam.courses.jf.practice.common.second.*;

public class TaskStorage implements ITaskStorage {

    @Override
    public <T extends ITestableTask> T getSolver(Class<T> taskInterface) {

        String interfaceName = taskInterface.getSimpleName();

        switch (interfaceName) {
            case "ITestableTask1":
                return (T) new Task1();
            case "ITestableTask2":
                return (T) new Task2();
            case "ITestableTask3":
                return (T) new Task3();
            case "ITestableTask4":
                return (T) new Task4();
            case "ITestableTask5":
                return (T) new Task5();
            case "ITestableTask6":
                return (T) new Task6();
            case "ITestableTask7":
                return (T) new Task7();
            case "ITestableTask8":
                return (T) new Task8();
            case "ITestableTask9":
                return (T) new Task9();
            case "ITestableTask10":
                return (T) new Task10();
            case "ITestableTask11":
                return (T) new Task11();
            case "ITestableTask12":
                return (T) new Task12();
            case "ITestableTask13":
                return (T) new Task13();
            case "ITestableTask14":
                return (T) new Task14();
            case "ITestableTask15":
                return (T) new Task15();
            case "ITestableTask16":
                return (T) new Task16();
            case "ITestableTask17":
                return (T) new Task17();
            case "ITestableTask18":
                return (T) new Task18();
            case "ITestableTask19":
                return (T) new Task19();
            default:
                return null;
        }

    }

}
