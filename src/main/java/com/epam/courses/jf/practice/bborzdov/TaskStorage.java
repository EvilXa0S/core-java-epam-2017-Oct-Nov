package com.epam.courses.jf.practice.bborzdov;

import com.epam.courses.jf.practice.common.second.ITaskStorage;

/**
 * Created by bogdan on 14.11.17.
 */
public class TaskStorage implements ITaskStorage {
    @Override
    public <T extends ITestableTask> T getSolver(Class<T> taskInterface) {

        if(taskInterface.isInstance(new Task1())){
            return taskInterface.cast(new Task1());
        }
        if(taskInterface.isInstance(new Task2())){
            return taskInterface.cast(new Task2());
        }
        if(taskInterface.isInstance(new Task3())){
            return taskInterface.cast(new Task3());
        }
        if(taskInterface.isInstance(new Task4())){
            return taskInterface.cast(new Task4());
        }
        if(taskInterface.isInstance(new Task5())){
            return taskInterface.cast(new Task5());
        }
        if(taskInterface.isInstance(new Task6())){
            return taskInterface.cast(new Task6());
        }
        if(taskInterface.isInstance(new Task7())){
            return taskInterface.cast(new Task7());
        }
        if(taskInterface.isInstance(new Task8())){
            return taskInterface.cast(new Task8());
        }
        if(taskInterface.isInstance(new Task9())){
            return taskInterface.cast(new Task9());
        }
        if(taskInterface.isInstance(new Task10())){
            return taskInterface.cast(new Task10());
        }
        if(taskInterface.isInstance(new Task11())){
            return taskInterface.cast(new Task11());
        }
        if(taskInterface.isInstance(new Task12())){
            return taskInterface.cast(new Task12());
        }
        if (taskInterface.isInstance(new Graph(0))){
            return taskInterface.cast(new Graph(0));
        }
        if(taskInterface.isInstance(new Task14())){
            return taskInterface.cast(new Task14());
        }
        if(taskInterface.isInstance(new Task15())){
            return taskInterface.cast(new Task15());
        }
        if(taskInterface.isInstance(new Task16())){
            return taskInterface.cast(new Task16());
        }
        return null;
    }
}
