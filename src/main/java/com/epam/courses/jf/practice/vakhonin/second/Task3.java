package com.epam.courses.jf.practice.vakhonin.second;

import com.epam.courses.jf.practice.common.second.ITestableTask3;

import java.util.*;

/**
 * Занести строки, составляющие стихотворения указанного автора, в список.
 * Провести сортировку по возрастанию длин строк.
 */
public class Task3 implements ITestableTask3 {

    /**
     * Формирует упорядоченный список строк из стихотворений указанного автора.
     * @param poems Анализируемое множество стихотворений.
     * @param author Автор, стихотворения которого необходимо выбрать.
     * @return Список, упорядоченных по длине строк, составляющих стихотворения автора.
     */

    @Override
    public List<String> sortPoems(Set<IPoem> poems, String author) {

        List<String> strings = new ArrayList<>();

        for (IPoem poem: poems) {
            if (poem.getAuthor().equals(author)) {
                 strings.addAll(poem.getLines());
            }
        }

        strings.sort(Comparator.comparingInt(String::length));

        return strings;
    }
}
