package com.epam.courses.jf.practice.SigarevVL.second;

import com.epam.courses.jf.practice.common.second.ITestableTask3;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Enter the line, composing the poem of the specified author,
 * in the list. Sorts by ascending row length.
 */
public class Task3 implements ITestableTask3 {

    /**
     * Forms an ordered list of lines from the poems of the author.
     *
     * @param poems  Analyzed set of poems.
     * @param author The author whose poems need to be chosen.
     * @return List, ordered by the length of the lines
     * that make up the author's poem.
     */
    @Override
    public List<String> sortPoems(Set<IPoem> poems, String author) {
        List<String> list = new ArrayList<>();

        for (IPoem poem : poems) {
            if (poem.getAuthor().equals(author)) {
                for (String string : poem.getLines()) {
                    list.add(string);
                }
            }
        }
        Collections.sort(list, (a, b) -> a.length() - b.length());

        return list;
    }

}
