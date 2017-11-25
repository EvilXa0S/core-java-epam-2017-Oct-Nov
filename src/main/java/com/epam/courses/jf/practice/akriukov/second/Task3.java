package com.epam.courses.jf.practice.akriukov.second;

import com.epam.courses.jf.practice.common.second.ITestableTask3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Task3 implements ITestableTask3{

    @Override
    public List<String> sortPoems(Set<IPoem> poems, String author) {

        List<String> sortedPoemsLines = new ArrayList<>();
        for (IPoem poem : poems) {
            if (poem.getAuthor().equals(author)) {
                sortedPoemsLines.addAll(poem.getLines());
            }
        }
        Collections.sort(sortedPoemsLines, (o1, o2) -> o1.length() - o2.length());

        return sortedPoemsLines;
    }
}
