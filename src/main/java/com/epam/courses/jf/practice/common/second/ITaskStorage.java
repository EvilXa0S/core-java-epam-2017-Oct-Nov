package com.epam.courses.jf.practice.common.second;

/**
 * Хранилище для объектов, осуществляющих решение задач.
 */
public interface ITaskStorage {

    /**
     * @param taskInterface Интерфейс требуемой задачи.
     * @return Сущность, решающую указанную задачу.
     */
    <T extends ITestableTask> T getSolver(Class<T> taskInterface);

    /**
     * Интерфейс-маркер для задач, поддерживающих юнит-тестирование.
     */
    interface ITestableTask {}
}
