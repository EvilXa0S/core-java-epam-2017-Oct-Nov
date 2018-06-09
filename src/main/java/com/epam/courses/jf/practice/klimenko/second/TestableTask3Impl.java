package com.epam.courses.jf.practice.klimenko.second;

import com.epam.courses.jf.practice.common.second.ITestableTask3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TestableTask3Impl implements ITestableTask3 {
    @Override
    public List<String> sortPoems(Set<IPoem> poems, String author) {
        List<String> list = new ArrayList<>();
        for (IPoem p : poems
                .stream()
                .filter((IPoem p) -> p.getAuthor().equals(author))
                .collect(Collectors.toList())) {
            list.addAll(p.getLines());
        }
        list.sort(Comparator.comparing(String::length));
        return list;
    }
}
