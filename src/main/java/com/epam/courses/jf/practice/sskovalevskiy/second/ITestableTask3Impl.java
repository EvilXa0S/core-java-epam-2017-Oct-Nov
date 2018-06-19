package com.epam.courses.jf.practice.sskovalevskiy.second;

import com.epam.courses.jf.practice.common.second.ITestableTask3;

import java.util.*;

/**
 * Created by asus on 23.10.2017.
 *
 * Занести строки, составляющие стихотворения указанного автора, в список.
 * Провести сортировку по возрастанию длин строк.
 */
public class ITestableTask3Impl implements ITestableTask3 {

    /**
     * Формирует упорядоченный список строк из стихотворений указанного автора.
     * @param poems Анализируемое множество стихотворений.
     * @param author Автор, стихотворения которого необходимо выбрать.
     * @return Список, упорядоченных по длине строк, составляющих стихотворения автора.
     */
    @Override
    public List<String> sortPoems(Set<IPoem> poems, String author) {

        List<String> sortedByAuthorPoemsLines = new ArrayList<>();

        for (IPoem poem : poems) {
            if (poem.getAuthor().equals(author)) {
                sortedByAuthorPoemsLines.addAll(poem.getLines());
            }
        }

        Collections.sort(sortedByAuthorPoemsLines, (o1, o2) -> o1.length() - o2.length());

        return sortedByAuthorPoemsLines;
    }
}
