package com.epam.courses.jf.practice.NikDevyatyi.second;

import com.epam.courses.jf.practice.common.second.ITaskStorage;

public class TaskStorage implements ITaskStorage {
    public <T extends ITestableTask> T getSolver(Class<T> taskInterface) {
        switch (taskInterface.getSimpleName()){
            case "ITestableTask1":
                return (T) new  TestableTask1();
        }
        switch (taskInterface.getSimpleName()){
            case "ITestableTask2":
                return (T) new  TestableTask2();
        }
        switch (taskInterface.getSimpleName()){
            case "ITestableTask3":
                return (T) new  TestableTask3();
        }
        switch (taskInterface.getSimpleName()){
            case "ITestableTask4":
                return (T) new  TestableTask4();
        }
        switch (taskInterface.getSimpleName()){
            case "ITestableTask5":
                return (T) new  TestableTask5();
        }
        switch (taskInterface.getSimpleName()){
            case "ITestableTask6":
                return (T) new  TestableTask6();
        }switch (taskInterface.getSimpleName()){
            case "ITestableTask7":
                return (T) new  TestableTask7();
        }switch (taskInterface.getSimpleName()){
            case "ITestableTask8":
                return (T) new  TestableTask8();
        }switch (taskInterface.getSimpleName()){
            case "ITestableTask9":
                return (T) new  TestableTask9();
        }switch (taskInterface.getSimpleName()){
            case "ITestableTask10":
                return (T) new  TestableTask10();
        }switch (taskInterface.getSimpleName()){
            case "ITestableTask11":
                return (T) new  TestableTask11();
        }switch (taskInterface.getSimpleName()){
            case "ITestableTask12":
                return (T) new  TestableTask12();
        }switch (taskInterface.getSimpleName()){
            case "ITestableTask13":
                return (T) new  TestableTask13();
        }switch (taskInterface.getSimpleName()){
            case "ITestableTask14":
                return (T) new  TestableTask14();
        }switch (taskInterface.getSimpleName()){
            case "ITestableTask15":
                return (T) new  TestableTask15();
        }switch (taskInterface.getSimpleName()){
            case "ITestableTask16":
                return (T) new  TestableTask16();
        }switch (taskInterface.getSimpleName()){
            case "ITestableTask17":
                return (T) new  TestableTask17();
        }switch (taskInterface.getSimpleName()){
            case "ITestableTask18":
                return (T) new  TestableTask18();
        }switch (taskInterface.getSimpleName()){
            case "ITestableTask19":
                return (T) new  TestableTask19();
        }

        return null;
    }
}
