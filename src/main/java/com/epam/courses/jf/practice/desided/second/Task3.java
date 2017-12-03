package com.epam.courses.jf.practice.desided.second;

import com.epam.courses.jf.practice.common.second.ITestableTask3;

import java.util.*;
import java.util.stream.Collectors;

public class Task3 implements ITestableTask3 {
    @Override
    public List<String> sortPoems(Set<IPoem> poems, String author) {

        List<String> list = new ArrayList<>();

        for (IPoem poem : poems){
            if (author.equals(poem.getAuthor())){
                list.addAll(poem.getLines());
            }
        }
        list.sort(Comparator.comparingInt(String::length));
        return list;
    }
}
