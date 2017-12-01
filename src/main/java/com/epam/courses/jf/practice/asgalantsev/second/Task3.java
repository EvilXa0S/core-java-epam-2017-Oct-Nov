package com.epam.courses.jf.practice.asgalantsev.second;

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
        List<String> list = Collections.emptyList();
        List<String> lines = new ArrayList<>();
        for (IPoem poem : poems) {
            if (poem.getAuthor().equals(author)) {
                lines.addAll(poem.getLines());
            }
        }
        if (lines.size() != 0)
            list = lines;

        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                if (s1.length() < s2.length())
                    return -1;
                else if (s1.length() > s2.length())
                    return 1;
                else
                    return 0;
            }
        });

        return list;
    }
}
