package com.epam.courses.jf.practice.NikDevyatyi.second;

import com.epam.courses.jf.practice.common.second.ITestableTask3;

import java.util.List;

public class Poem implements ITestableTask3.IPoem {
    private List<String> line;
    private String author;
    public Poem(List<String> line, String author){
        this.line = line;
        this.author = author;
    }
    @Override
    public List<String> getLines() {
        return this.line;
    }

    @Override
    public String getAuthor() {
        return this.author;
    }
}
