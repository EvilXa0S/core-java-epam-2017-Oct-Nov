package com.epam.courses.jf.practice.common.second;

import java.util.List;
import java.util.Set;

/**
 * Интерфейс для юнит-тестирования задания №3.
 * Занести строки, составляющие стихотворения указанного автора, в список.
 * Провести сортировку по возрастанию длин строк.
 */
public interface ITestableTask3 extends ITaskStorage.ITestableTask {

    /**
     * Формирует упорядоченный список строк из стихотворений указанного автора.
     * @param poems Анализируемое множество стихотворений.
     * @param author Автор, стихотворения которого необходимо выбрать.
     * @return Список, упорядоченных по длине строк, составляющих стихотворения автора.
     */
    List<String> sortPoems(Set<IPoem> poems, String author);


    /**
     * Стихотворение.
     */
    interface IPoem {

        /**
         * @return Список строк, составляющих стихотворение.
         */
        List<String> getLines();

        /**
         * @return Автор стихотворения.
         */
        String getAuthor();
    }
}
