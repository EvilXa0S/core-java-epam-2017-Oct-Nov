package com.epam.courses.jf.practice.nzenkova.second;

import com.epam.courses.jf.practice.common.second.ITestableTask3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

/**
 * The strings, which make up the poems, write to the list.
 * Sort strings in ascending order of their length.
 */

public class TestableTask3 implements ITestableTask3 {
    /**
     * Forms the ordered list of the lines from the poems of given author.
     * @param poems The list of the assayed  poems.
     * @param author The author whose poems will be chosen.
     * @return The ordered list of the lines, which make up the poems of given author.
     */
    @Override
    public List<String> sortPoems(Set<IPoem> poems, String author) {
        List<String> list = new ArrayList<>();
        for (IPoem poem : poems) {
            if (poem.getAuthor().equals(author)) {
                list.addAll(poem.getLines());
            }
        }
        list.sort(Comparator.comparingInt(String::length));

        return list;
    }
}
