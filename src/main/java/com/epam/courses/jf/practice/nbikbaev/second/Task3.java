package com.epam.courses.jf.practice.nbikbaev.second;

import com.epam.courses.jf.practice.common.second.ITestableTask3;

import java.util.*;

public class Task3 implements ITestableTask3 {
    @Override
    public List<String> sortPoems(Set<IPoem> poems, String author) {
        List<String> strings = new ArrayList<>();
        for (IPoem poem : poems) {
            if (poem.getAuthor().equals(author)) {
                strings.addAll(poem.getLines());
            }
        }
        strings.sort(Comparator.comparingInt(String::length));
        return strings;
    }
}
