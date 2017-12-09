package com.epam.courses.jf.practice.igulyaev.second;

import com.epam.courses.jf.practice.common.second.ITestableTask3;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TestableTask3 implements ITestableTask3 {
    @Override
    public List<String> sortPoems(Set<IPoem> poems, String author) {
        return poems.stream().filter(p -> p.getAuthor().equals(author))
                .flatMap(e ->e.getLines().stream())
                .sorted( (s1,s2) ->
                        s1.length() < s2.length() ? -1 : (s1.length() > s2.length() ? 1 : s1.compareTo(s2)))
                .collect(Collectors.toList());
    }

    public static class Poem implements ITestableTask3.IPoem{
        List<String> lines;
        String author;

        public Poem(List<String> lines, String author) {
            this.lines = lines;
            this.author = author;
        }

        @Override
        public List<String> getLines() {
            return lines;
        }

        @Override
        public String getAuthor() {
            return author;
        }
    }
}
