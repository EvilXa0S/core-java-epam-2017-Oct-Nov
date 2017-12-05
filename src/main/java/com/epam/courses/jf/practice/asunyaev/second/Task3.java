package com.epam.courses.jf.practice.asunyaev.second;

import com.epam.courses.jf.practice.common.second.ITestableTask3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class Task3 implements ITestableTask3 {
    public List<String> sortPoems(Set<IPoem> poems, String author) {
        List<String> lines = new ArrayList<>();
        for (IPoem poem : poems) {
           if (author.equals(poem.getAuthor())) {
               lines.addAll(poem.getLines());
           }
       }
       lines.sort(new Comparator<String>() {
           @Override
           public int compare(String o1, String o2) {
               return (Integer.compare(o1.length(), o2.length()));
           }
       });
       return lines;
    }

}
