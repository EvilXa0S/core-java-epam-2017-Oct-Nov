package com.epam.courses.jf.practice.vkostin.second;

import com.epam.courses.jf.practice.common.second.ITestableTask3;

import java.util.*;

public class Task3 implements ITestableTask3 {

    @Override
    public List<String> sortPoems(Set<IPoem> poems, String author) {
        List<String> authorPoems = new ArrayList<>();
        for (IPoem poem : poems){
            if (poem.getAuthor().equals(author)){
                authorPoems.addAll(poem.getLines());
            }
        }
        authorPoems.sort(Comparator.comparingInt(String::length));
        return authorPoems;
    }

    static class Poem implements IPoem {

        private String author;
        private List<String> lines;

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
