package com.epam.courses.jf.practice.bborzdov.second;

import com.epam.courses.jf.practice.common.second.ITestableTask3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Created by bogdan on 15.11.17.
 */

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
        for (IPoem poem : poems) {
            if(poem.getAuthor().equals(author)){
                strings.addAll(poem.getLines());
            }
        }
        Collections.sort(strings);
        return strings;
    }
}
